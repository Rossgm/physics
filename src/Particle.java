import java.awt.Color;
import java.util.ArrayList;


public class Particle {
	
	private double x;
	private double y;	
	private double vx;
	private double vy;
	private double radius = 5.0;
	private double mass = 1.0;
	public static double stepSize = 0.05;
	public Color color = new Color(1, 1, 0);

	public Particle(double xInit, double yInit){
		setX(xInit);
		setY(yInit);
		vx = Math.random()*2;
		vy = Math.random()*2;
	}
	
	public Particle(double xInit, double yInit, double vxInit, double vyInit){
		x = xInit;
		y = yInit;
		vx = vxInit;
		vy = vyInit;
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * @return the vx
	 */
	public double getVX() {
		return vx;
	}

	/**
	 * @param vx the vx to set
	 */
	public void setVX(double vx) {
		this.vx = vx;
	}

	/**
	 * @return the vy
	 */
	public double getVY() {
		return vy;
	}

	/**
	 * @param vy the vy to set
	 */
	public void setVY(double vy) {
		this.vy = vy;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	/**
	 * @param mass the mass to set
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * @return the mass
	 */
	public double getMass() {
		return mass;
	}

	public void translate(){
		vy -= .05*Particle.stepSize;
		x += vx*Particle.stepSize;
		y += vy*Particle.stepSize;
		
		checkBoundries();
		
	}
	
	// checks if the particle is outside the window, and handles bouncy collisions
	private void checkBoundries(){
		if(x >= 500){
			vx *= -0.8;
			x = 500;
		}
		if(x <= 0){
			vx *= -0.8;
			x = 0;
		}
		if(y >= 500){
			vy *= -0.5;
			y = 500;
		}
		if(y <= 0){
			vy *= -0.5;
			y = 0;
			if(Math.abs(vy) < .05)
				vx *= 0.95;
		}
	}
	
	public Particle clone(){
		Particle p = new Particle(x, y, vx, vy);
		p.setRadius(radius);
		return p;
	}
	
	public static void compareParticles(ArrayList<Particle> particles){
		int size = particles.size();
		for(int a = 0; a < size-1; a++)
			for(int b = a+1; b < size; b++)
				Particle.checkCollisions(particles.get(a), particles.get(b));
	}
	
	private static void checkCollisions(Particle a, Particle b){
		
		Particle a2 = a.clone();
		Particle b2 = b.clone();
		a2.translate();
		b2.translate();
		
		double x1 = a2.getX();
		double y1 = a2.getY();
		double vx1 = a2.getVX();
		double vy1 = a2.getVY();
		double r1 = a2.getRadius();
		double x2 = b2.getX();
		double y2 = b2.getY();
		double vx2 = b2.getVX();
		double vy2 = b2.getVY();
		double r2 = b2.getRadius();

		double dx = x2-x1;
		double dy = y2-y1;
		double s1 = Math.sqrt(vx1*vx1+vy1*vy1); //initial speed of particle 1
		double s2 = Math.sqrt(vx2*vx2+vy2*vy2); //initial speed of particle 2
		double theta1 = Math.atan2(vy1, vx1); //initial direction of particle 1
		double theta2 = Math.atan2(vy2, vx2); //initial direction of particle 2

		/*System.out.printf("s1:%f theta1:%f\n", s1, theta1);
		System.out.printf("s2:%f theta2:%f\n\n", s2, theta2);*/
		
		//check if the particles are intersecting
		if( Math.pow(dx, 2) + Math.pow(dy, 2) <= Math.pow(r1+r2, 2) ){
			//System.out.println("Colide " + Math.atan2(dy, dx));
			double theta3 = Math.acos(dx/(Math.sqrt(dy*dy+dx*dx))); //the angle of the line of collision
			double vt1 = s1*Math.cos(theta1-theta3); //particle a's velocity tangent to the line of collision (conserved)
			double vp1 = s1*Math.sin(theta1-theta3); //particle a's velocity perpendicular to the line of collision
			double vt2 = s2*Math.cos(theta2-theta3); //particle b's velocity tangent to the line of collision (conserved)
			double vp2 = s2*Math.sin(theta2-theta3); //particle b's velocity perpendicular to the line of collision
			double vt1t = (vt1*(a2.getMass()-b2.getMass())+(b2.getMass()+b2.getMass())*vt2)/(a2.getMass()+b2.getMass());
			double vt2t = ((a2.getMass()+a2.getMass())*vt1+(b2.getMass()-a2.getMass())*vt2)/(a2.getMass()+b2.getMass());
			
		    a.setVX(Math.cos(theta3)*vt1t+Math.cos(theta3+Math.PI/2)*vp1);
		    a.setVY(Math.sin(theta3)*vt1t+Math.sin(theta3+Math.PI/2)*vp1);
		    b.setVX(Math.cos(theta3)*vt2t+Math.cos(theta3+Math.PI/2)*vp2);
		    b.setVY(Math.sin(theta3)*vt2t+Math.sin(theta3+Math.PI/2)*vp2);
		    
		    a.color = Color.CYAN;
		    b.color = Color.ORANGE;
		    /*
			System.out.printf("dx:%f dy:%f dr:%f\n", dx, dy, 1.0);
			System.out.printf("x:%f y:%f vx:%f vy:%f\n", x1, y1, vx1, vy1);
			System.out.printf("x:%f y:%f vx:%f vy:%f\n", x2, y2, vx2, vy2);
			System.out.printf("t1:%f p1:%f t2:%f p2:%f\n", vt1, vp1, vt2, vp2);
			System.out.printf("t1t:%f t2t:%f\n", vt1t, vt2t);
			System.out.printf("theta1:%f theta2:%f theta3:%f\n", theta1, theta2, theta3);
			System.out.printf("vx1:%f vy1:%f vx2:%f vy2:%f\n\n", a.getVX(), a.getVY(), b.getVX(), b.getVY());*/
		}
	}
	
	public void setVelocity(double speed, double direction){
		vx = speed*Math.cos(direction);
		vy = speed*Math.sin(direction);
	}
	
	public static void quake(ArrayList<Particle> parts){
		int size = parts.size();
		for(int a = 0; a < size; a++)
			parts.get(a).setVelocity(Math.random()*10, Math.random()*Math.PI);
		//for(Particle p: particles)
		//	p.setVelocity(Math.random()*2, Math.random()*Math.PI);
	}
	
}
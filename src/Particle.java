import java.util.ArrayList;


public class Particle {
	
	private double x;
	private double y;	
	private double vx;
	private double vy;
	private double radius = 5.0;
	public static double stepSize = 0.1;

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
				vx *= 0.9;
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

		double ax = a.getX();
		double ay = a.getY();
		double avx = a.getVX();
		double avy = a.getVY();
		double ar = a.getRadius();
		double bx = b.getX();
		double by = b.getY();
		double bvx = b.getVX();
		double bvy = b.getVY();
		double br = b.getRadius();
		
		//System.out.printf("dx:%f dy:%f dr:%f\n", ax-bx, ay-by, ar+br);
		if( Math.pow(ax-bx, 2) + Math.pow(ay-by, 2) <= Math.pow(ar+br, 2) ){
			//a.setX(a.getX());
			System.out.println("Colide");
		}
	}
	
}
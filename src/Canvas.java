import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class Canvas implements GLEventListener, MouseListener{

	ArrayList<Particle> particles;
	int time = 0;
	
	public void display(GLAutoDrawable draw) {
		for(int steps = 0; steps < 2; steps++)
			update();
		render(draw);
	}
	
	public void update(){
		if(time%240 == 0 && particles.size() < 50)
			particles.add(new Particle(Math.random()*500, Math.random()*500));
			
		time++;
		for(Particle p: particles)
			p.translate();
		Particle.compareParticles(particles);
	}
	
	public void render(GLAutoDrawable draw){
	    GL2 gl = draw.getGL().getGL2();
	    
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
	    /*
	    // draw a triangle filling the window
	    gl.glBegin(GL2.GL_TRIANGLES);
	    gl.glColor3f(1, 0, 0);
	    gl.glVertex2f(-1, -1);
	    gl.glColor3f(0, 1, 0);
	    gl.glVertex2f(0, 1);
	    gl.glColor3f(0, 0, 1);
	    gl.glVertex2f(1, -1);
	    gl.glEnd();*/
	    
		gl.glBegin(GL.GL_POINTS);
	    gl.glColor3f(1, 1, 1);
		for(Particle p: particles){
		    gl.glColor3f(p.color.getRed(), p.color.getGreen(), p.color.getBlue());
			gl.glVertex2d(-1.0+2.0*(p.getX()/500.0), -1.0+2.0*(p.getY()/500.0));
		}
		gl.glEnd();
	}

	public void dispose(GLAutoDrawable draw) {
		
	}

	public void init(GLAutoDrawable draw) {
		particles = new ArrayList<Particle>();
		//particles.add(new Particle(Math.random()*500, Math.random()*500));
		Particle p = new Particle(100, 100);
		p.setVelocity(-1, 1*Math.PI);
		particles.add(p);
		Particle p2 = new Particle(400, 400);
		p2.setVelocity(-1, 0.5*Math.PI);
		p2.color = new Color(1, 0, 1);
		particles.add(p2);
		
		
	}

	public void reshape(GLAutoDrawable draw, int arg1, int arg2, int arg3, int arg4) {
		
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		Particle.quake(particles);
		
	}

}

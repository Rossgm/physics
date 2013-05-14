import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

public class Canvas implements GLEventListener{

	ArrayList<Particle> particles;
	int time = 0;
	
	public void display(GLAutoDrawable draw) {
		for(int steps = 0; steps < 10; steps++)
			update();
		render(draw);
	}
	
	public void update(){
		if(time%240 == 0)
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
		for(Particle p: particles)
			gl.glVertex2d(-1.0+2.0*(p.getX()/500.0), -1.0+2.0*(p.getY()/500.0));
		gl.glEnd();
	}

	public void dispose(GLAutoDrawable draw) {
		
	}

	public void init(GLAutoDrawable draw) {
		particles = new ArrayList<Particle>();
		particles.add(new Particle(Math.random()*500, Math.random()*500));
	}

	public void reshape(GLAutoDrawable draw, int arg1, int arg2, int arg3, int arg4) {
		
	}

}

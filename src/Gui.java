import javax.media.opengl.*;
import javax.media.opengl.awt.*;

public class Gui {
	private GLProfile glp;
	private GLCapabilities caps;
	private GLCanvas canvas;
	public static final int WIDTH = 100;
	public static final int HEIGHT = 100;
	
	public void initialize(){
		glp = GLProfile.getDefault();
		caps = new GLCapabilities(glp);
		canvas = new GLCanvas(caps);
		Canvas c = new Canvas();
        canvas.addGLEventListener(c);
        canvas.addMouseListener(c);
	}
	
	public GLCanvas getCanvas(){
		return canvas;
	}
	
}

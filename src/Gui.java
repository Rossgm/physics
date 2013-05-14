import javax.media.opengl.*;
import javax.media.opengl.awt.*;

public class Gui {
	private GLProfile glp;
	private GLCapabilities caps;
	private GLCanvas canvas;
	//public static final int 
	
	public void initialize(){
		glp = GLProfile.getDefault();
		caps = new GLCapabilities(glp);
		canvas = new GLCanvas(caps);
        canvas.addGLEventListener(new Canvas());
	}
	
	public GLCanvas getCanvas(){
		return canvas;
	}
	
}

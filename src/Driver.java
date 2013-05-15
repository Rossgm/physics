import javax.swing.JFrame;

import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;


public class Driver {

	/**
	 * The main entry point of the program
	 * @param args unused
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("DOOT DOOT");
		frame.setSize(100, 100);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Gui gui = new Gui();
		gui.initialize();
		frame.add(gui.getCanvas());
		frame.setVisible(true);

        Animator animator = new Animator(gui.getCanvas());
        animator.add(gui.getCanvas());
        animator.start();
	}

}

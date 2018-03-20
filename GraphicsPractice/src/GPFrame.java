import java.awt.GridLayout;

import javax.swing.JFrame;

public class GPFrame extends JFrame {

	public GPFrame()
	{
		super("Graphics Practice"); // call my superclass (JFrame) constructor, passing "Graphics Practice" as a window title.
		setSize(600,400);
		setResizable(true);
		
		getContentPane().setLayout(new GridLayout(1,1)); // arrange the content of this window so that it consists of a 
														 //  (1 x 1) grid of spaces that stretch to fill the window.
		
		GPPanel customGraphicsPanel= new GPPanel(); // make a custom panel where we do the drawing
		
		getContentPane().add(customGraphicsPanel); // place the panel into the content area, where it will be stretched to fit the window.
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // quit program when this window is closed.
	}
	
}

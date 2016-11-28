package zombiehunter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Window extends JFrame{
	
	private Window()
	{
		this.setTitle("Zombie Hunter"); //sets title for this frame
		
		//sets size of the frame
		
		this.setSize(800, 600); //size of frame
		this.setLocationRelativeTo(null); //centers frame on screen
	    this.setResizable(false); //frame cannot be resized
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setContentPane(new Framework());
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new Window();
			}
		});
	}

}

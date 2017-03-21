package graphics;

import java.awt.Color;

import javax.swing.JFrame;

public class Window extends JFrame
{

	public Window()
	{
		super();
		this.setSize(700, 700);
		this.add(new Display());
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}

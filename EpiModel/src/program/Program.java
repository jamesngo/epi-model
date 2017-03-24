package program;

import java.awt.Color;

import javax.swing.JFrame;

public class Program extends JFrame
{

	public Program()
	{
		super();
		this.setSize(700, 700);
		this.add(new Display());
		this.setBackground(Color.WHITE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
}

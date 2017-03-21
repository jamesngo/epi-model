package main;

import diseases.Conditions;
import diseases.Disease;
import networkModel.World;

public class Main
{

	public static void main(String[] args)
	{
		
		
		World world = new World(10000);
		System.out.println("Created world");
		world.randomizeCellConnections(300);
		System.out.println("Randomized Connections");
		world.introEpidemic(5, new Disease(.011, .002, .02, Conditions.SUSCEPTIBLE));
		System.out.println("epidemic introduced");
		
		for(int i = 0; i < 1000000; i++)
		{
			System.out.println("Update: "+i+" "+world+" ");
			world.update();
		}
		System.out.println("final update: " + world);
	}
	
}

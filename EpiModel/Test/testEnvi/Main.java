package testEnvi;

import diseases.Conditions;
import diseases.Disease;
import networkModel.Cell;
import networkModel.TestCell;
import networkModel.World;

public class Main {

	public static void main(String[] args)
	{
		World world = new World(100);
		world.randomizeCellConnections(30);
		for(Cell cell: world.getWorld())
		{
			TestCell.cellUpdatesTest(cell, world);
		}
		Disease disease = new Disease(.1,.1,.1, Conditions.SUSCEPTIBLE);
		world.introEpidemic(12, disease);
		for(int i = 0; i < 100; i++)
		{
			world.update();
		}
		for(Cell cell: world.getWorld())
		{
			TestCell.cellUpdatesTest(cell, world);
		}
		
		
	}
}

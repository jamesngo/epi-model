package networkModel;

import diseases.Conditions;
import diseases.Disease;

public class TestCell {
	

	public static void infectedUpdateTest()
	{
		infectTest(mock, new Disease(.1,.1,.1,Conditions.SUSCEPTIBLE));
		Cell oldState = new Cell(mock);
		Cell newCell = mock.infectedUpdate();
		assert mock.equals(oldState) : "Mock has been modified";
		assert newCell.condition == mock.disease.getRetCondition() || 
				newCell.condition == Conditions.INFECTED
				: "Condition's state is unexpected";
		assert newCell.infectTime == 0 || newCell.infectTime == mock.infectTime+1 : "infect time not updated";
		
		System.out.println("infectedUpdate test passed");
	}
	
	public static void immuneUpdateTest()
	{
		
	}
	
	public static void susceptibleUpdateTest()
	{
		int location = findCellLocation(mock, mockWorld);
		Cell oldState = new Cell(mock);
		Cell newCell = mock.susceptibleUpdate(mockWorld.getWorld(), mockWorld.connections.get(location));
		assert mock.equals(oldState) : "Mock has been modified";
		assert newCell.condition == Conditions.SUSCEPTIBLE
				|| newCell.condition == Conditions.INFECTED
				|| newCell.condition== Conditions.EXPOSED
				: "Final Condition impossible";
		
		System.out.println("susceptibleUpdate test passed.");
		
	}
	
	public static void removedUpdateTest()
	{
		int location = findCellLocation(mock, mockWorld);
		Cell oldState = new Cell(mock);
		Cell newCell = mock.immuneUpdate(mockWorld.getWorld(), mockWorld.connections.get(location));
		assert mock.equals(oldState);
		assert newCell.condition == Conditions.REMOVED || 
				newCell.condition == Conditions.SUSCEPTIBLE
				: "Final condition not possible";
		
		System.out.println("removedUpdate test passed");
	}
	
	public static void recoverTest()
	{
		assert mock.condition == Conditions.INFECTED : "No reason to recover, cell is not infected";
		mock.recover();
		assert mock.condition == Conditions.SUSCEPTIBLE || mock.condition == Conditions.REMOVED : "Cell not correctly recovered";
		assert mock.infectTime == 0 : "infectTime was not reset to zero";
		assert mock.disease == null : "Disease was not added to mock cell obj";
	}
	
	//not a pure function, edits stuff
	public static void infectTest()
	{
		mock.infect(disease);
		assert mock.condition == Conditions.INFECTED : "Could not infect Cell";
		assert mock.infectTime == 0 : "infectTime was not reset to zero";
		assert mock.disease == disease : "Disease was not added to mock cell obj";
		
		System.out.println("infect test passed");
	}
	
	public static int findCellLocation(Cell cell, World world)
	{
		int location = 0;
		for(int i = 0; i < world.getWorld().size(); i++)
		{
			if(world.getWorld().get(i)==cell) break;
			if(i==world.getWorld().size()-1) assert false : "World does not contained specified cell"; 
			location++;
		}
		return location;
	}
}

package networkModel;

import java.util.ArrayList;

import diseases.Conditions;
import diseases.Disease;

public class TestCell {
	

	public static void infectedUpdateTest()
	{
		Cell mock = new Cell(Conditions.INFECTED);
		Disease disease = new Disease(.1,.1,.1,Conditions.REMOVED);
		mock.disease = disease;
		Cell oldState = new Cell(mock);
		Cell newCell = mock.infectedUpdate();
		assert mock.equals(oldState) : "Mock has been modified";
		assert newCell.condition == mock.disease.getRetCondition() || 
				newCell.condition == Conditions.INFECTED
				: "Condition's state is unexpected";
		assert newCell.infectTime == 0 || newCell.infectTime == mock.infectTime+1 : "infect time not updated";

		System.out.println("infectedUpdate test passed");
	}
	
	public static void susceptibleUpdateTest()
	{
		World mockWorld = new World(100);
		mockWorld.randomizeCellConnections(5);
		mockWorld.introEpidemic(10, new Disease(.1, .1, .1, Conditions.REMOVED));
		Cell mock = mockWorld.getCell(0);
		mock.condition = Conditions.SUSCEPTIBLE;
		mock.disease = null;
		mock.infectTime = 0;
		Cell oldCell = new Cell(mock);
		Cell newCell = mock.susceptibleUpdate(mockWorld.getWorld(), mockWorld.connections.get(0));
		assert mock.equals(oldCell) : "Mock has been modified";
		assert newCell.condition == Conditions.SUSCEPTIBLE
				|| newCell.condition == Conditions.INFECTED
				|| newCell.condition== Conditions.EXPOSED
				: "Final Condition impossible";
		
		System.out.println("susceptibleUpdate test passed.");
		
	}
	
	public static void removedUpdateTest()
	{
		World mockWorld = new World(100);
		mockWorld.randomizeCellConnections(5);
		mockWorld.introEpidemic(10, new Disease(.1, .1, .1, Conditions.REMOVED));
		Cell mock = mockWorld.getCell(0);
		mock.condition = Conditions.REMOVED;
		mock.disease = null;
		mock.infectTime = 0;
		Cell oldCell = new Cell(mock);
		Cell newCell = mock.removedUpdate(mockWorld.getWorld(), mockWorld.connections.get(0));
		assert mock.equals(oldCell);
		assert newCell.condition == Conditions.REMOVED || 
				newCell.condition == Conditions.SUSCEPTIBLE
				: "Final condition not possible";
		
		System.out.println("removedUpdate test passed");
	}
	
	public static void recoverTest()
	{
		Cell mock = new Cell(Conditions.INFECTED);
		Disease disease = new Disease(.1,.1,.1, Conditions.REMOVED);
		mock.disease = disease;
		mock.recover();
		assert mock.condition == disease.getRetCondition() : "Cell not correctly recovered to disease retCondition";
		assert mock.infectTime == 0 : "infectTime was not reset to zero";
		assert mock.disease == null : "Disease was not removed from mock cell obj";
	}
	
	//not a pure function, edits stuff
	public static void infectTest()
	{
		Cell mock = new Cell(Conditions.SUSCEPTIBLE);
		Disease disease = new Disease(.1, .1, .1, Conditions.REMOVED);
		mock.infect(disease);
		assert mock.condition == Conditions.INFECTED : "Could not infect Cell";
		assert mock.infectTime == 0 : "infectTime was not reset to zero";
		assert mock.disease == disease : "Disease was not added to mock cell obj";
		
		System.out.println("infect test passed");
	}
	
	public static int findCellLocation(Cell cell, ArrayList<Cell> world)
	{
		int location = 0;
		for(int i = 0; i < world.size(); i++)
		{
			if(world.get(i)==cell) return location;;
			if(i==world.size()-1) return -1; 
			location++;
		}
		return -1;
	}
}

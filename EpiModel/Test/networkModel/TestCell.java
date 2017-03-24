package networkModel;

import diseases.Conditions;
import diseases.Disease;

public class TestCell {
	

	public static void testInfectedUpdate(Cell mock)
	{
		testInfect(mock, new Disease(.1,.1,.1,Conditions.SUSCEPTIBLE));
		Cell oldState = new Cell(mock);
		Cell newCell = mock.infectedUpdate();
		assert mock.equals(oldState) : "Mock has been modified";
		assert newCell.condition == mock.disease.getRetCondition() || 
				newCell.condition == Conditions.INFECTED: "Condition's state is unexpected";
		assert newCell.infectTime == 0 || newCell.infectTime == mock.infectTime+1 : "infect time not updated";
	}
	
	public static void testImmuneUpdate()
	{
		
		
		
	}
	
	public static void testSusceptibleUpdate()
	{
		
	}
	
	public static void testRemovedUpdate(Cell mock, World mockWorld)
	{
		int location = 0;
		for(int i = 0; i < mockWorld.world.size(); i++)
		{
			if(mockWorld.world.get(i)==mock) break;
			if(i==mockWorld.world.size()-1) assert false : "World does not contained specified cell"; 
			location++;
		}
		Cell oldState = new Cell(mock);
		Cell newCell = mock.immuneUpdate(mockWorld.world, mockWorld.connections.get(location));
		assert mock.equals(oldState);
		assert newCell.condition == Conditions.REMOVED || 
				newCell.condition == Conditions.SUSCEPTIBLE : "Final condition not possible";
	}
	
	public static void testDoesWhatOnUpdate()
	{
		
	}
	
	public static void testRecover()
	{
		
	}
	
	//not a pure function, edits stuff
	public static void testInfect(Cell mock, Disease disease)
	{
		mock.infect(disease);
		assert mock.condition == Conditions.INFECTED : "Could not infect Cell";
		assert mock.infectTime == 0 : "infectTime was not reset to zero";
		assert mock.disease == disease : "Disease was not added to mock cell obj";
	}
}

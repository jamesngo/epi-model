package networkModel;

import java.util.ArrayList;

import diseases.Conditions;
import diseases.Disease;

/*
 * represents a cell
 *
 * Cells can only handle world having 1 disease @ a time
 *
 * TODO:
 *  - Cannot remove connections in case of hypothetical disease "crippling subject" but still able to get again
 *  - Condition possibilities depend on disease or on model? (does disease dictate what conditions there are in the model?)
 *  - Am I forgetting some conditions?
 *  - Removing some connections after death?
 */
public class Cell
{

	/*
	 * Contains the cells that touches THIS. Is not bi-directional yet
	 */
	public int infectTime;
	public Disease disease;
	public Conditions condition;
//	public int location;


	//the connect object needs to have the size of worldsize
	public Cell(Conditions condition)
	{
		this.condition = condition;
	}
	
	public Cell(Cell cell)
	{
		this.infectTime = cell.infectTime;
		this.disease = cell.disease;
		this.condition = cell.condition;
	}

	//infects the current cell with disease. Takes a disease as a parameter to infect the cell w/
	public Cell infect(Disease disease)
	{
		Cell retCell = this;
		retCell.disease = disease;
		retCell.condition = Conditions.INFECTED;
		retCell.infectTime = 0;
		return retCell;
	}

	//recovers the current cell
	public Cell recover()
	{
		Cell retCell = this;
		retCell.condition = disease.getRetCondition();
		retCell.disease = null;
		return retCell;
	}

	//returns what the infected cell should do after it's update period
	public Cell doesWhatOnUpdate(ArrayList<Cell> world, ArrayList<Integer> connections)
	{
		switch(condition)
		{
		case REMOVED:
			return removedUpdate(world, connections);
		case SUSCEPTIBLE:
			return susceptibleUpdate(world, connections);
		case INFECTED:
			return infectedUpdate();
		default:
			System.err.println("Update error: Cell "+this+" failed to correctly update. Continuing program");
			return this;
		}

	}
	
	public Cell removedUpdate(ArrayList<Cell> world, ArrayList<Integer> connections)
	{
		
		return this;
	}
	
	public Cell susceptibleUpdate(ArrayList<Cell> world, ArrayList<Integer> connections)
	{
		int infectedsTouched = 0;
		Disease currentDisease = null;
		for(Integer i: connections)
		{
			if(world.get(i).condition == Conditions.INFECTED){
				if(currentDisease!=world.get(i).disease) 
				{
					currentDisease = world.get(i).disease;
				}
				infectedsTouched++;
			}
		}
		
		if(infectedsTouched > 0)
		{
			double infectChance = currentDisease.calcInfectChance(infectedsTouched);
			if(Math.random() < infectChance)
			{
				return infect(currentDisease);
			}
			else
			{
				return this;
			}
		}

		return this;
	}
	
	public Cell immuneUpdate(ArrayList<Cell> world, ArrayList<Integer> connections)
	{
		
		return null;
	}
	
	public Cell infectedUpdate()
	{
		infectTime++;
		double infectChance = disease.calcRecoveryChance(infectTime);
		if(Math.random() < infectChance)
		{
			return recover();
		}
		else
		{
			return this;
		}
	}
	
	public boolean equals(Cell cell)
	{
		if(cell.infectTime != this.infectTime) return false;
		if(cell.disease != this.disease) return false;
		if(cell.condition != this.condition) return false;
		else return true;
	}
}

package networkModel;

import java.util.ArrayList;

import diseases.Conditions;
import diseases.Disease;

//Finished SIR & SIS type models

/*
 * TODO:
 *  - Implement more model types, allow to create own
 *  - Contact rate, people don't need to touch all their connections every single update
 *  - Introduce birth death system
 */
public class World
{

	//Arraylist of Cells
	private ArrayList<Cell> world;
	ArrayList<ArrayList<Integer>> connections;
	ArrayList<Disease> diseasesInWorld;
	/*
	 * Creates a world with a certain number of cells and an initial condition
	 */
	public World(int cellNum)
	{
		setWorld(new ArrayList<Cell>(cellNum));
		connections = new ArrayList<ArrayList<Integer>>(cellNum);
		diseasesInWorld = new ArrayList<Disease>(1);
		
		for(int i = 0; i < cellNum; i++)
		{
			getWorld().add(new Cell(Conditions.SUSCEPTIBLE));
			connections.add(new ArrayList<Integer>(cellNum));
		}
		
	}

	/*
	 * Given an arbitrary number of connections (might want to restrict to above 0), it will create
	 * random connections between cells with each cell being connected to x number of other cells not including itself
	 */
	public void randomizeCellConnections(int connectionNum)
	{
		for(int i = 0; i < getWorld().size(); i++)
		{
			for(int k = 0; k < connectionNum; k++)
			{
				int location;
			
				do
				{
					location = (int) Math.floor(Math.random()*getWorld().size());
				}
				
				while(location == i || connections.get(i).contains(location));

				connections.get(i).add(location);	
			}	
		}
	}

	/*
	 * Introduces an epidemic with a certain infectivity constant, todo: abstract more so that user does not have to input
	 * all this stuff, put into diseases package, just outbreak patients is the initial number of infected individuals
	 */
	public void introEpidemic(int outbreakPatients, Disease disease)
	{
		diseasesInWorld.add(disease);
		for(int i = 0; i < outbreakPatients; i++)
		{
			getWorld().get((int) (Math.random()* getWorld().size())).infect(disease);
		}
	}

	/*
	 * updates 1 time interval, does this by calculating whether infected individuals will heal by the end of the time jump, then
	 * updates who they will infect, then heals them
	 */
	public void update()
	{
		ArrayList<Cell> newWorld = new ArrayList<Cell>(getWorld().size());
		
		for(int i = 0; i < getWorld().size(); i++)
		{
			newWorld.add(getWorld().get(i).doesWhatOnUpdate(getWorld(), connections.get(i)));
		}
		
		setWorld(newWorld);
	}
	
	//calculates how many infecteds are in the current population
	public int howManyInfecteds()
	{
		int infecteds = 0;
		for(int i = 0; i < getWorld().size(); i++)
		{
			if(getWorld().get(i).condition == Conditions.INFECTED)
			{
				infecteds++;
			}
		}
		return infecteds;
	}

	public String toString()
	{
		return "infecteds:" + howManyInfecteds();

	}

	public ArrayList<Cell> getWorld() {
		return world;
	}

	public void setWorld(ArrayList<Cell> world) {
		this.world = world;
	}
	
	public Cell getCell(int index) {
		return world.get(index);
	}
	
	public void setCell(int index, Cell cell)
	{
		world.set(index, cell);
	}
	
	public ArrayList<Integer> getConnections(int cellIndex)
	{
		return connections.get(cellIndex);
	}
	
}

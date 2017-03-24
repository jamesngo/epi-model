package differentialModel;

import java.util.HashMap;
import java.util.Map;

import diseases.Conditions;

/*
 * Ask Mr. Kuszmaul about what type of data struct should use?
 */
public class Population {

	public HashMap<Conditions, Integer> compartments;
	public HashMap<Conditions, Expression> compartmentConnections;
	
	public Population(HashMap<Conditions, Integer> compartments, 
			HashMap<Conditions, Expression> compartmentConnections)
	{
		this.compartments = compartments;
		this.compartmentConnections = compartmentConnections;
	}
	
	public void update()
	{
		HashMap<Conditions, Integer> dCompartments = compartments;
		
		for(Map.Entry<Conditions, Expression> connect: compartmentConnections.entrySet())
		{
			dCompartments.put(connect.getKey(), 
					calcDelta(connect.getValue()));
		}
	}
	
	public int calcTotalPopulation()
	{
		int n = 0;
		for(Map.Entry<Conditions, Integer> compartment: compartments.entrySet())
		{
			n = n + compartment.getValue();
		}
		return n;
	}
	
	public int calcDelta(Expression expression)
	{
		
		return 0;
	}
	
}

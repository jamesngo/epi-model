package diseases;

public class Disease
{
	double infectivity;
	double recovery;
	Conditions retCondition;
	double incubation;
	
	
	public Disease(double infectivity, double recovery, double incubation, Conditions retCondition)
	{
		this.infectivity = infectivity;
		this.recovery = recovery;
		this.retCondition = retCondition;
		this.incubation = incubation;
	}

	public double getInfectivity()
	{
		return this.infectivity;
	}

	public double getRecovery()
	{
		return this.recovery;
	}

	public Conditions getRetCondition()
	{
		return this.retCondition;
	}

	public double getIncubation()
	{
		return this.incubation;
	}
	
	public double calcRecoveryChance(int infectTime)
	{
		return infectTime*recovery;
	}
	
	public double calcInfectChance(int infectedsTouched)
	{
		return infectivity*infectedsTouched;
	}
}

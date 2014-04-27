package com.enzor.LD29.model;

public class Buff {

	StatModifier effect;
	
	int turnsLeft;
	boolean lastForever; //if true, the buff never goes away unless removed by a special function
	boolean readyToDelete;
	
	//Needs to be called before a buff is deleted because it will un-modify the targeted stat.
	void endEffect()
	{
		effect.target.removeModifier(effect);
	}
	
	void processTurn()
	{
		if(!lastForever)
		{
			turnsLeft--;
			if(turnsLeft <= 0)
				readyToDelete = true;
		}
	}
}
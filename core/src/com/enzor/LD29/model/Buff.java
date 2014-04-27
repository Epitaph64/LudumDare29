package com.enzor.LD29.model;

public class Buff {
	
	Fighter target;

	StatModifier effect;
	
	int turnsLeft;
	boolean lastForever; //if true, the buff never goes away unless removed by a special function
	boolean readyToDelete;
	
	//damage (or healing) over time effect
	boolean hasDotEffect;
	int dotEffect;
	
	//Needs to be called before a buff is deleted because it will un-modify the targeted stat.
	void endEffect()
	{
		effect.target.removeModifier(effect);
	}
	
	void processTurn()
	{
		if(hasDotEffect)
		{
			if(dotEffect >= 0)
				target.damage(dotEffect);
			else
				target.heal(dotEffect);
		}
		
		if(!lastForever)
		{
			turnsLeft--;
			if(turnsLeft <= 0)
				readyToDelete = true;
		}
	}
}
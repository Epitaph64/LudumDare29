package com.enzor.LD29.model;

import java.util.ArrayList;

public class Fighter {

	//Stats
	Stat strength;
	Stat dexterity;
	Stat defense;
	Stat speed;
	
	//Character status
	Stat maxHealth;
	float currentHealth;
	
	//Modifiers
	ArrayList<Buff> buffs; //a buff can be positive or negative, but there are two lists for organizations sake
	ArrayList<Buff> debuffs;
	ArrayList<GearItem> gear;
	
	//Movetimer
	float moveTimer; //The amount of 'move points' needed before the fighter can perform another action
	boolean canMove;
	
	void processTurn()
	{
		//Handle buffs
		for(int i = buffs.size() - 1; i >= 0; i--)
		{
			buffs.get(i).processTurn();
			if(buffs.get(i).readyToDelete)
			{
				buffs.get(i).endEffect();
				buffs.remove(i);
			}
		}
		for(int i = debuffs.size() - 1; i >= 0; i--)
		{
			buffs.get(i).processTurn();
			if(buffs.get(i).readyToDelete)
			{
				buffs.get(i).endEffect();
				buffs.remove(i);
			}
		}
		
		//Handle action cooldown
		waitForMove();
	}
	
	//Function that will be used each turn to determine whether or not the fighter can do something
	void waitForMove()
	{
		moveTimer -= speed.finalValue;
		if(moveTimer < 0)
			canMove = true;
	}
	
}

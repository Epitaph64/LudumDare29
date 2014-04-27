package com.enzor.LD29.model;

import java.util.ArrayList;

import com.enzor.LD29.model.Action.ActionType;

public class Fighter implements Comparable<Fighter> {

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
	
	//Action
	Action nextAction;
	
	void doNextAction()
	{
		if(nextAction.type == ActionType.ACTION_ATTACK)
		{
			//todo
			//nextAction.target.currentHealth -= strength.finalValue; //todo will finalValue have been calculated by then?
		}
	}
	
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

	@Override
	public int compareTo(Fighter o) {
		return (moveTimer < o.moveTimer) ? 0 : 1;
	}
	
}

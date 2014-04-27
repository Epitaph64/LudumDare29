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
	
	//Attacking stuff
	Weapon weapon; //if has a weapon
	boolean hasWeapon;
	
	//Movetimer
	float moveTimer; //The amount of 'move points' needed before the fighter can perform another action
	boolean canMove;
	
	//Action
	Action nextAction;
	
	//Party
	Party ownerParty;
	
	static float getAttackDamage(Fighter attacker, Fighter target)
	{
		attacker.strength.calculateFinalValue();
		target.defense.calculateFinalValue();
		float damage = attacker.strength.finalValue;
		if(attacker.hasWeapon)
			damage *= attacker.weapon.getDamageRoll();
		
		return damage/target.defense.finalValue;
	}
	
	void setNextAction(Action action)
	{
		nextAction = action;
	}
	
	void doNextAction()
	{		
		nextAction.perform();
		moveTimer += nextAction.timeCost;
		canMove = false;
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

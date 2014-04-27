package com.enzor.LD29.model;

import java.util.ArrayList;

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
	
	//Other gear
	GearItem ring1;
	GearItem ring2;
	GearItem amulet;
	GearItem helmet;
	GearItem armor;
	GearItem gloves;
	GearItem boots;
	
	enum GearSlot
	{
		WEAPON,
		RING1,
		RING2,
		AMULET,
		HELM,
		ARMOR,
		GLOVE,
		BOOT
	}
	
	//Movetimer
	float moveTimer; //The amount of 'move points' needed before the fighter can perform another action
	boolean canMove;
	
	//Action
	Action nextAction;
	
	//Party
	Party ownerParty;
	
	//Equips an item. Returns the item that was already in that slot if there was one.
	GearItem equipItem(GearItem item, GearSlot slot)
	{
		switch(slot)
		{
		case WEAPON:
			GearItem tempWep = weapon;
			weapon = (Weapon)item; //todo make it safer than this
			return tempWep;
		case RING1:
			GearItem temp = ring1;
			ring1 = item;
			return temp;
		case RING2:
			GearItem temp2 = ring2;
			ring2 = item;
			return temp2;
		}
		//todo: redo this since it's idiotic...
		return null;
	}
	
	void damage(float damage)
	{
		currentHealth -= damage;
		//todo handle if dead.
	}
	
	void heal(float amount)
	{
		currentHealth += amount;
		maxHealth.calculateFinalValue();
		if(currentHealth > maxHealth.finalValue)
			currentHealth = maxHealth.finalValue;
	}
	
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

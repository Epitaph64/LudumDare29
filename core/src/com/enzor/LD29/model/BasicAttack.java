package com.enzor.LD29.model;

import com.enzor.LD29.model.Action.ActionType;

public class BasicAttack extends Action{

	public BasicAttack(Fighter attacker, Fighter defender)
	{
		actor = attacker;
		target = defender;
		type = ActionType.ACTION_ATTACK;
		timeCost = 10;
		if(attacker.hasWeapon)
			timeCost += attacker.weapon.attackTimeCost;
	}
	
	@Override
	public void perform()
	{
			target.currentHealth -= Fighter.getAttackDamage(actor, target);
	}
	
}

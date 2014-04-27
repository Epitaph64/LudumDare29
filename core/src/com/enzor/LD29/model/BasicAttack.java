package com.enzor.LD29.model;

public class BasicAttack extends Action{

	public BasicAttack(Fighter attacker, Fighter defender)
	{
		actor = attacker;
		target = defender;
		timeCost = 10;
		if(attacker.hasWeapon)
			timeCost += attacker.weapon.attackTimeCost;
	}
	
	@Override
	public void perform()
	{
			target.damage(Fighter.getAttackDamage(actor, target));
	}
	
}

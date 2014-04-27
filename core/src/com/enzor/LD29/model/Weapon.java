package com.enzor.LD29.model;

import java.util.Random;

public class Weapon extends GearItem {

	Random rand; //todo replace with mersenne twister...
	
	int minDamage;
	int maxDamage;
	
	int getDamageRoll()
	{
		int damageDifferential = maxDamage - minDamage;
		int damage = rand.nextInt(damageDifferential);
		return minDamage + damage;
	}
}

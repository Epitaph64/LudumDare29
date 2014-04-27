package com.enzor.LD29.model;

public class StatModifier {

	enum ModifierType
	{
		BASE_INCREASE, //Adds directly to the base stat, such as +10 strength
		PERCENT_INCREASE //percentage calculated on the base stat, such as 10% increased strength
	}
	
	float modifierValue;
	ModifierType type;
	Stat target; //keeps track of the stat affected by this modifier - maybe change this later...
}

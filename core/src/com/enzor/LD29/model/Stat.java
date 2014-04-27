package com.enzor.LD29.model;

import java.util.ArrayList;

import com.enzor.LD29.model.StatModifier.ModifierType;

public class Stat {

	//Value of the stat before modifiers is applied
	float baseValue;
	//Value of the stat after modifiers
	float finalValue;
	
	//stat modifications
	float baseMod;
	float prctMod;
	
	//Restrictions
	boolean hasMinimumValue;
	float minimumValue;
	
	ArrayList<StatModifier> modifiers;
	
	Stat()
	{
		commonConstruct();
	}
	
	void commonConstruct()
	{
		baseMod = 0;
		prctMod = 0;
		hasMinimumValue = true;
		minimumValue = 1;
		baseValue = 1;
		finalValue = 1;
		modifiers = new ArrayList<StatModifier>();
	}
	
	void addModifier(StatModifier mod)
	{
		modifiers.add(mod);
		calculateFinalValue();
	}
	
	void removeModifier(StatModifier mod)
	{
		modifiers.remove(mod);
		calculateFinalValue();
	}
	
	void calculateBaseModifiers()
	{
		baseMod = 0;
		
		for(StatModifier m : modifiers)
		{
			if(m.type == ModifierType.BASE_INCREASE)
			{
				baseMod += m.modifierValue;
			}
		}
	}
	
	void calculatePercentModifiers()
	{
		prctMod = 0;
		
		for(StatModifier m : modifiers)
		{
			if(m.type == ModifierType.PERCENT_INCREASE)
			{
				prctMod += m.modifierValue;
			}
		}
	}
	
	//Todo, positive vs negative % modifiers?
	void calculateFinalValue()
	{
		calculateBaseModifiers();
		calculatePercentModifiers();
		
		float augmentedBase = baseValue + baseMod;
		if(hasMinimumValue && augmentedBase < minimumValue)
		{
			augmentedBase = minimumValue;
		}
		float percentIncrease = augmentedBase * (prctMod/100);
		
		finalValue = augmentedBase + percentIncrease;
		if(hasMinimumValue && finalValue < minimumValue)
		{
			finalValue = minimumValue;
		}
	}
}

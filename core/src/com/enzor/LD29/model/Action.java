package com.enzor.LD29.model;

public class Action {

	enum ActionType
	{
		ACTION_ATTACK
	}
	
	ActionType type;
	Fighter target;
	float timeCost;
	
	public Action(ActionType t, Fighter aTarget)
	{
		type = t;
		target = aTarget;
		
		//default time cost: 10
		timeCost = 10;
	}
}
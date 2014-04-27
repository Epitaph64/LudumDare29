package com.enzor.LD29.model;

public class Action {

	enum ActionType
	{
		ACTION_ATTACK
	}
	
	ActionType type;
	Fighter target;
	
	public Action(ActionType t, Fighter aTarget)
	{
		type = t;
		target = aTarget;
	}
}
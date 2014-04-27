package com.enzor.LD29.model;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Battle {

	Party team1;
	Party team2;
	
	PriorityQueue<Fighter> activeFighters;

	ArrayList<Fighter> getAvailableFighters(Party team)
	{
		Party activeTeam = null;
		if(team.equals(team1))
			activeTeam = team1;
		else if(team.equals(team2))
			activeTeam = team2;
		
		ArrayList<Fighter> result = new ArrayList<Fighter>();
		if(activeTeam == null)
			return result;
		
		for(Fighter f : activeTeam.fighters)
		{
			if(f.canMove)
				result.add(f);
		}
		
		return result;
	}
	
	//todo this function may be useless...
	void doActions()
	{
		while(activeFighters.size() > 0)
		{
			Fighter current = activeFighters.peek();
			//current.doNextAction();
			//activeFighters.remove();
		}
	}
	
	void processNextFighter()
	{
		if(activeFighters.size() > 0)
		{
			Fighter nextFighter = activeFighters.peek();
			if(nextFighter.ownerParty.equals(team1))
			{
				//player-owned fighter
				//todo - figure out how to wait while allowing the player to choose this character's next action.
				//todo nextFighter.setNextAction()
				nextFighter.doNextAction();
			}
			else
			{
				//Bad AI for now - attack first of player's team
				//nextFighter.setNextAction(new Action(ActionType.ACTION_ATTACK, team1.fighters.get(0)));
				nextFighter.setNextAction(new BasicAttack(nextFighter, team1.fighters.get(0)));
				nextFighter.doNextAction();
			}
		}
	}
	
	void processTurn()
	{
		for(Fighter f : team1.fighters)
		{
			f.processTurn();
		}
		for(Fighter f : team2.fighters)
		{
			f.processTurn();
		}
	}
	
}

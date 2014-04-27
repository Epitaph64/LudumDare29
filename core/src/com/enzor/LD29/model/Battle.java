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
	
	void doActions()
	{
		while(activeFighters.size() > 0)
		{
			Fighter current = activeFighters.peek();
			current.doNextAction();
			activeFighters.remove();
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

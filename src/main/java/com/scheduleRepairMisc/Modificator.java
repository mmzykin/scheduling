package com.scheduleRepairMisc;

import com.domain.Day;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.TeamAssignment;
import com.domain.TravelingTournament;

public  class Modificator{
    public static void setDate(TravelingTournament solution, Match match, Day day) {
        
        if(solution.getMatchList().contains(match)) {    
            for (TeamAssignment teamAssignment : solution.getTeamAssignmentList()) {
                
                if (teamAssignment.getId() == match.getHomeTeam().getId() || teamAssignment.getId() == match.getAwayTeam().getId()) {
                   
                    if(match.getDay() != null) {
                        teamAssignment.getMatchMap().remove(match.getDay().getIndex());
                    }
                    
                    if(day != null) {
                        teamAssignment.getMatchMap().put(day.getIndex(), match);
                    }
                }
            }
            match.setDay(day);
        }   
    }
    
    public static boolean checkDate(TravelingTournament solution, Match match, Day day) {
        boolean checkStatus = true;
        
        if(solution.getMatchList().contains(match) && solution.getDayList().contains(day)) { 
   
            for (TeamAssignment teamAssignment : solution.getTeamAssignmentList()) {
   
                if (teamAssignment.getId() == match.getHomeTeam().getId() || teamAssignment.getId() == match.getAwayTeam().getId()) {
                   
                  
                    if (checkStatus == false ||
                        day.getActiveFlag() == false ||
                        day.getFixingState() == true ||
                        match.getFixingState() == true ||
                        teamAssignment.getMatchMap().containsKey(day.getIndex()) ||
                        match.getHomeTeam().getMatchType(day) == MatchType.NO_MATCH ||
                        match.getHomeTeam().getMatchType(day) == MatchType.AWAY ||
                        match.getAwayTeam().getMatchType(day) == MatchType.NO_MATCH) {
                        
                        checkStatus = false;
                    }
                }
            }
        }
        else {
            checkStatus = false;
        }
        
        return checkStatus;
    }
    
}
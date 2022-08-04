package com.solver.seria;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.domain.Match;
import com.domain.MatchType;
import com.domain.Team;
import com.domain.TeamAssignment;

public class SeriaFactory {

    public List<Seria> seriaList;
    public Team team;
    public Match lastMatch;

    private int minIntBetweenSameType = 3;
    
    public void create(TeamAssignment teamAssignment) {

        TreeMap<Integer, Match> matchMap = teamAssignment.getMatchMap();
        Match firstMatch = matchMap.firstEntry().getValue();
        Seria seria = null;

        lastMatch = matchMap.lastEntry().getValue();
        seriaList = new ArrayList<>();
        team = (firstMatch.getHomeTeam().getId() == teamAssignment.getId()) ? firstMatch.getHomeTeam()
                : firstMatch.getAwayTeam();
        int matchTypeSequenceId = 1;
        int res;
        
        for (Match match : matchMap.values()) {

            
            if (seria == null) {
                seria = new Seria(team, matchTypeSequenceId);
                seriaList.add(seria);
            } 
            else if((res = canInsertMatch(seria, match)) < 0) {
                
                if(res==-1){
                    matchTypeSequenceId++;    
                }
                
                seria.closeSeria();
                seria = new Seria(team, matchTypeSequenceId);
                seriaList.add(seria);
            }

            seria.insertMatch(match);

            if (match == lastMatch) {
                seria.closeSeria();
            }
        }
    }
    
    
    public int canInsertMatch(Seria seria, Match match) {
        
        int result = 1;
       
        if (seria.getSeriaLength() > 0) {
            
            MatchType matchType = match.getMatchType(seria.getTeam());
            
            if (matchType != seria.getSeriaType()) {
                result = -1;
            }
            else {
                Match prevMatch = seria.getLastMatch();
                if(match.getDay().getIndex() - prevMatch.getDay().getIndex() - 1 >= minIntBetweenSameType && matchType == MatchType.AWAY) {
                    result = -2;
                }
            }
        }

        return result;
    }
    
    public List<Seria> getSeriaList() {
        return seriaList;
    }

    public Team getTeam() {
        return team;
    }

    public Match getLastMatch() {
        return lastMatch;
    }

}
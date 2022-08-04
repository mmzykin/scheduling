package com.solver.seria;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.domain.Day;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.Period;
import com.domain.RegionType;
import com.domain.Team;

import lombok.Getter;
import lombok.Setter;

public class Seria {

    //ID последовательности матчей с одинаковым типом (Домашний или Выездной)
    @Getter @Setter 
    private int matchTypeSequenceId;
    private Team team;
    private Integer sumDistance;
    private MatchType seriaType;
    private List<Match> matchList;
    private Match firstMatch;
    private Match lastMatch;
    private Seria prevSeria;
    private Seria nextSeria;
    private Integer optimalDistance;
    private Integer notAvailableArenaCount;
    private boolean isMoveToFarEast;

    public Seria(Team team, int matchTypeSequenceId) {
        matchList = new ArrayList<>();
        this.team = team;
        sumDistance = 0;
        isMoveToFarEast = false;
        optimalDistance = 0;
        notAvailableArenaCount = 0;
        this.matchTypeSequenceId = matchTypeSequenceId;
    }

    public void insertMatch(Match match) {
        if (matchList.size() == 0) {
            seriaType = match.getMatchType(team);
            firstMatch = match;

            if (seriaType == MatchType.AWAY) {
                sumDistance = match.getAwayTeam().getDistance(match.getHomeTeam());

            }
        } else {
            if (seriaType == MatchType.AWAY) {
                sumDistance -= lastMatch.getHomeTeam().getDistance(lastMatch.getAwayTeam());
                sumDistance += lastMatch.getHomeTeam().getDistance(match.getHomeTeam());
            }
        }

        if (seriaType == MatchType.AWAY) {
            sumDistance += match.getHomeTeam().getDistance(match.getAwayTeam());
        }

        if (match.getAwayTeam().getMatchType(match.getDay()) == MatchType.AWAY) {
            notAvailableArenaCount += 1;
        }

        if (match.getHomeTeam().getRegionType() == RegionType.FAR_EAST) {
            isMoveToFarEast = true;
        }

        matchList.add(match);
        lastMatch = match;
    }

    public boolean getIsMoveToFarEast() {
        return isMoveToFarEast;
    }

    public static boolean canInsertMatch(Seria seria, Match match) {

        MatchType matchType = match.getMatchType(seria.getTeam());
        boolean result = true;

        if (matchType != seria.getSeriaType()) {
            result = false;
        }

        return result;
    }

    public Team getTeam() {
        return team;
    }

    public MatchType getSeriaType() {
        return seriaType;
    }

    public Match getLastMatch() {
        return lastMatch;
    }

    public Match getFirstMatch() {
        return firstMatch;
    }

    public Integer getDistance() {
        return sumDistance;
    }

    public void setPrevSeria(Seria prevSeria) {
        this.prevSeria = prevSeria;
    }

    public void setNextSeria(Seria nextSeria) {
        this.nextSeria = nextSeria;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public Integer getSeriaLength() {
        return matchList.size();
    }

    public void calculateOptimalDistance() {

        if (seriaType == MatchType.AWAY) {
            Team prevTeam = team;
            Integer flightDistance = 0;
            Integer minFlightDistance = 0;
            int index = 0;

            List<Team> teamList = new ArrayList<>();
            for (Match match : matchList) {
                teamList.add(match.getHomeTeam());
            }

            for (int i = 0; i < matchList.size(); i++) {

                for (int j = 0; j < teamList.size(); j++) {

                    flightDistance = prevTeam.getDistance(teamList.get(j));
                    if (j == 0 || minFlightDistance > flightDistance) {
                        minFlightDistance = flightDistance;
                        index = j;
                    }
                }

                prevTeam = teamList.get(index);
                teamList.remove(index);
                optimalDistance += minFlightDistance;
            }

            optimalDistance += prevTeam.getDistance(team);

        }
    }

    public Integer getOptimalDistance() {
        return optimalDistance;
    }

    public void closeSeria() {
        calculateOptimalDistance();
    }

    public Integer getNotAvailableArenaCount() {
        return notAvailableArenaCount;
    }

}

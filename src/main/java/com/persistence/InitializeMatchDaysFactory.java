package com.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.domain.Day;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.Team;
import com.domain.TravelingTournament;

public class InitializeMatchDaysFactory {

    public void initialize(TravelingTournament solution) {
        List<Day> activeDays = new ArrayList<>();
        Map<Day, List<Team>> dayTeams = new HashMap<>();
        for (Day day : solution.getDayList()) {
            if (day.getActiveFlag() == true && day.getFixingState() == false) {
                activeDays.add(day);
                dayTeams.put(day, new ArrayList<Team>());
            }
        }

        List<Match> initMatchList = new ArrayList<>();
        for (Match match : solution.getMatchList()) {
            if (match.getDay() == null) {
                initMatchList.add(match);
            } else if (dayTeams.containsKey(match.getDay())) {
                dayTeams.get(match.getDay()).add(match.getHomeTeam());
                dayTeams.get(match.getDay()).add(match.getAwayTeam());
            }

        }

        if (initMatchList.size() > 0) {
            for (Day day : activeDays) {

                Iterator<Match> i = initMatchList.iterator();
                while (i.hasNext()) {

                    Match match = i.next();
                    Team homeTeam = match.getHomeTeam();
                    Team awayTeam = match.getAwayTeam();

                    if (!dayTeams.get(day).contains(match.getHomeTeam()) &&
                            !dayTeams.get(day).contains(match.getAwayTeam()) &&
                            homeTeam.getMatchType(day) != MatchType.AWAY &&
                            homeTeam.getMatchType(day) != MatchType.NO_MATCH &&
                            awayTeam.getMatchType(day) != MatchType.NO_MATCH) {

                        dayTeams.get(day).add(match.getHomeTeam());
                        dayTeams.get(day).add(match.getAwayTeam());
                        match.setDay(day);
                        i.remove();
                    }
                }
            }
        }

        /*
         * for(Match match : solution.getMatchList()) {
         * if (match.getId() == 59065L) {
         * int i;
         * i=1;
         * }
         * }
         */

    }
}
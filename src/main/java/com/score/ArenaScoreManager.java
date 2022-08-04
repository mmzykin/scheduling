package com.score;

import java.util.TreeMap;

import com.domain.ArenaAssignment;
import com.domain.Match;
import com.score.arenaConstraints.HomeArenaForSeveralTeamsConstraint;

public class ArenaScoreManager extends CustomScoreManager {

    private HomeArenaForSeveralTeamsConstraint homeArenaForSeveralTeamsConstraint;

    public ArenaScoreManager(Boolean keepScoreElement) {

        homeArenaForSeveralTeamsConstraint = new HomeArenaForSeveralTeamsConstraint(keepScoreElement);

        constraintList.add(homeArenaForSeveralTeamsConstraint);

        defaultConsraintSettingsList = getConstraintSettingsList();
    }

    public void calculate(ArenaAssignment arenaAssignment) {

        TreeMap<String, Match> matchMap = arenaAssignment.getMatchMap();
        Match prevMatch = null;

        for (Match match : matchMap.values()) {

            homeArenaForSeveralTeamsConstraint.insert(match, prevMatch);

            prevMatch = match;
        }

    }
}

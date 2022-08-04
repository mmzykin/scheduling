package com.score.teamConstraints;

import com.domain.Match;
import com.domain.Team;

public class SameRivalSequenceConstraint extends Constraint {

    public SameRivalSequenceConstraint(Boolean keepScoreElement) {
        super(5L, "SameRivalSequence", true, 1, keepScoreElement);
    }

    public void insert(Team team, Match match, Match prevMatch) {

        if (active == true && prevMatch != null) {

            if (match.getAnotherTeam(team) == prevMatch.getAnotherTeam(team) &&
                    !(match.getHomeTeam().getName().equals("АМР") || match.getHomeTeam().getName().equals("АДМ"))) {

                
                this.setPenalty(penaltyValue, match, prevMatch);
            }
        }
    }
}

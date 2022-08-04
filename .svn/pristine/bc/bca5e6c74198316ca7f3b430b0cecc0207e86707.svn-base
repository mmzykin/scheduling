package com.score.arenaConstraints;

import com.domain.ConstraintSettings;
import com.domain.Match;
import com.score.teamConstraints.Constraint;

public class HomeArenaForSeveralTeamsConstraint extends Constraint {

    private Integer minDayCountParam;

    public HomeArenaForSeveralTeamsConstraint(Boolean keepScoreElement) {
        super(15L, "HomeArenaForSeveralTeams", true, 2, keepScoreElement);
        minDayCountParam = 1;
    }

    @Override
    public void setConstraintSettings(ConstraintSettings constraintSettings) {
        super.setConstraintSettings(constraintSettings);
        minDayCountParam = Integer.parseInt(constraintSettings.getProperty("minDayCountParam"));
    }

    @Override
    public ConstraintSettings getConstraintSettings() {
        ConstraintSettings constraintSettings = super.getConstraintSettings();
        constraintSettings.setProperty("minDayCountParam", minDayCountParam);
        return constraintSettings;
    }

    public void insert(Match match, Match prevMatch) {

        if (active == true && prevMatch != null) {

            Integer dayCount = match.getDay().getIndex() - prevMatch.getDay().getIndex() - 1;

            if (prevMatch.getHomeTeam() != match.getHomeTeam() && dayCount < minDayCountParam) {
                
                this.setPenalty(penaltyValue, match, prevMatch);
            }
        }
    }
}

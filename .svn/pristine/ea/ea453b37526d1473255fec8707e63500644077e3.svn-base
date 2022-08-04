package com.score.teamConstraints;

import com.domain.ConstraintSettings;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.Team;
import com.solver.seria.Seria;

public class HomeSeriaMatchIntervalConstraint extends Constraint {

    private Integer minDayCountParam;

    public HomeSeriaMatchIntervalConstraint(Boolean keepScoreElement) {
        super(4L, "HomeSeriaMatchInterval", true, 1, keepScoreElement);
        minDayCountParam = 1;
    }

    public void insert(Team team, Seria seria, Match match, Match prevMatch) {

        if (active == true && prevMatch != null) {

            Integer dayCount = match.getDay().getIndex() - prevMatch.getDay().getIndex() - 1;

            if (dayCount < minDayCountParam &&
                    prevMatch.getMatchType(team) == MatchType.HOME &&
                    match.getMatchType(team) == MatchType.HOME) {
                   
                this.setPenalty(penaltyValue, match, prevMatch);
            }
        }
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

}
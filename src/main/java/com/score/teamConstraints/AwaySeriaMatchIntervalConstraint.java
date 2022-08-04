package com.score.teamConstraints;

import com.domain.ConstraintSettings;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.Team;
import com.solver.seria.Seria;

public class AwaySeriaMatchIntervalConstraint extends Constraint {

    private Integer minDayCountParam;
    private Integer maxDayCountParam;

    public AwaySeriaMatchIntervalConstraint(Boolean keepScoreElement) {
        super(1L, "AwaySeriaMatchInterval", true, 1, keepScoreElement);
        minDayCountParam = 1;
        maxDayCountParam = 1;
    }

    public void insert(Team team, Seria seria, Match match, Match prevMatch) {

        if (active == true && prevMatch != null) {

            Integer dayCount = match.getDay().getIndex() - prevMatch.getDay().getIndex() - 1;

            if ((dayCount < minDayCountParam || dayCount > maxDayCountParam) &&
                    prevMatch.getMatchType(team) == MatchType.AWAY &&
                    match.getMatchType(team) == MatchType.AWAY &&
                    seria.getFirstMatch().getDay().getIndex() <= prevMatch.getDay().getIndex()) {
                    //(match.getDay().getIndex() - match.getDay().getActingIndex() == prevMatch.getDay().getIndex() - prevMatch.getDay().getActingIndex() ||
                      //      seria.getIsMoveToFarEast() == true)) {

                this.setPenalty(penaltyValue, match, prevMatch);
            }
        }
    }
    
    
    @Override
    public void setConstraintSettings(ConstraintSettings constraintSettings) {
        super.setConstraintSettings(constraintSettings);
        minDayCountParam = Integer.parseInt(constraintSettings.getProperty("minDayCountParam"));
        maxDayCountParam = Integer.parseInt(constraintSettings.getProperty("maxDayCountParam"));

    }

     @Override
    public ConstraintSettings getConstraintSettings() {
        ConstraintSettings constraintSettings = super.getConstraintSettings();
        constraintSettings.setProperty("minDayCountParam", minDayCountParam);
        constraintSettings.setProperty("maxDayCountParam", maxDayCountParam);
        return constraintSettings;
    }
}
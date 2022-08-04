package com.score.teamConstraints;

import com.domain.ConstraintSettings;
import com.domain.Team;
import com.solver.seria.Seria;

public class SeriaIntervalConstraint extends Constraint {

    private Integer minDayCountParam;

    public SeriaIntervalConstraint(Boolean keepScoreElement) {
        super(6L, "SeriaInterval", true, 2, keepScoreElement);
        minDayCountParam = 2;
    }

    public void insert(Team team, Seria seria, Seria prevSeria) {

        if (active == true && prevSeria != null) {

            Integer dayCount = seria.getFirstMatch().getDay().getIndex() -
                    prevSeria.getLastMatch().getDay().getIndex() - 1;

            if (dayCount < minDayCountParam) {
                this.setPenalty(penaltyValue,seria, prevSeria);
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

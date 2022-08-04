package com.score;

import java.util.ArrayList;
import java.util.List;

import com.domain.ConstraintSettings;
import com.score.teamConstraints.Constraint;

public class CustomScoreManager {

    protected List<Constraint> constraintList;
    protected List<ConstraintSettings> defaultConsraintSettingsList;
    protected Integer hardScore;
    protected Integer softScore;

    public CustomScoreManager() {
        hardScore = 0;
        softScore = 0;
        constraintList = new ArrayList<>();
    }

    public void resetConstraints() {
        for (Constraint constraint : constraintList) {
            constraint.reset();
        }
        hardScore = 0;
        softScore = 0;
    }

    public void disableConstraints() {
        for (Constraint constraint : constraintList) {
            constraint.setActive(false);
        }
    }

    public Constraint getConstraint(Long id) {
        Constraint resultConstraint = null;

        for (Constraint constraint : constraintList) {
            if (constraint.getId() == id) {
                resultConstraint = constraint;
            }
        }

        return resultConstraint;
    }

    public void setConstraintSettingsList(List<ConstraintSettings> constraintSettingsList) {

        disableConstraints();

        for (ConstraintSettings constraintSettings : constraintSettingsList) {
            Constraint constraint = getConstraint(constraintSettings.getId());

            if (constraint != null) {
                constraint.setConstraintSettings(constraintSettings);
            }
        }
    }

    public List<ConstraintSettings> getConstraintSettingsList() {
        List<ConstraintSettings> constraintSettingsList = new ArrayList<>();

        for (Constraint constraint : constraintList) {
            if (constraint.getActive()) {
                constraintSettingsList.add(constraint.getConstraintSettings());
            }
        }

        return constraintSettingsList;
    }

    public List<ConstraintSettings> getDefaultConsraintSettingsList() {
        return defaultConsraintSettingsList;
    }

    public Integer getSoftScore() {
        return softScore;
    }

    public Integer getHardScore() {
        hardScore = 0;

        for (Constraint constraint : constraintList) {
            hardScore += constraint.getScoreValue();
        }

        return hardScore;
    }

    public List<Constraint> getConstraintList() {
        return constraintList;
    }

}
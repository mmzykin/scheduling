package com.score;

import java.util.ArrayList;
import java.util.List;

import com.domain.ConstraintSettings;
import com.domain.Match;
import com.domain.Team;
import com.score.constraintModel.ForgivenScores;
import com.score.teamConstraints.*;
import com.solver.seria.Seria;

public class TeamScoreManager {

    private ForgivenScores forgivenScores;
    private AwaySeriaMatchIntervalConstraint awaySeriaMatchIntervalConstraint;
    private AwaySeriaRouteConstraint awaySeriaRouteConstraint;
    private HomeSeriaMatchIntervalConstraint homeSeriaMatchIntervalConstraint;
    private SameRivalSequenceConstraint sameRivalSequenceConstraint;
    private SeriaIntervalConstraint seriaIntervalConstraint;
    private SeriaLengthConstraint seriaLengthConstraint;
    private ConsecutiveAwaySeriesConstraint consecutiveAwaySeriesConstraint;

    private List<Constraint> constraintList;
    private List<ConstraintSettings> defaultConsraintSettingsList;
    private Integer hardScore;
    private Integer softScore;

    public TeamScoreManager(Boolean keepScoreElement) {

        forgivenScores = new ForgivenScores();
        
        hardScore = 0;
        softScore = 0;

        awaySeriaMatchIntervalConstraint = new AwaySeriaMatchIntervalConstraint(keepScoreElement);
        awaySeriaRouteConstraint = new AwaySeriaRouteConstraint(keepScoreElement);
        homeSeriaMatchIntervalConstraint = new HomeSeriaMatchIntervalConstraint(keepScoreElement);
        sameRivalSequenceConstraint = new SameRivalSequenceConstraint(keepScoreElement);
        seriaIntervalConstraint = new SeriaIntervalConstraint(keepScoreElement);
        seriaLengthConstraint = new SeriaLengthConstraint(keepScoreElement, forgivenScores);
        consecutiveAwaySeriesConstraint = new ConsecutiveAwaySeriesConstraint(keepScoreElement, forgivenScores);

        constraintList = new ArrayList<>();
        constraintList.add(awaySeriaMatchIntervalConstraint);
        constraintList.add(awaySeriaRouteConstraint);
        constraintList.add(homeSeriaMatchIntervalConstraint);
        constraintList.add(sameRivalSequenceConstraint);
        constraintList.add(seriaIntervalConstraint);
        constraintList.add(seriaLengthConstraint);
        constraintList.add(consecutiveAwaySeriesConstraint);

        defaultConsraintSettingsList = getConstraintSettingsList();
    }

    public void resetConstraints() {
        forgivenScores.clear();
        
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

    public void calculate(Team team, List<Seria> seriaList, Match lastMatch) {

        Seria prevSeria = null;
        Match prevMatch = null;

        for (Seria seria : seriaList) {

            awaySeriaRouteConstraint.insert(team, seria);
            seriaIntervalConstraint.insert(team, seria, prevSeria);
            consecutiveAwaySeriesConstraint.insert(team, seria, prevSeria);
            seriaLengthConstraint.insert(team, seria);
            

            softScore += seria.getDistance();

            for (Match match : seria.getMatchList()) {

                awaySeriaMatchIntervalConstraint.insert(team, seria, match, prevMatch);
                homeSeriaMatchIntervalConstraint.insert(team, seria, match, prevMatch);
                sameRivalSequenceConstraint.insert(team, match, prevMatch);

                prevMatch = match;
            }
            prevSeria = seria;
        }
        
        forgivenScores.processCandidate();
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

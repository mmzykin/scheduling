package com.score;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import com.domain.ConstraintSettings;
import com.domain.LogisticGroup;
import com.domain.Team;
import com.domain.TeamAssignment;
import com.domain.TravelingTournament;
import com.score.constraintModel.Score;
import com.score.constraintModel.ScoreElement;
import com.score.teamConstraints.AwaySeriaRouteConstraint;
import com.score.teamConstraints.Constraint;
import com.solver.seria.SeriaFactory;

public class MajorScoreManager {

    private TeamScoreManager teamScoreManager;
    //private ArenaScoreManager arenaScoreManager;

    public MajorScoreManager(Boolean keepScoreElement) {
        teamScoreManager = new TeamScoreManager(keepScoreElement);
        //arenaScoreManager = new ArenaScoreManager(keepScoreElement);

        //setConstraintSettingsList(solution.getConstraintSettingsList());

    }

    public void setLogisticGroups(List<LogisticGroup> logisticGroups) {
        for (Constraint constraint : getConstraintList()) {

            if (constraint.getName().equals("AwaySeriaRoute")) {
                ((AwaySeriaRouteConstraint) constraint).setLogisticGroups(logisticGroups);
            }
        }
    }

    public void setConstraintSettingsList(List<ConstraintSettings> constraintSettingsList) {
        teamScoreManager.setConstraintSettingsList(constraintSettingsList);
        //arenaScoreManager.setConstraintSettingsList(constraintSettingsList);
    }

    public List<ConstraintSettings> getConstraintSettingsList() {
        List<ConstraintSettings> teamConstraintSettingsList = teamScoreManager.getConstraintSettingsList();
        //List<ConstraintSettings> arenaConstraintSettingsList = arenaScoreManager.getConstraintSettingsList();
        List<ConstraintSettings> resultConstraintSettingsList = new ArrayList<>();
        resultConstraintSettingsList.addAll(teamConstraintSettingsList);
        //resultConstraintSettingsList.addAll(arenaConstraintSettingsList);
        return resultConstraintSettingsList;
    }

    public List<ConstraintSettings> getDefaultConstraintSettingsList() {
        List<ConstraintSettings> teamConstraintSettingsList = teamScoreManager.getDefaultConsraintSettingsList();
        //List<ConstraintSettings> arenaConstraintSettingsList = arenaScoreManager.getDefaultConsraintSettingsList();
        List<ConstraintSettings> resultConstraintSettingsList = new ArrayList<>();
        resultConstraintSettingsList.addAll(teamConstraintSettingsList);
        //resultConstraintSettingsList.addAll(arenaConstraintSettingsList);
        return resultConstraintSettingsList;
    }

    public List<Constraint> getConstraintList() {
        List<Constraint> teamConstraintList = teamScoreManager.getConstraintList();
        //List<Constraint> arenaConstraintList = arenaScoreManager.getConstraintList();
        List<Constraint> resultConstraintList = new ArrayList<>();
        resultConstraintList.addAll(teamConstraintList);
        //resultConstraintList.addAll(arenaConstraintList);

        return resultConstraintList;
    }

    public void resetConstraints() {
        teamScoreManager.resetConstraints();
        //arenaScoreManager.resetConstraints();
    }

    public HardSoftScore calculateTeamConstraints(TeamAssignment teamAssignmnet) {
        SeriaFactory seriaFactory = new SeriaFactory();
        seriaFactory.create(teamAssignmnet);
        teamScoreManager.resetConstraints();
        teamScoreManager.calculate(seriaFactory.getTeam(), seriaFactory.getSeriaList(), seriaFactory.getLastMatch());

        return HardSoftScore.of(
                -teamScoreManager.getHardScore(),
                -teamScoreManager.getSoftScore());
    }

    /*
     * public Integer calculateAreaConstraints(ArenaAssignment arenaAssignment) {
     * arenaScoreManager.resetConstraints();
     * arenaScoreManager.calculate(arenaAssignment);
     * return -arenaScoreManager.getHardScore();
     * }
     */
    public List<Score> getScoreList(TravelingTournament solution) {

        Map<String, Score> newScoreMap = new LinkedHashMap<>();
        List<Score> newScoreList = new ArrayList<>();
        Score newScore = null;

        for (TeamAssignment teamAssignmnet : solution.getTeamAssignmentList()) {

            calculateTeamConstraints(teamAssignmnet);

            for (Constraint constraint : getConstraintList()) {

                Score score = constraint.getScore();

                if (score.getScoreValue() > 0) {
                    String scoreName = score.getName();
                    if (!newScoreMap.containsKey(scoreName)) {
                        newScore = new Score(scoreName);
                        newScoreMap.put(scoreName, newScore);
                    } else {
                        newScore = newScoreMap.get(scoreName);
                    }

                    newScore.addScoreValue(score.getScoreValue());

                    for (ScoreElement scoreElement : score.getScoreElementList()) {
                        newScore.SetScoreElement(scoreElement);
                    }
                }
            }
        }

        if (newScoreMap.size() > 0) {
            for (Score score : newScoreMap.values()) {
                newScoreList.add(score);
            }
        }

        return newScoreList;

    }

}

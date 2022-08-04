package com.score;

import java.util.HashMap;
import java.util.Map;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.calculator.IncrementalScoreCalculator;

import com.domain.LogisticGroup;
import com.domain.TeamAssignment;
import com.domain.TravelingTournament;
import com.score.teamConstraints.AwaySeriaRouteConstraint;
import com.score.teamConstraints.Constraint;

public class TravelingTournamentIncrementalScoreCalculator
        implements IncrementalScoreCalculator<TravelingTournament, HardSoftScore> {

    private MajorScoreManager majorScoreManager;
    private Map<Long, HardSoftScore> teamIdScoreMap;
    //private Integer arenaHardScore;

    @Override
    public void resetWorkingSolution(TravelingTournament solution) {

        Boolean keepScoreElement = false;

        majorScoreManager = new MajorScoreManager(keepScoreElement);
    //    majorScoreManager.setConstraintSettingsList(solution.getConstraintSettingsList());
        majorScoreManager.setLogisticGroups(solution.getLogisticGroupList());

        teamIdScoreMap = new HashMap<>();

        for (TeamAssignment teamAssignmnet : solution.getTeamAssignmentList()) {
            HardSoftScore hardSoftScore = majorScoreManager.calculateTeamConstraints(teamAssignmnet);
            teamIdScoreMap.put(teamAssignmnet.getId(), hardSoftScore);
        }

        //arenaHardScore = majorScoreManager.calculateAreaConstraints(solution.getArenaAssignment());
    }

    @Override
    public void beforeEntityAdded(Object entity) {
        // Do nothing
    }

    @Override
    public void afterEntityAdded(Object entity) {
        // Do nothing
    }

    @Override
    public void beforeVariableChanged(Object entity, String variableName) {
        /*
         * if (entity instanceof NewTeamAssignment) {
         * 
         * }
         */
    }

    @Override
    public void afterVariableChanged(Object entity, String variableName) {

        if (entity instanceof TeamAssignment) {
            HardSoftScore hardSoftScore = majorScoreManager.calculateTeamConstraints((TeamAssignment) entity);
            teamIdScoreMap.put(((TeamAssignment) entity).getId(), hardSoftScore);
        }
        /*
         * else if(entity instanceof ArenaAssignment) {
         * arenaHardScore = majorScoreManager.calculateAreaConstraints((ArenaAssignment) entity);
         * }
         */
    }

    @Override
    public void beforeEntityRemoved(Object entity) {

        // Do nothing
    }

    @Override
    public void afterEntityRemoved(Object entity) {
        // Do nothing
    }

    @Override
    public HardSoftScore calculateScore() {

        Integer curHardScore = 0;
        Integer curSoftScore = 0;

        for (HardSoftScore hsScore : teamIdScoreMap.values()) {
            curHardScore += hsScore.getHardScore();
            curSoftScore += hsScore.getSoftScore();
        }

        //curHardScore += arenaHardScore;

        return HardSoftScore.of(curHardScore, curSoftScore);
    }
}

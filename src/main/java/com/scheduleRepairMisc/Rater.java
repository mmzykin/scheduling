package com.scheduleRepairMisc;

import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;

import com.domain.LogisticGroup;
import com.domain.TeamAssignment;
import com.domain.TravelingTournament;
import com.score.MajorScoreManager;

public class Rater {
    
    private MajorScoreManager majorScoreManager;
    
    public Rater(List<LogisticGroup> logisticGroupList) {
        majorScoreManager = new MajorScoreManager(false);
        majorScoreManager.setLogisticGroups(logisticGroupList);
    }
    
    public HardSoftScore calculateScore(TravelingTournament solution) {
        Integer hardScore = 0;
        Integer softScore = 0;
        
        for (TeamAssignment teamAssignmnet : solution.getTeamAssignmentList()) {
            HardSoftScore hardSoftScore = majorScoreManager.calculateTeamConstraints(teamAssignmnet);
            
            hardScore += hardSoftScore.getHardScore();
            softScore += hardSoftScore.getSoftScore();
        }
        
        return HardSoftScore.of(hardScore, softScore);
    }
    
}

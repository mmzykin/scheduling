package com.score.constraintModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ForgivenScores {

    private HashMap<Integer, List<ScoreElement>>  candidatesByGroupIdMap;
    
    public ForgivenScores() {
        candidatesByGroupIdMap = new HashMap<Integer, List<ScoreElement>>();
    }
    
    public void setCandidate(int groupId, ScoreElement scoreElement) {
        
        if(!candidatesByGroupIdMap.containsKey(groupId)) {
            candidatesByGroupIdMap.put(groupId, new ArrayList<>());
        }
        
        candidatesByGroupIdMap.get(groupId).add(scoreElement);
    }
    
    public void processCandidate() {
        if(candidatesByGroupIdMap.size() > 0) {
           
            for (List<ScoreElement> scoreElementList : candidatesByGroupIdMap.values()) {
                
                int seriaLengthIndex = -1;
                int consAwaySeriesIndex = -1;
                
                 for(int i = 0; i < scoreElementList.size(); i++) {
                     
                     String constraintName = scoreElementList.get(i).getMainScore().getName();
                     
                     if(seriaLengthIndex == -1 && constraintName.equals("SeriaLength")) {
                         seriaLengthIndex = i;
                     }
                     else if(consAwaySeriesIndex == -1 && constraintName.equals("ConsecutiveAwaySeriesConstraint")) {
                         consAwaySeriesIndex = i;
                     }
                     else {
                         break;
                     }
                 }
                 
                 int resultIndex = consAwaySeriesIndex>-1?consAwaySeriesIndex:seriaLengthIndex;
                 
                 if(resultIndex > -1) {
                     ScoreElement scoreElement = scoreElementList.get(resultIndex);
                     scoreElement.getMainScore().removeScoreElement(scoreElement);
                     scoreElement.getMainScore().addScoreValue(scoreElement.getScoreValue()*-1);
                     scoreElementList.clear();
                     scoreElementList.add(scoreElement);  
                 }
            }
        }
    }
    
    public void clear() {
        if(candidatesByGroupIdMap.size() > 0) {
            candidatesByGroupIdMap.clear();
        }
    }
    
}
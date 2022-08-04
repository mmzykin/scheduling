package com.score.constraintModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.domain.Day;
import com.domain.Match;
import com.domain.Period;

import lombok.Getter;
import lombok.Setter;

public class ScoreElement {

    public int scoreValue;
    public List<Match> matchList;
    @Getter @Setter  
    private Score mainScore;
    
    public ScoreElement() {
        matchList = new ArrayList<>();
        scoreValue = 0;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public void addScoreValue(Integer scoreValue) {
        this.scoreValue += scoreValue;
    }
    
    
    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public void appendMatchList(List<Match> matchList) {
        this.matchList.addAll(matchList);
    }

    public void setMatch(Match match) {
        this.matchList.add(match);
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void clear() {
        matchList.clear();
        scoreValue = 0;
    }
   
   
}
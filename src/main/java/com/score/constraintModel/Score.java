package com.score.constraintModel;



import java.util.ArrayList;
import java.util.List;
import com.domain.Match;


public class Score {
    private String name;
    private Integer scoreValue;
    private List<ScoreElement> scoreElementList;

    public Score(String name) {
        this.name = name;
        scoreValue = 0;
        scoreElementList = new ArrayList<ScoreElement>();
    }

    public void reset() {
        scoreValue = 0;
        if (scoreElementList != null) {
            scoreElementList.clear();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScoreValue(Integer scoreValue) {
        this.scoreValue = scoreValue;
    }

    public void addScoreValue(Integer scoreValue) {
        this.scoreValue += scoreValue;
    }

    /*
    public void SetScoreElementList(List<ScoreElement> scoreElementList) {
        this.scoreElementList = scoreElementList;
    }
    */

    public void SetScoreElement(ScoreElement scoreElement) {
        scoreElement.setMainScore(this);
        scoreElementList.add(scoreElement);
    }

    public boolean removeScoreElement (ScoreElement scoreElement) {
        return scoreElementList.remove(scoreElement);
    }
    
    
    /*
    public void SetScoreElement(Integer scoreValue, Match match, Match prevMatch) {
        ScoreElement scoreElement = new ScoreElement();
        scoreElement.setMainScore(this);
        scoreElement.setMatch(prevMatch);
        scoreElement.setMatch(match);
        scoreElement.addScoreValue(scoreValue);
        scoreElementList.add(scoreElement);
    }
    */
    

    public String getName() {
        return name;
    }

    public Integer getScoreValue() {
        return scoreValue;
    }

    public List<ScoreElement> getScoreElementList() {
        return scoreElementList;
    }

}
package com.score.teamConstraints;

import java.util.ArrayList;
import java.util.List;

import com.domain.ConstraintSettings;
import com.domain.Match;
import com.score.constraintModel.ForgivenScores;
import com.score.constraintModel.Score;
import com.score.constraintModel.ScoreElement;
import com.solver.seria.Seria;

public class Constraint {

    protected Long id;
    protected String name;
    protected Boolean active;
    protected Integer penaltyValue;

    protected Score score;
    protected boolean keepScoreElement;
    //protected Integer curScoreValue;
    private ForgivenScores forgivenScores;

    public Constraint(Long id, String name, Boolean active, Integer penaltyValue, boolean keepScoreElement) {

        this.id = id;
        this.name = name;
        this.active = active;
        this.penaltyValue = penaltyValue;
        this.keepScoreElement = keepScoreElement;
        score = new Score(name);
        //curScoreValue = 0;
        //this.forgivenScores = forgivenScores;
    }

    public void setConstraintSettings(ConstraintSettings constraintSettings) {
        active = constraintSettings.getActive();
        penaltyValue = constraintSettings.getPenaltyValue();
    }

    public ConstraintSettings getConstraintSettings() {
        ConstraintSettings constraintSettings = new ConstraintSettings();
        constraintSettings.setId(id);
        constraintSettings.setName(name);
        constraintSettings.setActive(active);
        constraintSettings.setPenaltyValue(penaltyValue);
        return constraintSettings;

    }

    public void reset() {
        //curScoreValue = 0;
        score.reset();
    }

    /*
     * public void insert(Seria seria) {
     * 
     * }
     * 
     * public void insert(Match match) {
     * 
     * }
     * 
     * public void insert(Match match, Seria seria) {
     * 
     * }
     */


    
    
    public void setPenalty(Integer penalty, Match match, Match prevMatch) {
        score.addScoreValue(penalty);
        
        if (keepScoreElement && penalty > 0) {
            ScoreElement scoreElement = new ScoreElement();
            scoreElement.addScoreValue(penalty);    
            if (prevMatch != null) {
                scoreElement.setMatch(prevMatch);
            }
            scoreElement.setMatch(match);
            score.SetScoreElement(scoreElement);    
        }
    }
    
    public void setPenalty(Integer penalty, Seria seria, Seria prevSeria) {
        score.addScoreValue(penalty);
        
        if (keepScoreElement && penalty > 0) {
            ScoreElement scoreElement = new ScoreElement();
            scoreElement.addScoreValue(penalty);
            if (prevSeria != null) {
                scoreElement.appendMatchList(prevSeria.getMatchList());
            }
            scoreElement.appendMatchList(seria.getMatchList());
            score.SetScoreElement(scoreElement);
        }
    }
    
    
    public void setPenalty(Integer penalty, List<Match> matchList) {
        score.addScoreValue(penalty);
        
        if (keepScoreElement && penalty > 0) {
            ScoreElement scoreElement = new ScoreElement();
            scoreElement.addScoreValue(penalty);
            scoreElement.setMatchList(matchList);
            score.SetScoreElement(scoreElement);
        }
    }
    
    
    /*
    public void addScoreValue(Integer scoreValue) {
        score.addScoreValue(scoreValue);
        curScoreValue += scoreValue;
    }

    public void setScoreValue(Integer scoreValue) {
        score.addScoreValue(scoreValue);
        curScoreValue = scoreValue;
    }
*/
    
    
    /*
    public Boolean setScoreElement(Match match, Match prevMatch) {

        if (keepScoreElement && curScoreValue > 0) {
            ScoreElement scoreElement = new ScoreElement();
            if (prevMatch != null) {
                scoreElement.setMatch(prevMatch);
            }
            scoreElement.setMatch(match);
            scoreElement.addScoreValue(curScoreValue);
            score.SetScoreElement(scoreElement);
            curScoreValue = 0;
            return true;
        }
        return false;
    }

    public Boolean setScoreElement(Seria seria, Seria prevSeria) {

        if (keepScoreElement && curScoreValue > 0) {
            ScoreElement scoreElement = new ScoreElement();
            if (prevSeria != null) {
                scoreElement.appendMatchList(prevSeria.getMatchList());
            }
            scoreElement.appendMatchList(seria.getMatchList());
            scoreElement.addScoreValue(curScoreValue);
            score.SetScoreElement(scoreElement);
            curScoreValue = 0;
            return true;
        }
        return false;
    }

    public Boolean setScoreElement(List<Match> matchList) {

        if (keepScoreElement && curScoreValue > 0) {
            ScoreElement scoreElement = new ScoreElement();
            scoreElement.setMatchList(matchList);
            scoreElement.addScoreValue(curScoreValue);
            score.SetScoreElement(scoreElement);
            curScoreValue = 0;
            return true;
        }
        return false;
    }
*/
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getPenaltyValue() {
        return penaltyValue;
    }

    public Score getScore() {
        return score;
    }

    public String getScoreName() {
        return score.getName();
    }

    public Integer getScoreValue() {
        return score.getScoreValue();
    }

    public List<ScoreElement> getScoreElementList() {
        return score.getScoreElementList();
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String print() {

        String delim = "\n";
        String res;
        if (score.getScoreValue() > 0) {
            res = score.getName() + " score " + score.getScoreValue() + delim;
            List<ScoreElement> scoreElementList = score.getScoreElementList();
            if (scoreElementList != null) {
                for (ScoreElement scoreElement : scoreElementList) {
                    res += "score " + scoreElement.getScoreValue() + " - ";
                    for (Match match : scoreElement.getMatchList()) {
                        res += match.getDay().getIndex() + " ";
                    }
                    res += "\n";
                }
            }
        } else {
            res = "";
        }
        return res;
    }

}
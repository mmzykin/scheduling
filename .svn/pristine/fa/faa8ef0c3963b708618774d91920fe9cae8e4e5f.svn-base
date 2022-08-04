package com.score.teamConstraints;

import com.domain.ConstraintSettings;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.Team;
import com.score.constraintModel.ForgivenScores;
import com.score.constraintModel.ScoreElement;
import com.solver.seria.Seria;

public class ConsecutiveAwaySeriesConstraint extends Constraint {
    
    private ForgivenScores forgivenScores;
    private int exc1ParamMinConsecutiveHomeNa;
    private int exc1ParamMinIntervalBetweenSeries;
    private int exc2ParamMinIntervalBetweenSeries;
    private int exc2ParamMinLeftConsecutiveHomeNa;
    private int exc2ParamMinRightConsecutiveHomeNa;
    private int exc3ParamMinIntervalBetweenSeries = 7;
     
    public ConsecutiveAwaySeriesConstraint(Boolean keepScoreElement, ForgivenScores forgivenScores) {
        super(8L, "ConsecutiveAwaySeriesConstraint", true, 1, keepScoreElement);
        this.forgivenScores = forgivenScores;
        
        exc1ParamMinConsecutiveHomeNa = 13;
        exc1ParamMinIntervalBetweenSeries = 3;
        exc2ParamMinIntervalBetweenSeries = 3;
        exc2ParamMinLeftConsecutiveHomeNa = 5;
        exc2ParamMinRightConsecutiveHomeNa = 5;
        exc3ParamMinIntervalBetweenSeries = 7;
    }

    public void insert(Team team, Seria seria, Seria prevSeria) {
        
        if (active == true && seria.getSeriaType() == MatchType.AWAY && prevSeria != null && prevSeria.getSeriaType() == MatchType.AWAY) {
            
            
            boolean penalyzeStatus = true;
            int intervalBtwSeries = seria.getFirstMatch().getDay().getIndex() - prevSeria.getLastMatch().getDay().getIndex() - 1;
            int nGamingIntervalBtwSeries = (seria.getFirstMatch().getDay().getIndex() - seria.getFirstMatch().getDay().getActingIndex()) -
                                                   (prevSeria.getLastMatch().getDay().getIndex() - prevSeria.getLastMatch().getDay().getActingIndex()); 
                    
            if(nGamingIntervalBtwSeries == 0 && 
               exc1ParamMinIntervalBetweenSeries <= intervalBtwSeries && 
               team.checkHomeNaCondition(prevSeria.getFirstMatch().getDay(), seria.getLastMatch().getDay(), exc1ParamMinConsecutiveHomeNa, 1.0) == true) {
                //exc1ParamMinConsecutiveHomeNa <= team.getLenConsecutiveHomeNA(prevSeria.getFirstMatch().getDay(), seria.getLastMatch().getDay())) {
                penalyzeStatus = false;
            }
            else if(exc2ParamMinIntervalBetweenSeries <= nGamingIntervalBtwSeries &&
                    team.checkHomeNaCondition(prevSeria.getFirstMatch().getDay(), prevSeria.getLastMatch().getDay(), exc2ParamMinLeftConsecutiveHomeNa, 0.5) == true &&
                    team.checkHomeNaCondition(seria.getFirstMatch().getDay(), seria.getLastMatch().getDay(), exc2ParamMinRightConsecutiveHomeNa, 0.5) == true) {
                    //exc2ParamMinLeftConsecutiveHomeNa <= team.getLenConsecutiveHomeNA(prevSeria.getFirstMatch().getDay(), prevSeria.getLastMatch().getDay()) &&
                    //exc2ParamMinRightConsecutiveHomeNa <= team.getLenConsecutiveHomeNA(seria.getFirstMatch().getDay(), seria.getLastMatch().getDay())) {
                penalyzeStatus = false;
            }
            else if(exc3ParamMinIntervalBetweenSeries <= nGamingIntervalBtwSeries) {
                penalyzeStatus = false;
            }
            

            if(penalyzeStatus) {
                this.setPenalty(penaltyValue, seria, prevSeria);
            }
            else {
                setExceptionalPenalty(penaltyValue, seria, prevSeria);
            }
        }
    }
    
    
    public void setExceptionalPenalty(Integer penalty, Seria seria, Seria prevSeria) {
        boolean doSwitch = keepScoreElement?false:true;
        keepScoreElement = true;
        
        this.setPenalty(penalty, seria, prevSeria);
        ScoreElement scoreElement =  score.getScoreElementList().get(score.getScoreElementList().size()-1);
        forgivenScores.setCandidate(seria.getMatchTypeSequenceId(), scoreElement);
        
        keepScoreElement = doSwitch?false:true;
    }
    
    
    @Override 
    public void setConstraintSettings(ConstraintSettings constraintSettings) {
        super.setConstraintSettings(constraintSettings);
        
        exc1ParamMinConsecutiveHomeNa = Integer.parseInt(constraintSettings.getProperty("exc1ParamMinConsecutiveHomeNa"));
        exc1ParamMinIntervalBetweenSeries = Integer.parseInt(constraintSettings.getProperty("exc1ParamMinIntervalBetweenSeries"));
        exc2ParamMinIntervalBetweenSeries = Integer.parseInt(constraintSettings.getProperty("exc2ParamMinIntervalBetweenSeries"));
        exc2ParamMinLeftConsecutiveHomeNa = Integer.parseInt(constraintSettings.getProperty("exc2ParamMinLeftConsecutiveHomeNa"));
        exc2ParamMinRightConsecutiveHomeNa = Integer.parseInt(constraintSettings.getProperty("exc2ParamMinRightConsecutiveHomeNa"));
    }
    
    @Override
    public ConstraintSettings getConstraintSettings() {
        
        ConstraintSettings constraintSettings = super.getConstraintSettings();
        constraintSettings.setProperty("exc1ParamMinConsecutiveHomeNa", exc1ParamMinConsecutiveHomeNa);
        constraintSettings.setProperty("exc1ParamMinIntervalBetweenSeries", exc1ParamMinIntervalBetweenSeries);
        constraintSettings.setProperty("exc2ParamMinIntervalBetweenSeries", exc2ParamMinIntervalBetweenSeries);
        constraintSettings.setProperty("exc2ParamMinLeftConsecutiveHomeNa", exc2ParamMinLeftConsecutiveHomeNa);
        constraintSettings.setProperty("exc2ParamMinRightConsecutiveHomeNa", exc2ParamMinRightConsecutiveHomeNa);
        return constraintSettings;
    }
    
    
    @Override 
    public void reset() {
        super.reset();
    }
    
}
package com.persistence.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class SolveProperty {

    @JsonInclude(Include.NON_EMPTY)
    private LocalDateTime processed;

    @JsonInclude(Include.NON_EMPTY)
    private LocalDateTime lastImproved;

    @JsonInclude(Include.NON_EMPTY)
    private SolvingState solvingState;

    @JsonInclude(Include.NON_EMPTY)
    private Long timeSpent;

    @JsonInclude(Include.NON_EMPTY)
    private int stepIndex;

    private String score;

    @JsonInclude(Include.NON_EMPTY)
    private String bestScore;

    @JsonInclude(Include.NON_EMPTY)
    Long selectedMoveCount;

    @JsonInclude(Include.NON_EMPTY)
    Long acceptedMoveCount;

    @JsonInclude(Include.NON_EMPTY)
    private String stepString;

    public void reset() {
        timeSpent = null;
        stepIndex = 0;
        score = null;
        bestScore = null;
        selectedMoveCount = null;
        acceptedMoveCount = null;
        processed = null;
        solvingState = SolvingState.INIT;
    }

    public void setProcessed(LocalDateTime processed) {
        this.processed = processed;
    }

    public LocalDateTime getProcessed() {
        return processed;
    }

    public void setLastImproved(LocalDateTime lastImproved) {
        this.lastImproved = lastImproved;
    }

    public LocalDateTime getLastImproved() {
        return lastImproved;
    }

    public void setSolvingState(SolvingState solvingState) {
        this.solvingState = solvingState;
    }

    public SolvingState getSolvingState() {
        return solvingState;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setStepIndex(int stepIndex) {
        this.stepIndex = stepIndex;
    }

    public int getStepIndex() {
        return stepIndex;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScore() {
        return score;
    }

    public void setBestScore(String bestScore) {
        this.bestScore = bestScore;
    }

    public String getBestScore() {
        return bestScore;
    }

    public void setSelectedMoveCount(Long selectedMoveCount) {
        this.selectedMoveCount = selectedMoveCount;
    }

    public Long getSelectedMoveCount() {
        return selectedMoveCount;
    }

    public void setAcceptedMoveCount(Long acceptedMoveCount) {
        this.acceptedMoveCount = acceptedMoveCount;
    }

    public Long getAcceptedMoveCount() {
        return acceptedMoveCount;
    }

    public void setStepString(String stepString) {
        this.stepString = stepString;
    }

    public String getStepString() {
        return stepString;
    }
}

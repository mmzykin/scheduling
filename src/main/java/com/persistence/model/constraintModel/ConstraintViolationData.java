package com.persistence.model.constraintModel;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ConstraintViolationData {

    public int score;
    @JacksonXmlElementWrapper(localName = "matchesId")
    @JacksonXmlProperty(localName = "matchId")
    public List<Long> matchesId;

    public ConstraintViolationData(int score, List<Long> matchesId) {
        this.score = score;
        this.matchesId = matchesId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMatchesId(List<Long> MatchesId) {
        this.matchesId = MatchesId;
    }

    public int getScore() {
        return score;
    }

    public List<Long> getMatchesId() {
        return matchesId;
    }

}
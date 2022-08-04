package com.app;

import com.domain.Day;
import com.domain.Match;

public class MatchDayDiffCount {
    Match match;
    Day day;
    Integer dayDiff;
    public Integer countVars;
    //constructor
    public MatchDayDiffCount(Match match, Day day, Integer dayDiff, Integer countVars){
        this.match = match;
        this.day = day;
        this.dayDiff = dayDiff;
        this.countVars = countVars;
    }
    public MatchDayDiffCount(){
        this.match = null;
        this.day = null;
        this.dayDiff = 0;
        this.countVars = Integer.MAX_VALUE;
    }
    //getters and setters
    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public Day getDay() {
        return day;
    }
    public void setDay(Day day) {
        this.day = day;
    }
    public Integer getDayDiff() {
        return dayDiff;
    }
    public void setDayDiff(Integer dayDiff) {
        this.dayDiff = dayDiff;
    }
    public Integer getCountVars() {
        return countVars;
    }
    public void setCountVars(Integer countVars) {
        this.countVars = countVars;
    }

}


package com.persistence.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class MatchData {
    private Long id;
    private Long homeTeamId;
    private Long awayTeamId;

    @JsonInclude(Include.NON_EMPTY)
    private Integer fixed;

    @JsonInclude(Include.NON_EMPTY)
    private LocalDate date;

    @SuppressWarnings("unused")
    public MatchData() {
    }

    public MatchData(Long id, Long homeTeamId, Long awayTeamId, LocalDate date, boolean fixingState) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.date = date;
        if (fixingState == true) {
            this.fixed = 1;
        }
    }

    public Long getId() {
        return id;
    }

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getFixed() {
        return fixed;
    }

}

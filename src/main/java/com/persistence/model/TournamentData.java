package com.persistence.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.persistence.model.constraintModel.ConstraintData;

@JacksonXmlRootElement(localName = "tournament")
public class TournamentData {
    public Long id;
    @JsonInclude(Include.NON_EMPTY)
    public SeasonPeriodData seasonPeriod;

    @JacksonXmlElementWrapper(localName = "teams")
    @JacksonXmlProperty(localName = "team")
    @JsonInclude(Include.NON_EMPTY)
    public List<TeamData> teams;

    @JacksonXmlElementWrapper(localName = "routes")
    @JacksonXmlProperty(localName = "route")
    @JsonInclude(Include.NON_EMPTY)
    public List<RouteData> routes;

    @JacksonXmlElementWrapper(localName = "matches")
    @JacksonXmlProperty(localName = "match")
    @JsonInclude(Include.NON_EMPTY)
    public List<MatchData> matches;

    @JacksonXmlElementWrapper(localName = "logisticGroups")
    @JacksonXmlProperty(localName = "group")
    @JsonInclude(Include.NON_EMPTY)
    public List<LogisticGroupData> logisticGroups;

    @JsonInclude(Include.NON_EMPTY)
    public SolveProperty solveProperty;

    @JacksonXmlElementWrapper(localName = "constraints")
    @JacksonXmlProperty(localName = "constraint")
    @JsonInclude(Include.NON_EMPTY)
    public List<ConstraintData> constraints;

    /*
     * public TournamentData() {
     * seasonPeriod = new SeasonPeriodData();
     * }
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setSeasonPeriod(SeasonPeriodData seasonPeriod) {
        this.seasonPeriod = seasonPeriod;
    }

    public SeasonPeriodData getSeasonPeriod() {
        return seasonPeriod;
    }

    public void setTeams(List<TeamData> teams) {
        this.teams = teams;
    }

    public List<TeamData> getTeams() {
        return teams;
    }

    public void setRoutes(List<RouteData> routes) {
        this.routes = routes;
    }

    public List<RouteData> getRoutes() {
        return routes;
    }

    public void setMatches(List<MatchData> matches) {
        this.matches = matches;
    }

    public List<MatchData> getMatches() {
        return matches;
    }

    public void setLogisticGroups(List<LogisticGroupData> logisticGroups) {
        this.logisticGroups = logisticGroups;
    }

    public List<LogisticGroupData> getLogisticGroups() {
        return logisticGroups;
    }

    public void setSolveProperty(SolveProperty solveProperty) {
        this.solveProperty = solveProperty;
    }

    public SolveProperty getSolveProperty() {
        return solveProperty;
    }

    public void setConstraints(List<ConstraintData> constraints) {
        this.constraints = constraints;
    }

    public List<ConstraintData> getConstraints() {
        return constraints;
    }

}

/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.domain;

import java.util.Arrays;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;

import com.common.domain.AbstractPersistable;

@PlanningEntity
public class Match extends AbstractPersistable {

    //  @CustomShadowVariable(variableListenerClass = TeamAssignmentUpdatingVariableListener.class,
    //  sources = {@PlanningVariableReference(entityClass = Match.class, variableName = "day")})

    Long homeTeamNextMatchId;

    public Long getHomeTeamNextMatchId() {
        return homeTeamNextMatchId;
    }

    public void setHomeTeamNextMatchId(Long homeTeamNextMatchId) {
        this.homeTeamNextMatchId = homeTeamNextMatchId;
    }

    //  @CustomShadowVariable(variableListenerRef = @PlanningVariableReference(variableName = "homeTeamNextMatchId"))
    private Long awayTeamNextMatchId;

    public Long getAwayTeamNextMatchId() {
        return awayTeamNextMatchId;
    }

    public void setAwayTeamNextMatchId(Long awayTeamNextMatchId) {
        this.awayTeamNextMatchId = awayTeamNextMatchId;
    }

    private Team homeTeam;
    private Team awayTeam;
    private Day day;
    private Integer index;
    private boolean fixingState;

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @PlanningVariable(valueRangeProviderRefs = { "dayRange" })
    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public void setFixingState(boolean fixingState) {
        this.fixingState = fixingState;
    }

    public boolean getFixingState() {
        return fixingState;
    }

    public MatchType getMatchType(Team team) {
        return (homeTeam == team) ? MatchType.HOME : MatchType.AWAY;
    }

    public Team getAnotherTeam(Team team) {
        return (homeTeam == team) ? awayTeam : homeTeam;
    }

    public boolean farEasternTeam(Team team) {
        String name = team.getName();
        boolean result;

        if (name.equals("АМР") || name.equals("АДМ") || name.equals("КРС")) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    /**
     * The normal methods {@link #equals(Object)} and {@link #hashCode()} cannot be used
     * because the rule engine already requires them (for performance in their original state).
     * 
     * @see #solutionHashCode()
     */
    public boolean solutionEquals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Match) {
            Match other = (Match) o;
            return new EqualsBuilder()
                    .append(id, other.id)
                    .append(homeTeam, other.homeTeam)
                    .append(awayTeam, other.awayTeam)
                    .append(day, other.day)
                    .isEquals();
        } else {
            return false;
        }
    }

    /**
     * The normal methods {@link #equals(Object)} and {@link #hashCode()} cannot be used
     * because the rule engine already requires them (for performance in their original state).
     * 
     * @see #solutionEquals(Object)
     */
    public int solutionHashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(homeTeam)
                .append(awayTeam)
                .append(day)
                .toHashCode();
    }

    @Override
    public String toString() {
        return homeTeam + "+" + awayTeam + "+" + day.getIndex() + "+" + day.getActingIndex();
    }

    public int penalizeMatchRepeater(Match match) {
        int result = 1;

        if (match.getHomeTeam() == homeTeam &&
                (Arrays.asList("АМР", "АДМ", "КРС").contains(homeTeam.getName())
                        || Arrays.asList("АМР", "АДМ", "КРС").contains(awayTeam.getName()))) {
            result = 0;
        }

        return result;
    }

}

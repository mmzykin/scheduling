/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
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

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningScore;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty;
import org.optaplanner.core.api.domain.solution.ProblemFactProperty;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.persistence.xstream.api.score.buildin.hardsoft.HardSoftScoreXStreamConverter;

import com.common.domain.AbstractPersistable;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@PlanningSolution
public class TravelingTournament extends AbstractPersistable {

    private List<Day> dayList;
    private List<Team> teamList;
    private List<Match> matchList;
    private List<TeamAssignment> teamAssignmentList;
    private List<LogisticGroup> logisticGroupList;

    //private List<ConstraintSettings> constraintSettingsList;
    private ArenaAssignment arenaAssignment;

    @XStreamConverter(HardSoftScoreXStreamConverter.class)
    private HardSoftScore score;

    @ValueRangeProvider(id = "dayRange")
    @ProblemFactCollectionProperty
    public List<Day> getDayList() {
        return dayList;
    }

    public void setDayList(List<Day> dayList) {
        this.dayList = dayList;
    }

    @ProblemFactCollectionProperty
    public List<Team> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<Team> teamList) {
        this.teamList = teamList;
    }

    /*
     * @ProblemFactCollectionProperty
     * public List<ConstraintSettings> getConstraintSettingsList() {
     * return constraintSettingsList;
     * }
     * 
     * public void setConstraintSettingsList(List<ConstraintSettings> constraintSettingsList) {
     * this.constraintSettingsList = constraintSettingsList;
     * }
     */
    @ProblemFactCollectionProperty
    public List<TeamAssignment> getTeamAssignmentList() {
        return teamAssignmentList;
    }

    public Match findMatch(Team team, Day day) {
        Match match = null;
        for (TeamAssignment teamAssignment : teamAssignmentList) {
            if (teamAssignment.getId() == team.getId() && teamAssignment.getMatchMap().containsKey(day.getIndex())) {
                match = teamAssignment.getMatchMap().get(day.getIndex());
            }
        }

        return match;
    }

    public void setTeamAssignmentList(List<TeamAssignment> teamAssignmentList) {
        this.teamAssignmentList = teamAssignmentList;
    }

    @ProblemFactProperty
    public ArenaAssignment getArenaAssignment() {
        return arenaAssignment;
    }

    public void setArenaAssignment(ArenaAssignment arenaAssignment) {
        this.arenaAssignment = arenaAssignment;
    }

    @PlanningEntityCollectionProperty
    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchSets) {
        this.matchList = matchSets;
    }

    public List<LogisticGroup> getLogisticGroupList() {
        return logisticGroupList;
    }

    public void setLogisticGroupList(List<LogisticGroup> logisticGroupList) {
        this.logisticGroupList = logisticGroupList;
    }

    @PlanningScore
    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    public Team getTeam(int teamId) {

        for (Team team : teamList) {
            if (team.getId() == teamId) {
                return team;
            }
        }

        return null;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public int getN() {
        return teamList.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (id == null || !(o instanceof TravelingTournament)) {
            return false;
        } else {
            TravelingTournament other = (TravelingTournament) o;
            if (matchList.size() != other.matchList.size()) {
                return false;
            }
            for (Iterator<Match> it = matchList.iterator(), otherIt = other.matchList.iterator(); it.hasNext();) {
                Match match = it.next();
                Match otherMatch = otherIt.next();
                // Notice: we don't use equals()
                if (!match.solutionEquals(otherMatch)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
        for (Match match : matchList) {
            // Notice: we don't use hashCode()
            hashCodeBuilder.append(match.solutionHashCode());
        }
        return hashCodeBuilder.toHashCode();
    }

}

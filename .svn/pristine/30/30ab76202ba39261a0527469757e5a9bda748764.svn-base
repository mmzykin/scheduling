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

package com.solver;

import java.util.List;

import org.optaplanner.core.api.domain.variable.VariableListener;
import org.optaplanner.core.api.score.director.ScoreDirector;

import com.domain.ArenaAssignment;
import com.domain.Day;
import com.domain.Match;
import com.domain.TeamAssignment;
import com.domain.TravelingTournament;

//import static java.lang.Math.toIntExact;

public class TeamAssignmentUpdatingVariableListener implements VariableListener<TravelingTournament, Match> {

    @Override
    public boolean requiresUniqueEntityEvents() {
        return true;
    }

    @Override
    public void beforeEntityAdded(ScoreDirector<TravelingTournament> scoreDirector, Match match) {
        // Do nothing
    }

    @Override
    public void afterEntityAdded(ScoreDirector<TravelingTournament> scoreDirector, Match match) {
        insert(scoreDirector, match);
    }

    @Override
    public void beforeVariableChanged(ScoreDirector<TravelingTournament> scoreDirector, Match match) {
        retract(scoreDirector, match);

    }

    @Override
    public void afterVariableChanged(ScoreDirector<TravelingTournament> scoreDirector, Match match) {
        insert(scoreDirector, match);
    }

    @Override
    public void beforeEntityRemoved(ScoreDirector<TravelingTournament> scoreDirector, Match match) {
        retract(scoreDirector, match);
    }

    @Override
    public void afterEntityRemoved(ScoreDirector<TravelingTournament> scoreDirector, Match match) {
        // Do nothing
    }

    private void insert(ScoreDirector<TravelingTournament> scoreDirector, Match match) {
        TravelingTournament solution = (TravelingTournament) scoreDirector.getWorkingSolution();

        Day matchDay = match.getDay();
        if (matchDay != null) {

            List<TeamAssignment> teamAssignmentList = solution.getTeamAssignmentList();
            for (TeamAssignment teamAssignment : teamAssignmentList) {
                if (teamAssignment.getId() == match.getHomeTeam().getId()
                        || teamAssignment.getId() == match.getAwayTeam().getId()) {
                    scoreDirector.beforeVariableChanged(teamAssignment, "matchMap");
                    teamAssignment.getMatchMap().put(match.getDay().getIndex(), match);
                    scoreDirector.afterVariableChanged(teamAssignment, "matchMap");
                }
            }

        }

        if (match.getHomeTeam().getArenaId() != null && match.getHomeTeam().getArenaId() == 1L) {
            ArenaAssignment arenaAssignment = solution.getArenaAssignment();
            scoreDirector.beforeVariableChanged(arenaAssignment, "matchMap");
            arenaAssignment.setMatch(match);
            scoreDirector.afterVariableChanged(arenaAssignment, "matchMap");
        }
    }

    private void retract(ScoreDirector<TravelingTournament> scoreDirector, Match match) {

        TravelingTournament solution = (TravelingTournament) scoreDirector.getWorkingSolution();

        Day matchDay = match.getDay();
        if (matchDay != null) {

            List<TeamAssignment> teamAssignmentList = solution.getTeamAssignmentList();
            for (TeamAssignment teamAssignment : teamAssignmentList) {
                if (teamAssignment.getId() == match.getHomeTeam().getId()
                        || teamAssignment.getId() == match.getAwayTeam().getId()) {
                    scoreDirector.beforeVariableChanged(teamAssignment, "matchMap");
                    teamAssignment.getMatchMap().remove(match.getDay().getIndex());
                    scoreDirector.afterVariableChanged(teamAssignment, "matchMap");

                }
            }
        }

        if (match.getHomeTeam().getArenaId() != null && match.getHomeTeam().getArenaId() == 1L) {
            ArenaAssignment arenaAssignment = solution.getArenaAssignment();
            scoreDirector.beforeVariableChanged(arenaAssignment, "matchMap");
            arenaAssignment.removeMatch(match);
            scoreDirector.afterVariableChanged(arenaAssignment, "matchMap");
        }

    }

}

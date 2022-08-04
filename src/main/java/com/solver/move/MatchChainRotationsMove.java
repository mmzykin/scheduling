
/////////////////
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

package com.solver.move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.optaplanner.core.api.score.director.ScoreDirector;
import org.optaplanner.core.impl.heuristic.move.AbstractMove;

import com.domain.Day;
import com.domain.Match;
import com.domain.TravelingTournament;

public class MatchChainRotationsMove extends AbstractMove<TravelingTournament> {

    private List<Match> firstMatchList;
    private List<Match> secondMatchList;
    private List<Day> dayList;
    private Map<Integer, Day> nextDayMap;

    public MatchChainRotationsMove(List<Match> firstMatchList, List<Match> secondMatchList, List<Day> dayList) {
        this.firstMatchList = firstMatchList;
        this.secondMatchList = secondMatchList;
        this.dayList = dayList;

        nextDayMap = new LinkedHashMap<>();
        Iterator<Day> it = dayList.iterator();
        Day previousDay = it.next();
        Day firstDay = previousDay;
        Day day = null;

        while (it.hasNext()) {
            day = it.next();
            nextDayMap.put(previousDay.getIndex(), day);
            previousDay = day;
        }
        nextDayMap.put(previousDay.getIndex(), firstDay);
    }

    @Override
    public boolean isMoveDoable(ScoreDirector<TravelingTournament> scoreDirector) {
        return true;
    }

    @Override
    public MatchChainRotationsMove createUndoMove(ScoreDirector<TravelingTournament> scoreDirector) {

        List<Match> newFirstMatchList = new ArrayList<>(firstMatchList);
        //Collections.reverse(inverseFirstMatchList);
        List<Match> newSecondMatchList = new ArrayList<>(secondMatchList);
        //Collections.reverse(inverseSecondMatchList);
        List<Day> inverseDayList = new ArrayList<>(dayList);
        Collections.reverse(inverseDayList);
        return new MatchChainRotationsMove(newFirstMatchList, newSecondMatchList, inverseDayList);
    }

    @Override
    protected void doMoveOnGenuineVariables(ScoreDirector<TravelingTournament> scoreDirector) {
        rotateList(scoreDirector, firstMatchList);
        if (!secondMatchList.isEmpty()) { // TODO create SingleMatchListRotateMove
            rotateList(scoreDirector, secondMatchList);
        }
    }

    private void rotateList(ScoreDirector<TravelingTournament> scoreDirector, List<Match> matchList) {
        for (Match match : matchList) {
            TravelingTournamentMoveHelper.moveDay(scoreDirector, match, nextDayMap.get(match.getDay().getIndex()));
        }
    }

    @Override
    public MatchChainRotationsMove rebase(ScoreDirector<TravelingTournament> destinationScoreDirector) {
        return new MatchChainRotationsMove(rebaseList(firstMatchList, destinationScoreDirector),
                rebaseList(secondMatchList, destinationScoreDirector),
                rebaseList(dayList, destinationScoreDirector));
    }

    @Override
    public Collection<? extends Object> getPlanningEntities() {
        List<Match> entities = new ArrayList<>(firstMatchList.size() + secondMatchList.size());
        entities.addAll(firstMatchList);
        entities.addAll(secondMatchList);
        return entities;
    }

    @Override
    public Collection<? extends Object> getPlanningValues() {
        List<Day> values = new ArrayList<>(firstMatchList.size() + secondMatchList.size());
        for (Match match : firstMatchList) {
            values.add(match.getDay());
        }
        for (Match match : secondMatchList) {
            values.add(match.getDay());
        }
        return values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof MatchChainRotationsMove) {
            MatchChainRotationsMove other = (MatchChainRotationsMove) o;
            return new EqualsBuilder()
                    .append(firstMatchList, other.firstMatchList)
                    .append(secondMatchList, other.secondMatchList)
                    .append(dayList, other.dayList)
                    .isEquals();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(firstMatchList)
                .append(secondMatchList)
                .append(dayList)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Rotation " + firstMatchList + " & Rotation " + secondMatchList;
    }

}

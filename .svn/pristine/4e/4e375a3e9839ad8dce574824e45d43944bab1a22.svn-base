/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
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

import org.optaplanner.core.api.score.director.ScoreDirector;

import com.domain.Day;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.TravelingTournament;

public class TravelingTournamentMoveHelper {

    public static void moveDay(ScoreDirector<TravelingTournament> scoreDirector, Match match, Day toDay) {
        scoreDirector.beforeVariableChanged(match, "day");
        match.setDay(toDay);
        scoreDirector.afterVariableChanged(match, "day");

    }

    public static boolean checkCanShiftMatch(Match match, Day day) {

        Boolean status = true;

        if (day.getActiveFlag() == false ||
                day.getFixingState() == true ||
                match.getFixingState() == true ||
                match.getHomeTeam().getMatchType(day) == MatchType.NO_MATCH ||
                match.getHomeTeam().getMatchType(day) == MatchType.AWAY ||
                match.getAwayTeam().getMatchType(day) == MatchType.NO_MATCH) {

            status = false;
        }

        return status;
    }

    private TravelingTournamentMoveHelper() {
    }

}

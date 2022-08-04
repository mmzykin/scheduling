package com.score.teamConstraints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domain.LogisticGroup;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.Team;
import com.solver.seria.Seria;

public class AwaySeriaRouteConstraint extends Constraint {

    private List<List<Long>> teamIdListList;

    public AwaySeriaRouteConstraint(Boolean keepScoreElement) {
        super(2L, "AwaySeriaRoute", true, 2, keepScoreElement);

        teamIdListList = new ArrayList<>();
    }

    public void setLogisticGroups(List<LogisticGroup> logisticGroupList) {
        teamIdListList.clear();
        for (LogisticGroup logisticGroup : logisticGroupList) {
            teamIdListList.add(logisticGroup.getTeams());
        }
    }

    public void insert(Team team, Seria seria) {

        if (active == true && seria.getSeriaType() == MatchType.AWAY) {

            Boolean routeStatus = true;

            if (seria.getSeriaLength() < 3) {
                if ((seria.getSeriaLength() == 1 && seria.getDistance() > 2000)
                        || (seria.getSeriaLength() == 2 && seria.getDistance() > 5000)) {

                    routeStatus = false;
                }
            } else {
                //check teams

                Long AnotherTeamId;

                for (List<Long> teamIdList : teamIdListList) {

                    routeStatus = true;

                    for (Match match : seria.getMatchList()) {
                        AnotherTeamId = match.getAnotherTeam(team).getId();

                        if (!teamIdList.contains(AnotherTeamId)) {
                            routeStatus = false;
                            break;
                        }
                    }

                    if (routeStatus) {
                        break;
                    }
                }
            }

            //check route
            if (routeStatus && seria.getDistance() - seria.getOptimalDistance() > 1000) {
                routeStatus = false;
            }

            if (routeStatus == false) {
                this.setPenalty(penaltyValue, seria, null);
            }
        }
    }

}

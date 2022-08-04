package com.persistence;

import java.util.ArrayList;
import java.util.List;

import com.domain.Day;
import com.domain.LogisticGroup;
import com.domain.Match;
import com.domain.MatchType;
import com.domain.Team;
import com.domain.TravelingTournament;
import com.persistence.model.*;
import com.persistence.model.constraintModel.*;
import com.score.constraintModel.ScoreElement;
import com.score.teamConstraints.Constraint;

public class TransformSolutionToView {

    private TravelingTournament solution;
    private TournamentData view;

    public TournamentData convert(TravelingTournament solution, Boolean getOnlyResultData) {
        view = new TournamentData();
        view.setId(solution.getId());
        this.solution = solution;
        setMatches();

        if (getOnlyResultData == false) {
            setSeasonPeriod();
            setTeams();
            setRoutes();
            setLogisticGroups();
        }

        return view;
    }

    private List<ConstraintViolationData> getConstraintViolationDataList(Constraint constraint) {

        List<ConstraintViolationData> constraintViolationDataList = null;

        if (constraint.getScoreElementList().size() > 0) {

            constraintViolationDataList = new ArrayList<>();

            for (ScoreElement scoreElement : constraint.getScoreElementList()) {

                List<Long> matchesId = new ArrayList<>();
                for (Match match : scoreElement.getMatchList()) {
                    matchesId.add(match.getId());
                }
                constraintViolationDataList.add(new ConstraintViolationData(scoreElement.getScoreValue(), matchesId));
            }
        }
        return constraintViolationDataList;
    }
    /*
     * private void setConstraints(TravelingTournament solution) {
     * 
     * Map<Long, ConstraintData> constraintDataMap = new LinkedHashMap<>();
     * Boolean keepScoreElement = true;
     * MajorScoreManager majorScoreManager = new MajorScoreManager(keepScoreElement);
     * majorScoreManager.setConstraintSettingsList(solution.getConstraintSettingsList());
     * 
     * for(TeamAssignment teamAssignmnet : solution.getTeamAssignmentList()) {
     * 
     * majorScoreManager.calculateTeamConstraints(teamAssignmnet);
     * 
     * for(Constraint constraint : majorScoreManager.getConstraintList()) {
     * 
     * List<ConstraintViolationData> constraintViolationDataList = getConstraintViolationDataList(constraint);
     * 
     * if (constraintViolationDataList != null) {
     * 
     * Long constraintId = constraint.getId();
     * 
     * ConstraintData constraintData = null;
     * if(!constraintDataMap.containsKey(constraintId)) {
     * constraintData = new ConstraintData(constraint.getName());
     * constraintDataMap.put(constraintId, constraintData);
     * }
     * else {
     * constraintData = constraintDataMap.get(constraintId);
     * }
     * constraintData.addViolations(constraintViolationDataList);
     * }
     * }
     * 
     * }
     * 
     * majorScoreManager.resetConstraints();
     * 
     * majorScoreManager.calculateAreaConstraints(solution.getArenaAssignment());
     * 
     * for(Constraint constraint : majorScoreManager.getConstraintList()) {
     * 
     * List<ConstraintViolationData> constraintViolationDataList = getConstraintViolationDataList(constraint);
     * 
     * if (constraintViolationDataList != null) {
     * 
     * Long constraintId = constraint.getId();
     * 
     * ConstraintData constraintData = null;
     * if(!constraintDataMap.containsKey(constraintId)) {
     * constraintData = new ConstraintData(constraint.getName());
     * constraintDataMap.put(constraintId, constraintData);
     * }
     * else {
     * constraintData = constraintDataMap.get(constraintId);
     * }
     * constraintData.addViolations(constraintViolationDataList);
     * }
     * }
     * 
     * if(constraintDataMap.size() > 0) {
     * List<ConstraintData> constraintDataList = new ArrayList<>();
     * for(ConstraintData constraintData : constraintDataMap.values()) {
     * constraintDataList.add(constraintData);
     * }
     * view.setConstraints(constraintDataList);
     * }
     * }
     */

    private void setSeasonPeriod() {

        List<Day> sltDayList = solution.getDayList();
        SeasonPeriodData seasonPeriod = new SeasonPeriodData();
        seasonPeriod.since = sltDayList.get(0).getDate();
        seasonPeriod.till = sltDayList.get(sltDayList.size() - 1).getDate();

        Integer indx = 0;
        Day sltPreviousDay = new Day();
        PeriodData periodData = new PeriodData();
        List<PeriodData> periodDataList = new ArrayList<>();

        for (Day sltDay : sltDayList) {

            if (sltDay.getActiveFlag() == false && (indx == 0 || sltPreviousDay.getActiveFlag() == true)) {
                periodData = new PeriodData();
                periodData.setSince(sltDay.getDate());
                periodDataList.add(periodData);
            }

            if (sltDay.getActiveFlag() == false && indx == sltDayList.size() - 1) {
                periodData.setTill(sltDay.getDate());
            } else if (sltDay.getActiveFlag() == true && indx != 0 && sltPreviousDay.getActiveFlag() == false) {
                periodData.setTill(sltPreviousDay.getDate());
            }

            sltPreviousDay = sltDay;
            indx++;
        }

        if (periodDataList.size() > 0) {
            seasonPeriod.excludedPeriods = periodDataList;
        }

        view.setSeasonPeriod(seasonPeriod);
    }

    private void setTeams() {

        List<TeamData> teams = new ArrayList<>();
        for (Team sltTeam : solution.getTeamList()) {

            TeamData teamData = new TeamData();
            teamData.setId(sltTeam.getId());
            teamData.setName(sltTeam.getName());
            teamData.setRegionType(sltTeam.getRegionType());
            teams.add(teamData);
            
            
            if (!sltTeam.getPeriodByDayMap().isEmpty()) {

                PeriodData periodData = new PeriodData();
                List<PeriodData> awayPeriods = new ArrayList<>();
                List<PeriodData> noPeriods = new ArrayList<>();

                Day sltPreviousDay = null;
                for (Day sltDay : sltTeam.getPeriodByDayMap().keySet()) {
                   
                    if (sltPreviousDay == null ||
                       (sltDay.getIndex()-sltPreviousDay.getIndex() > 1) ||
                       sltTeam.getPeriodByDayMap().get(sltPreviousDay).getPeriodType() != sltTeam.getPeriodByDayMap().get(sltDay).getPeriodType()) {
                        
                        periodData = new PeriodData(sltDay.getDate(), sltDay.getDate());
                        if (sltTeam.getPeriodByDayMap().get(sltDay).getPeriodType() ==  MatchType.AWAY) {
                            awayPeriods.add(periodData);
                        }
                        else if(sltTeam.getPeriodByDayMap().get(sltDay).getPeriodType() ==  MatchType.NO_MATCH) {
                            noPeriods.add(periodData);
                        }
                    }
                    else {
                        periodData.setTill(sltDay.getDate());
                    }
                    sltPreviousDay = sltDay;
                    
                }

                if (awayPeriods.size() > 0) {
                    PeriodDataList awayPeriodDataList = new PeriodDataList();
                    awayPeriodDataList.setPeriods(awayPeriods);
                    teamData.setnoHomePeriods(awayPeriodDataList);
                }

                if (noPeriods.size() > 0) {
                    PeriodDataList noPeriodDataList = new PeriodDataList();
                    noPeriodDataList.setPeriods(noPeriods);
                    teamData.setNoPeriods(noPeriodDataList);
                }
            }
        }

        view.setTeams(teams);
    }

    private void setLogisticGroups() {

        List<LogisticGroupData> logisticGroupsData = new ArrayList<>();
        for (LogisticGroup logisticGroup : solution.getLogisticGroupList()) {

            LogisticGroupData logisticGroupData = new LogisticGroupData();
            logisticGroupData.setId(logisticGroup.getId());
            logisticGroupData.setName(logisticGroup.getName());
            logisticGroupData.setTeams(logisticGroup.getTeams());
            logisticGroupsData.add(logisticGroupData);

        }
        view.setLogisticGroups(logisticGroupsData);
    }

    private void setRoutes() {

        List<Team> sltTeamList = new ArrayList<>();
        List<RouteData> routes = new ArrayList<>();
        for (Team sltTeam1 : solution.getTeamList()) {
            for (Team sltTeam2 : sltTeam1.getDistanceToTeamMap().keySet()) {
                if (!sltTeamList.contains(sltTeam2)) {
                    routes.add(new RouteData(
                            sltTeam1.getId(),
                            sltTeam2.getId(),
                            sltTeam1.getDistanceToTeamMap().get(sltTeam2)));
                }

            }
            sltTeamList.add(sltTeam1);
        }
        view.setRoutes(routes);
    }

    private void setMatches() {
        List<MatchData> matches = new ArrayList<>();
        for (Match sltMatch : solution.getMatchList()) {
            matches.add(new MatchData(
                    sltMatch.getId(),
                    sltMatch.getHomeTeam().getId(),
                    sltMatch.getAwayTeam().getId(),
                    sltMatch.getDay().getDate(),
                    sltMatch.getFixingState()));
        }
        view.setMatches(matches);
    }

}

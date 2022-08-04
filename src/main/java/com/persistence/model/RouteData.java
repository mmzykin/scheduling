package com.persistence.model;

public class RouteData {
    private Long team1Id;
    private Long team2Id;
    private Integer distance;

    @SuppressWarnings("unused")
    public RouteData() {
    }

    public RouteData(Long team1Id, Long team2Id, Integer distance) {
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.distance = distance;
    }

    /*
     * public void setTeam1Id(Long team1Id) {
     * this.team1Id = team1Id;
     * }
     * 
     * public void setTeam2Id(Long team2Id) {
     * this.team2Id = team2Id;
     * }
     * 
     * public void setDistance(Integer distance) {
     * this.distance = distance;
     * }
     */

    public Long getTeam1Id() {
        return team1Id;
    }

    public Long getTeam2Id() {
        return team2Id;
    }

    public Integer getDistance() {
        return distance;
    }

}

package com.persistence.model;

import com.domain.RegionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TeamData {

    private Long id;
    private String name;

    @JsonInclude(Include.NON_EMPTY)
    private PeriodDataList noHomePeriods;

    @JsonInclude(Include.NON_EMPTY)
    private PeriodDataList noPeriods;

    private RegionType regionType;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setnoHomePeriods(PeriodDataList noHomePeriods) {
        this.noHomePeriods = noHomePeriods;
    }

    public void setNoPeriods(PeriodDataList noPeriods) {
        this.noPeriods = noPeriods;
    }

    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PeriodDataList getNoHomePeriods() {
        return noHomePeriods;
    }

    public PeriodDataList getNoPeriods() {
        return noPeriods;
    }

    public RegionType getRegionType() {
        return regionType;
    }
}

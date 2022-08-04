package com.persistence.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SeasonPeriodData {

    public LocalDate since;
    public LocalDate till;

    @JacksonXmlElementWrapper(localName = "excludedPeriods")
    @JacksonXmlProperty(localName = "period")
    @JsonInclude(Include.NON_EMPTY)
    public List<PeriodData> excludedPeriods;

    public SeasonPeriodData() {
        excludedPeriods = new ArrayList<>();
    }

    public LocalDate getSince() {
        return this.since;
    }

    public LocalDate getTill() {
        return this.till;
    }

    public List<PeriodData> getExcludedPeriods() {
        return this.excludedPeriods;
    }
}

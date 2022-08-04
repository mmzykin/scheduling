package com.persistence.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PeriodDataList {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "period")
    private List<PeriodData> periods;

    public void setPeriods(List<PeriodData> periods) {
        this.periods = periods;
    }

    public List<PeriodData> getPeriods() {
        return periods;
    }
}
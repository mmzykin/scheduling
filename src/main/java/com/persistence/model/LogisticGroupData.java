package com.persistence.model;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class LogisticGroupData {

    private Long id;
    private String name;

    @JacksonXmlElementWrapper(localName = "teams")
    @JacksonXmlProperty(localName = "teamId")
    private List<Long> teams;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeams(List<Long> teams) {
        this.teams = teams;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getTeams() {
        return teams;
    }
}

package com.persistence.model.constraintSettingsModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ConstraintData {
    @JsonInclude(Include.NON_EMPTY)
    private Long id;
    @JsonInclude(Include.NON_EMPTY)
    private String name;
    @JsonInclude(Include.NON_EMPTY)
    private Integer penaltyValue;
    @JsonInclude(Include.NON_EMPTY)
    private Boolean active;
    @JsonInclude(Include.NON_EMPTY)
    private ConstraintDetailsData details;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPenaltyValue(Integer penaltyValue) {
        this.penaltyValue = penaltyValue;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setDetails(ConstraintDetailsData details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPenaltyValue() {
        return penaltyValue;
    }

    public Boolean getActive() {
        return active;
    }

    public ConstraintDetailsData getDetails() {
        return details;
    }
}
package com.persistence.model.constraintModel;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ConstraintData {
    public String name;
    @JacksonXmlElementWrapper(localName = "violations")
    @JacksonXmlProperty(localName = "violation")
    @JsonInclude(Include.NON_EMPTY)
    public List<ConstraintViolationData> violations;

    public ConstraintData(String name) {
        this.name = name;
        violations = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setViolations(List<ConstraintViolationData> violations) {
        this.violations = violations;
    }

    public void addViolations(List<ConstraintViolationData> violations) {
        this.violations.addAll(violations);
    }

    public String getName() {
        return name;
    }

    public List<ConstraintViolationData> getViolations() {
        return violations;
    }

    public void setViolationTmp(ConstraintViolationData violation) {
        violations.add(violation);
    }

}

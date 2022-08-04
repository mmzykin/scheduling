package com.persistence.model.constraintSettingsModel;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "constraintSettings")
public class ConstraintSettingsData {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "constraint")
    private List<ConstraintData> constraintList;

    public void setConstraintList(List<ConstraintData> constraintList) {
        this.constraintList = constraintList;
    }

    public List<ConstraintData> getConstraintList() {
        return constraintList;
    }
}
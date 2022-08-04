package com.persistence.model.constraintSettingsModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ConstraintDetailsData {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "propertySet")
    @JsonInclude(Include.NON_EMPTY)
    private List<PropertySetData> propertySetList;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "property")
    @JsonInclude(Include.NON_EMPTY)
    private List<PropertyData> propertyList;

    public void setPropertySetList(List<PropertySetData> propertySetList) {
        this.propertySetList = propertySetList;
    }

    public void setPropertyList(List<PropertyData> propertyList) {
        this.propertyList = propertyList;
    }

    public List<PropertySetData> getPropertySetList() {
        return propertySetList;
    }

    public List<PropertyData> getPropertyList() {
        return propertyList;
    }

}
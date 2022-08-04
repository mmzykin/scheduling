package com.persistence.model.constraintSettingsModel;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PropertySetData {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "property")
    private List<PropertyData> propertyList;

    public void setPropertyList(List<PropertyData> propertyList) {
        this.propertyList = propertyList;
    }

    public List<PropertyData> getPropertyList() {
        return propertyList;
    }
}
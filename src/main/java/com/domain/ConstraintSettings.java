package com.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.common.domain.AbstractPersistable;

public class ConstraintSettings extends AbstractPersistable {

    private String name;
    private Integer penaltyValue;
    private Boolean active;
    private Map<String, String> properties;
    private List<Map<String, String>> propertiesList;

    public void setName(String name) {
        this.name = name;
    }

    public void setPenaltyValue(Integer penaltyValue) {
        this.penaltyValue = penaltyValue;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public void setPropertiesList(List<Map<String, String>> propertiesList) {
        this.propertiesList = propertiesList;
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

    public Map<String, String> getProperties() {
        return properties;
    }

    public List<Map<String, String>> getPropertiesList() {
        return propertiesList;
    }

    public void setProperty(String propertyKey, Integer propertyValue) {

        if (properties == null) {
            properties = new HashMap<>();
        }

        properties.put(propertyKey, propertyValue.toString());

    }

    public String getProperty(String propertyKey) {
        String propertyValue = null;

        if (properties != null && properties.size() > 0) {
            if (properties.containsKey(propertyKey)) {
                propertyValue = properties.get(propertyKey);
            }
        }

        return propertyValue;
    }

}
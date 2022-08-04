package com.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.domain.ConstraintSettings;
import com.persistence.model.constraintSettingsModel.ConstraintData;
import com.persistence.model.constraintSettingsModel.ConstraintDetailsData;
import com.persistence.model.constraintSettingsModel.ConstraintSettingsData;
import com.persistence.model.constraintSettingsModel.PropertyData;
import com.persistence.model.constraintSettingsModel.PropertySetData;

public class ConstraintSettingsConverter {

    public List<ConstraintSettings> convertModelToDomain(ConstraintSettingsData constraintSettingsData) {

        List<ConstraintSettings> constraintSettingsList = new ArrayList<>();

        for (ConstraintData constraintData : constraintSettingsData.getConstraintList()) {
            ConstraintSettings constraint = new ConstraintSettings();
            constraintSettingsList.add(constraint);
            constraint.setId(constraintData.getId());
            constraint.setActive(constraintData.getActive());
            constraint.setName(constraintData.getName());
            constraint.setPenaltyValue(constraintData.getPenaltyValue());

            if (constraintData.getDetails() != null) {
                if (constraintData.getDetails().getPropertyList() != null) {

                    Map<String, String> properties = new HashMap<>();
                    constraint.setProperties(properties);

                    for (PropertyData propertyData : constraintData.getDetails().getPropertyList()) {
                        properties.put(propertyData.getKey(), propertyData.getValue());
                    }
                }

                if (constraintData.getDetails().getPropertySetList() != null) {

                    List<Map<String, String>> propertiestList = new ArrayList<>();
                    constraint.setPropertiesList(propertiestList);

                    for (PropertySetData propertySetData : constraintData.getDetails().getPropertySetList()) {

                        Map<String, String> properties = new HashMap<>();
                        for (PropertyData propertyData : propertySetData.getPropertyList()) {
                            properties.put(propertyData.getKey(), propertyData.getValue());
                        }
                        propertiestList.add(properties);
                    }
                }
            }
        }
        return constraintSettingsList;
    }

    public ConstraintSettingsData convertDomenToModel(List<ConstraintSettings> constraintSettingsList) {

        ConstraintSettingsData constraintSettingsData = new ConstraintSettingsData();
        List<ConstraintData> constraintDataList = new ArrayList<>();
        constraintSettingsData.setConstraintList(constraintDataList);

        for (ConstraintSettings constraintSettings : constraintSettingsList) {
            ConstraintData constraintData = new ConstraintData();
            constraintDataList.add(constraintData);

            constraintData.setId(constraintSettings.getId());
            constraintData.setActive(constraintSettings.getActive());
            constraintData.setName(constraintSettings.getName());
            constraintData.setPenaltyValue(constraintSettings.getPenaltyValue());

            ConstraintDetailsData constraintDetailsData = null;

            List<Map<String, String>> propertiesList = constraintSettings.getPropertiesList();

            if (propertiesList != null) {

                if (constraintDetailsData == null) {
                    constraintDetailsData = new ConstraintDetailsData();
                }
                List<PropertySetData> propertySetDataList = new ArrayList<>();
                constraintDetailsData.setPropertySetList(propertySetDataList);

                for (Map<String, String> properties : propertiesList) {

                    PropertySetData propertySetData = new PropertySetData();
                    List<PropertyData> propertyDataList = new ArrayList<>();
                    propertySetData.setPropertyList(propertyDataList);
                    propertySetDataList.add(propertySetData);

                    for (String propertyKey : properties.keySet()) {
                        PropertyData propertyData = new PropertyData();
                        propertyDataList.add(propertyData);
                        propertyData.setKey(propertyKey);
                        propertyData.setValue(properties.get(propertyKey));
                    }
                }
            }

            Map<String, String> properties = constraintSettings.getProperties();
            if (properties != null) {

                if (constraintDetailsData == null) {
                    constraintDetailsData = new ConstraintDetailsData();
                }

                List<PropertyData> propertyDataList = new ArrayList<>();
                constraintDetailsData.setPropertyList(propertyDataList);

                for (String propertyKey : properties.keySet()) {
                    PropertyData propertyData = new PropertyData();
                    propertyDataList.add(propertyData);
                    propertyData.setKey(propertyKey);
                    propertyData.setValue(properties.get(propertyKey));
                }
            }

            if (constraintDetailsData != null) {
                constraintData.setDetails(constraintDetailsData);
            }
        }

        return constraintSettingsData;
    }

}
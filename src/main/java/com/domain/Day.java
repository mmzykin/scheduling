/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.domain;

import java.time.LocalDate;

import com.common.domain.AbstractPersistable;
import com.common.swingui.components.Labeled;

public class Day extends AbstractPersistable implements Labeled {

    private LocalDate date;
    //Порядковый номер дня в сезоне
    private int index;

    //Порядковый номер игрового дня в сезоне
    private int actingIndex;

    private boolean activeFlag;

    private Day nextDay;

    private boolean fixingState;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getActingIndex() {
        return actingIndex;
    }

    public void setActingIndex(int actingIndex) {
        this.actingIndex = actingIndex;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean getActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public Day getNextDay() {
        return nextDay;
    }

    public void setNextDay(Day nextDay) {
        this.nextDay = nextDay;
    }

    @Override
    public String getLabel() {
        return Integer.toString(index);
    }

    @Override
    public String toString() {
        return "Day-" + index;
    }

    public void setFixingState(boolean fixingState) {
        this.fixingState = fixingState;
    }

    public boolean getFixingState() {
        return fixingState;
    }

}

package com.persistence.model;

import java.time.LocalDate;

public class PeriodData {
    private LocalDate since;
    private LocalDate till;

    public PeriodData() {
    }

    public PeriodData(LocalDate since, LocalDate till) {
        this.since = since;
        this.till = till;
    }

    public void setSince(LocalDate since) {
        this.since = since;
    }

    public void setTill(LocalDate till) {
        this.till = till;
    }

    public LocalDate getSince() {
        return since;
    }

    public LocalDate getTill() {
        return till;
    }

}

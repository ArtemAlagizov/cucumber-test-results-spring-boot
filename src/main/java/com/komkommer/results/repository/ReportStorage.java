package com.komkommer.results.repository;

import com.komkommer.results.model.ElementList;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportStorage {
    private List<ElementList> elementList = new ArrayList<>();
    private String dateTimeOfReportCreation = "";

    public void storeReport(List<ElementList> elementList, String dateTimeOfReportCreation) {
        this.elementList = elementList;
        this.dateTimeOfReportCreation = dateTimeOfReportCreation;
    }

    public List<ElementList> getElementList() {
        return this.elementList;
    }

    public String getDateTimeOfReportCreation() {
        return this.dateTimeOfReportCreation;
    }
}

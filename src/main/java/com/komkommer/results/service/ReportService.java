package com.komkommer.results.service;

import com.komkommer.results.model.ElementList;
import com.komkommer.results.repository.ReportStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    private ReportStorage reportStorage;

    @Autowired
    public ReportService(ReportStorage reportStorage) {
        this.reportStorage = reportStorage;
    }

    public List<ElementList> getAllReportElements() {
        return reportStorage.getElementList();
    }

    public String getDateTimeOfReportCreation() {
        return reportStorage.getDateTimeOfReportCreation();
    }

    public void createReport(List<ElementList> elementList, String dateTimeOfReportCreation) {
        reportStorage.storeReport(elementList, dateTimeOfReportCreation);
    }
}

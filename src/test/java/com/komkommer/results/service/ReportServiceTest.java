package com.komkommer.results.service;

import com.komkommer.results.model.ElementList;
import com.komkommer.results.repository.ReportStorage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceTest {
    @InjectMocks
    private ReportService reportService;

    @Mock
    private ReportStorage reportStorage;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shallCallGetAllReportElementsOfReportStorageWhenGetAllReportElementsIsCalled() {
        reportService.getAllReportElements();

        verify(reportStorage, times(1)).getElementList();
    }

    @Test
    public void shallCallCreateReportOfReportStorageWhenCreateReportIsCalled() {
        List<ElementList> elementLists = new ArrayList<>();
        String dateTimeOfReportCreation = "01-01-2019";

        reportService.createReport(elementLists, dateTimeOfReportCreation);

        verify(reportStorage, times(1)).storeReport(elementLists, dateTimeOfReportCreation);
    }

    @Test
    public void shallCallGetDateTimeOfReportCreationOfReportStorageWhenGetDateTimeOfReportCreationIsCalled() {
        reportService.getDateTimeOfReportCreation();

        verify(reportStorage, times(1)).getDateTimeOfReportCreation();
    }

}

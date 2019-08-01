package com.komkommer.results.controller;

import com.komkommer.results.model.ElementList;
import com.komkommer.results.service.ReportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class EndPointControllerTest {
    @InjectMocks
    private EndPointController endPointController;

    @Mock
    private ReportService reportService;

    @Mock
    private SimpMessagingTemplate template;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shallCallGetAllReportElementsOfReportServiceWhenGetAllReportElementsIsCalled() {
        endPointController.getAllReportElements();

        verify(reportService, times(1)).getAllReportElements();
    }

    @Test
    public void shallCallCreateReportOfReportServiceWhenCreateReportIsCalled() {
        List<ElementList> elementLists = new ArrayList<>();
        String dateTimeOfReportCreation = "01-01-2019";

        endPointController.createReport(elementLists, dateTimeOfReportCreation);

        verify(reportService, times(1)).createReport(elementLists, dateTimeOfReportCreation);
    }

    @Test
    public void shallCallGetDateTimeOfReportCreationOfReportServiceWhenGetDateTimeOfReportCreationIsCalled() {
        endPointController.getTimestampOfReportCreation();

        verify(reportService, times(1)).getDateTimeOfReportCreation();
    }

    @Test
    public void shallCallSendCucumberReportStatusThroughWsOfReportServiceWhenGetTimestampOfReportCreationIsCalled() {
        String status = "cucumber_report_ready";
        endPointController.sendCucumberReportStatusThroughWs(status);

        verify(this.template, times(1)).convertAndSend("/topic/eventToClient", status);
    }
}

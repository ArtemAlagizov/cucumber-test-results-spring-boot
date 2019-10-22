package com.komkommer.results.controller;

import com.komkommer.results.model.ElementList;
import com.komkommer.results.service.ReportService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller, provides /cucumber-report endpoint
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/cucumber-report")
public class CucumberReportController {
    private ReportService reportService;

    @Autowired
    protected CucumberReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Autowired
    protected SimpMessagingTemplate template;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get the report")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched the report"),
            @ApiResponse(code = 401, message = "You are not authorized to get the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<ElementList> getAllReportElements() {
        return reportService.getAllReportElements();
    }

    @GetMapping(path = "/date-time")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get the report date-time")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched report date time"),
            @ApiResponse(code = 401, message = "You are not authorized to get the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public String getTimestampOfReportCreation() {
        return reportService.getDateTimeOfReportCreation();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Post the report")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully posted the report"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void createReport(@RequestBody List<ElementList> elementList, @RequestParam String dateTimeOfReportCreation) {

        reportService.createReport(elementList, dateTimeOfReportCreation);

        this.sendCucumberReportStatusThroughWs("cucumber_report_ready");
    }

    public void sendCucumberReportStatusThroughWs(String status) {
        this.template.convertAndSend("/topic/eventToClient", status);
    }
}
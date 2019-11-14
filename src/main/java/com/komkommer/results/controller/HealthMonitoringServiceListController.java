package com.komkommer.results.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.komkommer.results.model.HealthMonitoringServiceDetails;
import com.komkommer.results.model.HealthMonitoringServiceList;
import com.komkommer.results.service.HealthMonitoringServiceListService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Rest controller, provides /health-monitoring-services endpoint
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/health-monitoring-services")
public class HealthMonitoringServiceListController {
    private HealthMonitoringServiceListService healthMonitoringServiceListService;

    @Autowired
    protected HealthMonitoringServiceListController(HealthMonitoringServiceListService healthMonitoringServiceListService) {
        this.healthMonitoringServiceListService = healthMonitoringServiceListService;
    }

    @Autowired
    protected SimpMessagingTemplate template;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get the health monitoring services list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched the health monitoring services list"),
            @ApiResponse(code = 401, message = "You are not authorized to get the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<HealthMonitoringServiceDetails> getHealthMonitoringServiceList() {
        return healthMonitoringServiceListService.getHealthMonitoringServiceList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Post the health monitoring services list")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully posted the health monitoring services list"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void createHealthMonitoringServiceList(@RequestBody HealthMonitoringServiceList healthMonitoringServiceList) {

        healthMonitoringServiceListService.createHealthMonitoringServiceList(healthMonitoringServiceList);

        this.sendDeploymentDataStatusThroughWs("health_monitoring_service_list_ready");
    }

    public void sendDeploymentDataStatusThroughWs(String status) {
        this.template.convertAndSend("/topic/eventToClient", status);
    }
}
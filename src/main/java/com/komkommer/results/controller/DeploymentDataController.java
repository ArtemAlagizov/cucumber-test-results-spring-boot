package com.komkommer.results.controller;

import com.komkommer.results.model.DeploymentData;
import com.komkommer.results.service.DeploymentDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * Rest controller, provides /deployment-data endpoint
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/deployment-data")
public class DeploymentDataController {
    private DeploymentDataService deploymentDataService;

    @Autowired
    protected DeploymentDataController(DeploymentDataService deploymentDataService) {
        this.deploymentDataService = deploymentDataService;
    }

    @Autowired
    protected SimpMessagingTemplate template;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get the deployment data")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched the deployment data"),
            @ApiResponse(code = 401, message = "You are not authorized to get the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public DeploymentData getDeploymentReleaseVersion() {
        return deploymentDataService.getDeploymentData();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Post the deployment data")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully posted the deployment dat"),
            @ApiResponse(code = 401, message = "You are not authorized to access the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public void createDeploymentData(@RequestBody DeploymentData deploymentData) {

        deploymentDataService.createDeploymentData(deploymentData);

        this.sendDeploymentDataStatusThroughWs("deployment_data_ready");
    }

    public void sendDeploymentDataStatusThroughWs(String status) {
        this.template.convertAndSend("/topic/eventToClient", status);
    }
}
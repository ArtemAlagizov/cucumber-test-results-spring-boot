package com.komkommer.results.service;

import com.komkommer.results.model.DeploymentData;
import com.komkommer.results.repository.DeploymentDataStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeploymentDataService {
    private DeploymentDataStorage deploymentDataStorage;

    @Autowired
    public DeploymentDataService(DeploymentDataStorage deploymentDataStorage) {
        this.deploymentDataStorage = deploymentDataStorage;
    }

    public DeploymentData getDeploymentData() {
        return deploymentDataStorage.getDeploymentData();
    }

    public void createDeploymentData(DeploymentData deploymentData) {
        deploymentDataStorage.storeDeploymentData(deploymentData);
    }
}

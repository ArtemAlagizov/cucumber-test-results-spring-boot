package com.komkommer.results.repository;

import com.komkommer.results.model.DeploymentData;
import org.springframework.stereotype.Repository;

@Repository
public class DeploymentDataStorage {
    private DeploymentData deploymentData = new DeploymentData();

    public void storeDeploymentData(DeploymentData deploymentData) {
        this.deploymentData = deploymentData;
    }

    public DeploymentData getDeploymentData() {
        return this.deploymentData;
    }
}

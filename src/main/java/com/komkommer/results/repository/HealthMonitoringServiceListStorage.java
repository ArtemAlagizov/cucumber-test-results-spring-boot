package com.komkommer.results.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.komkommer.results.model.HealthMonitoringServiceDetails;

@Repository
public class HealthMonitoringServiceListStorage {
    private List<HealthMonitoringServiceDetails> healthMonitoringServiceList = new ArrayList<>();

    public void storeHealthMonitoringServiceList(List<HealthMonitoringServiceDetails> healthMonitoringServiceList) {
        this.healthMonitoringServiceList = healthMonitoringServiceList;
    }

    public List<HealthMonitoringServiceDetails> getHealthMonitoringServiceList() {
        return this.healthMonitoringServiceList;
    }
}

package com.komkommer.results.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.komkommer.results.model.HealthMonitoringServiceDetails;
import com.komkommer.results.model.HealthMonitoringServiceList;
import com.komkommer.results.repository.HealthMonitoringServiceListStorage;

@Service
public class HealthMonitoringServiceListService {
    private HealthMonitoringServiceListStorage healthMonitoringServiceListStorage;

    @Autowired
    public HealthMonitoringServiceListService(HealthMonitoringServiceListStorage healthMonitoringServiceListStorage) {
        this.healthMonitoringServiceListStorage = healthMonitoringServiceListStorage;
    }

    public List<HealthMonitoringServiceDetails> getHealthMonitoringServiceList() {
        return healthMonitoringServiceListStorage.getHealthMonitoringServiceList();
    }

    public void createHealthMonitoringServiceList(HealthMonitoringServiceList healthMonitoringServiceList) {
        List<HealthMonitoringServiceDetails> healthMonitoringServiceListUpdated = healthMonitoringServiceList
                .getHealthMonitoringServiceDetails()
                .stream()
                .map(service -> {
                    service.setHealthCheckUrl(service.getHealthCheckUrl().replace("{BACKEND_IP}", healthMonitoringServiceList.getBackendIp()));
                    service.setSwaggerUrl(service.getSwaggerUrl().replace("{BACKEND_IP}", healthMonitoringServiceList.getBackendIp()));

                    return service;
                })
                .collect(Collectors.toList());

        healthMonitoringServiceListStorage.storeHealthMonitoringServiceList(healthMonitoringServiceListUpdated);
    }
}

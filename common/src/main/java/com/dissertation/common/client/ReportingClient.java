package com.dissertation.common.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "reporting-service")
public interface ReportingClient {
}

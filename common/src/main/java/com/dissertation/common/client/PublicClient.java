package com.dissertation.common.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "public-service")
public interface PublicClient {
}

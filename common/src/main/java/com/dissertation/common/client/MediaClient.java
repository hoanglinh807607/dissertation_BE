package com.dissertation.common.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "media-service")
public interface MediaClient {
}

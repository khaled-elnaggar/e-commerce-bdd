package org.example.infrastructure.httpclients.authorization;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "AuthorizationClient", url = "${external-services.authorization}")
public interface AuthorizationClient {
  @PostMapping("/auth")
  ResponseEntity<String> authorizeUser(@RequestHeader("Authorization") String authorization);
}

package org.example.infrastructure.httpclients.notifications;

import org.example.presentation.rest.dto.Receipt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "NotificationsClient", url = "${external-services.notifications}")
public interface NotificationsClient {
  @PostMapping("/notifications")
  void notify(@RequestHeader("Authorization") String authorization, @RequestBody Receipt receipt);
}

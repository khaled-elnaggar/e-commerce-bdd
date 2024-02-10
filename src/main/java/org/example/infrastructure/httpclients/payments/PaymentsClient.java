package org.example.infrastructure.httpclients.payments;

import org.example.infrastructure.httpclients.payments.dto.PaymentAmount;
import org.example.infrastructure.httpclients.payments.dto.SuccessfulPaymentDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "PaymentsClient", url = "${external-services.payments}")
public interface PaymentsClient {
  @PostMapping("/payments")
  SuccessfulPaymentDetails makePayment(@RequestHeader("Authorization") String authorization, @RequestBody PaymentAmount amount);
}

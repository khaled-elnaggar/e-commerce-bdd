package org.example.application.gateways;

import org.example.presentation.rest.dto.Receipt;

public interface NotificationsGateway {
  void notify(String authorization, Receipt receipt);
}

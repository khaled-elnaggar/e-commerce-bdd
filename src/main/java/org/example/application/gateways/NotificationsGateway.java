package org.example.application.gateways;

import org.example.presentation.rest.dto.Receipt;

public interface NotificationsGateway {
  void notifyUser(String authorization, Receipt receipt);
}

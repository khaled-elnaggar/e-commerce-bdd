package org.example.infrastructure.httpclients.notifications;

import org.example.application.gateways.NotificationsGateway;
import org.example.presentation.rest.dto.Receipt;
import org.springframework.stereotype.Component;

@Component
public class NotificationAdapter implements NotificationsGateway {
  private final
  NotificationsClient notificationsClient;

  public NotificationAdapter(NotificationsClient notificationsClient) {
    this.notificationsClient = notificationsClient;
  }

  @Override
  public void notify(String authorization, Receipt receipt) {
    notificationsClient.notify(authorization, receipt);
  }
}

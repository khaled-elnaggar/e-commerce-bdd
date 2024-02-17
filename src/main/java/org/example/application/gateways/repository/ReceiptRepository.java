package org.example.application.gateways.repository;

import org.example.presentation.rest.dto.Order;

public interface ReceiptRepository {
  void saveReceipt(Order receipt);
}

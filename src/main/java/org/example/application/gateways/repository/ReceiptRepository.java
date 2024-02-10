package org.example.application.gateways.repository;

import org.example.presentation.rest.dto.Order;
import org.example.presentation.rest.dto.Receipt;

public interface ReceiptRepository {
  Receipt saveReceipt(Order receipt);
}

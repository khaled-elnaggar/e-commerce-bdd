package org.example.application.gateways.repository;

import org.example.presentation.rest.dto.Receipt;
import org.example.presentation.rest.dto.Order;

public interface ReceiptRepository {
  Receipt saveReceipt(Order receipt);
}

package org.example.application.gateways.repository;

import org.example.presentation.rest.dto.Receipt;
import org.example.presentation.rest.dto.SuccessfulOrder;

public interface ReceiptRepository {
  Receipt saveReceipt(SuccessfulOrder receipt);
}

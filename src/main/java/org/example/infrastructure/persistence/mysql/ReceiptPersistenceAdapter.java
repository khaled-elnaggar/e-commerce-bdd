package org.example.infrastructure.persistence.mysql;

import org.example.application.gateways.repository.ReceiptRepository;
import org.example.presentation.rest.dto.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiptPersistenceAdapter implements ReceiptRepository {
  @Autowired
  ReceiptJpaRepository receiptJpaRepository;

  @Override
  public void saveReceipt(Order order) {
    ReceiptEntity receiptEntity = new ReceiptEntity(order);
    receiptJpaRepository
            .save(receiptEntity);
  }
}

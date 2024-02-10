package org.example.infrastructure.persistence.mysql;

import org.example.application.gateways.repository.ReceiptRepository;
import org.example.presentation.rest.dto.Receipt;
import org.example.presentation.rest.dto.SuccessfulOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiptPersistenceAdapter implements ReceiptRepository {
  @Autowired
  ReceiptJpaRepository receiptJpaRepository;

  @Override
  public Receipt saveReceipt(SuccessfulOrder successfulOrder) {
    ReceiptEntity receiptEntity = new ReceiptEntity(successfulOrder);
    return receiptJpaRepository
            .save(receiptEntity)
            .toUseCaseModel();
  }
}

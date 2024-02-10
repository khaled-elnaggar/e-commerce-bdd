package org.example.infrastructure.persistence.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptJpaRepository extends JpaRepository<ReceiptEntity, Long> {
}

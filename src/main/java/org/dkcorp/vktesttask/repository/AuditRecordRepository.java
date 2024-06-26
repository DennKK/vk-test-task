package org.dkcorp.vktesttask.repository;

import org.dkcorp.vktesttask.domain.entity.AuditRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRecordRepository extends JpaRepository<AuditRecord, Long> {
}

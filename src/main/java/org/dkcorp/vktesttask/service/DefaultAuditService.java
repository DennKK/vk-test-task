package org.dkcorp.vktesttask.service;

import lombok.RequiredArgsConstructor;
import org.dkcorp.vktesttask.domain.entity.AuditRecord;
import org.dkcorp.vktesttask.repository.AuditRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultAuditService implements AuditService {
    private final AuditRecordRepository auditRecordRepository;

    @Override
    public void recordAudit(String username, String requestUri, String method, String requestParams, String status) {
        AuditRecord auditRecord = AuditRecord.builder()
                .timestamp(LocalDateTime.now())
                .username(username)
                .requestUri(requestUri)
                .method(method)
                .requestParams(requestParams)
                .status(status)
                .build();

        auditRecordRepository.save(auditRecord);
    }
}

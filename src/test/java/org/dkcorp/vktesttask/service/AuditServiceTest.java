package org.dkcorp.vktesttask.service;

import org.dkcorp.vktesttask.domain.entity.AuditRecord;
import org.dkcorp.vktesttask.repository.AuditRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AuditServiceTest {
    @Mock
    AuditRecordRepository auditRecordRepository;

    @InjectMocks
    DefaultAuditService auditService;

    @Test
    void recordAudit_ShouldSaveAuditRecord() {
        // given
        String username = "testUser";
        String requestUri = "/test/uri";
        String method = "GET";
        String requestParams = "param1=value1&param2=value2";
        String status = "200";

        // when
        auditService.recordAudit(username, requestUri, method, requestParams, status);

        // then
        ArgumentCaptor<AuditRecord> auditRecordCaptor = ArgumentCaptor.forClass(AuditRecord.class);
        verify(auditRecordRepository).save(auditRecordCaptor.capture());

        AuditRecord capturedAuditRecord = auditRecordCaptor.getValue();

        assertEquals(username, capturedAuditRecord.getUsername());
        assertEquals(requestUri, capturedAuditRecord.getRequestUri());
        assertEquals(method, capturedAuditRecord.getMethod());
        assertEquals(requestParams, capturedAuditRecord.getRequestParams());
        assertEquals(status, capturedAuditRecord.getStatus());
        assertEquals(LocalDateTime.now().getYear(), capturedAuditRecord.getTimestamp().getYear());
        assertEquals(LocalDateTime.now().getMonth(), capturedAuditRecord.getTimestamp().getMonth());
        assertEquals(LocalDateTime.now().getDayOfMonth(), capturedAuditRecord.getTimestamp().getDayOfMonth());
    }
}

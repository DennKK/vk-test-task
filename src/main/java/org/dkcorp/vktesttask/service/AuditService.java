package org.dkcorp.vktesttask.service;

public interface AuditService {
    void recordAudit(String username, String requestUri, String method, String requestParams, String status);
}

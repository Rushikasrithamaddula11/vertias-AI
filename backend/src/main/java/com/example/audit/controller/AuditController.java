package com.example.audit.controller;

import com.example.audit.model.AuditData;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/audit")
public class AuditController {

    private final AuditData auditData = new AuditData();

    @GetMapping
    public AuditData getAuditData() {
        return auditData;
    }

    @GetMapping("/report/{type}")
    public ResponseEntity<String> downloadReport(@PathVariable String type) {
        String filename = "model_audit_report_" + type + ".json";
        String timestamp = Instant.now().toString();

        var report = Map.of(
                "timestamp", timestamp,
                "modelName", "ML Model Audit Report",
                "performance", auditData.getPerformance(),
                "dataQuality", auditData.getDataQuality(),
                "featureImportance", auditData.getFeatureImportance(),
                "biasAnalysis", auditData.getBiasAnalysis()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        return ResponseEntity.ok().headers(headers).body(AuditData.toJson(report));
    }
}

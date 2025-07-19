package com.example.audit.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class AuditData {

    public static class MetricSet {
        public double accuracy, precision, recall, f1Score, auc;
    }

    public static class DataQuality {
        public double completeness, consistency, accuracy, validity, uniqueness;
    }

    public static class Feature {
        public String name;
        public double importance;

        public Feature(String name, double importance) {
            this.name = name;
            this.importance = importance;
        }
    }

    public static class BiasAnalysis {
        public double demographicParity, equalizedOdds, calibration, individualFairness;
    }

    private final MetricSet performance = new MetricSet();
    private final DataQuality dataQuality = new DataQuality();
    private final List<Feature> featureImportance;
    private final BiasAnalysis biasAnalysis = new BiasAnalysis();

    public AuditData() {
        performance.accuracy = 0.92;
        performance.precision = 0.89;
        performance.recall = 0.94;
        performance.f1Score = 0.915;
        performance.auc = 0.87;

        dataQuality.completeness = 0.95;
        dataQuality.consistency = 0.88;
        dataQuality.accuracy = 0.91;
        dataQuality.validity = 0.93;
        dataQuality.uniqueness = 0.89;

        featureImportance = List.of(
                new Feature("Feature A", 0.25),
                new Feature("Feature B", 0.18),
                new Feature("Feature C", 0.15),
                new Feature("Feature D", 0.12),
                new Feature("Feature E", 0.10),
                new Feature("Feature F", 0.08),
                new Feature("Feature G", 0.07),
                new Feature("Feature H", 0.05)
        );

        biasAnalysis.demographicParity = 0.85;
        biasAnalysis.equalizedOdds = 0.88;
        biasAnalysis.calibration = 0.92;
        biasAnalysis.individualFairness = 0.87;
    }

    public MetricSet getPerformance() {
        return performance;
    }

    public DataQuality getDataQuality() {
        return dataQuality;
    }

    public List<Feature> getFeatureImportance() {
        return featureImportance;
    }

    public BiasAnalysis getBiasAnalysis() {
        return biasAnalysis;
    }

    public static String toJson(Object obj) {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            return "{}";
        }
    }
}

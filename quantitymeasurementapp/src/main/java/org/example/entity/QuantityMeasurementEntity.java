package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="quantity_measurements")
public class QuantityMeasurementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Operand 1
    private double value1;
    private String unit1;

    // Operand 2 (nullable for convert)
    private Double value2;
    private String unit2;

    // Operation type
    private String operation; // CONVERT, ADD, SUBTRACT, etc.

    // Result
    private double resultValue;
    private String resultUnit;

    // Category (Length, Weight, etc.)
    private String category;

    // User tracking (IMPORTANT for multi-user)
    private String userEmail;

    private LocalDateTime createdAt;

    public QuantityMeasurementEntity() {}

    public QuantityMeasurementEntity(
            Long id,
            double value1,
            String unit1,
            Double value2,
            String unit2,
            String operation,
            double resultValue,
            String resultUnit,
            String category,
            String userEmail,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.value1 = value1;
        this.unit1 = unit1;
        this.value2 = value2;
        this.unit2 = unit2;
        this.operation = operation;
        this.resultValue = resultValue;
        this.resultUnit = resultUnit;
        this.category = category;
        this.userEmail = userEmail;
        this.createdAt = createdAt;
    }


    @PrePersist
    public void onCreate(){
        if(this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }
    }

    // getters & setters

    public Long getId() {
        return id;
    }

    // value1
    public double getValue1() {
        return value1;
    }
    public void setValue1(double value1) {
        this.value1 = value1;
    }

    // unit1
    public String getUnit1() {
        return unit1;
    }
    public void setUnit1(String unit1) {
        this.unit1 = unit1;
    }

    // value2
    public Double getValue2() {
        return value2;
    }
    public void setValue2(Double value2) {
        this.value2 = value2;
    }

    // unit2
    public String getUnit2() {
        return unit2;
    }
    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    // operation
    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }

    // resultValue
    public double getResultValue() {
        return resultValue;
    }
    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    // resultUnit
    public String getResultUnit() {
        return resultUnit;
    }
    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit;
    }

    // category
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    // userEmail
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

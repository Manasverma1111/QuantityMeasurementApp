package org.example.repository;

import org.example.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {

}
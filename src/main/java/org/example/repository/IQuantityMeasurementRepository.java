package org.example.repository;


import org.example.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity,Long> {

    List<QuantityMeasurementEntity> findByOperation(String operation);

    List<QuantityMeasurementEntity> findByOperationIgnoreCase(String operation);

    long countByOperation(String operation);
}
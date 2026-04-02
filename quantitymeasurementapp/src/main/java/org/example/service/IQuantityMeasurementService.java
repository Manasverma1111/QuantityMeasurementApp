package org.example.service;


import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO q1, QuantityDTO q2, String userEmail);

    QuantityDTO convert(QuantityDTO source, String targetUnit, String userEmail);

    QuantityDTO add(QuantityDTO q1,QuantityDTO q2, String userEmail);

    QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2, String userEmail);

    double divide(QuantityDTO q1,QuantityDTO q2, String userEmail);

    List<QuantityMeasurementEntity> getHistory(String userEmail);
}

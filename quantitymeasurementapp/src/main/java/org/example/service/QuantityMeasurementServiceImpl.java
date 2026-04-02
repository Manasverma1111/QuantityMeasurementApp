package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.exception.QuantityMeasurementException;
import org.example.quantity.Quantity;
import org.example.repository.IQuantityMeasurementRepository;
import org.example.units.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private IQuantityMeasurementRepository repository;

    // Convert DTO → quantity.Quantity Model
    private Quantity convertDTOToQuantity(QuantityDTO dto){

        String unitName=dto.getUnit();
        double value=dto.getValue();

        switch(dto.getMeasurementType().toUpperCase()){

            case "LENGTH":
                return new Quantity(value, LengthUnit.valueOf(unitName));

            case "WEIGHT":
                return new Quantity(value, WeightUnit.valueOf(unitName));

            case "VOLUME":
                return new Quantity(value, VolumeUnit.valueOf(unitName));

            case "TEMPERATURE":
                return new Quantity(value, TemperatureUnit.valueOf(unitName));

            default:
                throw new QuantityMeasurementException("Invalid measurement type");
        }
    }

    // Convert quantity.Quantity → DTO
    private QuantityDTO convertQuantityToDTO(Quantity quantity){

        String measurementType = quantity.getUnit().getClass()
                .getSimpleName()
                .replace("Unit","")
                .toUpperCase();

        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().toString(),
                measurementType
        );
    }

    // COMPARE
    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity quantity1 = convertDTOToQuantity(q1);
            Quantity quantity2 = convertDTOToQuantity(q2);

            if (!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())) {
                throw new QuantityMeasurementException("Different measurement types");
            }

            boolean result = quantity1.equals(quantity2);

            repository.save(new QuantityMeasurementEntity(
                    null,
                    quantity1.getValue(),
                    quantity1.getUnit().toString(),
                    quantity2.getValue(),
                    quantity2.getUnit().toString(),
                    "COMPARE",
                    result ? 1.0 : 0.0,
                    "BOOLEAN",
                    q1.getMeasurementType(),
                    userEmail,
                    null
            ));

            return result;

        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }
    // CONVERT
    @Override
    public QuantityDTO convert(QuantityDTO source, String targetUnit, String userEmail) {
        try {
            Quantity quantity = convertDTOToQuantity(source);

            IMeasurable unit = (IMeasurable) Enum.valueOf(
                    (Class<? extends Enum>) quantity.getUnit().getClass(),
                    targetUnit
            );

            Quantity result = quantity.convertTo(unit);

            repository.save(new QuantityMeasurementEntity(
                    null,
                    quantity.getValue(),
                    quantity.getUnit().toString(),
                    null,
                    targetUnit,
                    "CONVERT",
                    result.getValue(),
                    result.getUnit().toString(),
                    source.getMeasurementType(),
                    userEmail,
                    null
            ));

            return convertQuantityToDTO(result);

        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ADD
    @Override
    public QuantityDTO add(QuantityDTO q1,QuantityDTO q2, String userEmail){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);

            Quantity result=quantity1.add(quantity2);

            // SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    null,
                    quantity1.getValue(),
                    quantity1.getUnit().toString(),
                    quantity2.getValue(),
                    quantity2.getUnit().toString(),
                    "ADD", // or SUBTRACT
                    result.getValue(),
                    result.getUnit().toString(),
                    q1.getMeasurementType(),
                    userEmail,
                    null
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // 🔥 SUBTRACT
    @Override
    public QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2, String userEmail){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);

            Quantity result=quantity1.subtract(quantity2);

            // SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    null,
                    quantity1.getValue(),
                    quantity1.getUnit().toString(),
                    quantity2.getValue(),
                    quantity2.getUnit().toString(),
                    "SUBTRACT", // or SUBTRACT
                    result.getValue(),
                    result.getUnit().toString(),
                    q1.getMeasurementType(),
                    userEmail,
                    null
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(e.getMessage());
        }
    }
    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2, String userEmail) {
        try {
            Quantity quantity1 = convertDTOToQuantity(q1);
            Quantity quantity2 = convertDTOToQuantity(q2);

            double result = quantity1.divide(quantity2);

            repository.save(new QuantityMeasurementEntity(
                    null,
                    quantity1.getValue(),
                    quantity1.getUnit().toString(),
                    quantity2.getValue(),
                    quantity2.getUnit().toString(),
                    "DIVIDE",
                    result,
                    "SCALAR",
                    q1.getMeasurementType(),
                    userEmail,
                    null
            ));

            return result;

        } catch (Exception e) {
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getHistory(String userEmail) {
        return repository.findByUserEmailOrderByCreatedAtDesc(userEmail);
    }
}
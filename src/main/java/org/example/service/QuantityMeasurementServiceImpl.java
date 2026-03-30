package org.example.service;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.exception.QuantityMeasurementException;
import org.example.quantity.Quantity;
import org.example.repository.IQuantityMeasurementRepository;
import org.example.units.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    @Autowired
    private IQuantityMeasurementRepository repository;

    // 🔹 Convert DTO → quantity.Quantity Model
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
    public boolean compare(QuantityDTO q1,QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);

            if(!q1.getMeasurementType().equalsIgnoreCase(q2.getMeasurementType())){
                throw new QuantityMeasurementException("Different measurement types");
            }

            boolean result=quantity1.equals(quantity2);

            // SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "COMPARE",
                    String.valueOf(result)
            ));

            return result;

        }catch(Exception e){
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // CONVERT
    @Override
    public QuantityDTO convert(QuantityDTO source,String targetUnit){

        try{

            Quantity quantity=convertDTOToQuantity(source);

            IMeasurable unit=(IMeasurable)Enum.valueOf(
                    (Class<? extends Enum>)quantity.getUnit().getClass(),
                    targetUnit
            );

            Quantity result=quantity.convertTo(unit);

            // SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity.toString(),
                    targetUnit,
                    "CONVERT",
                    result.toString()
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // ADD
    @Override
    public QuantityDTO add(QuantityDTO q1,QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);

            Quantity result=quantity1.add(quantity2);

            // SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "ADD",
                    result.toString()
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    // SUBTRACT
    @Override
    public QuantityDTO subtract(QuantityDTO q1,QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);

            Quantity result=quantity1.subtract(quantity2);

            // SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "SUBTRACT",
                    result.toString()
            ));

            return convertQuantityToDTO(result);

        }catch(Exception e){
            throw new QuantityMeasurementException(e.getMessage());
        }
    }
    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2){

        try{

            Quantity quantity1=convertDTOToQuantity(q1);
            Quantity quantity2=convertDTOToQuantity(q2);

            double result=quantity1.divide(quantity2);

            // SAVE TO DB
            repository.save(new QuantityMeasurementEntity(
                    quantity1.toString(),
                    quantity2.toString(),
                    "DIVIDE",
                    String.valueOf(result)
            ));

            return result;

        }catch(Exception e){
            throw new QuantityMeasurementException(e.getMessage());
        }
    }
}
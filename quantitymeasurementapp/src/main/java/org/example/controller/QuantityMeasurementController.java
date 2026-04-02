package org.example.controller;

import org.example.dto.QuantityDTO;
import org.example.entity.QuantityMeasurementEntity;
import org.example.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

//    @GetMapping("/home")
//    public String home(){
//        return "App Started";
//    }
    @PostMapping("/compare")
    public boolean compare(@RequestBody QuantityDTO[] quantities, Authentication auth){

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        if (quantities == null || quantities.length < 2) {
            throw new IllegalArgumentException("Two quantities required");
        }

        return service.compare(quantities[0],quantities[1], auth.getName());
    }

    @PostMapping("/convert")
    public QuantityDTO convert(@RequestBody QuantityDTO quantity,
                               @RequestParam String targetUnit, Authentication auth){

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        return service.convert(quantity,targetUnit, auth.getName());
    }

    @PostMapping("/add")
    public QuantityDTO add(@RequestBody QuantityDTO[] quantities, Authentication auth){

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        if (quantities == null || quantities.length < 2) {
            throw new IllegalArgumentException("Two quantities required");
        }

        return service.add(quantities[0],quantities[1], auth.getName());
    }

    @PostMapping("/subtract")
    public QuantityDTO subtract(@RequestBody QuantityDTO[] quantities, Authentication auth){

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        if (quantities == null || quantities.length < 2) {
            throw new IllegalArgumentException("Two quantities required");
        }

        return service.subtract(quantities[0],quantities[1], auth.getName());
    }

    @PostMapping("/divide")
    public double divide(@RequestBody QuantityDTO[] quantities, Authentication auth){

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        if (quantities == null || quantities.length < 2) {
            throw new IllegalArgumentException("Two quantities required");
        }

        return service.divide(quantities[0], quantities[1], auth.getName());
    }

    @GetMapping("/history")
    public List<QuantityMeasurementEntity> getHistory(Authentication auth) {

        if (auth == null) {
            throw new RuntimeException("Unauthorized");
        }

        return service.getHistory(auth.getName());
    }
}
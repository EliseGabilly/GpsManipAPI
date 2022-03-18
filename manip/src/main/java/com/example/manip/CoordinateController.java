package com.example.manip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CoordinateController {

    @Autowired
    private final CoordinateRepository coordinateRepository;

    public CoordinateController(CoordinateRepository coordinateRepository) {
        this.coordinateRepository = coordinateRepository;
    }

}

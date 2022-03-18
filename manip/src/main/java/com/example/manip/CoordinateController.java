package com.example.manip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CoordinateController {

    @Autowired
    private final CoordinateRepository coordinateRepository;

    public CoordinateController(CoordinateRepository coordinateRepository) {
        this.coordinateRepository = coordinateRepository;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Coordinates> getAllCoordinates() {
        return coordinateRepository.findAll();
    }

    @PostMapping("/add")
    public void addCoordinates(@RequestBody Coordinates coordinates) {
        coordinateRepository.save(coordinates);
    }

    @PostMapping("/add_detail")
    public void addCoordinatesWithDetail(@RequestParam double latitude, @RequestParam double longitude) {
        coordinateRepository.save(new Coordinates(latitude, longitude));
    }


}

package com.example.manip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @DeleteMapping("/del/{id}")
    public Map<String, Boolean> deleteCoordinate(@PathVariable(value = "id") Long id) throws Exception {
        Coordinates coordinates = coordinateRepository.findById(id)
                .orElseThrow(() -> new Exception("Coordinates not found for this id :: " + id));
        coordinateRepository.delete(coordinates);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @GetMapping(path="/comp")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<String> compareCoordinates(@RequestParam Coordinates start_coord, @RequestParam Coordinates end_coord) {
        double distance = Utils.distance(start_coord.getLatitude(), start_coord.getLongitude(), end_coord.getLatitude(), end_coord.getLongitude());
        boolean isClose = distance<=10;
        return new ResponseEntity<>("{\"is_close\" : "+isClose+", \"distance\" : "+distance+" }", HttpStatus.OK);
    }

    @GetMapping(path="/comp_detail")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<String> compareCoordinatesWithDetail(@RequestParam double start_lat, @RequestParam double start_long,
                                                     @RequestParam double end_lat, @RequestParam double end_long) {
        double distance = Utils.distance(start_lat, start_long, end_lat, end_long);
        boolean isClose = distance<=10;
        return new ResponseEntity<>("{\"is_close\" : "+isClose+", \"distance\" : "+distance+" }", HttpStatus.OK);
    }

}

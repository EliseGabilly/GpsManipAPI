package com.example.manip;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void addCoordinates(@RequestBody String coordinatesStr)  {
        ObjectMapper mapper = new ObjectMapper();

        Coordinates coordinates;
        try{
            coordinates = mapper.reader().forType(Coordinates.class).readValue(coordinatesStr);
            coordinateRepository.save(coordinates);
        } catch (JsonProcessingException ignored){
        }
    }

    @PostMapping("/add_detail")
    public void addCoordinatesWithDetail(@RequestParam String latitude, @RequestParam String longitude) {
        double latitudeVal = 0, longitudeVal = 0;
        try{
            latitudeVal = Double.parseDouble(latitude);
            longitudeVal = Double.parseDouble(longitude);
        } catch (NumberFormatException ignored){
        }
        coordinateRepository.save(new Coordinates(latitudeVal, longitudeVal));
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

    @GetMapping(path="/comp_detail")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<String> compareCoordinates(@RequestParam Coordinates start_coord, @RequestParam Coordinates end_coord) {
        double distance = Utils.distance(start_coord.getLatitude(), start_coord.getLongitude(), end_coord.getLatitude(), end_coord.getLongitude());
        boolean isClose = distance<=10;
        return new ResponseEntity<>("{\"is_close\" : "+isClose+", \"distance\" : "+distance+" }", HttpStatus.OK);
    }

    @GetMapping(path="/comp")
    @ResponseBody
    @ResponseStatus( HttpStatus.OK )
    public ResponseEntity<String> compareCoordinatesWithDetail(@RequestParam String start_lat, @RequestParam String start_long,
                                                     @RequestParam String end_lat, @RequestParam String end_long) {
        double startLat, startLong, endLat, endLong;
        try{
            startLat = Double.parseDouble(start_lat);
            startLong = Double.parseDouble(start_long);
            endLat = Double.parseDouble(end_lat);
            endLong = Double.parseDouble(end_long);
        } catch (NumberFormatException e){
            return new ResponseEntity<>("{\"responce\" : \"NumberFormatException\" }", HttpStatus.OK);
        }
        double distance = Utils.distance(startLat, startLong, endLat, endLong);
        boolean isClose = distance<=10;
        return new ResponseEntity<>("{\"is_close\" : "+isClose+", \"distance\" : "+distance+" }", HttpStatus.OK);
    }

}

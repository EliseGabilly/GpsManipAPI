package com.example.manip;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoordinateControllerTest {

    @Autowired
    private CoordinateController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void addCoordinates() {
        //init
        Iterable<Coordinates> allCoordinates = controller.getAllCoordinates();
        int countBefore = ((Collection<Coordinates>) allCoordinates).size();
        //test
        String coordinatesStr = "{\"latitude\":2, \"longitude\":3}";
        controller.addCoordinates(coordinatesStr);
        //assert
        allCoordinates = controller.getAllCoordinates();
        assertEquals(countBefore+1, ((Collection<Coordinates>) allCoordinates).size());
    }

    @Test
    void addCoordinates_catch() {
        //init
        ObjectMapper mapper = new ObjectMapper();
        String coordinatesStr = "{\"latitude\":bla, \"longitude\":bla}";
        //test
        assertThrows(JsonProcessingException.class, () -> mapper.reader().forType(Coordinates.class).readValue(coordinatesStr));
    }

    @Test
    void addCoordinatesWithDetail() {
        //init
        Iterable<Coordinates> allCoordinates = controller.getAllCoordinates();
        int countBefore = ((Collection<Coordinates>) allCoordinates).size();
        //test
        controller.addCoordinatesWithDetail("2", "3");
        //assert
        allCoordinates = controller.getAllCoordinates();
        assertEquals(countBefore+1, ((Collection<Coordinates>) allCoordinates).size());
    }

    @Test
    void deleteCoordinate_valid() throws Exception {
        //init
        Iterable<Coordinates> allCoordinates = controller.getAllCoordinates();
        int countBefore = ((Collection<Coordinates>) allCoordinates).size();
        //test
        controller.deleteCoordinate((long)1);
        //assert
        allCoordinates = controller.getAllCoordinates();
        assertEquals(countBefore-1, ((Collection<Coordinates>) allCoordinates).size());
    }

    @Test
    void deleteCoordinate_throw() {
        //test
        Exception exception = assertThrows(Exception.class, () -> controller.deleteCoordinate((long)999));
        assertEquals("Coordinates not found for this id :: " + 999, exception.getMessage());
    }

    @Test
    void compareCoordinatesWithDetail_valid_close() {
        ResponseEntity<String> stringResponseEntity = controller.compareCoordinatesWithDetail("2", "2", "2", "2");

        String expected = "{\"is_close\" : true, \"distance\" : 0.0 }";
        assertEquals(expected, stringResponseEntity.getBody());
        assertEquals(200, stringResponseEntity.getStatusCodeValue());
    }

    @Test
    void compareCoordinatesWithDetail_valid_far() {
        ResponseEntity<String> stringResponseEntity = controller.compareCoordinatesWithDetail("2", "2", "3", "3");

        String expected = "{\"is_close\" : false, \"distance\" : 157.17755181464074 }";
        assertEquals(expected, stringResponseEntity.getBody());
        assertEquals(200, stringResponseEntity.getStatusCodeValue());
    }

    @Test
    void compareCoordinatesWithDetail_catch() {
        ResponseEntity<String> stringResponseEntity = controller.compareCoordinatesWithDetail("b", "o", "b", "y");

        String expected = "{\"responce\" : \"NumberFormatException\" }";
        assertEquals(expected, stringResponseEntity.getBody());
        assertEquals(200, stringResponseEntity.getStatusCodeValue());
    }
}
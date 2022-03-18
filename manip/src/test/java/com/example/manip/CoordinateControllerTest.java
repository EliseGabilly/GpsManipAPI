package com.example.manip;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        //init
        Iterable<Coordinates> allCoordinates = controller.getAllCoordinates();
        //test
        Exception exception = assertThrows(Exception.class, () -> controller.deleteCoordinate((long)999));
        assertEquals("Coordinates not found for this id :: " + 999, exception.getMessage());
    }
}
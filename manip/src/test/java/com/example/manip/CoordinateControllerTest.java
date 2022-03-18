package com.example.manip;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

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
        Coordinates coordinates = new Coordinates(2, 3);
        controller.addCoordinates(coordinates);
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
        controller.addCoordinatesWithDetail(2, 3);
        //assert
        allCoordinates = controller.getAllCoordinates();
        assertEquals(countBefore+1, ((Collection<Coordinates>) allCoordinates).size());
    }
}
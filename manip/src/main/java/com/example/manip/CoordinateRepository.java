package com.example.manip;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends CrudRepository<Coordinates, Long> {
}

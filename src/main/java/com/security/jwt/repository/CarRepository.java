package com.security.jwt.repository;

import com.security.jwt.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByDoors(Integer doors);
    List<Car> findByManufacturerAndModel(String manufacturer, String model);
    List<Car> findByDoorsGreaterThanEqual(Integer doors);
    List<Car> findyByModelContaining(String model);
    List<Car> findByYearIn(List<Integer> years);
    List<Car> findByYearBetween(Integer startYear, Integer endYear);
    List<Car> findByReleaseDateBetween(Date starDate, Date endDate);
    List<Car> findByAvailableTrue();
    Long deleteAllByAvailableFalse();

}

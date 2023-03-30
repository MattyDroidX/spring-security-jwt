package com.security.jwt.service;

import com.security.jwt.domain.Car;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CarService {
    List<Car> findAll();
    Optional<Car> findById(Long id);
    Long count();
    Car save(Car car);
    void deleteById(Long id);
    void deleteAll();
    void deleteAll(List<Car> cars);
    void deleteAllById(List<Long> ids);

//    Custom methods

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

package com.security.jwt.service.implementation;

import com.security.jwt.domain.Car;
import com.security.jwt.repository.CarRepository;
import com.security.jwt.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CarServiceImplementation implements CarService {

    private static final Integer MIN_DOORS = 3;
    private final Logger log = LoggerFactory.getLogger(CarServiceImplementation.class);
    private final CarRepository carRepository;

    public CarServiceImplementation(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> findAll() {
        log.info("Executing findAll Cars");
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> findById(Long id) {
        log.info("Executing findById");
        return carRepository.findById(id);
    }

    public List<Car> findByDoors(Integer doors){
        log.info("Searching cars by doors");
        if(doors<MIN_DOORS){
            log.warn("Trying to search less than allowed doors");
            return new ArrayList<>();
        }
        return carRepository.findByDoors(doors);
    }

    @Override
    public List<Car> findByManufacturerAndModel(String manufacturer, String model) {
        if(!StringUtils.hasLength(manufacturer) || !StringUtils.hasLength(model)){
            return new ArrayList<>();
        }
        return carRepository.findByManufacturerAndModel(manufacturer,model);
    }

    @Override
    public List<Car> findByDoorsGreaterThanEqual(Integer doors) {
        if(doors ==null || doors < 0){
            return new ArrayList<>();
        }
        return carRepository.findByDoorsGreaterThanEqual(doors);
    }

    @Override
    public List<Car> findyByModelContaining(String model) {
        return carRepository.findyByModelContaining(model);
    }

    @Override
    public List<Car> findByYearIn(List<Integer> years) {
        return carRepository.findByYearIn(years);
    }

    @Override
    public List<Car> findByYearBetween(Integer startYear, Integer endYear) {
        return carRepository.findByYearBetween(startYear,endYear);
    }

    @Override
    public List<Car> findByReleaseDateBetween(Date starDate, Date endDate) {
        return carRepository.findByReleaseDateBetween(starDate,endDate);
    }

    @Override
    public List<Car> findByAvailableTrue() {
        return carRepository.findByAvailableTrue();
    }

    @Override
    public Long deleteAllByAvailableFalse() {
        return carRepository.deleteAllByAvailableFalse();
    }

    @Override
    public Long count() {
        log.info("Get total number of cars");
        return carRepository.count();
    }

    @Override
    public Car save(Car car) {
        log.info("Creating / Updating car");
        // pre build

        if(!this.validateCar(car))
            return null;

        //Actions
        // find template from db

        Car carDB = carRepository.save(car);

        //Post:
        // send notification
        // this.notificationService(NotificationType.CREATION, car)

        return carDB;
    }

    private Boolean validateCar(Car car){
        if(car == null){
            log.warn("Trying to create null car");
            return false;
        }
        if(car.getDoors() == null || car.getDoors()<MIN_DOORS){
            log.warn("Trying to create car with not allowed number of doors");
            return false;
        }
        //color validation
        //.....
        return true;
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting car by id");
        if(id == null || id<0 || id == 0){
            log.warn("Trying to delete car with wrong id");
            return;
        }
        try{
            carRepository.deleteById(id);
        }catch (Exception e){
            log.error("Error trying to delete car by id {}", id, e);
        }
    }

    @Override
    public void deleteAll() {
        log.info("Deleting Cars");
        carRepository.deleteAll();
    }

    @Override
    public void deleteAll(List<Car> cars) {
        log.info("Deleting Car by id");
        if(CollectionUtils.isEmpty(cars)){
            log.warn("Trying to delete an empty or null car list");
            return;
        }
        carRepository.deleteAll(cars);
    }

    @Override
    public void deleteAllById(List<Long> ids) {
        log.info("Deleting Car by id");
        if (CollectionUtils.isEmpty(ids)) {
            log.warn("Trying to delete an empty or null car list");
            return;
        }
        carRepository.deleteAllById(ids);
    }
}

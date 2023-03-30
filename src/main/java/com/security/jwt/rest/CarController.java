package com.security.jwt.rest;

import com.security.jwt.domain.Car;
import com.security.jwt.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CarController {
    private final Logger log = LoggerFactory.getLogger(CarController.class);
    private CarService carService;

    @GetMapping("/cars/{id}")
    @ApiOperation("Search car for id")
    public ResponseEntity<Car>findCarById(@ApiParam("Clave primaria car") Long id){
        log.info("REST request to find one car");
        Optional<Car> carOpt = carService.findById(id);
//        if (carOpt.isPresent()){
////            return ResponseEntity.ok(carOpt.get());
////        }
////        return ResponseEntity.notFound().build();
        return carOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cars")
    public List<Car> findAll(){
        log.info("REST request to find all cars");
        return carService.findAll();
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> create(@RequestBody Car car){
        log.info("REST request to create a new car");
        if(car.getId() !=null){
            log.warn("trying to create a new car with existend id");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(carService.save(car));
    }


}

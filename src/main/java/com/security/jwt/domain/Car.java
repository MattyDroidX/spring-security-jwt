package com.security.jwt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fabricante")
    private String manufacturer;
    private String model;
    private Double cc;
    private Integer doors;

    private Integer year;
    private Date releaseDate;
    private Boolean available;


}

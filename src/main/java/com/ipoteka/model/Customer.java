package com.ipoteka.model;

import lombok.*;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;
    private int homePrice;
    private int time;


    public Customer(String firstName, String lastName, Date birthDate, int homePrice, int time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.homePrice = homePrice;
        this.time = time;
    }
}

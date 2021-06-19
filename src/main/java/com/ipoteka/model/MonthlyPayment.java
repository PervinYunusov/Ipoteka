package com.ipoteka.model;


import lombok.*;
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

public class MonthlyPayment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int month;
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private LocalDate  date;
    private BigDecimal monthAmount;
    private BigDecimal percentMonthAmount;
    private BigDecimal noPercentMonthAmount;
    private BigDecimal basicAmount;
    private BigDecimal totalAmount;
    private BigDecimal initialPayment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public MonthlyPayment(int month, LocalDate date,
                          BigDecimal monthAmount,
                          BigDecimal percentMonthAmount,
                          BigDecimal noPercentMonthAmount,
                          BigDecimal basicAmount,
                          BigDecimal totalAmount,
                          BigDecimal initialPayment) {
        this.month = month;
        this.date = date;
        this.monthAmount = monthAmount;
        this.percentMonthAmount = percentMonthAmount;
        this.noPercentMonthAmount = noPercentMonthAmount;
        this.basicAmount = basicAmount;
        this.totalAmount = totalAmount;
        this.initialPayment = initialPayment;
    }
}

package com.ipoteka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentResponse {

    private int monthNumber;
   // @DateTimeFormat(pattern = "mm-dd-yyyy")
    private LocalDate date;
    private double monthAmount;
    private double percentMonthAmount;
    private double noPercentMonthAmount;
    private double basicAmount;
    private double totalAmount;
    private double initialPayment;
}

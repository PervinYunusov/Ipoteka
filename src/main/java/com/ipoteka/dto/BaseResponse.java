package com.ipoteka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {

    private CustomerRequest customerRequest;
    private List<PaymentResponse> paymentResponseList;
}

package com.ipoteka.service;


import com.ipoteka.dto.BaseResponse;
import com.ipoteka.dto.CustomerRequest;
import com.ipoteka.dto.PaymentResponse;
import com.ipoteka.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AllService allService;


    public BaseResponse sendResponseToFront(CustomerRequest customerRequest){


        //TODO ITS TEMPLATE
      BaseResponse baseResponse = new BaseResponse();

      List<PaymentResponse> paymentResponseList = allService.listQaytar(customerRequest);
      baseResponse.setCustomerRequest(customerRequest);
      baseResponse.setPaymentResponseList(paymentResponseList);



      return baseResponse;

    }




}

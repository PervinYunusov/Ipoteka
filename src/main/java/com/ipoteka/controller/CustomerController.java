package com.ipoteka.controller;


import com.ipoteka.dto.BaseResponse;
import com.ipoteka.dto.CustomerRequest;
import com.ipoteka.dto.PaymentResponse;
import com.ipoteka.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller


public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/showNewForm")
    public String showNewForm(Model model) {
        CustomerRequest customerRequest = new CustomerRequest();
        model.addAttribute("customer", customerRequest);
        return "customer_form";
    }

    @PostMapping("/show-result")
    public String showResult(@ModelAttribute("customer") CustomerRequest customerRequest,Model model){
        BaseResponse baseResponse = customerService.sendResponseToFront(customerRequest);
        model.addAttribute("baseResponse",baseResponse.getPaymentResponseList());
        return "monthly_payment";
    }




}

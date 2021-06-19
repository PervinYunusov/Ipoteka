package com.ipoteka.service;

import com.ipoteka.dto.CustomerRequest;
import com.ipoteka.dto.PaymentResponse;

import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllService {


    public static final double maxAmount = 150000;
    public static final double yearPercent = 0.08;
    public static final int maxAge = 65;
    public static final double initialPaymentPercent = 0.25;
    public static final double monthPercent = 0.0067;
    public static final int maxTime = 300;


    //public static final double diskontFaktoru = 119.55;

    DecimalFormat df = new DecimalFormat("###.##");


    public double diskont(CustomerRequest customerRequest) {

        double diskont1 = Math.pow((1 + monthPercent), customerRequest.getMonths()) - 1;
        double diskont2 = (diskont1 + 1) * monthPercent;
        double diskontFaktoru = (diskont1 / diskont2);


        df.setRoundingMode(RoundingMode.CEILING);

        String x = df.format(diskont1);
        double y = Double.valueOf(x);

        String a = df.format(y / diskont2);
        double b = Double.valueOf(a);
        return b;
    }


    public List<PaymentResponse> listQaytar(CustomerRequest customerRequest) {

        //CustomerResponse customerResponse = new CustomerResponse();
        System.out.println(" diskont Faktoru = " + diskont(customerRequest));
        List<PaymentResponse> paymentList = new ArrayList<>();


         LocalDate localDate = LocalDate.now();
        double monthAmount1 = calculateMonthAmount(customerRequest);
        double percentMonthAmount1 = calculatePercentMonthAmount(customerRequest);
        double noPercentMonthAmount1 = calculateNoPercentMonthAmount(customerRequest);
      double basicAmount1 = calculateBasicAmount(customerRequest) - percentMonthAmount1;
        double totalAmount1 = calculateTotalAmount(customerRequest);
        double initialPayment1 = calculateInitialPayment(customerRequest);




        for (int i = 1; i <= customerRequest.getMonths(); i++) {
            PaymentResponse monthlyPayment = new PaymentResponse();

            monthlyPayment.setMonthNumber(i);
            monthlyPayment.setDate(localDate);
            monthlyPayment.setBasicAmount(basicAmount1);
            monthlyPayment.setMonthAmount(monthAmount1);
            monthlyPayment.setNoPercentMonthAmount(noPercentMonthAmount1);
            monthlyPayment.setPercentMonthAmount(percentMonthAmount1);
            monthlyPayment.setTotalAmount(totalAmount1);
            monthlyPayment.setInitialPayment(initialPayment1);


            noPercentMonthAmount1 = (((basicAmount1) * yearPercent)) / 12;


            basicAmount1 = basicAmount1 - percentMonthAmount1;
            percentMonthAmount1 = monthAmount1 - noPercentMonthAmount1;
            // esasMebleg1 = esasMebleg1 - (faiznenAyliqOdenis1);

            localDate = monthlyPayment.getDate().plusMonths(1);

            paymentList.add(monthlyPayment);
        }
        return paymentList;
    }


//    public static final int maxAmount = 100000;
//    public static final BigDecimal yearPercent = BigDecimal.valueOf(0.08);
//    public static final int maxAge = 65;
//    public static final BigDecimal initialPaymentPercent = BigDecimal.valueOf(0.25);
//    public static final BigDecimal monthPercent = BigDecimal.valueOf(0.0067);
//    public static  final int maxTime = 300;
//    LocalDate localDate1 = LocalDate.now();
//
//
//
//
//
//            CustomerResponse customerResponse = new CustomerResponse();
//
//    public List<PaymentResponse> listQaytar(CustomerResponse customerResponse) {
//
//        System.out.println( " diskont Faktoru = " +  calculateDiskont());
//        List<PaymentResponse> paymentList = new ArrayList<>();
//
//
//        LocalDate localDate = LocalDate.now();
//
//
//        BigDecimal monthAmount1 = calculateMonthAmount();
//        BigDecimal percentMonthAmount1 = calculatePercentMonthAmount();
//        BigDecimal noPercentMonthAmount1 = calculateNoPercentMonthAmount();
//        BigDecimal basicAmount1 = calculateBasicAmount().subtract(percentMonthAmount1);
//
//
//        for (int i = 1; i <= this.customerResponse.getMonths(); i++) {
//
//            PaymentResponse monthlyPayment = new PaymentResponse();
//
//
//            monthlyPayment.setMonthNumber(i);
//            monthlyPayment.setDate(localDate);
//            monthlyPayment.setBasicAmount(basicAmount1);
//            monthlyPayment.setMonthAmount(monthAmount1);
//            monthlyPayment.setNoPercentMonthAmount(noPercentMonthAmount1);
//
//            monthlyPayment.setPercentMonthAmount(percentMonthAmount1);
//
//
//
//            noPercentMonthAmount1 = (((basicAmount1).multiply(yearPercent))).divide(BigDecimal.valueOf(12));
//
//
//            basicAmount1 = basicAmount1.subtract(percentMonthAmount1);
//            percentMonthAmount1 = monthAmount1.subtract(noPercentMonthAmount1);
//            // esasMebleg1 = esasMebleg1 - (faiznenAyliqOdenis1);
//
//            localDate = monthlyPayment.getDate().plusMonths(1);
//
//            paymentList.add(monthlyPayment);
//
//
//        }
//
//        return paymentList;
//    }
//
//        public BigDecimal calculateDiskont() {
////            BigDecimal diskontFaktoru = (((BigDecimal.valueOf(1).add(monthPercent)).pow(customerResponse.getMonths())).subtract(BigDecimal.valueOf(1)))
////                    .divide(((BigDecimal.valueOf(1).add(monthPercent)).pow(customerResponse.getMonths())).multiply(monthPercent));
//            double diskont1 = Math.pow((1 + monthPercent.doubleValue()), customerResponse.getMonths()) - 1;
//            double diskont2 = (diskont1 + 1) * monthPercent.doubleValue();
//            double diskontFaktoru = (diskont1 / diskont2);
//
//            DecimalFormat df =new DecimalFormat("#.0#");
//            df.setRoundingMode(RoundingMode.CEILING);
//
//            String x =  df.format(diskont1);
//            double y = Double.valueOf(x);
//
//            String  a =df.format(y/diskont2);
//            double b = Double.valueOf(a);
//            return BigDecimal.valueOf(b) ;
//
//
//        }
//
//
//
//        public BigDecimal calculateBasicAmount(){
//
//          BigDecimal  basicAmount = BigDecimal.valueOf(customerResponse.getHomePrice()).subtract(BigDecimal.valueOf(customerResponse.getHomePrice()).multiply(initialPaymentPercent));
//
//
//            return basicAmount;
//        }
//
//
//        public BigDecimal calculateMonthAmount(){
//
//            BigDecimal monthAmount =  calculateBasicAmount().divide(calculateDiskont());
//
//            return monthAmount;
//        }
//
//        public BigDecimal calculateTotalAmount(){
//
//            BigDecimal totalAmount =  calculateMonthAmount().multiply(BigDecimal.valueOf(customerResponse.getMonths()));
//
//            return totalAmount;
//        }
//
//        public BigDecimal calculatePercentMonthAmount(){
//
//            BigDecimal percentMonthAmount = calculateBasicAmount().multiply(monthPercent);
//
//            return percentMonthAmount;
//        }
//
//        public BigDecimal calculateNoPercentMonthAmount(){
//
//            BigDecimal noPercentMonthAmount = calculateMonthAmount().subtract(calculatePercentMonthAmount());
//
//            return noPercentMonthAmount;
//        }
//
//    public BigDecimal calculateInitialPayment() {
//
//
//        BigDecimal initialPayment, addPayment;
//        if (customerResponse.getHomePrice() > maxAmount) {
//
//            addPayment = BigDecimal.valueOf(customerResponse.getHomePrice() - maxAmount);
//
//            initialPayment = addPayment.add(BigDecimal.valueOf(maxAmount).multiply(initialPaymentPercent));
//
//
//            return initialPayment;
//
//
//        } else {
//
//            initialPayment = BigDecimal.valueOf(customerResponse.getHomePrice()).multiply(initialPaymentPercent);
//            return initialPayment;
//        }
//
//
//    }
//
//    public boolean credit() {
//
//
//        if ((localDate1.getYear() - customerResponse.getBirthDate().getYear()) < maxAge && customerResponse.getHomePrice() > 30000 && (localDate1.getYear() - customerResponse.getBirthDate().getYear()) > 18 && customerResponse.getMonths() <=maxTime) {
//
//
//            return true;
//        } else {
//
//
//            return false;
//        }
//    }


    LocalDate localDate1 = LocalDate.now();

    public double calculateInitialPayment(CustomerRequest customerRequest) {


        double initialPayment, addPayment;
        if (customerRequest.getHomePrice() > maxAmount) {

            addPayment = customerRequest.getHomePrice() - maxAmount;

            initialPayment = addPayment + (maxAmount * initialPaymentPercent);


            return initialPayment;


        } else {

            initialPayment = customerRequest.getHomePrice() * initialPaymentPercent;
            return initialPayment;
        }


    }
    /*





     */


    public double calculateTotalAmount(CustomerRequest customerRequest) {



        double   totalAmount = calculateMonthAmount(customerRequest) * customerRequest.getMonths();

        df.setRoundingMode(RoundingMode.CEILING);
        String format = df.format(totalAmount);

        double totalAmountRound = Double.valueOf(format);

        return totalAmountRound;
    }

    public double calculateBasicAmount(CustomerRequest customerRequest) {



        double  basicAmount = customerRequest.getHomePrice() - calculateInitialPayment(customerRequest);

        df.setRoundingMode(RoundingMode.CEILING);
        String format = df.format(basicAmount);

        double basicAmountRound = Double.valueOf(format);

        return basicAmountRound;
    }

    public double calculateMonthAmount(CustomerRequest customerRequest) {


        double monthAmount = calculateBasicAmount(customerRequest) / diskont(customerRequest);

        df.setRoundingMode(RoundingMode.CEILING);

        String format = df.format(monthAmount);

        double monthAmountRound = Double.valueOf(format);


        return monthAmountRound;

    }

    public double calculateNoPercentMonthAmount(CustomerRequest customerRequest) {


        double noPercentMonthAmount = (calculateBasicAmount(customerRequest) * (yearPercent)) / 12;

        df.setRoundingMode(RoundingMode.CEILING);

        String format = df.format(noPercentMonthAmount);

        double noPercentMonthAmountRound = Double.valueOf(format);

        return noPercentMonthAmountRound;

    }


    public double calculatePercentMonthAmount(CustomerRequest customerRequest) {



        double  percentMonthAmount = calculateMonthAmount(customerRequest) - (calculateNoPercentMonthAmount(customerRequest));

        df.setRoundingMode(RoundingMode.CEILING);

        String format = df.format(percentMonthAmount);

        double percentMonthAmountRound = Double.valueOf(format);

        return percentMonthAmountRound;
    }

//    public boolean credit(CustomerRequest customerRequest) {
//
//
//        if ((localDate1.getYear() - customerRequest.getBirthDate().getYear()) < maxAge && customerRequest.getHomePrice() > 30000 && (localDate1.getYear() - customerRequest.getBirthDate().getYear()) > 18 && customerRequest.getMonths() <= maxTime) {
//
//
//            return true;
//        } else {
//
//
//            return false;
//        }
//    }


}

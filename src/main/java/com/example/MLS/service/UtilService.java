package com.example.MLS.service;


import com.example.MLS.entity.House;
import com.example.MLS.entity.Mortgage;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.Math.pow;
import static java.util.Calendar.*;

@Service
public class UtilService {

    public Period getAge(Date date) {

        //LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate = date.toLocalDate();
        LocalDate now = LocalDate.now();
        Period period = Period.between(localDate, now);
        //System.out.println(period.getYears() + " years, " + period.getMonths() + " months, " + period.getDays() + " days");

        return period;
    }

    public String nextMortgagePaymentDue(Integer monthlyDueDay) {


        Calendar today = Calendar.getInstance();
//        System.out.println("today date: " + today.getTime());
        Calendar next = Calendar.getInstance();
        next.clear();
        next.set(YEAR, today.get(YEAR));
        next.set(MONTH, today.get(MONTH) + 1);
        next.set(DAY_OF_MONTH, Optional.ofNullable(monthlyDueDay).orElse(1));
//        System.out.println("next date: " + next.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
        sdf.applyPattern("EEE, d MMM yyyy");
        String formatedDate = sdf.format(next.getTime());

        return formatedDate;
        //return "" + next.get(Calendar.DAY_OF_WEEK) + " " + next.get(MONTH) + " " +next.get(DAY_OF_MONTH) + " " + next.get(YEAR);
    }

    public String amortizationSchedule(Float _interestP, Integer _year, Float _principal) {
//        String schedule = "";
        String schedule = "<div class=\"panel-group row p-4\" id=\"accordion\">\n";

        float _fixed = calculateFixed(_interestP, _year, _principal);
        float interest = 0, principal = 0, interestTot = 0;
        int year = 1, month = 1;
        while(year != (_year+1))
        {
//            schedule += schedule= "<div class=\"col-4\">";
            schedule += "<div class=\"panel panel-default col-md-4 col-6\">" +
                    "      <div class=\"panel-heading\">" +
                    "        <h4 class=\"panel-title\">" +
                    "          <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse" + year + "\">" +
                    "          Year " + year + "</a>" +
                    "        </h4>" +
                    "      </div>" +
                    "      <div id=\"collapse" + year + "\" class=\"panel-collapse collapse in\">" +
                    "        <div class=\"panel-body\" style=\"text-align:center;\">";
            //cout << "Year " << year << endl;
//            schedule += "Year " + year + "<br>";
            while(month != 13)
            {
                if(_principal != 0)
                {
                    interest = _principal*_interestP/1200; // money paid in interest
                    interestTot += interest;
                    principal = _fixed - interest; // money paid in principal
                    _principal -= principal;
                }

                schedule += "<strong>Month " + month + "</strong><br>";
                schedule += "<table class=\"mb-3\" style=\"margin:auto;\">\n" +
                        "    <tr>\n" +
                        "      <th style=\"padding:0 15px;\">Principal</th>\n" +
                        "      <th style=\"padding:0 15px;\">Interest</th>\n" +
                        "    </tr>\n" +
                        "    <tr>\n" +
                        "      <td style=\"padding:0 15px;\">$" + String.format("%.02f", principal) + "</td>" +
                        "      <td style=\"padding:0 15px;\">$" + String.format("%.02f", interest) + "</td>" +
                        "    </tr>\n" +
                        "   </table>";
//                schedule += "Principal $" + principal + "&emsp;" + "Interest $" + interest + "<br>";
//                cout << "Month " << month << endl;
//                cout << "Principal $" << principal << setw(20) << "Interest $" << interest << endl;

                month++;
            }

            if(month == 13)
            { // cycle through years
                year++;
                schedule += "<br></div></div></div>";
//                cout << endl;
                month -= 12;
            }
        }
        schedule += "</div>";
        schedule += "Total interest paid is $" + interestTot + "." + "<br>";
//        cout << "Total interest paid is $" << interestTot << "." << endl;

        return schedule;
    }

    public float calculateFixed(Float _interestP, Integer _year, Float _principal) {
        float fixed;
        Float c = _interestP/1200;
        Integer n = _year*12;

        fixed = (float)(_principal*(c*pow(1+c, n))/(pow(1+c, n) - 1));

        return fixed;
    }

    public HashMap<String, String> equityCalculator(Mortgage mortgage, House house) {
        float equity = house.getDownPaymentAmount(), interestPaid = 0, principalPaid = 0;
        Period age = getAge(house.getClosingDate());

        Float _interestP = mortgage.getInterestRate();
        Float _principal = mortgage.getTotalInitialLoan();
//        Integer _year = age.getYears();
        Integer _year = mortgage.getLength();
        float _fixed = calculateFixed(_interestP, _year, _principal);
        float interest = 0, principal = 0, interestTot = 0;

        Integer yearAge = age.getYears();
        int year = 1, month = 13 - age.getMonths();
        while(year != (_year+1))
        {
            //cout << "Year " << year << endl;
            while(month != 13)
            {
                if(_principal != 0)
                {
                    interest = _principal*_interestP/1200; // money paid in interest
                    interestTot += interest;
                    principal = _fixed - interest; // money paid in principal
                    _principal -= principal;
                }
                principalPaid += principal;
//                cout << "Month " << month << endl;
//                cout << "Principal $" << principal << setw(20) << "Interest $" << interest << endl;

                month++;
            }

            if(year > yearAge)
                break;

            if(month == 13)
            { // cycle through years
                year++;
//                cout << endl;
                month -= 12;
            }
        }
//        schedule += "Total interest paid is $" + interestTot + "." + "<br>";

        equity += principalPaid;

        DecimalFormat formatter = new DecimalFormat("#,###.00");
        DecimalFormat percentFormatter = new DecimalFormat(" #,##0.00 %");
        percentFormatter.setMultiplier(1);

        HashMap<String, String> equityCalc = new HashMap<>();
        equityCalc.put("PrincipalPaid", formatter.format(principalPaid));
        equityCalc.put("InterestPaid", formatter.format(interestTot));
        equityCalc.put("Equity", formatter.format(equity));
        equityCalc.put("EquityPercent", percentFormatter.format((equity/house.getPurchasePrice())*100));


        return equityCalc;
    }

    public String formatMoney(float num) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(num);
    }

}

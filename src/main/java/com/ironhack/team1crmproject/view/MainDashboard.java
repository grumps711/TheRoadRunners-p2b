package com.ironhack.team1crmproject.view;

import com.ironhack.team1crmproject.service.OpportunityService;
import com.ironhack.team1crmproject.utils.TerminalTools;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainDashboard {

    private final CrmDashboard crmDashboard;
    private final Scanner inputScanner;

    public MainDashboard(CrmDashboard crmDashboard, Scanner inputScanner) {
        this.crmDashboard = crmDashboard;
        this.inputScanner = inputScanner;
    }

    public void run() {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            try {
                System.out.println("𝐂𝐑𝐌");
                System.out.println(TerminalTools.ANSI_BLUE);
                System.out.println("""
                        --Lead Creation--
                        To create lead type -> NEW LEAD
                        
                        --Lead Conversion--
                        To CONVERT a known lead to an opportunity type -> CONVERT (followed by lead's id for conversion)
                                                
                        --Show All--
                        To show all lead/contact/account/opportunity on screen type -> SHOW LEADS/CONTACTS/ACCOUNTS/OPPORTUNITIES
                                                
                        --Lookup by id--
                        To check a specific lead/contact/account/opportunity type -> LOOKUP LEAD/CONTACT/ACCOUNT/OPPORTUNITY (followed by id)
                                             
                        --Change opportunity status--
                        To edit the opportunity status type -> CLOSE-WON/CLOSE-LOST (followed by id of the Opportunity that is a success/lost)
                                                
                        --Check count of Opportunities--
                        To check the count of all Opportunities sorted type -> REPORT OPPORTUNITY BY PRODUCTx /COUNTRYx /CITYx /INDUSTRY
                                                
                        --Opportunity status--
                        To check the count of all opportunity status sorted type -> REPORT CLOSED-WON/CLOSED-LOST/OPEN BY PRODUCT/COUNTRY/CITY/INDUSTRY
                        
                        --Number calculations--
                        To check the mean/median/maximum/minimum of the Employee-Count/Quantity/Opportunities type -> MEAN/MEDIAN/MAX/MIN EMPLOYEECOUNT/QUANTITY/OPPSPERACCOUNT

                        --Exit--
                        To exit please type -> EXIT
                        """);
                input = inputScanner.nextLine();
                String[] inputArray = input.split(" ");

                if (input.equalsIgnoreCase("EXIT")) {
                    System.out.println("Thanks for using this CRM application.");
                    System.exit(0);
                } else if ((inputArray[0].equalsIgnoreCase("CLOSE-WON")||(inputArray[0].equalsIgnoreCase("CLOSE-LOST")))) {
                    crmDashboard.setOpportunityStatus(inputArray[0], inputArray[1]);
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("NEW LEAD")) {
                    System.out.println("you want to create a new lead");
                    crmDashboard.createLead();
                }
               else if ((inputArray[0] + " " + inputArray[1] + " " + inputArray[2]).equalsIgnoreCase("REPORT OPPORTUNITY BY")) {
                    crmDashboard.checkCountOpportunityBy(inputArray[3]);}
                 else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP LEAD"))) {
                    crmDashboard.showLead(Long.parseLong(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW LEADS")) {
                    crmDashboard.showLead(null);
                } else if ((inputArray[0]).equalsIgnoreCase("CONVERT")) {
                    System.out.println("you want to convert a lead (with lead's id " + inputArray[1] + ") to an opportunity");
                    crmDashboard.convertLeadToOpportunity(Long.parseLong(inputArray[1]));
                } else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP ACCOUNT"))) {
                    crmDashboard.showLead(Long.parseLong(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW ACCOUNTS")) {
                    crmDashboard.showAccount(null);
                } else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP CONTACT"))) {
                    crmDashboard.showLead(Long.parseLong(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW CONTACTS")) {
                    crmDashboard.showContact(null);
                } else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP OPPORTUNITY"))) {
                    crmDashboard.showLead(Long.parseLong(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW OPPORTUNITIES")) {
                    crmDashboard.showOpportunity(null);
                } else {
                    System.out.println("Unrecognized command!");
                }
            } catch (Exception e){
                System.err.println("Wrong Command");
            }
        }
    }
}

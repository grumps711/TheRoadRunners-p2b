package com.ironhack.team1crmproject.view;

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
                        To show all leads on screen type -> SHOW LEADS
                        To show all contacts on screen type -> SHOW CONTACTS
                        To show all accounts on screen type -> SHOW ACCOUNTS
                        To show all opportunities on screen type -> SHOW OPPORTUNITIES
                        
                        --Lookup by id--
                        To check a specific lead type -> LOOKUP LEAD (followed by lead's id)
                        To check a specific contact type -> LOOKUP CONTACT (followed by lead's id)
                        To check a specific account type -> LOOKUP ACCOUNT (followed by lead's id)
                        To check a specific opportunity type -> LOOKUP OPPORTUNITY (followed by lead's id)
                        
                        --Exit--
                        To exit please type -> EXIT
                        """);
                input = inputScanner.nextLine();
                String[] inputArray = input.split(" ");
//            option 1
                if (input.equalsIgnoreCase("EXIT")) {
                    System.out.println("Thanks for using this CRM application.");
                    System.exit(0);
                }

                else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("NEW LEAD")) {
                    System.out.println("you want to create a new lead");
                    crmDashboard.createLead();
                }
//            option 2 and 3
                else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP LEAD"))) {
                    crmDashboard.showLead(Long.parseLong(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW LEADS")) {
                    crmDashboard.showLead(null);
                }
//            option 4
                else if ((inputArray[0]).equalsIgnoreCase("CONVERT")) {
                    System.out.println("you want to convert a lead (with lead's id " + inputArray[1] + ") to an opportunity");
                    crmDashboard.convertLeadToOpportunity(Long.parseLong(inputArray[1]));
                }
//
//                options 7 8 9
                else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP ACCOUNT"))) {
                    crmDashboard.showLead(Long.parseLong(inputArray[2]));}
                else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW ACCOUNTS")) {
                    crmDashboard.showAccount(null);}
                else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP CONTACT"))) {
                        crmDashboard.showLead(Long.parseLong(inputArray[2]));}
                else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW CONTACTS")) {
                        crmDashboard.showContact(null);}
                else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP OPPORTUNITY"))) {
                            crmDashboard.showLead(Long.parseLong(inputArray[2]));}
                else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW OPPORTUNITIES")) {
                            crmDashboard.showOpportunity(null);}
//            option 6
                else {
                    System.out.println("Unrecognized command!");
                }
            }catch (Exception e){
                System.err.println("Wrong Command");
            }
        }
    }
}

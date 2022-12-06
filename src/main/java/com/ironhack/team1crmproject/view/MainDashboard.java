package com.ironhack.team1crmproject.view;

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
            System.out.println(":::: CRM ::::");
            System.out.println("""
                    1. To CREATE lead type -> new lead
                    2. To CHECK a specific lead type -> lookup lead (lead's id)
                    3. To SHOW all leads on screen type -> show leads
                    4. To CONVERT a known lead to an opportunity type -> convert (lead's id of the lead you want to convert)
                    5. To CHECK a specific opportunity type -> lookup opportunity (opportunity's id)
                    6. To EXIT please type -> exit
                    """);
            input = inputScanner.nextLine();
            String[] inputArray = input.split(" ");
//            option 1 - se guarda va bien
            if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("NEW LEAD")) {
                System.out.println("you want to create a new lead");
                crmDashboard.createLead();
            }
//            option 2 and 3
            else if(((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP LEAD")) || ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW LEADS"))){
                crmDashboard.showLead(Long.parseLong(inputArray[2]));
            }
//            option 4
            else if((inputArray[0]).equalsIgnoreCase("CONVERT")){
                System.out.println("you want to convert a lead (with lead's id " + inputArray[2] + " to an opportunity, please type the lead's id you want to convert");
                crmDashboard.convertLeadToOpportunity(Long.parseLong(inputArray[2]));
            }
//            option 5
            else if((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP OPPORTUNITY")){
                System.out.println("you want to check a specific opportunity with id " + inputArray[2]);
                crmDashboard.checkOneOpportunity(Long.parseLong(inputArray[2]));
            }
//            option 6
            else if (input.equalsIgnoreCase("EXIT")){
                System.out.println("Thanks for using this CRM application.");
                System.exit(0);
            }

            else {
                System.out.println("Unrecognized command!");
            }
        }
    }
}

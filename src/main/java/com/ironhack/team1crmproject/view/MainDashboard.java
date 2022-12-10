package com.ironhack.team1crmproject.view;

import com.ironhack.team1crmproject.model.StatusType;
import com.ironhack.team1crmproject.repository.OpportunityRepository;
import com.ironhack.team1crmproject.utils.TerminalTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainDashboard {

    private final CrmDashboard crmDashboard;
    private final Scanner inputScanner;

    @Autowired
    OpportunityRepository opportunityRepository;

    public MainDashboard(CrmDashboard crmDashboard, Scanner inputScanner) {
        this.crmDashboard = crmDashboard;
        this.inputScanner = inputScanner;
    }

    public void run() {
        var input = "";
        while (!input.equalsIgnoreCase("EXIT")) {
            try {
                System.out.println(TerminalTools.CLEAR_SCREEN);
                System.out.println("\n" +
                        "\n" +
                        "      ___         ___         ___     \n" +
                        "     /  /\\       /  /\\       /__/\\    \n" +
                        "    /  /:/      /  /::\\     |  |::\\   \n" +
                        "   /  /:/      /  /:/\\:\\    |  |:|:\\  \n" +
                        "  /  /:/  ___ /  /:/~/:/  __|__|:|\\:\\ \n" +
                        " /__/:/  /  //__/:/ /:/__/__/::::| \\:\\\n" +
                        " \\  \\:\\ /  /:\\  \\:\\/:::::\\  \\:\\~~\\__\\/\n" +
                        "  \\  \\:\\  /:/ \\  \\::/~~~~ \\  \\:\\      \n" +
                        "   \\  \\:\\/:/   \\  \\:\\      \\  \\:\\     \n" +
                        "    \\  \\::/     \\  \\:\\      \\  \\:\\    \n" +
                        "     \\__\\/       \\__\\/       \\__\\/    \n" +
                        "                                   \n");
                System.out.println(TerminalTools.ANSI_BLUE + """
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
                        To check the count of all Opportunities sorted type -> REPORT OPPORTUNITY BY PRODUCT /COUNTRY /CITY /INDUSTRY
                                                
                        --Check count of Opportunity status--
                        To check the count of all opportunity status sorted type -> REPORT CLOSED-WON/CLOSED-LOST/OPEN BY PRODUCT/COUNTRY/CITY/INDUSTRY
                        
                        --Number calculations--
                        To check the mean/median/maximum/minimum of the Employee-Count/Quantity/Opportunities type -> MEAN/MEDIAN/MAX/MIN EMPLOYEECOUNT/QUANTITY/OPPSPERACCOUNT

                        --Exit--
                        To exit please type -> EXIT
                        """ + TerminalTools.ANSI_RESET);
                input = inputScanner.nextLine();
                String[] inputArray = input.split(" ");

                if (input.equalsIgnoreCase("EXIT")) {
                    System.out.println("Thanks for using this CRM application.");
                    System.exit(0);
                }
                else if(input.equalsIgnoreCase("report opportunity by product")) opportunityRepository.findAllOpportunitiesByTruck();
                else if(input.equalsIgnoreCase("report closed-won by product")) opportunityRepository.findAllClosedWonOpportunitiesByTruck();
                else if(input.equalsIgnoreCase("report closed-lost by product")) opportunityRepository.findAllClosedLostOpportunitiesByTruck();
                else if(input.equalsIgnoreCase("report open by product")) opportunityRepository.findAllOpenOpportunitiesByTruck();
                else if(input.equalsIgnoreCase("report opportunity by country")) opportunityRepository.findAllOpportunitiesByCountry();
                else if(input.equalsIgnoreCase("report closed-won by country")) opportunityRepository.findAllClosedWonOpportunitiesByCountry();
                else if(input.equalsIgnoreCase("report closed-lost by country")) opportunityRepository.findAllClosedLostOpportunitiesByCountry();
                else if(input.equalsIgnoreCase("report open by country")) opportunityRepository.findAllOpenOpportunitiesByCountry();
                else if(input.equalsIgnoreCase("report opportunity by city")) opportunityRepository.findAllOpportunitiesByCity();
                else if(input.equalsIgnoreCase("report closed-won by city")) opportunityRepository.findAllClosedWonOpportunitiesByCity();
                else if(input.equalsIgnoreCase("report closed-lost by city")) opportunityRepository.findAllClosedLostOpportunitiesByCity();
                else if(input.equalsIgnoreCase("report open by city")) opportunityRepository.findAllOpenOpportunitiesByCity();
                else if(input.equalsIgnoreCase("report opportunity by industry")) opportunityRepository.findAllOpportunitiesByIndustry();
                else if(input.equalsIgnoreCase("report closed-won by industry")) opportunityRepository.findAllClosedWonOpportunitiesByIndustry();
                else if(input.equalsIgnoreCase("report closed-lost by industry")) opportunityRepository.findAllClosedLostOpportunitiesByIndustry();
                else if(input.equalsIgnoreCase("report open by industry")) opportunityRepository.findAllOpenOpportunitiesByIndustry();

                else if(input.equalsIgnoreCase("median employeecount")) opportunityRepository.findEmployeeCountMedian();
                else if(input.equalsIgnoreCase("max employeecount")) opportunityRepository.findEmployeeCountMax();
                else if(input.equalsIgnoreCase("min employeecount")) opportunityRepository.findEmployeeCountMin();
                else if(input.equalsIgnoreCase("mean quantity")) opportunityRepository.findTruckQuantityAverage();
                else if(input.equalsIgnoreCase("median quantity")) opportunityRepository.findTruckQuantityMedian();
                else if(input.equalsIgnoreCase("max quantity")) opportunityRepository.findTruckQuantityMax();
                else if(input.equalsIgnoreCase("min quantity")) opportunityRepository.findTruckQuantityMin();
                else if(input.equalsIgnoreCase("mean opps per account")) opportunityRepository.findOpportunitiesPerAccountAverage();
                else if(input.equalsIgnoreCase("median opps per account")) opportunityRepository.findOpportunitiesPerAccountMedian();
                else if(input.equalsIgnoreCase("max opps per account")) opportunityRepository.findOpportunitiesPerAccountMax();
                else if(input.equalsIgnoreCase("min opps per account")) opportunityRepository.findOpportunitiesPerAccountMin();

                else if ((inputArray[0]).equalsIgnoreCase("CLOSED-LOST")) {
                    crmDashboard.changeOpportunityStatus(Integer.parseInt(inputArray[1]), StatusType.CLOSED_LOST);
                    System.out.println("Opportunity status set to CLOSED_LOST");
                }else if ((inputArray[0]).equalsIgnoreCase("CLOSED-WON")) {
                    crmDashboard.changeOpportunityStatus(Integer.parseInt(inputArray[1]), StatusType.CLOSED_WON);
                    System.out.println("Opportunity status set to CLOSED_WON");
                }
                else if ((inputArray[0]).equalsIgnoreCase("CONVERT")) {
                    System.out.println("you want to convert a lead (with lead's id " + inputArray[1] + ") to an opportunity");
                    crmDashboard.convertLeadToOpportunity(Long.parseLong(inputArray[1]));
                } else if ((inputArray[0].equalsIgnoreCase("CLOSE-WON") || (inputArray[0].equalsIgnoreCase("CLOSE-LOST")))) {
                    crmDashboard.setOpportunityStatus(inputArray[0], inputArray[1]);
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("NEW LEAD")) {
                    System.out.println("you want to create a new lead");
                    crmDashboard.createLead();
                } else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP LEAD"))) {
                    crmDashboard.showLead(Long.parseLong(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW LEADS")) {
                    crmDashboard.showLead(null);
                } else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP ACCOUNT"))) {
                    crmDashboard.showAccount(Integer.parseInt(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW ACCOUNTS")) {
                    crmDashboard.showAccount(null);
                } else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP CONTACT"))) {
                    crmDashboard.showContact(Integer.parseInt(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW CONTACTS")) {
                    crmDashboard.showContact(null);
                } else if (((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("LOOKUP OPPORTUNITY"))) {
                    crmDashboard.showOpportunity(Long.parseLong(inputArray[2]));
                } else if ((inputArray[0] + " " + inputArray[1]).equalsIgnoreCase("SHOW OPPORTUNITIES")) {
                    crmDashboard.showOpportunity(null);
                } else if ((inputArray[0] + " " + inputArray[1] + " " + inputArray[2]).equalsIgnoreCase("REPORT OPPORTUNITY BY")) {
                    crmDashboard.checkCountOpportunityBy(inputArray[3]);
                } else {
                    System.out.println("Unrecognized command!");
                }
            } catch (Exception e){
                System.err.println("Wrong Command");
            }
        }
    }
}

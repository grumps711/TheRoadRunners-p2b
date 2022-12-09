package com.ironhack.team1crmproject.view;

import com.ironhack.team1crmproject.model.Account;
import com.ironhack.team1crmproject.model.IndustryType;
import com.ironhack.team1crmproject.model.Lead;
import com.ironhack.team1crmproject.model.TruckType;
import com.ironhack.team1crmproject.service.AccountService;
import com.ironhack.team1crmproject.service.ContactService;
import com.ironhack.team1crmproject.service.LeadService;
import com.ironhack.team1crmproject.service.OpportunityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class CrmDashboard {

    private final Scanner inputScanner;
    private final LeadService leadService;
    private final OpportunityService opportunityService;
    private final ContactService contactService;
    private final AccountService accountService;

//Show leads antiguo
//    //    seleccion 1 antigua                                               SHOW ALL
//    public void showAllLeads() {
//        var input = "";
//        while (!input.equalsIgnoreCase("BACK")) {
//            System.out.println("Available leads:");
//            var leads = leadsService.findAll;
//            if (leads.size() == 0) {
//                System.out.println("You still dont have any lead! Don't be lazy and add a new one!");
//                break;
//            }
//            for (int i = 0; i < lead.size(); i++) {
//                System.out.printf("%s - %s -%s\n", i, lead.get(i).getAction(), lead.get(i).getStatus());
//            }
//            System.out.println("Select a lead by index, or BACK");
//            input = inputScanner.nextLine();
//
//            if (input.matches("\\d+")) {
//                var selectedId = Integer.parseInt(input);
//                if (selectedId < leads.size()) {
//                    selectedTask = leads.get(selectedId);
//                    System.out.printf("Lead selected is:\n* %s\n", selectedLead.getAction());
//                    System.out.printf("""
//                            The task you selected is: %s
//                            It was created at: %s
//                            Last updated at: %s
//                            Its status is: %s
//                            """.formatted(selectedTask.getAction(), selectedTask.getCreationTime(),
//                            selectedTask.getLastUpdate(), selectedTask.getStatus()));
//                    System.out.println("type back to go back");
//                    input = inputScanner.nextLine();
//                } else if (!input.equalsIgnoreCase("back")) {
//                    System.out.println("Unrecognized command!");
//                }
//            }
//        }
//    } dfd



//     seleccion 2 antigua
//    public void taskCreationRoutine () {
//        var input = "";
//        var activity = "";
//        while (!input.equalsIgnoreCase("BACK")) {
//            System.out.println("Ready for your next task? Type the action you want to do, or just BACK");
//            input = activity = inputScanner.nextLine();
//            if (!input.equalsIgnoreCase("BACK")) {
//                System.out.printf("You selected:\n* %s \n Are you sure?\nYes or No\n", input);
//                input = inputScanner.nextLine();
//                if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {
//                    System.out.println("Great");
//                    var storedTask = taskService.save(new Task(activity, MainDashboard.currentUserLogged));
//                    System.out.printf("Be ready to: %s !! Task was created successfully\n", storedTask.getAction());
//                }
//                System.out.println("Ok, let's pick another task to do");
//            }
//        }
//    }

//    public void taskUpdateRoutine () {
//        System.out.printf("The task you want to update is: %s \n Its status is: %s\n", selectedTask.getAction(), selectedTask.getStatus());
//        System.out.println("What you want to update: ACTIVITY / STATUS");
//        var updated = false;
//        var input = "";
//        while (!input.equalsIgnoreCase("BACK") || updated == true) {
//            input = inputScanner.nextLine();
//            if (input.equalsIgnoreCase("ACTIVITY")) {
//                updateActivityRoutine();
//                break;
//            } else if (input.equalsIgnoreCase("STATUS")) {
//                updateStatusRoutine();
//                break;
//            } else if (!input.equalsIgnoreCase("BACK")) {
//                System.out.println("Invalid command!");
//            }
//        }
//    }

//    private void updateStatusRoutine () {
//        var inputStatus = "";
//        while (!inputStatus.equalsIgnoreCase("BACK")) {
//            System.out.println("Update task status to:");
//            for (int i = 0; i < TaskStatus.values().length; i++) {
//                System.out.printf("%s - %s \n", i, TaskStatus.values()[i]);
//            }
//            inputStatus = inputScanner.nextLine();
//            if (inputStatus.matches("\\d+")) {
//                var selectedStatus = Integer.parseInt(inputStatus);
//                if (selectedStatus < TaskStatus.values().length) {
//                    selectedTask.setStatus(TaskStatus.values()[selectedStatus]);
//                    taskService.save(selectedTask);
//                    System.out.println("Task updated!");
//                    break;
//                } else {
//                    System.out.println("Invalid status picked");
//                }
//            }
//        }
//    }

//    private void updateActivityRoutine () {
//        System.out.println("Write you new activity");
//        var inputAction = inputScanner.nextLine();
//        if (!inputAction.isEmpty()) {
//            selectedTask.setAction(inputAction);
//            var storedTask = taskService.save(selectedTask);
//            System.out.printf("Be ready to: %s !! Task was UPDATED successfully\n", storedTask.getAction());
//
//        }
//    }


//    ------------------------------------------------------------------------------

    public void showLead (Long number){
        if (number == null) {
            System.out.println("all leads saved in the database are showing now on screen");
            leadService.showAllLeads();
        } else {
            System.out.println("you want to check a lead with lead's id " + number);
            leadService.showLeadById(number);
        }
    }

    public void createLead() {
        var input = "";
        var activity = "";
        while (!input.equalsIgnoreCase("BACK")) {

            try {

                System.out.println("Lead creation");
                System.out.println("You may now create your lead, you can always type BACK if you want to exit lead creation");

                System.out.println("Type the name of the lead:");
                String name = input = inputScanner.nextLine();
                //if (input.equalsIgnoreCase("back")) break;
                System.out.println("Type the role of the lead:");
                String role = input = inputScanner.nextLine();

                System.out.println("Type the email of the lead:");
                String email = input = inputScanner.nextLine();

                System.out.println("Type the phone number of the lead:");
                String phoneNumber = input = inputScanner.nextLine();

                System.out.println("Type the company's name of the lead:");
                String companyName = input = inputScanner.nextLine();


                if (!input.equalsIgnoreCase("BACK")) {

                    System.out.println("A new lead is going to create, are you sure it's correct? y/n");
                    input = inputScanner.nextLine();

                    if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {

                        System.out.println("Great");

                        leadService.save(new Lead(name, role, email, phoneNumber, companyName));
                        System.out.println("Lead was created successfully\n");
                    }
                    System.out.println("What do you want to do next? Create another Lead or go back?");
                    input = inputScanner.nextLine();
                }
            }catch(Exception e){
                System.err.println("Wrong command");
            }
        }
    }

    public void convertLeadToOpportunity(long parseLong) {
        var input = "";
        while (!input.equalsIgnoreCase("BACK")) { //TODO or have finished creating the opportunity) {
            try {
                var lead = leadService.findLeadById(parseLong);
                System.out.println("Please, indicate the type of Truck you are interested in:" +
                        "[0] - Hybrid Truck" +
                        "[1] - Flatbed Truck " +
                        "[2] - Box Truck");
                TruckType truckType = TruckType.values()[Integer.parseInt(input = inputScanner.nextLine())];

                System.out.println("Please, indicate the quantity of trucks you are interested in:");
                int quantity = Integer.parseInt(input = inputScanner.nextLine());

                var contact = contactService.createContact(lead);
                var account = createAccount(lead);

                var opportunity = opportunityService.createOpportunity(lead, truckType, quantity);
                opportunityService.setOpportunityContact(opportunity, contact);
                opportunityService.setOpportunityAccount(opportunity, account);
                System.out.println("Opportunity was created successfully\n");
                input = "back";
            } catch (IllegalStateException e) {
                System.out.println("Lead is not in the database");
            } catch (IndexOutOfBoundsException eg) {
                System.out.println("number does not match with truck type, try again");
            } catch (NumberFormatException ne) {
                System.out.println("you did not provide a number");
            }
        }
    }
    public Account createAccount(Lead lead) {
        var input = "";
        Account account = null;

        while (!input.equalsIgnoreCase("BACK")) {
            //TODO handle all errors
            System.out.println("It is time to fill some Company information:");
            System.out.println("Please, enter the total number of employees:");
            int numberOfEmployees = Integer.parseInt(input = inputScanner.nextLine());
            System.out.println("Please, enter the company's country:"); //check?
            String country = input = inputScanner.nextLine();
            System.out.println("Please, enter the company's city:"); //check?
            String city = input = inputScanner.nextLine();
            System.out.println("Please, indicate the company's type of industry:" +
                    "[0] - PRODUCE" +
                    "[1] - ECOMMERCE " +
                    "[2] - MANUFACTURING" +
                    "[3] - MEDICAL" +
                    "[4] - OTHER");
            IndustryType industryType = IndustryType.values()[Integer.parseInt(input = inputScanner.nextLine())];
            account = accountService.createAccount(numberOfEmployees, lead.getCompanyName(), country, city, industryType);
            input = "back";
        }
        return account;
    }

    public void checkOneOpportunity(long parseLong) {
    }
}

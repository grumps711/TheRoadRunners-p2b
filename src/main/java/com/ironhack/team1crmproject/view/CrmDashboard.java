package com.ironhack.team1crmproject.view;

import com.ironhack.team1crmproject.model.*;
import com.ironhack.team1crmproject.service.AccountService;
import com.ironhack.team1crmproject.service.ContactService;
import com.ironhack.team1crmproject.service.LeadService;
import com.ironhack.team1crmproject.service.OpportunityService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.net.Proxy;
import java.util.Objects;
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

        System.out.println("Lead creation");
        System.out.println("You may now create your lead, you can always type BACK if you want to exit lead creation");

        while (!input.equalsIgnoreCase("BACK")) {

            try {
                String[] inputArray;
                do {
                    System.out.println("Type first name followed by last name of the lead:");
                    input = inputScanner.nextLine();
                    if (input.equalsIgnoreCase("BACK")) break;
                    inputArray = input.split(" ");
                } while (!(nameValidation(inputArray[0]) && nameValidation(inputArray[1])));
                String name = input;
                if (input.equalsIgnoreCase("BACK")) break;


                do {
                    System.out.println("Type the role of the lead:");
                    input = inputScanner.nextLine();
                    if (input.equalsIgnoreCase("BACK")) break;
                } while (!(nameValidation(input)));
                String role = input;
                if (input.equalsIgnoreCase("BACK")) break;


                do {
                    System.out.println("Type the email of the lead:");
                    input = inputScanner.nextLine();
                    if (input.equalsIgnoreCase("BACK")) break;
                } while (!(emailValidation(input)));
                String email = input;
                if (input.equalsIgnoreCase("BACK")) break;



                do {
                    System.out.println("Type the phone number of the lead:");
                    input = inputScanner.nextLine();
                    if (input.equalsIgnoreCase("BACK")) break;
                } while (!(phoneValidation(input)));
                String phoneNumber = input;
                if (input.equalsIgnoreCase("BACK")) break;


                do {
                    System.out.println("Type the company's name of the lead:");
                    input = inputScanner.nextLine();
                    if (input.equalsIgnoreCase("BACK")) break;
                } while (!(nameValidation(input)));
                String companyName = input;


                if (!input.equalsIgnoreCase("BACK")) {

                    System.out.println("A new lead is going to create, are you sure it's correct?");
                    System.out.println("Type YES or BACK to go back to the menu");
                    input = inputScanner.nextLine();

                    if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) {

                        System.out.println("Great");
                        leadService.save(new Lead(name, role, email, phoneNumber, companyName));

                        System.out.println("Lead was created successfully\n");

                        System.out.println("What do you want to do next? Create another Lead or go back?");
                        input = inputScanner.nextLine();

                    }
                }
            }catch (IndexOutOfBoundsException o){
                System.err.println("The name entered is not valid, please try again");
                System.err.println("Please make sure to write first name and last name");
            }
            catch(Exception e){
                System.err.println("Wrong command");
            }
        }
    }

    public void convertLeadToOpportunity(long leadId) {
        var input = "";
        while (!input.equalsIgnoreCase("BACK")) { //TODO or have finished creating the opportunity) {
            try {
                var lead = leadService.findLeadById(leadId);

                do {
                    System.out.println("Please, indicate the type of Truck you are interested in:" +
                            "[0] - Hybrid Truck\n" +
                            "[1] - Flatbed Truck\n" +
                            "[2] - Box Truck\n");
                    input= inputScanner.nextLine();
                }while (!(Integer.parseInt(input) < 3 && Integer.parseInt(input) > -1));
                TruckType truckType = TruckType.values()[Integer.parseInt(input)];

                do {
                    System.out.println("Please, indicate the quantity of trucks you are interested in:");
                    input = inputScanner.nextLine();
                } while (!numberValidation(input));
                int quantity = Integer.parseInt(input);


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
            System.out.println("It is time to fill some Company information");

            do {
                System.out.println("Please, enter the total number of employees:");
                input = inputScanner.nextLine();
            } while (!numberValidation(input));
            int numberOfEmployees = Integer.parseInt(input);

            do {
                System.out.println("Please, enter the company's country:"); //check?
                input = inputScanner.nextLine();
            } while (!(nameValidation(input)));
            String country = input;

            do {
                System.out.println("Please, enter the company's city:"); //check?
                input = inputScanner.nextLine();
            } while (!(nameValidation(input)));
            String city = input;

            do {
                System.out.println("Please, indicate the company's type of industry: \n" +
                        "[0] - PRODUCE\n" +
                        "[1] - ECOMMERCE\n" +
                        "[2] - MANUFACTURING\n" +
                        "[3] - MEDICAL\n" +
                        "[4] - OTHER\n");
                input = inputScanner.nextLine();
            }while(!(Integer.parseInt(input) < 5 && Integer.parseInt(input) > -1));
            IndustryType industryType = IndustryType.values()[Integer.parseInt(input)];

            account = accountService.createAccount(numberOfEmployees, lead.getCompanyName(), country, city, industryType);
            input = "back";
        }
        return account;
    }

    public void checkOneOpportunity(long parseLong) {
        Opportunity opportunityToShow;
        try {
            opportunityToShow = opportunityService.findOpportunityByOpportunityId(parseLong);
            System.out.println("INFORMATION ABOUT OPPORTUNITY" + opportunityToShow.toString());
        } catch (IllegalArgumentException e) {
            System.err.println("Opportunity not present in database");
        }
    }

    // validate name
    public boolean nameValidation(@NotNull String name ) {
        boolean valid;
        valid = name.matches("^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$");
        if(!valid){
            System.err.println("The name entered is not valid, please try again");
            System.err.println("First letters should be upper case, for example: Thomas");
        }
        return valid;
    }

    // validate email
    public boolean emailValidation(@NotNull String email ) {
        boolean valid;
        valid = email.matches("([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})");
        if(!valid){
            System.err.println("The email entered is not valid, please try again");
        }
        return valid;
    }

    // validate 9-digit phone
    public boolean phoneValidation(@NotNull String phone ) {
        boolean valid;
        valid = phone.matches("^\\d{9}$");
        if(!valid){
            System.err.println("The phone entered is not valid, please try again");
            System.err.println("Check that your phone is 9 digits long");
        }
        return valid;
    }

    // validate number
    public boolean numberValidation(@NotNull String number ) {
        boolean valid;
        valid = number.matches("^[1-9]\\d*$");
        if(!valid){
            System.err.println("Please enter a valid number");
        }
        return valid;
    }
}

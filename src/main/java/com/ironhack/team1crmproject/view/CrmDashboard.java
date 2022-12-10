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

    public void showAccount (Long number){
        if (number == null) {
            System.out.println("all accounts saved in the database are showing now on screen");
            accountService.showAllAccounts();
        } else {
            System.out.println("you want to check an account with lead's id " + number);
            accountService.showAccountById(number);
        }
    }

    public void showContact (Long number){
        if (number == null) {
            System.out.println("all contacts saved in the database are showing now on screen");
            contactService.showAllContacts();
        } else {
            System.out.println("you want to check a contact with lead's id " + number);
            contactService.showContactById(number);
        }
    }

    public void showOpportunity (Long number){
        if (number == null) {
            System.out.println("all opportunities saved in the database are showing now on screen");
            opportunityService.showAllOpportunities();
        } else {
            System.out.println("you want to check an opportunity with lead's id " + number);
            opportunityService.showOpportunityById(number);
        }
    }

    public void setOpportunityStatus(String status,String id) {
            opportunityService.setOpportunityStatus(status,id);
    }

    public void checkCountOpportunityBy(String s) {
        opportunityService.checkOpportunityBy(s);
    }
}

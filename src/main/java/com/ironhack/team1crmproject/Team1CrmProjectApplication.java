package com.ironhack.team1crmproject;

import com.ironhack.team1crmproject.view.MainDashboard;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Team1CrmProjectApplication implements CommandLineRunner {


	private final MainDashboard mainDashboard;

	public Team1CrmProjectApplication(MainDashboard mainDashboard) {
		this.mainDashboard = mainDashboard;
	}

	public static void main(String[] args) {
		SpringApplication.run(Team1CrmProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mainDashboard.run();
	}
}
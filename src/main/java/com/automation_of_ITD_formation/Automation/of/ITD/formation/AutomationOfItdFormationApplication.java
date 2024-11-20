package com.automation_of_ITD_formation.Automation.of.ITD.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AutomationOfItdFormationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutomationOfItdFormationApplication.class, args);
	}

}

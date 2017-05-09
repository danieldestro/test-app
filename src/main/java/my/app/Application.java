package my.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import my.app.entity.Proposal;
import my.app.service.PricingService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PricingService service;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {

        Proposal prop = service.createProposal();
        service.calculate(prop.getId());
        // service.delete(2);

        prop = service.createProposal();
        service.calculate(prop.getId());
        service.delete(3);
    }
}
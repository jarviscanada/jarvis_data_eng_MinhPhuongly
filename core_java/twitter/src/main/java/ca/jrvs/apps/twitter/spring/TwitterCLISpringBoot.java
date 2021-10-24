package ca.jrvs.apps.twitter.spring;

import ca.jrvs.apps.twitter.controller.TwitterApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ca.jrvs.apps.twitter")
public class TwitterCLISpringBoot implements CommandLineRunner {
    private TwitterApp app;

    @Autowired
    public TwitterCLISpringBoot(TwitterApp app) {
        this.app = app;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TwitterCLISpringBoot.class);
        //Turn off web service
        app.setWebApplicationType(WebApplicationType.NONE);

        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        app.run(args);
    }
}

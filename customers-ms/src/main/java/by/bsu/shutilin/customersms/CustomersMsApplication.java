package by.bsu.shutilin.customersms;

import by.bsu.shutilin.customersms.controller.CustomersController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CustomersMsApplication {
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger log = LogManager.getLogger(CustomersMsApplication.class.getName());
    private static final Logger x =LogManager.getLogger("file");

    public static void main(String[] args) {
        SpringApplication.run(CustomersMsApplication.class, args);
    }
}

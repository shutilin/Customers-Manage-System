package by.bsu.shutilin.customersms;

import by.bsu.shutilin.customersms.controller.CustomersController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableJpaAuditing
public class CustomersMsApplication {
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger log = LogManager.getLogger(CustomersMsApplication.class.getName());
    private static final Logger x =LogManager.getLogger("file");

    public static void main(String[] args) {
        SpringApplication.run(CustomersMsApplication.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("by.bsu.shutilin.customersms")).build();
    }
}

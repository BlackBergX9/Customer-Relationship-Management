package org.blackbergx9.customerhandler;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class CustomerHandlerApplication {

    public static void main(String[] args) {

//        SpringApplication.run(CustomerHandlerApplication.class, args);

        new SpringApplicationBuilder(CustomerHandlerApplication.class)
//                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(true)
                .run(args);

    }

}

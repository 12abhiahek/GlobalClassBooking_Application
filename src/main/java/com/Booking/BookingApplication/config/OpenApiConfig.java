package com.Booking.BookingApplication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI courseBookingOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("Course Booking System API")
                                .version("1.0")
                                .description(
                                        "Teacher and Student Course Booking APIs")
                                .contact(
                                        new Contact()
                                                .name("Abhishek")
                                                .email("Abhishek184april@gmail.com")
                                )
                );
    }
}

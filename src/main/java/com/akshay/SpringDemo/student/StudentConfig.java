package com.akshay.SpringDemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository){
        return args -> {
            Student akash = new Student("Akash","akash02@gmail.com", LocalDate.of(2002, Month.JANUARY,6));
            Student rahul = new Student("Rahul","rahul04@gmail.com",LocalDate.of(2004,Month.APRIL,25));
            repository.saveAll(List.of(akash,rahul));
        };
    }


}

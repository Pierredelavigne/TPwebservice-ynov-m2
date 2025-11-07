package org.example.sec03;

import org.example.models.sec03.Address;
import org.example.models.sec03.School;
import org.example.models.sec03.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoComposition {


    private static final Logger log = LoggerFactory.getLogger(DemoComposition.class);


    public static void main(String[] args) {

        var address = Address.newBuilder()
                .setStreet("123 main st")
                .setCity("atlanta")
                .setState("GA")
                .build();
        var student = Student.newBuilder()
                .setName("sam")
                .setAddress(address)
                .build();

        var school = School.newBuilder()
                .setId(1)
                .setName("high school")
                .setAddress(address.toBuilder().setStreet("234 main st").build())
                .build();

        log.info("school: {}", school);

        log.info("student: {}", student);


    }



}

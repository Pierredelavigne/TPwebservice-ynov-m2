package org.example.sec01;

import org.example.models.sec01.PersonOuterClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDemoProto {


    private static final Logger log = LoggerFactory.getLogger(SimpleDemoProto.class);

    public static void main(String[] args) {

        var person = PersonOuterClass.Person.newBuilder()
                .setName("Tom")
                .setAge(67)
                .setEmail("tom@gmail.com")
                .build();

        log.info("person: {}", person);


    }

}

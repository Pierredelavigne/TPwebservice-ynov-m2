package org.example.sec02;

import org.example.models.sec02.Person;
import org.example.sec01.SimpleDemoProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtoDemo {

    private static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);

    public static void main(String[] args) {

        var person1  = createPerson();
        var person2  = createPerson();

        log.info("equals: {}", person1.equals(person2));
        log.info("==: {}", person1 == person2);

    }




    private static Person createPerson() {
        return Person.newBuilder()
                .setName("Flavian")
                .setAge(56)
                .setEmail("flav@gmail.com")
                .setIsActif(true)
                .build();
    }


}

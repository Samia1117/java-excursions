package org.example;

import protobuf.person.PersonClass;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Base64;

public class ProtobufExample {

    public static void play() {

        // Create project
        // Add protobuf dependency to project (pom.xml or gradle.kts)
        // create something.proto file, that defines desired Class
        // run "proto I=. out_file = ." -> generates the actual classes from the .proto file

        // Encode
        PersonClass.Person.Builder personBuilder = PersonClass.Person.newBuilder()
                .setFavoriteNumber(1337)
                .setUserName("Martin");
        personBuilder.addInterests("daydreaming");
        personBuilder.addInterests("hacking");

        String base64EncodedClass = Base64.getEncoder().encodeToString(personBuilder.build().toByteArray());
        System.out.println("Base64 string: " + base64EncodedClass);

        // Decode
        byte[] decodedBytes = Base64.getDecoder().decode(base64EncodedClass);
        try {
            PersonClass.Person p = PersonClass.Person.parseFrom(decodedBytes);
            System.out.println(p.toString());
        } catch (InvalidProtocolBufferException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}

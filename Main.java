package org.example;

import com.google.protobuf.InvalidProtocolBufferException;
import protobuf.person.PersonClass;

import java.io.IOException;
import java.util.Base64;

public class Main {
    public static void main(String[] args) {

        try {
            LogAggregator la = new LogAggregator();
            la.aggregate(1);
        } catch (IOException e) {
            System.out.println("Exception = " + e.getMessage());
        }

    }
}
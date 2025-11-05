package com.napier.sem;


public class App {
    public static void main(String[] args) {
        DatabaseManager.connect();

        DatabaseManager.disconnect();
    }
}
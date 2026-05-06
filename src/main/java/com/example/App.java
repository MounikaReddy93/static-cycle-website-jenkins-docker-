package com.example;

public class App {
    public static void main(String[] args) {
        System.out.println("Application started successfully!");
        
        // Keep app running (important for containers)
        while (true) {
            try {
                Thread.sleep(10000);
                System.out.println("App is running...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

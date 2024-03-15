package org.ticket.booking;

import org.ticket.booking.entities.Train;
import org.ticket.booking.entities.User;
import org.ticket.booking.services.UserBookingService;
import org.ticket.booking.utilities.UserServiceUtil;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        System.out.println("Running Train Booking System");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService = null;
        try {
            userBookingService = new UserBookingService();
        } catch (IOException ex) {
            System.out.println("Failed to initialize the booking service. Exiting...");
            ex.printStackTrace(); // Print the exception details for debugging
            return;
        }
        while (option != 7) {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Bookings");
            System.out.println("4. Search Trains");
            System.out.println("5. Book a Seat");
            System.out.println("6. Cancel my Booking");
            System.out.println("7. Exit the App");
            option = scanner.nextInt();
            Train trainSelectedForBooking = new Train();
            switch (option) {
                case 1:
                    System.out.println("Enter the username to signup");
                    String nameToSignUp = scanner.next();
                    System.out.println("Enter the password to signup");
                    String passwordToSignUp = scanner.next();
                    User userToSignup = new User(nameToSignUp, passwordToSignUp, UserServiceUtil.hashPassword(passwordToSignUp), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(userToSignup);
                    break;
                case 2:
                    System.out.println("Enter the username to Login");
                    String nameToLogin = scanner.next();
                    System.out.println("Enter the password to login");
                    String passwordToLogin = scanner.next();
                    User userToLogin = new User(nameToLogin, passwordToLogin, UserServiceUtil.hashPassword(passwordToLogin), new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingService = new UserBookingService(userToLogin);
                    } catch (IOException ex) {
                        System.out.println("Failed to login. Please try again.");
                        ex.printStackTrace(); // Print the exception details for debugging
                    }
                    break;
                case 3:
                    System.out.println("Fetching your bookings");
                    userBookingService.fetchBookings();
                    break;
                case 4:
                    // Code for searching trains
                    break;
                case 5:
                    // Code for booking a seat
                    break;
                case 6:
                    // Code for cancelling a booking
                    break;
                case 7:
                    System.out.println("Exiting the application");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
        scanner.close(); // Close the scanner to prevent resource leak
    }

    public Object getGreeting() {
        return "Hello";
    }
}
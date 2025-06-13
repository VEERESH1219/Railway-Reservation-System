package com.Railway.System;
import java.util.Scanner;
import java.sql.*;

public class RailwaySystemProject {
	

	 
	    public static void main(String[] args) {
	    
	        Scanner scanner = new Scanner(System.in);
	        boolean loggedIn = false;

	        // User Login
	        while (!loggedIn) {
	            System.out.print("Enter Username: ");
	            String username = scanner.next();
	            System.out.print("Enter Password: ");
	            String password = scanner.next();

	            loggedIn = User.login(username, password);
	        }

	        while (true) {
	            System.out.println("\nRailway Reservation System");
	            System.out.println("1. View Train Schedule");
	            System.out.println("2.See Available Trains From Source  To Destination");
	            System.out.println("3. Book Ticket");
	            System.out.println("4. Cancel Ticket");
	            System.out.println("5. View Reservations");
	            System.out.println("6. Exit");
	            System.out.print("Enter choice: ");
	            
	            int choice = scanner.nextInt();
	            switch (choice) {
	                case 1:
	                    viewTrains();
	                    break;
	                case 2:
	                	System.out.println("2.See Available Trains From Source  To Destination");
	                	 Scanner sc = new Scanner(System.in);
	                	    System.out.println("Enter Source Station:");
	                	    String source = sc.nextLine();
	                	    System.out.println("Enter Destination Station:");
	                	    String destination = sc.nextLine();

	                	    viewAvailableTrains(source, destination);
	                	    break;

	                case 3:
	                    System.out.print("Enter Passenger ID: ");
	                    int passengerId = scanner.nextInt();
	                    System.out.print("Enter Train ID: ");
	                    int trainId = scanner.nextInt();
	                    System.out.print("Enter Seat Number: ");
	                    int seatNumber = scanner.nextInt();
	                    System.out.print("Enter Payment Amount: ");
	                    double amount = scanner.nextDouble();
	                    System.out.print("Enter Status: ");
	                    String st= scanner.next();
	                    Reservation.bookTicket(passengerId, trainId, seatNumber, amount,st);
	                    break;
	                case 4:
	                    System.out.print("Enter Reservation ID to cancel: ");
	                    int reservationId = scanner.nextInt();
	                    Reservation.cancelTicket(reservationId);
	                    break;
	                case 5:
	                    Reservation.viewBookings();
	                    break;
	                case 6:
	                    System.out.println("Exiting system.");
	                    System.exit(0);
	                    return;
	                default:
	                    System.out.println("Invalid choice! Try again.");
	            }
	        }
	    }

	    public static void viewTrains() {
	        Connection conn = DBConnection.getConnection();
	        try {
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM trains");
	            ResultSet rs = ps.executeQuery();

	            System.out.println("\nTrain Schedule:");
	            while (rs.next()) {
	                System.out.println(rs.getInt("train_id") + " | " + rs.getString("name") + " | " +
	                        rs.getString("source") + " -> " + rs.getString("destination") + " | " + rs.getString("time"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static void viewAvailableTrains(String source, String destination) {
		    Connection conn = DBConnection.getConnection();

		    try {
		        PreparedStatement ps = conn.prepareStatement(
		            "SELECT t.name, at.departure_time, at.arrival_time, at.seats_available " +
		            "FROM available_trains at " +
		            "JOIN trains t ON at.train_id = t.train_id " +
		            "WHERE at.source = ? AND at.destination = ?"
		        );
		        ps.setString(1, source);
		        ps.setString(2, destination);
		        ResultSet rs = ps.executeQuery();

		        boolean trainFound = false;
		        System.out.println("\nAvailable Trains from " + source + " to " + destination + ":");
		        while (rs.next()) {
		            trainFound = true;
		            System.out.println(
		                "Train: " + rs.getString("name") +
		                ", Departure: " + rs.getTime("departure_time") +
		                ", Arrival: " + rs.getTime("arrival_time") +
		                ", Seats Available: " + rs.getInt("seats_available")
		            );
		        }

		        // Throw custom exception if no trains are found
		        if (!trainFound) {
		            throw new NoTrainAvailableException("No trains available from " + source + " to " + destination);
		        }

		    } catch (NoTrainAvailableException e) {
		        System.out.println("Error: " + e.getMessage());
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	    
}

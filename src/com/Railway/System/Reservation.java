package com.Railway.System;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Reservation {
    public static void bookTicket(int passengerId, int trainId, int seatNumber, double amount,String Status) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO reservations (passenger_id, train_id, seat_number,status) VALUES (?, ?, ?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setInt(1, passengerId);
            ps.setInt(2, trainId);
            ps.setInt(3, seatNumber);
            ps.setString(4, Status);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int reservationId = rs.getInt(1);
                  
                    processPayment(reservationId, amount);
                }
                System.out.println("Ticket booked successfully!");
            } else {
                System.out.println("Booking failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error: Seat may be already booked.");
        }
    }

    private static void processPayment(int reservationId, double amount) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO payments (reservation_id, amount, payment_status) VALUES (?, ?, 'Paid')"
            );
            ps.setInt(1, reservationId);
            ps.setDouble(2, amount);
            ps.executeUpdate();
            System.out.println("Payment successful. Amount: $" + amount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cancelTicket(int reservationId) {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM reservations WHERE id = ?");
            ps.setInt(1, reservationId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Reservation cancelled successfully.");
               
            } else {
                System.out.println("Cancellation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewBookings() {
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT r.id, p.name, t.name AS train, r.seat_number, py.amount, py.payment_status " +
                "FROM reservations r " +
                "JOIN passengers p ON r.passenger_id = p.id " +
               "JOIN trains t ON r.train_id = t.train_id " +
                "LEFT JOIN payments py ON r.id = py.reservation_id"
            );
            ResultSet rs = ps.executeQuery();

            System.out.println("Reservations List:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Passenger: " + rs.getString("name") +
                        ", Train: " + rs.getString("train") + ", Seat: " + rs.getInt("seat_number") +
                        ", Payment: $" + rs.getDouble("amount") + " (" + rs.getString("payment_status") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



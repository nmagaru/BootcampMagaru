package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final String connectionString;
    private final String userName;
    private final String password;


    public VehicleDao(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }

    public List<Vehicle> getVehicles (List<String> search, String searchType) {
        List<Vehicle> list = new ArrayList<>();
        String query = switch (searchType) {
            case "price"        -> "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
            case "make/model"   -> "SELECT * FROM vehicles WHERE brand = ? AND model = ?";
            case "year"         -> "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
            case "color"        -> "SELECT * FROM vehicles WHERE color = ?";
            case "mileage"      -> "SELECT * FROM vehicles WHERE mileage BETWEEN ? AND ?";
            case "type"         -> "SELECT * FROM vehicles WHERE type = ?";
            default             -> "SELECT * FROM vehicles";
        };

        try(Connection conn = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement stmt = conn.prepareStatement(query)) {

            switch (searchType) {
                case "price":
                    stmt.setDouble(1, Double.parseDouble(search.get(0)));
                    stmt.setDouble(2, Double.parseDouble(search.get(1)));
                    break;
                case "make/model":
                    stmt.setString(1, search.get(0));
                    stmt.setString(2, search.get(1));
                    break;
                case "year", "mileage":
                    stmt.setInt(1, Integer.parseInt(search.get(0)));
                    stmt.setInt(2, Integer.parseInt(search.get(1)));
                    break;
                case "color", "type":
                    stmt.setString(1, search.get(0));
                    break;
                default:
                    break;
            }

            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    Vehicle newVehicle = new Vehicle(
                            rs.getInt("vin"),
                            rs.getInt("year"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getString("type"),
                            rs.getString("color"),
                            rs.getInt("mileage"),
                            rs.getDouble("price")
                    );

                    list.add(newVehicle);
                }
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public void addVehicle(Vehicle vehicle) {
        String vehicleQuery =
                "INSERT INTO vehicles (vin, brand, model, year, color, type, sold, mileage, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, FALSE, ?, ?)";
        String inventoryQuery =
                "INSERT INTO inventory (dealership_id, vin) " +
                "VALUES (1, ?)";

        try(Connection conn = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement vehicleStmt = conn.prepareStatement(vehicleQuery);
            PreparedStatement inventoryStmt = conn.prepareStatement(inventoryQuery)) {

            vehicleStmt.setInt(1, vehicle.getVin());
            vehicleStmt.setString(2, vehicle.getMake());
            vehicleStmt.setString(3, vehicle.getModel());
            vehicleStmt.setInt(4, vehicle.getYear());
            vehicleStmt.setString(5, vehicle.getColor());
            vehicleStmt.setString(6, vehicle.getVehicleType());
            vehicleStmt.setInt(7, vehicle.getOdometer());
            vehicleStmt.setDouble(8, vehicle.getPrice());
            vehicleStmt.executeUpdate();

            inventoryStmt.setInt(1, vehicle.getVin());
            inventoryStmt.executeUpdate();

            System.out.println("Vehicle added successfully.");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicle(int vin) {
        String vehicleQuery = "DELETE FROM vehicles WHERE vin = ?";
        String inventoryQuery = "DELETE FROM inventory WHERE vin = ?";

        try(Connection conn = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement vehicleStmt = conn.prepareStatement(vehicleQuery);
            PreparedStatement inventoryStmt = conn.prepareStatement(inventoryQuery)) {

            vehicleStmt.setInt(1, vin);
            vehicleStmt.executeUpdate();

            inventoryStmt.setInt(1, vin);
            inventoryStmt.executeUpdate();

            System.out.println("Vehicle removed successfully.");
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

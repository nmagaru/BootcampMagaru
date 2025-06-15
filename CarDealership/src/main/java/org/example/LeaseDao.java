package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseDao {
    private final String connectionString;
    private final String userName;
    private final String password;


    public LeaseDao(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }


    public void addLeaseContract(LeaseContract contract) {
        String query =
                "INSERT INTO lease_contracts (name, email, phone, date, base_price, vin, total_price, monthly_payment) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, contract.getCustomerName());
            stmt.setString(2, contract.getCustomerEmail());
            stmt.setString(3, contract.getCustomerPhone());
            stmt.setString(4, contract.getDate());
            stmt.setDouble(5, contract.getVehicleSold().getPrice());
            stmt.setInt(6, contract.getVehicleSold().getVin());
            stmt.setDouble(7, contract.getTotalPrice());
            stmt.setDouble(8, contract.getMonthlyPayment());

            stmt.executeUpdate();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Contract> getAllLeaseContracts() {
        List<Contract> list = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts";

        try(Connection conn = DriverManager.getConnection(connectionString, userName, password);
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                VehicleDao vehicleDao = new VehicleDao(connectionString, userName, password);
                Vehicle vehicle = vehicleDao.getVehicle(rs.getInt("vin"));

                LeaseContract newContract = new LeaseContract(
                        rs.getString("date"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        vehicle
                );

                list.add(newContract);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}

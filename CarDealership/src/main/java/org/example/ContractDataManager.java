package org.example;

import java.io.*;
import java.rmi.dgc.Lease;
import java.util.ArrayList;
import java.util.List;

public class ContractDataManager {
    private static final String FILE_PATH = "src/main/resources/contracts.csv";

    public static void saveContract(Contract contract) {
        File file = new File(FILE_PATH);

        try {
            File folder = file.getParentFile();
            if (!folder.exists()) {
                folder.mkdirs();
            }

            boolean fileExists = file.exists();
            if (!fileExists) {
                try {
                    if (file.createNewFile()) {
                        //System.out.println("File created: " + file.getName());
                    }
                } catch (IOException ex) {
                    System.out.println("File could not be created.");
                    ex.printStackTrace();
                }
            }

            FileWriter writer = new FileWriter(file, true);

            if (contract instanceof SalesContract) {
                writer.write("SALE|");

                writer.write(
                        contract.getDate() + "|" +
                            contract.getCustomerName() + "|" +
                            contract.getCustomerEmail() + "|"
                );

                writer.write(contract.getVehicleSold().toString() + "|");

                writer.write(
                        String.format("%.2f", ((SalesContract) contract).getSalesTax()) + "|" +
                            String.format("%.2f", ((SalesContract) contract).getRecordingFee()) + "|" +
                            String.format("%.2f", ((SalesContract) contract).getProcessingFee()) + "|"
                );

                if (((SalesContract) contract).isFinanced()) {
                    writer.write("YES|");
                }
                else {
                    writer.write("NO|");
                }


            }
            else if (contract instanceof LeaseContract) {
                writer.write("LEASE|");

                writer.write(
                        contract.getDate() + "|" +
                                contract.getCustomerName() + "|" +
                                contract.getCustomerEmail() + "|"
                );

                writer.write(contract.getVehicleSold().toString() + "|");

                writer.write(
                        String.format("%.2f", ((LeaseContract) contract).getExpEndingPrice()) + "|" +
                            String.format("%.2f", ((LeaseContract) contract).getLeaseFee()) + "|"
                );
            }

            writer.write(
                    String.format("%.2f", contract.getTotalPrice()) + "|" +
                        String.format("%.2f", contract.getMonthlyPayment()) + "\n"
            );

            writer.close();

        } catch (IOException ex) {
                System.out.println("Contract could not be written to file.");
                ex.printStackTrace();
            }
    }

    public static List<Contract> getContracts() {
        String input;
        ArrayList<Contract> contracts = new ArrayList<>();
        boolean isFinanced = true;

        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((input = bufferedReader.readLine()) != null) {
                String[] properties = input.split("\\|");

                Vehicle currentVehicle = new Vehicle(
                        Integer.parseInt(properties[4]),
                        Integer.parseInt(properties[5]),
                        properties[6],
                        properties[7],
                        properties[8],
                        properties[9],
                        Integer.parseInt(properties[10]),
                        Double.parseDouble(properties[11])
                );

                if (properties[0].equalsIgnoreCase("sale")) {
                    if (properties[15].equalsIgnoreCase("no")) {
                        isFinanced = false;
                    }
                    else if (properties[15].equalsIgnoreCase("yes")) {
                        isFinanced = true;
                    }

                    SalesContract salesContract = new SalesContract(
                            properties[1],
                            properties[2],
                            properties[3],
                            currentVehicle,
                            isFinanced
                    );

                    contracts.add(salesContract);
                }
                else if (properties[0].equalsIgnoreCase("lease")) {
                    LeaseContract leaseContract = new LeaseContract(
                            properties[1],
                            properties[2],
                            properties[3],
                            currentVehicle
                    );

                    contracts.add(leaseContract);
                }
            }

            bufferedReader.close();
        }
        catch (IOException ex) {
            System.out.println("Failed to load csv file.");
            ex.printStackTrace();
        }

        return contracts;
    }
}

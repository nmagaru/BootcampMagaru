package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.dgc.Lease;

public class ContractDataManager {
    public static void saveContract(Contract contract) {
        String filePath = "src/main/resources/contracts.csv";
        File file = new File(filePath);

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
}

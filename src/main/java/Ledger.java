import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;

public class Ledger {
    private final String fileName = "transactions.csv";

    //  Load transactions from file
    public List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                String[] parts = line.split("\\|");
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String desc = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                transactions.add(new Transaction(date, time, desc, vendor, amount));
            }
        } catch (IOException e) {
            System.out.println("⚠ Error loading transactions!");
        }
        return transactions;
    }

    // 💾 Save one transaction
    public void saveTransaction(Transaction t) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(t.toString() + "\n");
        } catch (IOException e) {
            System.out.println("⚠ Could not save transaction!");
        }
    }

    //  Show all transactions
    public void showAll() {
        List<Transaction> transactions = loadTransactions();
        Collections.reverse(transactions);
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}

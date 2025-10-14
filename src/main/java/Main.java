import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String fileName = "transactions.csv";

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== NETFLIX MONEY TRACKER =====");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    showLedger();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    //  Step 4: Add Deposit method
    public static void addDeposit() {
        try {
            System.out.print("Enter description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());

            String line = LocalDate.now() + "|" + LocalTime.now().withNano(0) + "|" + desc + "|" + vendor + "|" + amount;
            saveTransaction(line);
            System.out.println(" Deposit added!");
        } catch (Exception e) {
            System.out.println(" Error adding deposit!");
        }
    }

    //  Step 5: Make Payment method
    public static void makePayment() {
        try {
            System.out.print("Enter description: ");
            String desc = scanner.nextLine();
            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine()) * -1;

            String line = LocalDate.now() + "|" + LocalTime.now().withNano(0) + "|" + desc + "|" + vendor + "|" + amount;
            saveTransaction(line);
            System.out.println(" Payment added!");
        } catch (Exception e) {
            System.out.println(" Error adding payment!");
        }
    }

    //  Step 6: Save to CSV file
    public static void saveTransaction(String line) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(line + "\n");
        } catch (IOException e) {
            System.out.println(" Could not save transaction!");
        }
    }

    // Step 7: Show Ledger
    public static void showLedger() {
        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(new File(fileName).toPath()));
            Collections.reverse(lines); // Show newest first
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(" Error reading ledger!");
        }
    }





import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

//  Colors (optional)
    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";

    static Scanner scanner = new Scanner(System.in);
    static String fileName = "transactions.csv";

    //  Add these here:
    static Ledger ledger = new Ledger();
    static Report report = new Report();

    public static void main(String[] args) {
        //  Welcome message
        System.out.println(GREEN + "====================================" + RESET);
        System.out.println(CYAN + " Welcome to Prosperity's Ledger App! " + RESET);
        System.out.println(GREEN + "Track your money easily and smartly!" + RESET);
        System.out.println(GREEN + "====================================" + RESET);

        // Start the main menu
        showMainMenu();
    }
    public class Main {

        // 1️ Global variables and objects at the top
        static Scanner scanner = new Scanner(System.in);
        static Ledger ledger = new Ledger(); //  Add this near top
        static Report report = new Report(); //  (optional for later)

        // 2️ Your main method
        public static void main(String[] args) {
            // Welcome message and menu start
            showMainMenu();
        }

        // 3️ Your other methods like addDeposit() and makePayment()

        public static void addDeposit() {
            // your deposit code here
        }

        public static void makePayment() {
            // your payment code here
        }

        // 4️ NOW add showLedger() right after those
        public static void showLedger() {
            ledger.showAll(); //  This is where your Ledger class does the work
        }

        // 5️ And your showMainMenu() goes somewhere near the top or bottom
    }

    //  Main Menu
    public static void showMainMenu() {
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

    //  Add Deposit
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
            System.out.println("Deposit added!");
        } catch (Exception e) {
            System.out.println("Error adding deposit!");
        }
    }

    //  Make Payment
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
            System.out.println("Payment added!");
        } catch (Exception e) {
            System.out.println("Error adding payment!");
        }
    }

    //  Save Transaction
    public static void saveTransaction(String line) {
        try (FileWriter fw = new FileWriter(fileName, true)) {
            fw.write(line + "\n");
        } catch (IOException e) {
            System.out.println("Could not save transaction!");
        }
    }

    //  Show Ledger
    public static void showLedger() {
        try {
            List<String> lines = new ArrayList<>(Files.readAllLines(new File(fileName).toPath()));
            Collections.reverse(lines); // Show newest first
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading ledger!");
        }
    }





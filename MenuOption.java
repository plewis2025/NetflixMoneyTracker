public enum MenuOption {
    DEPOSIT,
    PAYMENT,
    LEDGER,
    EXIT
System.out.println("D) Add Deposit");
System.out.println("P) Make Payment");
System.out.println("L) Ledger");
System.out.println("X) Exit");
    String input = scanner.nextLine().toUpperCase();

try {
        MenuOption option = MenuOption.valueOf(input);
        switch (option) {
            case DEPOSIT -> addDeposit();
            case PAYMENT -> makePayment();
            case LEDGER -> showLedger();
            case EXIT -> System.exit(0);
        }
    } catch (IllegalArgumentException e) {
        System.out.println("Invalid option!");
    }


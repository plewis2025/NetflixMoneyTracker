import java.time.LocalDate;
import java.util.List;

public class Report {
    private Ledger ledger = new Ledger();

    //  Search transactions by vendor
    public void searchByVendor(String vendorName) {
        List<Transaction> transactions = ledger.loadTransactions();
        System.out.println("Results for vendor: " + vendorName);
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendorName)) {
                System.out.println(t);
            }
        }
    }

    //  Month-to-date report
    public void monthToDate() {
        List<Transaction> transactions = ledger.loadTransactions();
        LocalDate now = LocalDate.now();
        LocalDate startOfMonth = now.withDayOfMonth(1);

        System.out.println("Month-to-Date Report:");
        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(startOfMonth)) {
                System.out.println(t);
            }
        }
    }
}


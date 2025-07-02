package Bank;

import Bank.BankStatementCSVParser;
import Bank.BankTransaction;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

        final String RESOURCES = "../src/"; // csv 파일 위치 지정
//        final String fileName = "bank-date-simple.csv";
        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFromCSV(lines);
        final Month month = Month.JANUARY;

        System.out.println("The total: " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions: " + month + " " + selectInMonth(bankTransactions, month));
    }

    public static double calculateTotalAmount(final List<BankTransaction> bankTransactions){
        double total = 0d;

        for (BankTransaction bankTransaction : bankTransactions){
            total += bankTransaction.getAmount();
        }

        return total;
    }

    public static List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month){
        final List<BankTransaction> bankTransactionsInMonth = new ArrayList<>();

        for (BankTransaction bankTransaction : bankTransactions){
            if(bankTransaction.getDate().getMonth() == month){
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
    }
}

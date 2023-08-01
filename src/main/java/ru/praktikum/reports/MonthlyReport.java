package ru.praktikum.reports;

import ru.praktikum.services.decoder.BooleanDecoder;
import ru.praktikum.services.fileReader.FileLinesReader;
import ru.praktikum.services.fileReader.Report;

import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    private int monthNumber;
    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int unitPrice;

    public MonthlyReport() {

    }
    private MonthlyReport(int monthNumber,
                          String itemName,
                          boolean isExpense,
                          int quantity,
                          int unitPrice
    ) {

        this.monthNumber = monthNumber;
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public ArrayList<MonthlyReport> getTransactionsByMonth() {
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
        FileLinesReader fileReader = new FileLinesReader();
        HashMap<Integer, ArrayList<String>> monthReportsHashMap = fileReader
                .readMonthReports(
                        fileReader
                                .getListOfFiles(Report.MONTH)
                );
        MonthlyReport monthlyReport;
        for (Integer month : monthReportsHashMap.keySet()) {
            ArrayList<String> line = monthReportsHashMap.get(month);
            for (String s : line) {
                String[] array = s.split(",");
                monthlyReport = new MonthlyReport(
                        month,
                        array[0],
                        BooleanDecoder.booleanDecoder(array[1]),
                        Integer.parseInt(array[2]),
                        Integer.parseInt(array[3])
                );
                monthlyReports.add(monthlyReport);
            }
        }
        return monthlyReports;
    }
}

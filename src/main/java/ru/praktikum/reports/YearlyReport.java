package ru.praktikum.reports;

import ru.praktikum.services.decoder.BooleanDecoder;
import ru.praktikum.services.fileReader.FileLinesReader;
import ru.praktikum.services.fileReader.Report;

import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    private int year;
    private int month;
    private int amount;
    private boolean isExpense;

    public YearlyReport() {
    }
    public YearlyReport(int year, int month, int amount, boolean isExpense) {
        this.year = year;
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public ArrayList<YearlyReport> createYearlyReport() {
        ArrayList<YearlyReport> yearlyReports = new ArrayList<>();
        FileLinesReader fileReader = new FileLinesReader();
        HashMap<Integer, ArrayList<String>> yearReport = fileReader.readYearReports(
                        fileReader.getListOfFiles(Report.YEAR)
                );

        YearlyReport yearlyReport;

        for (Integer year : yearReport.keySet()) {
            ArrayList<String> line = yearReport.get(year);
            for (String s : line) {
                String[] str = s.split(",");
                yearlyReport = new YearlyReport(
                        year,
                        Integer.parseInt(str[0]),
                        Integer.parseInt(str[1]),
                        BooleanDecoder.booleanDecoder(str[2])
                );
                yearlyReports.add(yearlyReport);
            }
        }
        return yearlyReports;
    }
}

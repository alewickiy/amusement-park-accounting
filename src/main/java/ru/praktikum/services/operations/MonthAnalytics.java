package ru.praktikum.services.operations;

import ru.praktikum.reports.MonthlyReport;
import ru.praktikum.services.decoder.MonthDecoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class MonthAnalytics {
    public static void getAnalytics(ArrayList<MonthlyReport> monthlyReports) {
        if (monthlyReports.isEmpty()) {
            System.out.println("Не загружены месячные отчёты. Пожалуйста, загрузите отчёты, выбрав пункт 1 главного меню");
            return;
        }

        HashMap<Integer, ArrayList<MonthlyReport>> monthlyReportHM = createMonthlyReportHashMap(monthlyReports);

        for (Integer month : monthlyReportHM.keySet()) {
            String maxIncomeSumItem = "";
            String maxExpenseSumItem = "";
            int maxIncomeSum = 0;
            int maxExpenseSum = 0;
            ArrayList<MonthlyReport> monthlyReportAL = monthlyReportHM.get(month);

            for (MonthlyReport monthlyReport : monthlyReportAL) {
                if (monthlyReport.isExpense()) {
                    if (getSum(monthlyReport) > maxExpenseSum) {
                        maxExpenseSum = getSum(monthlyReport);
                        maxExpenseSumItem = monthlyReport.getItemName();
                    }
                } else {
                    if (getSum(monthlyReport) > maxIncomeSum) {
                        maxIncomeSum = getSum(monthlyReport);
                        maxIncomeSumItem = monthlyReport.getItemName();
                    }
                }
            }

            System.out.printf("%s:\n", MonthDecoder.nameOfMonth(month));
            System.out.printf("Самый прибыльный товар - %s, сумма: %d\n", maxIncomeSumItem, maxIncomeSum);
            System.out.printf("Самая большая трата - %s, сумма: %d\n\n", maxExpenseSumItem, maxExpenseSum);

        }
    }

    private static HashMap<Integer, ArrayList<MonthlyReport>> createMonthlyReportHashMap(ArrayList<MonthlyReport> monthlyReports) {
        HashMap<Integer, ArrayList<MonthlyReport>> monthlyReportHM = new HashMap<>();
        Set<Integer> months = new TreeSet<>();
        for (MonthlyReport monthlyReport : monthlyReports) {
            months.add(monthlyReport.getMonthNumber());
        }

        ArrayList<MonthlyReport> mrs;
        for (Integer t : months) {
            mrs = new ArrayList<>();
            for (MonthlyReport monthlyReport : monthlyReports) {
                if (monthlyReport.getMonthNumber() == t) {
                    mrs.add(monthlyReport);
                }
            }
            monthlyReportHM.put(t, mrs);
        }
        return monthlyReportHM;
    }
    public static int getSum(MonthlyReport monthlyReport) {
        return monthlyReport.getUnitPrice() * monthlyReport.getQuantity();
    }
}

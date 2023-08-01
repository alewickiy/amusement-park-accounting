package ru.praktikum.services.operations;

import ru.praktikum.reports.MonthlyReport;
import ru.praktikum.reports.YearlyReport;
import ru.praktikum.services.decoder.MonthDecoder;

import java.util.ArrayList;

public class YearAnalytics {
    public static void getAnalytics(ArrayList<YearlyReport> yearlyReports, ArrayList<MonthlyReport> monthlyReports) {
        if (yearlyReports.isEmpty()) {
            System.out.println("Не загружен годовой отчёт. Пожалуйста, загрузите отчёт, выбрав пункт 2 главного меню");
            return;
        }
        if (monthlyReports.isEmpty()) {
            System.out.println("Не загружены месячные отчёты. Пожалуйста, загрузите отчёты, выбрав пункт 1 главного меню");
            return;
        }
        getYearlyAnalytics(yearlyReports, monthlyReports);
    }


    private static void getYearlyAnalytics(ArrayList<YearlyReport> yearlyReports, ArrayList<MonthlyReport> monthlyReports) {
        int expensesSum = 0;
        int expensesCount = 0;
        int incomeSum = 0;
        int incomeCount = 0;

        System.out.printf("\nГод: %d\n", yearlyReports.get(0).getYear());

        for (YearlyReport yearlyReport : yearlyReports) {
            if (!yearlyReport.isExpense()) {
                System.out.printf("Прибыль за %s составила %d\n"
                        , MonthDecoder.nameOfMonth(yearlyReport.getMonth())
                        , yearlyReport.getAmount()
                );
            }
        }
        //TODO средний расход за все имеющиеся операции в году;
        for (MonthlyReport monthlyReport : monthlyReports) {
            if (monthlyReport.isExpense()) {
                expensesSum += monthlyReport.getQuantity() * monthlyReport.getUnitPrice();
                expensesCount++;
            } else {
                incomeSum += monthlyReport.getQuantity() * monthlyReport.getUnitPrice();
                incomeCount++;
            }
        }

        System.out.printf("средний расход за операцию в отчётном периоде: %d\n", (expensesSum / expensesCount));
        System.out.printf("средний доход за операцию в отчётном периоде: %d\n\n", (incomeSum / incomeCount));
    }
}

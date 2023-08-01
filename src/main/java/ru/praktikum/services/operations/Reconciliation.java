package ru.praktikum.services.operations;

import ru.praktikum.services.decoder.ExpensesDecoder;
import ru.praktikum.services.decoder.MonthDecoder;
import ru.praktikum.reports.MonthlyReport;
import ru.praktikum.reports.YearlyReport;

import java.util.ArrayList;

public class Reconciliation {
    public static void getReconciliation(
            ArrayList<MonthlyReport> transactionsByMonth,
            ArrayList<YearlyReport> yearlyReport
    ) {
        if (transactionsByMonth.isEmpty() && yearlyReport.isEmpty()) {
            System.out.println("Не загружены месячные или годовые отчёты. Пожалуйста, загрузите отчёты, выбрав поочерёдно пункты 1 и 2 главного меню");
        } else {
            boolean error = false;
            int errorMonthNumber = 0;
            boolean errorIsExpense = false;
            int errorAmountYear = 0;
            int errorAmountMonth = 0;
            for (YearlyReport report : yearlyReport) {
                int month = report.getMonth();
                boolean isExpense = report.isExpense();
                int sumExpense = 0;
                for (MonthlyReport monthlyReport : transactionsByMonth) {
                    if (monthlyReport.getMonthNumber() == month && monthlyReport.isExpense() == isExpense) {
                        sumExpense += (monthlyReport.getUnitPrice() * monthlyReport.getQuantity());
                    }
                }
                if (sumExpense != report.getAmount()) {
                    error = true;
                    errorMonthNumber = month;
                    errorIsExpense = isExpense;
                    errorAmountMonth = sumExpense;
                    errorAmountYear = report.getAmount();
                }
            }
            if (error) {
                System.out.printf("Ошибка в сверке за месяц: %s, в типе операций: %s\n"
                        , MonthDecoder.nameOfMonth(errorMonthNumber)
                        , ExpensesDecoder.accountingOperationName(errorIsExpense)
                );
                System.out.printf("В годовом отчёте указана сумма операций: %d / в месячном: %d\n"
                        , errorAmountYear
                        , errorAmountMonth
                );
            } else {
                System.out.println("Сверка произведена. Ошибки отсутствуют");
            }
        }
    }
}

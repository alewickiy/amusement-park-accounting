package ru.praktikum;

import ru.praktikum.reports.MonthlyReport;
import ru.praktikum.reports.YearlyReport;
import ru.praktikum.services.operations.MonthAnalytics;
import ru.praktikum.services.operations.Reconciliation;
import ru.praktikum.services.operations.YearAnalytics;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
        ArrayList<YearlyReport> yearlyReports = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while(true) {
            printMenu();
            int userChoice = scanner.nextInt();
            if (userChoice == 1) {
                monthlyReports = monthlyReport.getTransactionsByMonth();
            } else if (userChoice == 2) {
                yearlyReports = yearlyReport.createYearlyReport();
            } else if (userChoice == 3) {
                Reconciliation.getReconciliation(monthlyReports, yearlyReports);
            } else if (userChoice == 4) {
                MonthAnalytics.getAnalytics(monthlyReports);
            } else if (userChoice == 5) {
                YearAnalytics.getAnalytics(yearlyReports, monthlyReports);
            } else if (userChoice == 0) {
                break;
            } else {
                System.out.println("Пожалуйста, введите цифру соответствующую необходимому пункту меню.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать месячные отчёты");
        System.out.println("2 - Считать годовые отчёты");
        System.out.println("3 - Сверить годовые и месячные отчёты");
        System.out.println("4 - Вывести информацию о месячных отчётах"); //Вывести информацию обо всех месячных отчётах — по сохранённым данным вывести в консоль имеющуюся информацию.
        System.out.println("5 - Вывести информацию о годовом отчёте"); //Вывести информацию о годовом отчёте — по сохранённым данным вывести в консоль имеющуюся информацию.
        System.out.println("0 - Выход");
    }
}
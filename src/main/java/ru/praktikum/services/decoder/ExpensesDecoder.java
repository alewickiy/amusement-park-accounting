package ru.praktikum.services.decoder;

public class ExpensesDecoder {
    public static String accountingOperationName(boolean isExpense) {
        if (isExpense) {
            return "Расходы";
        } else {
            return "Доходы";
        }
    }
}

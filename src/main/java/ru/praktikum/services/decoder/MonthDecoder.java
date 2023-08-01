package ru.praktikum.services.decoder;

public class MonthDecoder {
    public static String nameOfMonth(int month) {
        String nameOfMonth;
        switch (month) {
            case 1:
                nameOfMonth = "Январь";
                break;
            case 2:
                nameOfMonth = "Февраль";
                break;
            case 3:
                nameOfMonth = "Март";
                break;
            case 4:
                nameOfMonth = "Апрель";
                break;
            case 5:
                nameOfMonth = "Май";
                break;
            case 6:
                nameOfMonth = "Июнь";
                break;
            case 7:
                nameOfMonth = "Июль";
                break;
            case 8:
                nameOfMonth = "Август";
                break;
            case 9:
                nameOfMonth = "Сентябрь";
                break;
            case 10:
                nameOfMonth = "Октябрь";
                break;
            case 11:
                nameOfMonth = "Ноябрь";
                break;
            case 12:
                nameOfMonth = "Декабрь";
                break;
            default:
                nameOfMonth = "wrong month";
        }
        return nameOfMonth;
    }
}

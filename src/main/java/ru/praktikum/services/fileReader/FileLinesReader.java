package ru.praktikum.services.fileReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FileLinesReader {
    private final String PATH = "./src/main/resources/";

    public HashMap<Integer, ArrayList<String>> readMonthReports(List<String> fileNames) {
        HashMap<Integer, ArrayList<String>> reportsByMonth = new HashMap<>();
        for (String fileName : fileNames) {
            int month;
            String path = PATH + fileName;
            try {
                ArrayList<String> transactions = new ArrayList<>(Files.readAllLines(Path.of(path)));
                clearTableHeader(transactions);
                month = Integer.parseInt(fileName.substring(6, 8));
                reportsByMonth.put(month, transactions);
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
                throw new RuntimeException(e);
            }
        }
        return reportsByMonth;
    }

    public HashMap<Integer, ArrayList<String>> readYearReports(List<String> fileNames) {
        HashMap<Integer, ArrayList<String>> reportsByYear = new HashMap<>();
        for (String fileName : fileNames) {
            int year;
            String path = PATH + fileName;
            try {
                ArrayList<String> transactions = new ArrayList<>(Files.readAllLines(Path.of(path)));
                clearTableHeader(transactions);
                year = Integer.parseInt(fileName.substring(2, 6));
                reportsByYear.put(year, transactions);
            } catch (IOException e) {
                System.out.println("Невозможно прочитать файл с отчётом. Возможно, файл отсутствует в нужной директории.");
                throw new RuntimeException(e);
            }
        }

        return reportsByYear;
    }

    public ArrayList<String> getListOfFiles(Report report) {
        ArrayList<String> reports = new ArrayList<>();
        File dir = new File(PATH);
        List<File> listOfFiles = Arrays.asList(Objects.requireNonNull(dir.listFiles()));
        switch (report){
            case MONTH:
                for (File file : listOfFiles) {
                    if (file.getName().startsWith("m")) {
                        reports.add(file.getName());
                    }
                }
                break;
            case YEAR:
                for (File file : listOfFiles) {
                    if (file.getName().startsWith("y")) {
                        reports.add(file.getName());
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + report);
        }
        return reports;
    }

    private void clearTableHeader(ArrayList<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (
                    lines.get(i).equals("item_name,is_expense,quantity,unit_price")
                            || lines.get(i).equals("month,amount,is_expense")
            ) {
                lines.remove(i);
            }
        }
    }
}

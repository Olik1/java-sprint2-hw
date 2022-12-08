import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        YearReport yearReport = new YearReport(2021);
        ArrayList<MonthReport> reports = new ArrayList<>();
        Checker checker = new Checker(yearReport, reports);

        while (true) {
            printMenu();
            int input = scanner.nextInt();
            if (input == 1) {
                for (int i = 1; i <= 3; i++) {
                    MonthReport report = new MonthReport(2021, i);
                    reports.add(report);
                }
                System.out.println("Все месячные отчеты считаны");
                //"1 - Считать все месячные отчеты"
            } else if (input == 2) {
                //"2 - Считать годовой отчет"
                yearReport.load();
            } else if (input == 3) {
                //"3 - Сверить отчеты"
                checker.check();
            } else if (input == 4) {
                for (int i = 1; i <= 3; i++) {
                    MonthReport report = new MonthReport(2021, i);
                    report.showMonthsStatistic();
                }
                //"4 - Вывести информацию о всех месячных отчетах"
            } else if (input == 5) {
                yearReport.showStatistic();
                //"5 - Вывести информацию о годовом отчете"
            } else if (input == 0) {
                return;
            } else {
                System.out.println("Вы ввели неверную комманду, попробуйте снова!");
            }
        }

    }

    public static void printMenu() {
        System.out.println("Что Вы хотите сделать?");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию о всех месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выйти из приложения");
    }
}

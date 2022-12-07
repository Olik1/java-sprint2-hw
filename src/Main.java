import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        YearReport yearReport = new YearReport(2021);

        //MonthReport monthReport = new MonthReport();

        yearReport.showStatistic();

//        Checker checker = new Checker(yearReport, monthReport);
//        boolean check = checker.check();
//        System.out.println("Результат проверки:  " + check);
//        while (true) {
//            printMenu();
//            int input = scanner.nextInt();
//            if (input == 1) {
//                //"1 - Считать все месячные отчеты"
//            } else if (input == 2) {
//                //"2 - Считать годовой отчет"
//            } else if (input == 3) {
//                //"3 - Сверить отчеты"
//            }else if (input == 4) {
//                //"4 - Вывести информацию о всех месячных отчетах"
//            }else if (input == 5) {
//                //"5 - Вывести информацию о годовом отчете"
//            }else if (input == 0) {
//                return;
//            } else {
//                System.out.println("Вы ввели неверную комманду, попробуйте снова!");
//            }
//        }


//        int b = (1000 *180) + (3500 * 300) + (2421 * 150);
//        int a = (50 * 2000) + (100000) + (3 * 50000);
//        System.out.println("доход = " + b);
//        System.out.println("трата = " + a);
//        String s = monthReport.getTopProduct(1);
//        System.out.println(s);

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

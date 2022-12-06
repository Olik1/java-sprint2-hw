public class Main {

    public static void main(String[] args) {

        YearReport yearReport = new YearReport("resources/y.2021.csv");


        MonthReport monthReport = new MonthReport();
        monthReport.loadMonth(1);
        monthReport.loadMonth(2);
        monthReport.loadMonth(3);

        Checker checker = new Checker(yearReport, monthReport);
        boolean check = checker.check();
        System.out.println("Результат проверки:  " + check);
    }
}


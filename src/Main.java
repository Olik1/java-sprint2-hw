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
//        int b = (1000 *180) + (3500 * 300) + (2421 * 150);
//        int a = (50 * 2000) + (100000) + (3 * 50000);
//        System.out.println("доход = " + b);
//        System.out.println("трата = " + a);
//        String s = monthReport.getTopProduct(1);
//        System.out.println(s);

        /*
        item_name,is_expense,quantity,sum_of_one
        Аренда коньков,FALSE,1000,180   трата (TRUE) или доход (FALSE)
        Продажа билетов, FALSE,3500,300
        Продажа кофе,FALSE,2421,150

        Коньки,TRUE,50,2000
        Новогодняя ёлка,TRUE,1,100000
        Ларёк с кофе,TRUE,3,50000
         */
    }
}


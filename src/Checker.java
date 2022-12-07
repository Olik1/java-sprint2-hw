import java.util.ArrayList;

public class Checker {

    public YearReport yearReport;
    public ArrayList<MonthReport> monthReports;

    public Checker(YearReport yearReport, ArrayList<MonthReport> monthReports) {
        this.yearReport = yearReport;
        this.monthReports = monthReports;
    }
/*
При вызове сверки данных программа должна:

    Подсчитывать две суммы: общие доходы и общие расходы по каждому из месяцев.
    Сверять полученные суммы с суммой доходов и расходов в отчёте по году.

Если обнаружена ошибка, программа должна выводить месяц, в котором обнаружено несоответствие.
Если ошибок не обнаружено, должна выводиться только информация об успешном завершении операции.
 */
    public void check() {
        boolean checked = true;
        for (MonthReport report : monthReports) {
            String month = (report.month < 10) ? ("0" + report.month) : String.valueOf(report.month);
            if (yearReport.expenceByMonth.get(month) != report.getExpence()) {
                checked = false;
                System.out.println("В месяце " + month + " обнаружено несоотвествие по расходам");
            }
            if (yearReport.incomeByMonth.get(month) != report.getIncome()) {
                checked = false;
                System.out.println("В месяце " + month + " обнаружено несоотвествие по доходам");
            }
        }
        if (yearReport.expenceByMonth.size() != monthReports.size()) {
            checked = false;
            System.out.println("Количество месячных отчетов не соответствует количеству в годовом");
        }
        if (checked) {
            System.out.println("Расхождений не обнаружено");
        }
    }
}

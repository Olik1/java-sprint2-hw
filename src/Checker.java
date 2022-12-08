import java.util.ArrayList;

public class Checker {

    public YearReport yearReport;
    public ArrayList<MonthReport> monthReports;

    public Checker(YearReport yearReport, ArrayList<MonthReport> monthReports) {
        this.yearReport = yearReport;
        this.monthReports = monthReports;
    }

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

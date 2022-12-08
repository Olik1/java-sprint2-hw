import java.util.ArrayList;
import java.util.HashMap;

public class YearReport {
    public Integer year;
    public HashMap<String, Integer> expenceByMonth = new HashMap<>();
    public HashMap<String, Integer> incomeByMonth = new HashMap<>();


    public YearReport(Integer year) {
        this.year = year;
        load();
    }

    public void load() {
        String path = "resources/y." + year + ".csv";
        String content = Reader.readFileContents(path);
        if (content == null) {
            System.out.println("Ошибка считывания, проверьте путь: " + path);
            return;
        }
        String[] lines = content.split("\r?\n");// ["1 строка", "2 строка"...]
        for (int i = 1; i < lines.length; i++) {
            //возвращаем массив строк по месяцу
            String[] parts = lines[i].split(","); //разбиваем значения на нужные данные и записываем в массив
            String month = parts[0];
            int amount = Integer.parseInt(parts[1]);
            boolean isExpence = Boolean.parseBoolean(parts[2]);
            if (isExpence) {
                expenceByMonth.put(month, amount);
            } else {
                incomeByMonth.put(month, amount);
            }
        }
        System.out.println("Годовой отчет считан успешно");
    }
    public void showStatistic() { //распечатываем статистику
        System.out.println("Приведенная статистика за отчетный период: " + year);
        for (int i = 1; i <= expenceByMonth.size(); i++) {
            String month = i < 10 ? ("0" + i) : String.valueOf(i);
            System.out.println("Прибыль за месяц " + month + " составляет " + sumProfit(month) + " руб.");
        }
        System.out.println("Средний расход за все месяцы в году составляет " + findAvgExp() + " руб.");
        System.out.println("Средний доход за все месяцы в году составляет " + findAvgInc() + " руб.");

    }

    public int findMonthExpense(String month) { //расходы по месяцу из годового отчета
        return expenceByMonth.getOrDefault(month, 0);
    }

    public int findMonthIncome(String month) { // доходы по месяцу из годового отчета
        return incomeByMonth.getOrDefault(month, 0);
    }

    public int sumProfit(String month) { //находим прибыль
        return findMonthIncome(month) - findMonthExpense(month);
    }

    private int findAvgExp() { //Средний расход за год
        int sum = 0;
        for (String month : expenceByMonth.keySet()) {
            sum += expenceByMonth.get(month);
        }
        return sum / expenceByMonth.size();
    }

    private int findAvgInc() { //Средний доход за год
        int sum = 0;
        for (String month : incomeByMonth.keySet()) {
            sum += incomeByMonth.get(month);
        }
        return sum / incomeByMonth.size();
    }
    public ArrayList<MonthReport> loadAllMonths() {
        ArrayList<MonthReport> reports = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            MonthReport monthReport = new MonthReport(this.year, i);
            if (!monthReport.isEmpty()) {
                reports.add(monthReport);
            }
        }
        return reports;
    }

}

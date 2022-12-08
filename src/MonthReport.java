import java.util.ArrayList;
import java.util.HashMap;

public class MonthReport { // храним данные по месячным отчетам
    public Integer month;
    public Integer year;

    ArrayList<MonthRecord> monthRecords = new ArrayList<>();
    HashMap<String, Integer> expenceByProduct = new HashMap<>();
    HashMap<String, Integer> incomeByProduct = new HashMap<>();

    public MonthReport(int year, int month) {
        this.year = year;
        this.month = month;
        load();
    }

    public void load() {

        String month = this.month < 10 ? ("0" + this.month) : String.valueOf(this.month);
        String path = "resources/m." + year + month + ".csv";
        String content = Reader.readFileContents(path);
        if (content == null) {
            System.out.println("Ошибка считывания, проверьте путь: " + path);
            return;
        }
        String[] lines = content.split("\r?\n"); // ["1 строка", "2 строка"...]
        //разбиваем по строкам
        for (int i = 1; i < lines.length; i++) { //начинаем с 1 эл.т.к. не нужен заголовок из файла
            //извлекаем n-ю строку из массива - "Продажа кофе,FALSE,1200,150"
            String[] parts = lines[i].split(","); //парсим на части
            String itemName = parts[0]; //"Продажа кофе"
            boolean isExpense = Boolean.parseBoolean(parts[1]); //"FALSE"
            int quantity = Integer.parseInt(parts[2]); //"1200"
            int sumOfOne = Integer.parseInt(parts[3]); // "150"
            //чтение данных по каждой строке из конкретного месяца в память объекта
            monthRecords.add(new MonthRecord(itemName, isExpense, quantity, sumOfOne)); // добавляем сохраненные данные из объекта monthRecord -> в список monthRecords
            if (isExpense) {
                expenceByProduct.put(itemName, expenceByProduct.getOrDefault(itemName, 0) + (quantity * sumOfOne));
            } else {
                incomeByProduct.put(itemName, incomeByProduct.getOrDefault(itemName, 0) + (quantity * sumOfOne));
            }
        }

    }


    public String getLowProduct() { // самый прибыльный продукт
        int maxSum = 0;
        String maxName = null;
        for (String name : expenceByProduct.keySet()) {
            if (expenceByProduct.get(name) > maxSum) {
                maxSum = expenceByProduct.get(name);
                maxName = name;
            }
        }
        return maxName;
    }

    public String getHighProduct() {
        int maxSum = 0;
        String maxName = null;
        for (String name : incomeByProduct.keySet()) {
            if (incomeByProduct.get(name) > maxSum) {
                maxSum = incomeByProduct.get(name);
                maxName = name;
            }
        }
        return maxName;
    }

    public void showMonthsStatistic() {
        String productHigh = getHighProduct();
        String productLow = getLowProduct();
        System.out.println("Информация за " + month + "-й месяц:");
        System.out.println("Самый прибыльный товар за месяц: " + productHigh + ". Продано на " + incomeByProduct.get(productHigh) + " руб.");
        System.out.println("Самая большая трата в месяце: " + productLow + ". Потрачено " + expenceByProduct.get(productLow) + "руб.");
    }

    public int getExpence() { //посчитать расходы за месяц, трата (TRUE)
        int sumExpence = 0;
        for (String name : expenceByProduct.keySet()) {
            sumExpence += expenceByProduct.get(name);
        }
        return sumExpence;
    }

    public int getIncome() { //посчитать расходы за месяц, трата (TRUE)
        int sumIncome = 0;
        for (String name : incomeByProduct.keySet()) {
            sumIncome += incomeByProduct.get(name);
        }
        return sumIncome;
    }

    public boolean isEmpty() {
        return incomeByProduct.isEmpty() && expenceByProduct.isEmpty();
    }

}

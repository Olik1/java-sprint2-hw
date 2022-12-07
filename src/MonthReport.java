import java.util.ArrayList;
import java.util.HashMap;

public class MonthReport { // храним данные по месячным отчетам
    String itemName;
    boolean isExpense;//income = false, expenses = true
    int quantity;
    int sumOfOne;
    int month;

    ArrayList<MonthRecord> monthRecords = new ArrayList<>();
    HashMap<Integer, ArrayList<MonthRecord>> allMonths = new HashMap<>();

    public MonthReport() {
        this.month = allMonths.size();
    }

    public void loadMonth(int month) {
        for (int i = 0; i <= month; i++) {
            String path = "resources/m.20210" + month + ".csv";
            String content = Reader.readFileContents(path);
            if (content == null) {
                System.out.println("Ошибка считывания, проверьте путь: " + path);
                return;
            }
            String[] lines = content.split("\r?\n"); // ["1 строка", "2 строка"...]
            //разбиваем по строкам
            for (int j = 1; j < lines.length; j++) { //начинаем с 1 эл.т.к. не нужен заголовок из файла
                String line = lines[j]; //извлекаем n-ю строку из массива - "Продажа кофе,FALSE,1200,150"
                String[] parts = line.split(","); //парсим на части
                itemName = parts[0]; //"Продажа кофе"
                isExpense = Boolean.parseBoolean(parts[1]); //"FALSE"
                quantity = Integer.parseInt(parts[2]); //"1200"
                sumOfOne = Integer.parseInt(parts[3]); // "150"
                MonthRecord monthRecord = new MonthRecord(itemName, isExpense, quantity, sumOfOne);//чтение данных по каждой строке из конкретного месяца в память объекта
                monthRecords.add(monthRecord); // добавляем сохраненные данные из объекта monthRecord -> в список monthRecords
            }
            allMonths.put(i, monthRecords);//добавляем данные по всем месяцам в мапу
        }
    }



    public int getExpence(int month) { //посчитать расходы за месяц, трата (TRUE)
        ArrayList<MonthRecord> records = allMonths.get(month);
        int sumExpence = 0;
        for (MonthRecord record : records) {
            int sum = record.getSumOfOne();
            int price = record.getQuantity();
            boolean isExpense = record.isExpense;
            if (isExpense) {
                sumExpence += sum * price;
            }
        }
        return sumExpence;
    }

    public int getIncome(int month) { //рассчитать доход за месяц, доход (FALSE)
        ArrayList<MonthRecord> records = allMonths.get(month);
        int sumIncome = 0;
        for (MonthRecord record : records) {
            int income = record.sumOfOne;
            int price = record.getQuantity();
            boolean isExpence = record.getIsExpense();
            if(!isExpence) {
                sumIncome += income * price;
            }
        }
        return sumIncome;
    }

    public String getTopProduct(int month) { // самый прибыльный продукт
        //самый прибыльный товар, товары могут повторяться, надо суммировать в мапе
        HashMap<String,Integer> freqs = new HashMap<>(); //название товара и количество продаж
        for (MonthRecord monthRecord : monthRecords) { // пройтись по всем товарам и узнать сколько штук купили
           //кладем по ключу -> название товара количество штук которых мы продали
            freqs.put(monthRecord.itemName, freqs.getOrDefault(monthRecord.itemName, 0) + monthRecord.quantity);
            //надо учесть что товар может повториться -> через freqs.get(monthRecord.itemName)
            // надо учесть случай когда товар не лежит в мапе get + null = nullpointerexception -> getOrDefault
        }
        //находим максимум по множеству ключей
        String maxName = null;
        for (String name : freqs.keySet()) { //пробегаемся по товарам по ключам мапы
            if(maxName == null) { //если не было никакого товара -> то  найденный товар max
                maxName = name;
                continue;
            }
            if(freqs.get(maxName) < freqs.get(name)) { //если у меня частота max товара < товара который достаю, то товар кот.доставли явл. max
                maxName = name;
            }
        }
        return maxName;
    }
    public void showMonthsStatistic() {


            System.out.println("Информация за " + "i" + "-й месяц:");
            System.out.println("Самый прибыльный товар за месяц: "  + ". Продано на " +   " рублей.");
            System.out.println("Самая большая трата в месяце: "   + ". Потрачено "
                    + " рублей.\n");
        }

}

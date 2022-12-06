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

    public void printStatistic() {
        for (int i = 0; i < this.month; i++) {
            ArrayList<MonthRecord> monthsArr = allMonths.get(i);
            System.out.println("Самый прибыльный товар за месяц: " + i);
        }
    }

    public int getExpence(int month) { //посчитать расходы за месяц, трата (TRUE)
        ArrayList<MonthRecord> records = monthRecords.get(month);
        int sumExpence = 0;
        for (MonthRecord record : records) {
            int sum = record.getSumOfOne();
            int price = record.getQuantity();
            if (isExpense) {
                sumExpence += sum * price;
            }
        }
        return sumExpence;
    }

    public int getIncome() { //рассчитать доход за месяц, доход (FALSE) КАК ПРАВИЛЬНО СЧИТАТЬ ДОХОД ЗА МЕСЯЦ ЧЕРЕЗ МАПУ ИЛИ СПИСОК?
        int sumIncome = 0;
        for (MonthRecord monthRecord : monthRecords) {
            if (!isExpense) {
                sumIncome = sumIncome + (monthRecord.sumOfOne * monthRecord.quantity);
            }
        }
        return sumIncome;
    }

    public String getTopProduct(int month) { // КАК ОТСОРТИРОВАТЬ ПО МЕСЯЦУ??
        //самый прибыльный товар, товары могут повторяться, надо суммировать в мапе
        HashMap<String,Integer> freqs = new HashMap<>(); //название товара и количество продаж
        for (MonthRecord monthRecord : monthRecords) { // пройтись по всем товарам и узнать сколько штук купили
           //кладем по ключу -> название товара количество штук которых мы продали
            freqs.put(monthRecord.itemName, freqs.getOrDefault(monthRecord.itemName, 0) + monthRecord.quantity); //надо учесть что товар может повториться -> через freqs.get(monthRecord.itemName)
            // надо учесть случай когда товар не лежит в мапе get + null = nullpointerexception -> getOrDefault
        }
        //находим максимум по множеству ключей
        String maxName = null;
        for (String name : freqs.keySet()) {
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


}

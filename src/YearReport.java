import java.util.ArrayList;

public class YearReport {
    public ArrayList<YearRecord> yearRecords = new ArrayList<>();

    public YearReport(String path) {

        String content = Reader.readFileContents(path);
        if (content == null) {
            System.out.println("Ошибка считывания, проверьте путь: " + path);
            return;
        }
        String line = "";
        String [] lines = content.split("\r?\n");// ["1 строка", "2 строка"...]
        for (int i = 1; i < lines.length; i++) {
            //возвращаем массив строк по месяцу
            line = lines[i];//01,1593150,false //записываем значение строки
            String [] parts = line.split(","); //разбиваем значения на нужные данные и записываем в массив
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpence = Boolean.parseBoolean(parts[2]);
            YearRecord yearRecord = new YearRecord(month, amount,isExpence);
            yearRecords.add(yearRecord);
        }
    }
    //    Рассматриваемый год;
    //    Прибыль по каждому месяцу. Прибыль — это разность доходов и расходов; not done
    //    Средний расход за все месяцы в году; done
    //    Средний доход за все месяцы в году. done

    public void showStatistic() { //распечатываем статистику
        int month = 0;
        int avgExp = findAvgExp();
        int avgInc = findAvgInc();
        System.out.println("Приведенная статистика за отчетный период:");
        for (int i = 0; i < yearRecords.size(); i++) {
            month ++;
            System.out.println("Средний расход за все месяцы в году составляет " + avgExp + "рублей");
            System.out.println("Средний доход за все месяцы в году составляет " + avgInc + "рублей");
        }
    }

    public int findMonthExpense(int month) { //расходы по месяцу из годового отчета
        int sumOfExpense = 0;
        boolean isExpense = yearRecords.get(month).getIsExpense();
        int amount = yearRecords.get(month).getAmount();
        if(isExpense) {
            sumOfExpense += amount;
        }
        return sumOfExpense;
    }

    public int findMonthIncome(int month) { // доходы по месяцу из годового отчета
        int sumOfIncome = 0;
        boolean isExpense = yearRecords.get(month).getIsExpense();
        int amount = yearRecords.get(month).getAmount();
        if(!isExpense) {
            sumOfIncome += amount;
        }
        return sumOfIncome;
    }
    public int sumProfit(int month) { //находим прибыль
        int sumOfProfit = findMonthIncome(month) - findMonthExpense(month);
        return sumOfProfit;
    }
    private int findAvgExp() { //Средний расход за год
        int avarageExpenses;
        int sumMonths = 0;
        int sumExpenses = 0;
        for (YearRecord yearRecord : yearRecords) {
            boolean isExpense = yearRecord.getIsExpense();
            int amount = yearRecord.getAmount();
            if (isExpense) {
                sumExpenses += amount;
                sumMonths ++;
            }
        }
        avarageExpenses = sumExpenses/sumMonths;
        return avarageExpenses;
    }
    private int findAvgInc() { //Средний доход за год
        int avarageIncome;
        int sumMonths = 0;
        int sumIncomes = 0;
        for (YearRecord yearRecord : yearRecords) {
            boolean isExpense = yearRecord.getIsExpense();
            int amount = yearRecord.getAmount();
            if (!isExpense) {
                sumIncomes += amount;
                sumMonths ++;
            }
        }
        avarageIncome = sumIncomes/sumMonths;
        return avarageIncome;
    }

}

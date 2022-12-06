import java.util.ArrayList;

public class YearReport {
    public ArrayList<YearRecord> yearRecords = new ArrayList<>();

    public YearReport(String path) {

        String content = Reader.readFileContents(path);
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
}

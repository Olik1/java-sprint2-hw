import java.util.ArrayList;

public class
MonthRecord extends ArrayList<MonthRecord> {
    String itemName; //название товара;
    boolean isExpense; //  трата (TRUE) или доход (FALSE)
    int quantity; // количество закупленного или проданного товара
    int sumOfOne; // стоимость одной единицы товара

    public MonthRecord(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean getIsExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSumOfOne() {
        return sumOfOne;
    }
}

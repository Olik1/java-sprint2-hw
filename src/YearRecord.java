public class YearRecord {
    //01,1593150,false
    int month;
    int amount;
    boolean isExpense;

    public YearRecord(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getIsExpense() {
        return isExpense;
    }
}

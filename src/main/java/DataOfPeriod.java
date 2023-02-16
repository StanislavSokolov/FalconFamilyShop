import java.util.ArrayList;

public class DataOfPeriod {
    int totalSale = 0;
    int totalOrder = 0;
    int totalSaleMoney = 0;

    ArrayList<Day> period;

    public DataOfPeriod(int totalSale, int totalOrder, int totalSaleMoney, ArrayList<Day> period) {
        this.totalSale = totalSale;
        this.totalOrder = totalOrder;
        this.totalSaleMoney = totalSaleMoney;
        this.period = period;
    }

    public int getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(int totalSale) {
        this.totalSale = totalSale;
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getTotalSaleMoney() {
        return totalSaleMoney;
    }

    public void setTotalSaleMoney(int totalSaleMoney) {
        this.totalSaleMoney = totalSaleMoney;
    }

    public ArrayList<Day> getPeriod() {
        return period;
    }

    public void setPeriod(ArrayList<Day> period) {
        this.period = period;
    }
}

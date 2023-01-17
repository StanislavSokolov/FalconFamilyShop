public class Day {
    private String date = "";
    private int sumSale = 0;
    private int sumOrder = 0;
    private int sumSaleMoney = 0;
    private String subject = "";

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSumSale() {
        return sumSale;
    }

    public void setSumSale(int sumSale) {
        this.sumSale = sumSale;
    }

    public int getSumOrder() {
        return sumOrder;
    }

    public void setSumOrder(int sumOrder) {
        this.sumOrder = sumOrder;
    }

    public int getSumSaleMoney() {
        return sumSaleMoney;
    }

    public void setSumSaleMoney(int sumSaleMoney) {
        this.sumSaleMoney = sumSaleMoney;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Day(String date, int sumSale, int sumOrder, int sumSaleMoney, String subject) {
        this.date = date;
        this.sumSale = sumSale;
        this.sumOrder = sumOrder;
        this.sumSaleMoney = sumSaleMoney;
        this.subject = subject;
    }
}

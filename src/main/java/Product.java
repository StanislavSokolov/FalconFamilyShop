public class Product {
    private String supplierArticle = "";
    private int quantity = 0;
    private int quantityFull = 0;
    private int nmId = 0;
    private String subject = "";
    private int price = 0;
    private int discount = 0;
    private int promoCode = 0;
    private int total = 0;
    private int profit = 0;
    private int costprice = 0;

    public int getCostprice() {
        return costprice;
    }

    public void setCostprice(int costprice) {
        this.costprice = costprice;
    }

    public int getShippingСost() {
        return shippingСost;
    }

    public void setShippingСost(int shippingСost) {
        this.shippingСost = shippingСost;
    }

    private int shippingСost = 0;

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public Product(String subject, String supplierArticle, int costprice, int nmId, int shippingСost, int quantity, int quantityFull, int price, int discount, int promoCode) {
        this.supplierArticle = supplierArticle;
        this.quantity = quantity;
        this.quantityFull = quantityFull;
        this.nmId = nmId;
        this.subject = subject;
        this.price = price;
        this.discount = discount;
        this.promoCode = promoCode;
        this.costprice = costprice;
        this.shippingСost = shippingСost;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Product(String supplierArticle, int quantity, int quantityFull, int nmId, String subject) {
        this.supplierArticle = supplierArticle;
        this.quantity = quantity;
        this.quantityFull = quantityFull;
        this.nmId = nmId;
        this.subject = subject;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityFull(int quantityFull) {
        this.quantityFull = quantityFull;
    }

    public void setPromoCode(int promoCode) {
        this.promoCode = promoCode;
    }

    public int getPromoCode() {
        return promoCode;
    }

    public void setSupplierArticle(String supplierArticle) {
        this.supplierArticle = supplierArticle;
    }

    public void setNmId(int nmId) {
        this.nmId = nmId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getSupplierArticle() {
        return supplierArticle;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantityFull() {
        return quantityFull;
    }

    public int getNmId() {
        return nmId;
    }

    public String getSubject() {
        return subject;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}

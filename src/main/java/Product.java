public class Product {
    private String subject = "";
    private String supplierArticle = "";
    private int costprice = 0;
    private int nmId = 0;
    private int shippingСost = 0;
    private int quantity = 0;
    private int quantityFull = 0;

    public Product() {

    }

    public void setSaintPetersburg(int saintPetersburg) {
        this.saintPetersburg = saintPetersburg;
    }

    public void setSaintPetersburg2(int saintPetersburg2) {
        this.saintPetersburg2 = saintPetersburg2;
    }

    public void setKoledino(int koledino) {
        this.koledino = koledino;
    }

    public void setElectrostal(int electrostal) {
        this.electrostal = electrostal;
    }

    public void setKazan(int kazan) {
        this.kazan = kazan;
    }

    public void setOther(int other) {
        this.other = other;
    }

    public int getSaintPetersburg() {
        return saintPetersburg;
    }

    public int getSaintPetersburg2() {
        return saintPetersburg2;
    }

    public int getKoledino() {
        return koledino;
    }

    public int getElectrostal() {
        return electrostal;
    }

    public int getKazan() {
        return kazan;
    }

    public int getOther() {
        return other;
    }

    public int getPriceShop() {
        return priceShop;
    }

    private int saintPetersburg = 0;
    private int saintPetersburg2 = 0;
    private int koledino = 0;
    private int electrostal = 0;
    private int kazan = 0;
    private int other = 0;
    private int price = 0;
    private int discount = 0;
    private int promoCode = 0;
    private int total = 0;
    private int profit = 0;
    private int priceShop = 0;

    public Product(String subject, String supplierArticle, int costprice, int nmId, int shippingСost, int quantity, int quantityFull, int saintPetersburg, int saintPetersburg2, int koledino, int electrostal, int kazan, int other, int price, int discount, int promoCode) {
        this.subject = subject;
        this.supplierArticle = supplierArticle;
        this.costprice = costprice;
        this.nmId = nmId;
        this.shippingСost = shippingСost;
        this.quantity = quantity;
        this.quantityFull = quantityFull;
        this.saintPetersburg = saintPetersburg;
        this.saintPetersburg2 = saintPetersburg2;
        this.koledino = koledino;
        this.electrostal = electrostal;
        this.kazan = kazan;
        this.other = other;
        this.price = price;
        this.discount = discount;
        this.promoCode = promoCode;
        profit = ((((this.price * (100 - this.discount) * 80)/10000) - shippingСost) * 90 / 100) - costprice;
        priceShop = (price * (100 - discount) * 90)/10000;
    }

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

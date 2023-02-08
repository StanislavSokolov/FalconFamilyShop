import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@WebServlet("/wb")
public class WBServlet extends HttpServlet {

    private final String TOKENWBSTANDART = "TOKENWBSTANDART";
    private final String TOKENWBSTATISTIC = "TOKENWBSTANDART";
    private final String TOKENWBADVERTISING = "TOKENWBSTANDART";

    ArrayList<ItemShop> productsOfTheDayPrev;
    ArrayList<ItemShop> productsOfTheWeekPrev;
    ArrayList<ItemShop> productsOfTheMonthPrev;
    ArrayList<Product> stockPrev;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        boolean authorizationStatus = false;
        String quantity = "0";
        Cookie[] cookies = httpServletRequest.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("quantity")) {
                authorizationStatus = true;
                quantity = cookie.getValue();
            }
        }

        if (!authorizationStatus) {
            Cookie cookie1 = new Cookie("quantity", quantity);
            cookie1.setMaxAge(60*60*24*90);
            httpServletResponse.addCookie(cookie1);
        }

        httpServletRequest.setAttribute("quantity", quantity);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String category = httpServletRequest.getParameter("category");
        String value = httpServletRequest.getParameter("value");

        if ((category != null) & (value != null)) {
            ItemShop productPopular = null;
            if (!productsOfTheDayPrev.isEmpty()) {
                productsOfTheDayPrev.sort((o1, o2) -> o2.getRating() - o1.getRating());
                productPopular = productsOfTheDayPrev.get(0);
            }
            if (category.equals("statday")) {
                if (value.equals("name")) productsOfTheDayPrev.sort((o1, o2) -> o1.getSubject().compareTo(o2.getSubject()));
                if (value.equals("order")) productsOfTheDayPrev.sort((o1, o2) -> o2.getOrder() - o1.getOrder());
                if (value.equals("sale")) productsOfTheDayPrev.sort((o1, o2) -> o2.getSale() - o1.getSale());
                if (value.equals("forpay")) productsOfTheDayPrev.sort((o1, o2) -> (int) (Double.parseDouble(o2.getForPay()) - Double.parseDouble(o1.getForPay())));
            }
            if (category.equals("statweek")) {
                if (value.equals("name")) productsOfTheWeekPrev.sort((o1, o2) -> o1.getSubject().compareTo(o2.getSubject()));
                if (value.equals("order")) productsOfTheWeekPrev.sort((o1, o2) -> o2.getOrder() - o1.getOrder());
                if (value.equals("sale")) productsOfTheWeekPrev.sort((o1, o2) -> o2.getSale() - o1.getSale());
                if (value.equals("forpay")) productsOfTheWeekPrev.sort((o1, o2) -> (int) (Double.parseDouble(o2.getForPay()) - Double.parseDouble(o1.getForPay())));
            }
            if (category.equals("statmonth")) {
                if (value.equals("name")) productsOfTheMonthPrev.sort((o1, o2) -> o1.getSubject().compareTo(o2.getSubject()));
                if (value.equals("order")) productsOfTheMonthPrev.sort((o1, o2) -> o2.getOrder() - o1.getOrder());
                if (value.equals("sale")) productsOfTheMonthPrev.sort((o1, o2) -> o2.getSale() - o1.getSale());
                if (value.equals("forpay")) productsOfTheMonthPrev.sort((o1, o2) -> (int) (Double.parseDouble(o2.getForPay()) - Double.parseDouble(o1.getForPay())));
            }
            if (category.equals("stock")) {
                if (value.equals("name")) stockPrev.sort((o1, o2) -> o1.getSubject().compareTo(o2.getSubject()));
                if (value.equals("remain")) stockPrev.sort((o1, o2) -> o2.getQuantity() - o1.getQuantity());
                if (value.equals("ontheway")) stockPrev.sort((o1, o2) -> (o2.getQuantityFull() - o2.getQuantity()) - (o1.getQuantityFull() - o1.getQuantity()));
                if (value.equals("profit")) stockPrev.sort((o1, o2) -> o2.getTotal() - o1.getTotal());
                if (value.equals("price")) stockPrev.sort((o1, o2) -> o2.getPrice() - o1.getPrice());
                if (value.equals("discount")) stockPrev.sort((o1, o2) -> o2.getDiscount() - o1.getDiscount());
            }
            int total = 0;

            for (Product productCurrent : stockPrev) {
                int money = productCurrent.getQuantity() * productCurrent.getPrice() * (100 - productCurrent.getDiscount()) * 68 / 10000;
                productCurrent.setTotal(money);
                total = total + money;
            }

            int sumSale = 0;
            int sumOrder = 0;
            int sumSaleMoney = 0;

            for (ItemShop ishop : productsOfTheDayPrev) {
                sumSale = sumSale + ishop.getSale();
                sumOrder = sumOrder + ishop.getOrder();
                String forPay = ishop.getForPay();
                sumSaleMoney = (int) (sumSaleMoney + Float.parseFloat(forPay));
            }

            httpServletRequest.setAttribute("productPopular", productPopular);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheDay", productsOfTheDayPrev);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheWeek", productsOfTheWeekPrev);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheMonth", productsOfTheMonthPrev);
            httpServletRequest.setAttribute("sumOrder", sumOrder);
            httpServletRequest.setAttribute("sumSale", sumSale);
            httpServletRequest.setAttribute("sumSaleMoney", sumSaleMoney);

            httpServletRequest.setAttribute("stock", stockPrev);
            httpServletRequest.setAttribute("total", total);

            httpServletRequest.setAttribute("shop1", "wb");
            httpServletRequest.setAttribute("shop2", "ozon");
            httpServletRequest.setAttribute("title1", "WB");
            httpServletRequest.setAttribute("title2", "OZON");
        } else {


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product> stock = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            URL generetedURL;
            String response = null;

            ArrayList<Product1> arrayListOfOrderedProductsDay = SQL.upDate1("wborders", 0);
            ArrayList<Product1> arrayListOfSoldProductsDay = SQL.upDate1("wbsales", 0);

            ArrayList<ItemShop> productsOfTheDay = toCreateListOfSoldProducts(arrayListOfOrderedProductsDay, arrayListOfSoldProductsDay);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product1> arrayListOfOrderedProductsWeek = new ArrayList<>();
            ArrayList<Product1> arrayListOfSoldProductsWeek = new ArrayList<>();

            for (int i = 0; i > -7; i--) {
                ArrayList<Product1> arrayListOfOrderedProductsCurrent = SQL.upDate1("wborders", i);
                for (Product1 p1: arrayListOfOrderedProductsCurrent) arrayListOfOrderedProductsWeek.add(p1);
                ArrayList<Product1> arrayListOfSoldProductsCurrent = SQL.upDate1("wbsales", i);
                for (Product1 p1: arrayListOfSoldProductsCurrent) arrayListOfSoldProductsWeek.add(p1);
            }

            ArrayList<ItemShop> productsOfTheWeek = toCreateListOfSoldProducts(arrayListOfOrderedProductsWeek, arrayListOfSoldProductsWeek);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product1> arrayListOfOrderedProductsMonth = new ArrayList<>();
            ArrayList<Product1> arrayListOfSoldProductsMonth = new ArrayList<>();

            for (int i = 0; i > -31; i--) {
                ArrayList<Product1> arrayListOfOrderedProductsCurrent = SQL.upDate1("wborders", i);
                for (Product1 p1: arrayListOfOrderedProductsCurrent) arrayListOfOrderedProductsMonth.add(p1);
                ArrayList<Product1> arrayListOfSoldProductsCurrent = SQL.upDate1("wbsales", i);
                for (Product1 p1: arrayListOfSoldProductsCurrent) arrayListOfSoldProductsMonth.add(p1);
            }

            ArrayList<ItemShop> productsOfTheMonth = toCreateListOfSoldProducts(arrayListOfOrderedProductsMonth, arrayListOfSoldProductsMonth);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            generetedURL = URLRequestResponse.generateURL(2, 5, TOKENWBSTATISTIC);
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTATISTIC);
                System.out.println(response);
                if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
                    JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
                    for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
                        boolean coincidence = false;
                        if (stock.isEmpty()) {
                            stock.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
                                    parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                    jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString()));
                        } else {
                            for (Product productCurrent : stock) {
                                if (productCurrent.getNmId() == parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
                                    productCurrent.setQuantity(productCurrent.getQuantity() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()));
                                    productCurrent.setQuantityFull(productCurrent.getQuantityFull() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()));
                                    coincidence = true;
                                }
                            }
                            if (!coincidence) {
                                stock.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
                                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
                                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                                        jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString()));
                            }
                            coincidence = false;
                        }
                    }
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ItemShop productPopular = null;
            if (!productsOfTheDay.isEmpty()) {
                productsOfTheDay.sort((o1, o2) -> o2.getRating() - o1.getRating());
                productPopular = productsOfTheDay.get(0);
            }

            int sumSale = 0;
            int sumOrder = 0;
            int sumSaleMoney = 0;

            if (!productsOfTheDay.isEmpty()) {
                for (ItemShop ishop : productsOfTheDay) {
                    sumSale = sumSale + ishop.getSale();
                    sumOrder = sumOrder + ishop.getOrder();
                    String forPay = ishop.getForPay();
                    sumSaleMoney = (int) (sumSaleMoney + Float.parseFloat(forPay));
                }
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            generetedURL = URLRequestResponse.generateURL(2, 1, TOKENWBSTANDART);
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENWBSTANDART);
                if (!response.equals("{\"errors\":[\"(api-new) too many requests\"]}")) {
                    System.out.println(response);
                    JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
                    for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
                        for (Product productCurrent : stock) {
                            if (productCurrent.getNmId() == parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
                                productCurrent.setPrice(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("price").toString()));
                                productCurrent.setDiscount(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("discount").toString()));
                                productCurrent.setPromoCode(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("promoCode").toString()));
                            }
                        }
                    }
                }
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            int total = 0;

            for (Product productCurrent : stock) {
                int money = productCurrent.getQuantity() * productCurrent.getPrice() * (100 - productCurrent.getDiscount()) * 68 / 10000;
                productCurrent.setTotal(money);
                total = total + money;
            }

            stock.sort((o1, o2) -> o2.getTotal() - o1.getTotal());


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            productsOfTheDayPrev = new ArrayList<>();
            for (ItemShop itemShop : productsOfTheDay) {
                productsOfTheDayPrev.add(itemShop);
            }
            productsOfTheWeekPrev = new ArrayList<>();
            for (ItemShop itemShop : productsOfTheWeek) {
                productsOfTheWeekPrev.add(itemShop);
            }
            productsOfTheMonthPrev = new ArrayList<>();
            for (ItemShop itemShop : productsOfTheMonth) {
                productsOfTheMonthPrev.add(itemShop);
            }
            stockPrev = new ArrayList<>();
            for (Product product : stock) {
                stockPrev.add(product);
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            httpServletRequest.setAttribute("productPopular", productPopular);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheDay", productsOfTheDay);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheWeek", productsOfTheWeek);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheMonth", productsOfTheMonth);
            httpServletRequest.setAttribute("sumOrder", sumOrder);
            httpServletRequest.setAttribute("sumSale", sumSale);
            httpServletRequest.setAttribute("sumSaleMoney", sumSaleMoney);

            httpServletRequest.setAttribute("stock", stock);
            httpServletRequest.setAttribute("total", total);

            httpServletRequest.setAttribute("shop1", "wb");
            httpServletRequest.setAttribute("shop2", "ozon");
            httpServletRequest.setAttribute("title1", "WB");
            httpServletRequest.setAttribute("title2", "OZON");

        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.getRequestDispatcher("data.jsp").forward(httpServletRequest, httpServletResponse);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private ArrayList<ItemShop> toCreateListOfSoldProducts(ArrayList<Product1> arrayListOfOrderedProducts, ArrayList<Product1> arrayListOfSoldProducts) {
        ArrayList<ItemShop> products = new ArrayList<>();
        for (int i = 0; i < arrayListOfSoldProducts.size(); i++) {
            boolean coincidence = false;
            if (products.isEmpty()) {
                products.add(new ItemShop(arrayListOfSoldProducts.get(i).getCsubject(), arrayListOfSoldProducts.get(i).getSupplierArticle(), String.valueOf(arrayListOfSoldProducts.get(i).getFinishedPrice()), String.valueOf(arrayListOfSoldProducts.get(i).getForPay()), "", arrayListOfSoldProducts.get(i).getOblastOkrugName(), arrayListOfSoldProducts.get(i).getCdate()));
            } else {
                for (ItemShop itemShopCurrent : products) {
                    if (itemShopCurrent.getSupplierArticle().equals(arrayListOfSoldProducts.get(i).getSupplierArticle())) {
                        itemShopCurrent.setSale(itemShopCurrent.getSale() + 1);
                        itemShopCurrent.setRating(itemShopCurrent.getRating() + 1);
                        itemShopCurrent.setForPay(String.valueOf((int) (Float.parseFloat(itemShopCurrent.getForPay()) + arrayListOfSoldProducts.get(i).getForPay()*0.9)));
                        coincidence = true;
                    }
                }
                if (!coincidence) {
                    products.add(new ItemShop(arrayListOfSoldProducts.get(i).getCsubject(), arrayListOfSoldProducts.get(i).getSupplierArticle(), String.valueOf(arrayListOfSoldProducts.get(i).getFinishedPrice()), String.valueOf(arrayListOfSoldProducts.get(i).getForPay()), "", arrayListOfSoldProducts.get(i).getOblastOkrugName(), arrayListOfSoldProducts.get(i).getCdate()));
                }
            }

        }

        for (int i = 0; i < arrayListOfOrderedProducts.size(); i++) {
            boolean coincidence = false;
            if (products.isEmpty()) {
                products.add(new ItemShop(arrayListOfOrderedProducts.get(i).getCsubject(), arrayListOfOrderedProducts.get(i).getSupplierArticle(), String.valueOf(arrayListOfOrderedProducts.get(i).getFinishedPrice()),  "", arrayListOfOrderedProducts.get(i).getOblastOkrugName(), arrayListOfOrderedProducts.get(i).getCdate()));
            } else {
                for (ItemShop itemShopCurrent : products) {
                    if (itemShopCurrent.getSupplierArticle().equals(arrayListOfOrderedProducts.get(i).getSupplierArticle())) {
                        itemShopCurrent.setOrder(itemShopCurrent.getOrder() + 1);
                        itemShopCurrent.setRating(itemShopCurrent.getRating() + 1);
                        coincidence = true;
                    }
                }
                if (!coincidence) {
                    products.add(new ItemShop(arrayListOfOrderedProducts.get(i).getCsubject(), arrayListOfOrderedProducts.get(i).getSupplierArticle(), String.valueOf(arrayListOfOrderedProducts.get(i).getFinishedPrice()),  "", arrayListOfOrderedProducts.get(i).getOblastOkrugName(), arrayListOfOrderedProducts.get(i).getCdate()));
                }
            }

        }
        return products;
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}

import org.json.JSONObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@WebServlet("/wb")
public class WBServlet extends HttpServlet {

    private final String TOKEN1 = "token";
    private final String TOKEN2 = "token";

    ArrayList<ItemShop> productsPrev;
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
            if (!productsPrev.isEmpty()) {
                productsPrev.sort((o1, o2) -> o2.getRating() - o1.getRating());
                productPopular = productsPrev.get(0);
            }
            if (category.equals("stat")) {
                if (value.equals("name")) productsPrev.sort((o1, o2) -> o1.getSubject().compareTo(o2.getSubject()));
                if (value.equals("order")) productsPrev.sort((o1, o2) -> o2.getOrder() - o1.getOrder());
                if (value.equals("sale")) productsPrev.sort((o1, o2) -> o2.getSale() - o1.getSale());
                if (value.equals("forpay")) productsPrev.sort((o1, o2) -> (int) (Double.parseDouble(o2.getForPay()) - Double.parseDouble(o1.getForPay())));
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

            for (ItemShop ishop : productsPrev) {
                sumSale = sumSale + ishop.getSale();
                sumOrder = sumOrder + ishop.getOrder();
                String forPay = ishop.getForPay();
                sumSaleMoney = (int) (sumSaleMoney + Float.parseFloat(forPay));
            }

            httpServletRequest.setAttribute("productPopular", productPopular);
            httpServletRequest.setAttribute("arrayList", productsPrev);
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

            ArrayList<ItemShop> products = new ArrayList<>();
            ArrayList<Product> stock = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            URL generetedURL;
            String response = null;

            ArrayList<Product1> product1ArrayList1 = SQL.upDate1("wborders", 0);
            ArrayList<Product1> product1ArrayList = SQL.upDate1("wbsales", 0);

            for (int i = 0; i < product1ArrayList.size(); i++) {
                boolean coincidence = false;
                if (products.isEmpty()) {
                    products.add(new ItemShop(product1ArrayList.get(i).getCsubject(), product1ArrayList.get(i).getSupplierArticle(), String.valueOf(product1ArrayList.get(i).getFinishedPrice()), String.valueOf(product1ArrayList.get(i).getForPay()), "", product1ArrayList.get(i).getOblastOkrugName(), product1ArrayList.get(i).getCdate()));
                } else {
                    for (ItemShop itemShopCurrent : products) {
                        if (itemShopCurrent.getSupplierArticle().equals(product1ArrayList.get(i).getSupplierArticle())) {
                            itemShopCurrent.setSale(itemShopCurrent.getSale() + 1);
                            itemShopCurrent.setRating(itemShopCurrent.getRating() + 1);
                            itemShopCurrent.setForPay(String.valueOf((int) (Float.parseFloat(itemShopCurrent.getForPay()) + product1ArrayList.get(i).getForPay()*0.9)));
                            coincidence = true;
                        }
                    }
                    if (!coincidence) {
                        products.add(new ItemShop(product1ArrayList.get(i).getCsubject(), product1ArrayList.get(i).getSupplierArticle(), String.valueOf(product1ArrayList.get(i).getFinishedPrice()), String.valueOf(product1ArrayList.get(i).getForPay()), "", product1ArrayList.get(i).getOblastOkrugName(), product1ArrayList.get(i).getCdate()));
                    }
                }

            }

            for (int i = 0; i < product1ArrayList1.size(); i++) {
                boolean coincidence = false;
                if (products.isEmpty()) {
                    products.add(new ItemShop(product1ArrayList1.get(i).getCsubject(), product1ArrayList1.get(i).getSupplierArticle(), String.valueOf(product1ArrayList1.get(i).getFinishedPrice()),  "", product1ArrayList1.get(i).getOblastOkrugName(), product1ArrayList1.get(i).getCdate()));
                } else {
                    for (ItemShop itemShopCurrent : products) {
                        if (itemShopCurrent.getSupplierArticle().equals(product1ArrayList1.get(i).getSupplierArticle())) {
                            itemShopCurrent.setOrder(itemShopCurrent.getOrder() + 1);
                            itemShopCurrent.setRating(itemShopCurrent.getRating() + 1);
                            coincidence = true;
                        }
                    }
                    if (!coincidence) {
                        products.add(new ItemShop(product1ArrayList1.get(i).getCsubject(), product1ArrayList1.get(i).getSupplierArticle(), String.valueOf(product1ArrayList1.get(i).getFinishedPrice()),  "", product1ArrayList1.get(i).getOblastOkrugName(), product1ArrayList1.get(i).getCdate()));
                    }
                }

            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            generetedURL = URLRequestResponse.generateURL(2, 5, TOKEN1);
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL);
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
            if (!products.isEmpty()) {
                products.sort((o1, o2) -> o2.getRating() - o1.getRating());
                productPopular = products.get(0);
            }

            int sumSale = 0;
            int sumOrder = 0;
            int sumSaleMoney = 0;

            if (!products.isEmpty()) {
                for (ItemShop ishop : products) {
                    sumSale = sumSale + ishop.getSale();
                    sumOrder = sumOrder + ishop.getOrder();
                    String forPay = ishop.getForPay();
                    sumSaleMoney = (int) (sumSaleMoney + Float.parseFloat(forPay));
                }
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            generetedURL = URLRequestResponse.generateURL(2, 1, TOKEN2);
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKEN2);
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

            productsPrev = new ArrayList<>();
            for (ItemShop itemShop : products) {
                productsPrev.add(itemShop);
            }
            stockPrev = new ArrayList<>();
            for (Product product : stock) {
                stockPrev.add(product);
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            httpServletRequest.setAttribute("productPopular", productPopular);
            httpServletRequest.setAttribute("arrayList", products);
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

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}

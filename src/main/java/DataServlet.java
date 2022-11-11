import org.json.JSONObject;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/data")
public class DataServlet extends HttpServlet {
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

        ArrayList<ItemShop> products = new ArrayList<>();
        ArrayList<Product> stock = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        URL generetedURL;
        String response = null;

        generetedURL = URLRequestResponse.generateURL(2, 6, "token");
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, "token");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = new JSONObject("{\"price\":" + response + "}");
        System.out.println(jsonObject.toString());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
            boolean coincidence = false;
            if (products.isEmpty()){
                products.add(new ItemShop(jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("totalPrice").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("forPay").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("regionName").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString()));
            } else {
                for (ItemShop itemShopCurrent : products) {
                    if (itemShopCurrent.getSupplierArticle().equals(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString())) {
                        itemShopCurrent.setSale(itemShopCurrent.getSale() + 1);
                        itemShopCurrent.setForPay(String.valueOf(Float.parseFloat(itemShopCurrent.getForPay()) + Float.parseFloat(jsonObject.getJSONArray("price").getJSONObject(i).get("forPay").toString())));
                        coincidence = true;
                    }
                }
                if (!coincidence) {
                    products.add(new ItemShop(jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("totalPrice").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("forPay").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("regionName").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString()));
                }
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        generetedURL = URLRequestResponse.generateURL(2, 7, "token");
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, "token");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        jsonObject = new JSONObject("{\"price\":" + response + "}");
        System.out.println(jsonObject.toString());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
            boolean coincidence = false;
            if (products.isEmpty()){
                products.add(new ItemShop(jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("totalPrice").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("oblast").toString(),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString()));
            } else {
                for (ItemShop itemShopCurrent : products) {
                    if (itemShopCurrent.getSupplierArticle().equals(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString())) {
                        itemShopCurrent.setOrder(itemShopCurrent.getOrder() + 1);
                        coincidence = true;
                    }
                }
                if (!coincidence) {
                    products.add(new ItemShop(jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("totalPrice").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("warehouseName").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("oblast").toString(),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("date").toString()));
                }
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        generetedURL = URLRequestResponse.generateURL(2, 5, "token");
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        jsonObject = new JSONObject("{\"price\":" + response + "}");
        for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
            boolean coincidence = false;
            if (stock.isEmpty()){
                stock.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("inWayToClient").toString()),
                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("inWayFromClient").toString()),
                        parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                        jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString()));
            } else {
                for (Product productCurrent : stock) {
                    if (productCurrent.getNmId() == parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
                        productCurrent.setQuantity(productCurrent.getQuantity() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()));
                        productCurrent.setQuantityFull(productCurrent.getQuantityFull() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()));
                        productCurrent.setInWayToClient(productCurrent.getInWayToClient() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("inWayToClient").toString()));
                        productCurrent.setInWayFromClient(productCurrent.getInWayFromClient() + parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("inWayFromClient").toString()));
                        coincidence = true;
                    }
                }
                if (!coincidence) {
                    stock.add(new Product(jsonObject.getJSONArray("price").getJSONObject(i).get("supplierArticle").toString(),
                            parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantity").toString()),
                            parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("quantityFull").toString()),
                            parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("inWayToClient").toString()),
                            parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("inWayFromClient").toString()),
                            parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString()),
                            jsonObject.getJSONArray("price").getJSONObject(i).get("subject").toString()));
                }
                coincidence = false;
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        products.sort((o1, o2) -> o2.getOrder() - o1.getOrder());
        ItemShop productPopular = products.get(0);
        int sumSale = 0;
        int sumOrder = 0;
        int sumSaleMoney = 0;

        for (ItemShop ishop: products) {
            sumSale = sumSale + ishop.getSale();
            sumOrder = sumOrder + ishop.getOrder();
            String forPay = ishop.getForPay();
            System.out.println(forPay);
            sumSaleMoney = (int) (sumSaleMoney + Float.parseFloat(forPay));
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        generetedURL = URLRequestResponse.generateURL(2, 1, "token");
        try {
            response = URLRequestResponse.getResponseFromURL(generetedURL, "token");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        jsonObject = new JSONObject("{\"price\":" + response + "}");
        for (int i = 0; i < jsonObject.getJSONArray("price").length(); i++) {
            for (Product productCurrent : stock) {
                if (productCurrent.getNmId() == parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("nmId").toString())) {
                    productCurrent.setPrice(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("price").toString()));
                    productCurrent.setDiscount(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("discount").toString()));
                    productCurrent.setPromoCode(parseInt(jsonObject.getJSONArray("price").getJSONObject(i).get("promoCode").toString()));
                }
            }
        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int total = 0;

        for (Product productCurrent: stock) {
            int money = productCurrent.getQuantity()*productCurrent.getPrice()*(100 - productCurrent.getDiscount())*68/10000;
            productCurrent.setTotal(money);
            total = total + money;
        }

        stock.sort((o1, o2) -> o2.getTotal() - o1.getTotal());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.setAttribute("productPopular", productPopular);
        httpServletRequest.setAttribute("arrayList", products);
        httpServletRequest.setAttribute("sumOrder", sumOrder);
        httpServletRequest.setAttribute("sumSale", sumSale);
        httpServletRequest.setAttribute("sumSaleMoney", sumSaleMoney);

        httpServletRequest.setAttribute("stock", stock);
        httpServletRequest.setAttribute("total", total);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.getRequestDispatcher("data.jsp").forward(httpServletRequest, httpServletResponse);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

@WebServlet("/stock")
public class StockServlet extends HttpServlet {

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

        String shop = httpServletRequest.getParameter("shop");
        String value = httpServletRequest.getParameter("value");

        ArrayList<Product> stock = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        int total = 0;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if (value != null) {
            if (value.equals("name")) stockPrev.sort((o1, o2) -> o1.getSubject().compareTo(o2.getSubject()));
            if (value.equals("remain")) stockPrev.sort((o1, o2) -> o2.getQuantity() - o1.getQuantity());
            if (value.equals("ontheway")) stockPrev.sort((o1, o2) -> (o2.getQuantityFull() - o2.getQuantity()) - (o1.getQuantityFull() - o1.getQuantity()));
            if (value.equals("profit")) stockPrev.sort((o1, o2) -> o2.getTotal() - o1.getTotal());
            if (value.equals("cleanprofit")) stockPrev.sort((o1, o2) -> o2.getProfit() - o1.getProfit());

            if (shop != null) {
                if (shop.equals("wb")) {
                    for (Product productCurrent : stockPrev) {
                        int money = ((productCurrent.getQuantity() * productCurrent.getPrice() * (100 - productCurrent.getDiscount()) * 80 / 10000) - (productCurrent.getQuantity() * productCurrent.getShippingСost())) * 90 / 100 ;
                        productCurrent.setTotal(money);
                        total = total + money;

                        if (money != 0) productCurrent.setProfit(money - productCurrent.getCostprice() * productCurrent.getQuantity());
                    }
                    httpServletRequest.setAttribute("shop1", "wb");
                    httpServletRequest.setAttribute("shop2", "ozon");
                    httpServletRequest.setAttribute("title1", "WB");
                    httpServletRequest.setAttribute("title2", "OZON");
                }
                if (shop.equals("ozon")) {
                    for (Product productCurrent : stockPrev) {
                        int money = productCurrent.getQuantity() * productCurrent.getPrice() * 80 / 100;
                        productCurrent.setTotal(money);
                        total = total + money;
                    }
                    httpServletRequest.setAttribute("shop1", "ozon");
                    httpServletRequest.setAttribute("shop2", "wb");
                    httpServletRequest.setAttribute("title1", "OZON");
                    httpServletRequest.setAttribute("title2", "WB");
                }
            }

            httpServletRequest.setAttribute("stock", stockPrev);
            httpServletRequest.setAttribute("total", total);

        } else {
            if (shop != null) {
                if (shop.equals("wb")) {

                    httpServletRequest.setAttribute("shop1", "wb");
                    httpServletRequest.setAttribute("shop2", "ozon");
                    httpServletRequest.setAttribute("title1", "WB");
                    httpServletRequest.setAttribute("title2", "OZON");

                    stock = SQL.getData("wb");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    for (Product productCurrent : stock) {
                        int money = ((productCurrent.getQuantity() * productCurrent.getPrice() * (100 - productCurrent.getDiscount()) * 80 / 10000) - (productCurrent.getQuantity() * productCurrent.getShippingСost())) * 90 / 100 ;
                        productCurrent.setTotal(money);
                        total = total + money;

                        if (money != 0) productCurrent.setProfit(money - productCurrent.getCostprice() * productCurrent.getQuantity());
                    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                }
                if (shop.equals("ozon")) {
                    httpServletRequest.setAttribute("shop1", "ozon");
                    httpServletRequest.setAttribute("shop2", "wb");
                    httpServletRequest.setAttribute("title1", "OZON");
                    httpServletRequest.setAttribute("title2", "WB");

//                    generetedURL = URLRequestResponse.generateURL(3, 1, "0");
//                    try {
//                        response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENOZON1, TOKENOZON2, 1, "0", "0");
//                        JSONObject jsonObject = new JSONObject(response);
//                        JSONObject jsonObject1 = new JSONObject(String.valueOf(jsonObject.get("result")));
//                        generetedURL = URLRequestResponse.generateURL(3, 2, "0");
//                        for (int i = 0; i < jsonObject1.getJSONArray("items").length(); i++) {
//                            try {
//                                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKENOZON1, TOKENOZON2, 2, jsonObject1.getJSONArray("items").getJSONObject(i).get("product_id").toString(), "0");
//                            } catch (IOException | URISyntaxException e) {
//                                e.printStackTrace();
//                            }
//                            JSONObject jsonObject2 = new JSONObject(response);
//                            JSONObject jsonObject3 = new JSONObject(String.valueOf(jsonObject2.get("result")));
//                            JSONObject jsonObject4 = new JSONObject(String.valueOf(jsonObject3.get("stocks")));
//                            Product product = new Product(jsonObject3.get("id").toString(), parseInt(jsonObject4.get("present").toString()), parseInt(jsonObject4.get("present").toString()) + parseInt(jsonObject4.get("reserved").toString()), parseInt(jsonObject3.get("id").toString()), jsonObject3.get("name").toString());
//                            product.setPrice(((int) Float.parseFloat(jsonObject3.get("price").toString())));
//                            product.setDiscount((int) (100 - 100 * (Float.parseFloat(jsonObject3.get("price").toString())/Float.parseFloat(jsonObject3.get("old_price").toString()))));
//                            stock.add(product);
//                        }
//                    } catch (IOException | URISyntaxException e) {
//                        e.printStackTrace();
//                    }

//                    for (Product productCurrent : stock) {
//                        int money = productCurrent.getQuantity() * productCurrent.getPrice() * 80 / 100;
//                        productCurrent.setTotal(money);
//                        total = total + money;
//                    }
                }
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            stock.sort((o1, o2) -> o2.getTotal() - o1.getTotal());

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


            stockPrev = new ArrayList<>();
            for (Product product : stock) {
                stockPrev.add(product);
            }

            httpServletRequest.setAttribute("stock", stock);
            httpServletRequest.setAttribute("total", total);
        }

        httpServletRequest.getRequestDispatcher("stock.jsp").forward(httpServletRequest, httpServletResponse);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}


import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet("/ozon")
public class OzonServlet extends HttpServlet {

    private final String TOKEN2 = "TOKEN2";
    private final String TOKEN1 = "TOKEN2";

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
                int money = productCurrent.getQuantity() * productCurrent.getPrice() * 80 / 100;
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

            httpServletRequest.setAttribute("shop1", "ozon");
            httpServletRequest.setAttribute("shop2", "wb");
            httpServletRequest.setAttribute("title1", "OZON");
            httpServletRequest.setAttribute("title2", "WB");
        } else {


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product> stock = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            URL generetedURL;
            String response = null;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            generetedURL = URLRequestResponse.generateURL(3, 1, "0");
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKEN1, TOKEN2, 1, "0", "0");
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonObject1 = new JSONObject(String.valueOf(jsonObject.get("result")));
            generetedURL = URLRequestResponse.generateURL(3, 2, "0");
            for (int i = 0; i < jsonObject1.getJSONArray("items").length(); i++) {
                try {
                    response = URLRequestResponse.getResponseFromURL(generetedURL, TOKEN1, TOKEN2, 2, jsonObject1.getJSONArray("items").getJSONObject(i).get("product_id").toString(), "0");
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
                JSONObject jsonObject2 = new JSONObject(response);
                JSONObject jsonObject3 = new JSONObject(String.valueOf(jsonObject2.get("result")));
                JSONObject jsonObject4 = new JSONObject(String.valueOf(jsonObject3.get("stocks")));
                Product product = new Product(jsonObject3.get("id").toString(), parseInt(jsonObject4.get("present").toString()), parseInt(jsonObject4.get("present").toString()) + parseInt(jsonObject4.get("reserved").toString()), parseInt(jsonObject3.get("id").toString()), jsonObject3.get("name").toString());
                product.setPrice(((int) Float.parseFloat(jsonObject3.get("price").toString())));
                product.setDiscount((int) (100 - 100 * (Float.parseFloat(jsonObject3.get("price").toString())/Float.parseFloat(jsonObject3.get("old_price").toString()))));
                stock.add(product);
            }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<ItemShop> productsOfTheDay = new ArrayList<>();
//            productsOfTheDay = SQL.upDate1("ozon", 0);

//            System.out.println(answerString);
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



            int total = 0;

            for (Product productCurrent : stock) {
                int money = productCurrent.getQuantity() * productCurrent.getPrice() * 80 / 100;
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
//            for (ItemShop itemShop : productsOfTheWeek) {
//                productsOfTheWeekPrev.add(itemShop);
//            }
            productsOfTheMonthPrev = new ArrayList<>();
//            for (ItemShop itemShop : productsOfTheMonth) {
//                productsOfTheMonthPrev.add(itemShop);
//            }
            stockPrev = new ArrayList<>();
            for (Product product : stock) {
                stockPrev.add(product);
            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            httpServletRequest.setAttribute("productPopular", productPopular);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheDay", productsOfTheDay);
//            httpServletRequest.setAttribute("arrayListSoldProductsOfTheWeek", productsOfTheWeek);
//            httpServletRequest.setAttribute("arrayListSoldProductsOfTheMonth", productsOfTheMonth);
            httpServletRequest.setAttribute("sumOrder", sumOrder);
            httpServletRequest.setAttribute("sumSale", sumSale);
            httpServletRequest.setAttribute("sumSaleMoney", sumSaleMoney);

            httpServletRequest.setAttribute("stock", stock);
            httpServletRequest.setAttribute("total", total);

            httpServletRequest.setAttribute("shop1", "ozon");
            httpServletRequest.setAttribute("shop2", "wb");
            httpServletRequest.setAttribute("title1", "OZON");
            httpServletRequest.setAttribute("title2", "WB");

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

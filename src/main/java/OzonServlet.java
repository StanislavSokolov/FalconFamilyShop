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

    private final String TOKEN1 = "";
    private final String TOKEN2 = "";

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
                int money = productCurrent.getQuantity() * productCurrent.getPrice() * 80 / 100;
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

            httpServletRequest.setAttribute("shop1", "ozon");
            httpServletRequest.setAttribute("shop2", "wb");
            httpServletRequest.setAttribute("title1", "OZON");
            httpServletRequest.setAttribute("title2", "WB");
        } else {


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<ItemShop> products = new ArrayList<>();
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
                Product product = new Product(jsonObject3.get("id").toString(), Integer.parseInt(jsonObject4.get("present").toString()), Integer.parseInt(jsonObject4.get("present").toString()) + Integer.parseInt(jsonObject4.get("reserved").toString()), Integer.parseInt(jsonObject3.get("id").toString()), jsonObject3.get("name").toString());
                product.setPrice(((int) Float.parseFloat(jsonObject3.get("price").toString())));
                product.setDiscount((int) (100 - 100 * (Float.parseFloat(jsonObject3.get("price").toString())/Float.parseFloat(jsonObject3.get("old_price").toString()))));
                stock.add(product);
            }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            generetedURL = URLRequestResponse.generateURL(3, 3, "0");
            try {
                response = URLRequestResponse.getResponseFromURL(generetedURL, TOKEN1, TOKEN2, 3, "0", "0");
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
            System.out.println(response);

            String answerString = "";

            JSONObject jsonObject5 = new JSONObject(response);
            for (int i = 0; i < jsonObject5.getJSONArray("result").length(); i++) {
                JSONArray jsonArray = (JSONArray) jsonObject5.getJSONArray("result").getJSONObject(i).get("products");
                JSONObject jsonObject6 = new JSONObject(String.valueOf(jsonObject5.getJSONArray("result").getJSONObject(i).get("analytics_data")));
                ItemShop itemShop = new ItemShop(jsonArray.getJSONObject(0).get("name").toString(),
                        "non",
                        jsonArray.getJSONObject(0).get("price").toString(),
                        jsonObject6.getString("warehouse_name"),
                        jsonObject6.getString("region") + " (" + jsonObject6.getString("city") + ")",
                        jsonObject5.getJSONArray("result").getJSONObject(i).get("created_at").toString());
                answerString = answerString
                        + "\n"
                        + "Наименование: "
                        + jsonArray.getJSONObject(0).get("name").toString()
                        + "\n"
                        + "Артикул: "
                        + jsonArray.getJSONObject(0).get("offer_id").toString()
                        + "\n"
                        + "Количество: "
                        + jsonArray.getJSONObject(0).get("quantity").toString()
                        + "\n"
                        + "Цена: "
                        + jsonArray.getJSONObject(0).get("price").toString()
                        + "\n"
                        + "Склад отгрузки: "
                        + jsonObject6.getString("warehouse_name")
                        + "\n"
                        + "Регион доставки: "
                        + jsonObject6.getString("region") + " (" + jsonObject6.getString("city") + ")"
                        + "\n"
                        + "Дата: "
                        + jsonObject5.getJSONArray("result").getJSONObject(i).get("created_at").toString()
                        + "\n";

                products.add(itemShop);
            }

            System.out.println(answerString);
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



            int total = 0;

            for (Product productCurrent : stock) {
                int money = productCurrent.getQuantity() * productCurrent.getPrice() * 80 / 100;
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

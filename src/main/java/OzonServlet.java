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

    ArrayList<ItemShop> productsOfTheDayPrev;
    ArrayList<ItemShop> productsOfTheWeekPrev;
    ArrayList<ItemShop> productsOfTheMonthPrev;

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

            httpServletRequest.setAttribute("shop1", "ozon");
            httpServletRequest.setAttribute("shop2", "wb");
            httpServletRequest.setAttribute("title1", "OZON");
            httpServletRequest.setAttribute("title2", "WB");
        } else {


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product> stock = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            httpServletRequest.setAttribute("productPopular", productPopular);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheDay", productsOfTheDay);
//            httpServletRequest.setAttribute("arrayListSoldProductsOfTheWeek", productsOfTheWeek);
//            httpServletRequest.setAttribute("arrayListSoldProductsOfTheMonth", productsOfTheMonth);
            httpServletRequest.setAttribute("sumOrder", sumOrder);
            httpServletRequest.setAttribute("sumSale", sumSale);
            httpServletRequest.setAttribute("sumSaleMoney", sumSaleMoney);

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

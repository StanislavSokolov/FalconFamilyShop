import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@WebServlet("/information")
public class InformationServlet extends HttpServlet {

    ArrayList<Day> daysPrev;

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
        String partNumber = httpServletRequest.getParameter("partNumber");

        ArrayList<ArrayList> arrayListsSales = new ArrayList<>();
        ArrayList<ArrayList> arrayListsOrders = new ArrayList<>();
        ArrayList<Day> week = new ArrayList<>();

        int totalSaleWeek = 0;
        int totalOrderWeek = 0;
        int totalSaleMoneyWeek = 0;
        if (shop != null) {
            if (shop.equals("wb")) {
                httpServletRequest.setAttribute("shop1", shop);
                httpServletRequest.setAttribute("shop2", "ozon");
                httpServletRequest.setAttribute("title1", "WB");
                httpServletRequest.setAttribute("title2", "OZON");

                if (partNumber != null) {
                    Product product = SQL.getData(shop, partNumber);
                    for (int i = -6; i < 1; i++) {
                        ArrayList<Product1> product1s = SQL.getData("wbsales", partNumber, i);
                        ArrayList<Product1> product2s = SQL.getData("wborders", partNumber, i);
                        arrayListsSales.add(product1s);
                        arrayListsOrders.add(product2s);
                    }
                    for (int i = 0; i < arrayListsSales.size(); i++) {
                        int orders = arrayListsOrders.get(i).size();
                        int sales = arrayListsSales.get(i).size();
                        int saleMoney = 0;
                        totalOrderWeek = totalOrderWeek + arrayListsOrders.get(i).size();
                        totalSaleWeek = totalSaleWeek + arrayListsSales.get(i).size();
                        ArrayList<Product1> product1s = arrayListsSales.get(i);
                        for (int j = 0; j < product1s.size(); j++) {
                            totalSaleMoneyWeek = totalSaleMoneyWeek + product1s.get(j).getForPay();
                            saleMoney = saleMoney + product1s.get(j).getForPay();
                        }
                        week.add(new Day(URLRequestResponse.getData(i-arrayListsSales.size() + 1), sales, orders, saleMoney, "test"));

                    }

                    httpServletRequest.setAttribute("product", product);
                    httpServletRequest.setAttribute("totalSaleWeek", totalSaleWeek);
                    httpServletRequest.setAttribute("totalOrderWeek", totalOrderWeek);
                    httpServletRequest.setAttribute("totalSaleMoneyWeek", totalSaleMoneyWeek);
                }
            } else {
                httpServletRequest.setAttribute("shop1", shop);
                httpServletRequest.setAttribute("shop2", "wb");
                httpServletRequest.setAttribute("title1", "OZON");
                httpServletRequest.setAttribute("title2", "WB");

                ArrayList<Day> allTime = SQL.upDate(shop);

                int countStart = 7;

                if (allTime.size() < countStart) countStart = allTime.size();
                for (int i = allTime.size() - countStart; i < allTime.size(); i++) {
                    week.add(allTime.get(i));
                }
                if (!week.isEmpty()) {
                    for (Day d: week) {
                        totalSaleWeek = totalSaleWeek + d.getSumSale();
                        totalOrderWeek = totalOrderWeek + d.getSumOrder();
                        totalSaleMoneyWeek = totalSaleMoneyWeek + d.getSumSaleMoney();
                    }
                }
            }

//            httpServletRequest.setAttribute("product", product);

//            if (!week.isEmpty()) {
////                httpServletRequest.setAttribute("arrayListWeek", week);
//                httpServletRequest.setAttribute("totalSaleWeek", totalSaleWeek);
//                httpServletRequest.setAttribute("totalOrderWeek", totalOrderWeek);
//                httpServletRequest.setAttribute("totalSaleMoneyWeek", totalSaleMoneyWeek);
//            }

        } else {

        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.getRequestDispatcher("product.jsp").forward(httpServletRequest, httpServletResponse);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}

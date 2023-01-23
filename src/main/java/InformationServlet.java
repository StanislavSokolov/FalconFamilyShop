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

@WebServlet("/information")
public class InformationServlet extends HttpServlet {

    ArrayList<Day> daysPrev;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/html");

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        boolean authorizationStatus = false;
//        String quantity = "0";
//        Cookie[] cookies = httpServletRequest.getCookies();
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals("quantity")) {
//                authorizationStatus = true;
//                quantity = cookie.getValue();
//            }
//        }
//
//        if (!authorizationStatus) {
//            Cookie cookie1 = new Cookie("quantity", quantity);
//            cookie1.setMaxAge(60*60*24*90);
//            httpServletResponse.addCookie(cookie1);
//        }
//
//        httpServletRequest.setAttribute("quantity", quantity);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String shop = httpServletRequest.getParameter("shop");

        if (shop != null) {
            if (shop.equals("wb")) {
                httpServletRequest.setAttribute("shop1", shop);
                httpServletRequest.setAttribute("shop2", "ozon");
                httpServletRequest.setAttribute("title1", "WB");
                httpServletRequest.setAttribute("title2", "OZON");
            } else {
                httpServletRequest.setAttribute("shop1", shop);
                httpServletRequest.setAttribute("shop2", "wb");
                httpServletRequest.setAttribute("title1", "OZON");
                httpServletRequest.setAttribute("title2", "WB");
            }

            ArrayList<Day> allTime = SQL.upDate(shop);
            ArrayList<Day> week = new ArrayList<>();

            int countStart = 7;

            if (allTime.size() < countStart) countStart = allTime.size();
            for (int i = allTime.size() - countStart; i < allTime.size(); i++) {
                week.add(allTime.get(i));
            }

            int totalSaleWeek = 0;
            int totalOrderWeek = 0;
            int totalSaleMoneyWeek = 0;

            if (!week.isEmpty()) {
                for (Day d: week) {
                    totalSaleWeek = totalSaleWeek + d.getSumSale();
                    totalOrderWeek = totalOrderWeek + d.getSumOrder();
                    totalSaleMoneyWeek = totalSaleMoneyWeek + d.getSumSaleMoney();
                }
                httpServletRequest.setAttribute("arrayListWeek", week);
                httpServletRequest.setAttribute("totalSaleWeek", totalSaleWeek);
                httpServletRequest.setAttribute("totalOrderWeek", totalOrderWeek);
                httpServletRequest.setAttribute("totalSaleMoneyWeek", totalSaleMoneyWeek);
            }

        } else {




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//            System.out.println(week.get(0).getSumSaleMoney());
//            ArrayList<Day> month = new ArrayList<>();
//            for (int i = allTime.size() - 28; i < allTime.size(); i++) {
//                week.add(allTime.get(i));
//            }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//            if (!week.isEmpty()) httpServletRequest.setAttribute("arrayListWeek", week);
//            httpServletRequest.setAttribute("arrayListWeek", week);
//            httpServletRequest.setAttribute("arrayListMonth", month);
//            httpServletRequest.setAttribute("test", "daysPrev");

        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        httpServletRequest.getRequestDispatcher("information.jsp").forward(httpServletRequest, httpServletResponse);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doPost(httpServletRequest, httpServletResponse);
    }
}

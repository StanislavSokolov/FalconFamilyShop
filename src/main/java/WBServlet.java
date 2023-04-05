import java.io.IOException;
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

    ArrayList<ItemShop> productsOfTheDayPrev;
    ArrayList<ItemShop> productsOfTheWeekPrev;
    ArrayList<ItemShop> productsOfTheMonthPrev;
    ArrayList<ItemShop> productsOfTheAllTimePrev;

    int totalSaleWeekPrev;
    int totalOrderWeekPrev;
    int totalSaleMoneyWeekPrev;

    ArrayList<Day> weekPrev;

    int totalSaleMonthPrev;
    int totalOrderMonthPrev;
    int totalSaleMoneyMonthPrev;

    ArrayList<Day> monthPrev;

    int totalSaleAllTimePrev;
    int totalOrderAllTimePrev;
    int totalSaleMoneyAllTimePrev;

    ArrayList<Day> allTimePrev;

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
            if (category.equals("statalltime")) {
                if (value.equals("name")) productsOfTheAllTimePrev.sort((o1, o2) -> o1.getSubject().compareTo(o2.getSubject()));
                if (value.equals("order")) productsOfTheAllTimePrev.sort((o1, o2) -> o2.getOrder() - o1.getOrder());
                if (value.equals("sale")) productsOfTheAllTimePrev.sort((o1, o2) -> o2.getSale() - o1.getSale());
                if (value.equals("forpay")) productsOfTheAllTimePrev.sort((o1, o2) -> (int) (Double.parseDouble(o2.getForPay()) - Double.parseDouble(o1.getForPay())));
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
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheAllTime", productsOfTheAllTimePrev);
            httpServletRequest.setAttribute("sumOrder", sumOrder);
            httpServletRequest.setAttribute("sumSale", sumSale);
            httpServletRequest.setAttribute("sumSaleMoney", sumSaleMoney);

            httpServletRequest.setAttribute("shop1", "wb");
            httpServletRequest.setAttribute("shop2", "ozon");
            httpServletRequest.setAttribute("title1", "WB");
            httpServletRequest.setAttribute("title2", "OZON");

            if (!weekPrev.isEmpty()) {
                httpServletRequest.setAttribute("arrayListWeek", weekPrev);
                httpServletRequest.setAttribute("totalSaleWeek", totalSaleWeekPrev);
                httpServletRequest.setAttribute("totalOrderWeek", totalOrderWeekPrev);
                httpServletRequest.setAttribute("totalSaleMoneyWeek", totalSaleMoneyWeekPrev);
            }
            if (!monthPrev.isEmpty()) {
                httpServletRequest.setAttribute("arrayListMonth", monthPrev);
                httpServletRequest.setAttribute("totalSaleMonth", totalSaleMonthPrev);
                httpServletRequest.setAttribute("totalOrderMonth", totalOrderMonthPrev);
                httpServletRequest.setAttribute("totalSaleMoneyMonth", totalSaleMoneyMonthPrev);
            }
            if (!allTimePrev.isEmpty()) {
                httpServletRequest.setAttribute("arrayListAllTime", allTimePrev);
                httpServletRequest.setAttribute("totalSaleAllTime", totalSaleAllTimePrev);
                httpServletRequest.setAttribute("totalOrderAllTime", totalOrderAllTimePrev);
                httpServletRequest.setAttribute("totalSaleMoneyAllTime", totalSaleMoneyAllTimePrev);
            }
        } else {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product1> arrayListOfOrderedProductsDay = SQL.getData("wborders", 0);
            ArrayList<Product1> arrayListOfSoldProductsDay = SQL.getData("wbsales", 0);

            ArrayList<ItemShop> productsOfTheDay = toCreateListOfSoldProducts(arrayListOfOrderedProductsDay, arrayListOfSoldProductsDay);

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

            ArrayList<Product1> arrayListOfOrderedProductsWeek = new ArrayList<>();
            ArrayList<Product1> arrayListOfSoldProductsWeek = new ArrayList<>();

            ArrayList<ArrayList> arrayListsOfOrderedProductsWeek = new ArrayList<>();
            ArrayList<ArrayList> arrayListsOfSoldProductsWeek = new ArrayList<>();

            for (int i = 0; i > -7; i--) {
                ArrayList<Product1> arrayListOfOrderedProductsCurrent = SQL.getData("wborders", i);
                arrayListsOfOrderedProductsWeek.add(arrayListOfOrderedProductsCurrent);
                for (Product1 p1: arrayListOfOrderedProductsCurrent) arrayListOfOrderedProductsWeek.add(p1);
                ArrayList<Product1> arrayListOfSoldProductsCurrent = SQL.getData("wbsales", i);
                arrayListsOfSoldProductsWeek.add(arrayListOfSoldProductsCurrent);
                for (Product1 p1: arrayListOfSoldProductsCurrent) arrayListOfSoldProductsWeek.add(p1);
            }

            ArrayList<ItemShop> productsOfTheWeek = toCreateListOfSoldProducts(arrayListOfOrderedProductsWeek, arrayListOfSoldProductsWeek);

            DataOfPeriod week = toCreateListOfDays(arrayListsOfSoldProductsWeek, arrayListsOfOrderedProductsWeek);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product1> arrayListOfOrderedProductsMonth = new ArrayList<>();
            ArrayList<Product1> arrayListOfSoldProductsMonth = new ArrayList<>();

            ArrayList<ArrayList> arrayListsOfOrderedProductsMonth = new ArrayList<>();
            ArrayList<ArrayList> arrayListsOfSoldProductsMonth = new ArrayList<>();

            for (int i = 0; i > -31; i--) {
                ArrayList<Product1> arrayListOfOrderedProductsCurrent = SQL.getData("wborders", i);
                arrayListsOfOrderedProductsMonth.add(arrayListOfOrderedProductsCurrent);
                for (Product1 p1: arrayListOfOrderedProductsCurrent) arrayListOfOrderedProductsMonth.add(p1);
                ArrayList<Product1> arrayListOfSoldProductsCurrent = SQL.getData("wbsales", i);
                arrayListsOfSoldProductsMonth.add(arrayListOfSoldProductsCurrent);
                for (Product1 p1: arrayListOfSoldProductsCurrent) arrayListOfSoldProductsMonth.add(p1);
            }

            ArrayList<ItemShop> productsOfTheMonth = toCreateListOfSoldProducts(arrayListOfOrderedProductsMonth, arrayListOfSoldProductsMonth);

            DataOfPeriod month = toCreateListOfDays(arrayListsOfSoldProductsMonth, arrayListsOfOrderedProductsMonth);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ArrayList<Product1> arrayListOfOrderedProductsAllTime = new ArrayList<>();
            ArrayList<Product1> arrayListOfSoldProductsAllTime = new ArrayList<>();

            ArrayList<ArrayList> arrayListsOfOrderedProductsAllTime = new ArrayList<>();
            ArrayList<ArrayList> arrayListsOfSoldProductsAllTime = new ArrayList<>();

            for (int i = 0; i > -100; i--) {
                ArrayList<Product1> arrayListOfOrderedProductsCurrent = SQL.getData("wborders", i);
                arrayListsOfOrderedProductsAllTime.add(arrayListOfOrderedProductsCurrent);
                for (Product1 p1: arrayListOfOrderedProductsCurrent) arrayListOfOrderedProductsAllTime.add(p1);
                ArrayList<Product1> arrayListOfSoldProductsCurrent = SQL.getData("wbsales", i);
                arrayListsOfSoldProductsAllTime.add(arrayListOfSoldProductsCurrent);
                for (Product1 p1: arrayListOfSoldProductsCurrent) arrayListOfSoldProductsAllTime.add(p1);
            }

            ArrayList<ItemShop> productsOfTheAllTime = toCreateListOfSoldProducts(arrayListOfOrderedProductsAllTime, arrayListOfSoldProductsAllTime);

            DataOfPeriod allTime = toCreateListOfDays(arrayListsOfSoldProductsAllTime, arrayListsOfOrderedProductsAllTime);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
            productsOfTheAllTimePrev = new ArrayList<>();
            for (ItemShop itemShop : productsOfTheAllTime) {
                productsOfTheAllTimePrev.add(itemShop);
            }

            totalSaleWeekPrev = week.getTotalSale();
            totalOrderWeekPrev = week.getTotalOrder();
            totalSaleMoneyWeekPrev = week.getTotalSaleMoney();

            weekPrev = week.getPeriod();

            totalSaleMonthPrev = month.getTotalSale();
            totalOrderMonthPrev = month.getTotalOrder();
            totalSaleMoneyMonthPrev = month.getTotalSaleMoney();

            monthPrev = month.getPeriod();

            totalSaleAllTimePrev = allTime.getTotalSale();
            totalOrderAllTimePrev = allTime.getTotalOrder();
            totalSaleMoneyAllTimePrev = allTime.getTotalSaleMoney();

            allTimePrev = allTime.getPeriod();



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            httpServletRequest.setAttribute("productPopular", productPopular);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheDay", productsOfTheDay);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheWeek", productsOfTheWeek);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheMonth", productsOfTheMonth);
            httpServletRequest.setAttribute("arrayListSoldProductsOfTheAllTime", productsOfTheAllTime);
            httpServletRequest.setAttribute("sumOrder", sumOrder);
            httpServletRequest.setAttribute("sumSale", sumSale);
            httpServletRequest.setAttribute("sumSaleMoney", sumSaleMoney);

            httpServletRequest.setAttribute("shop1", "wb");
            httpServletRequest.setAttribute("shop2", "ozon");
            httpServletRequest.setAttribute("title1", "WB");
            httpServletRequest.setAttribute("title2", "OZON");

            if (!week.getPeriod().isEmpty()) {
                httpServletRequest.setAttribute("arrayListWeek", week.getPeriod());
                httpServletRequest.setAttribute("totalSaleWeek", week.getTotalSale());
                httpServletRequest.setAttribute("totalOrderWeek", week.getTotalOrder());
                httpServletRequest.setAttribute("totalSaleMoneyWeek", week.getTotalSaleMoney());
            }

            if (!month.getPeriod().isEmpty()) {
                httpServletRequest.setAttribute("arrayListMonth", month.getPeriod());
                httpServletRequest.setAttribute("totalSaleMonth", month.getTotalSale());
                httpServletRequest.setAttribute("totalOrderMonth", month.getTotalOrder());
                httpServletRequest.setAttribute("totalSaleMoneyMonth", month.getTotalSaleMoney());
            }
            if (!allTime.getPeriod().isEmpty()) {
                httpServletRequest.setAttribute("arrayListAllTime", allTime.getPeriod());
                httpServletRequest.setAttribute("totalSaleAllTime", allTime.getTotalSale());
                httpServletRequest.setAttribute("totalOrderAllTime", allTime.getTotalOrder());
                httpServletRequest.setAttribute("totalSaleMoneyAllTime", allTime.getTotalSaleMoney());
            }

        }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        httpServletRequest.getRequestDispatcher("data.jsp").forward(httpServletRequest, httpServletResponse);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }

    private DataOfPeriod toCreateListOfDays(ArrayList<ArrayList> arrayListsOfSoldProducts, ArrayList<ArrayList> arrayListsOfOrderedProducts) {
        int totalSale = 0;
        int totalOrder = 0;
        int totalSaleMoney = 0;

        ArrayList<Day> period = new ArrayList<>();

        for (int i = arrayListsOfSoldProducts.size() - 1; i > -1; i--) {
            int orders = arrayListsOfOrderedProducts.get(i).size();
            int sales = arrayListsOfSoldProducts.get(i).size();
            int saleMoney = 0;
            totalOrder = totalOrder + arrayListsOfOrderedProducts.get(i).size();
            totalSale = totalSale + arrayListsOfSoldProducts.get(i).size();
            ArrayList<Product1> product1s = arrayListsOfSoldProducts.get(i);
            for (int j = 0; j < product1s.size(); j++) {
                totalSaleMoney = totalSaleMoney + product1s.get(j).getForPay();
                saleMoney = saleMoney + product1s.get(j).getForPay();
            }
            period.add(new Day(URLRequestResponse.getData(-i), sales, orders, saleMoney, "test"));
        }

        DataOfPeriod dataOfPeriod = new DataOfPeriod(totalSale, totalOrder, totalSaleMoney, period);

        return dataOfPeriod;
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

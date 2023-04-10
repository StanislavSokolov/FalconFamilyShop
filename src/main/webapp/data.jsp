<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html style="font-size: 16px;">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Travel, Start Your Jorney, Discount 10-30% Off, Why Choose Us">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>SOKOL0VE ${title1}</title>
    <link rel="stylesheet" href="nicepage.css" media="screen">
<link rel="stylesheet" href="SOKOL0VE.css" media="screen">
    <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages':['bar']});
            google.charts.setOnLoadCallback(drawChartWeek);
                function drawChartWeek() {
                    var data = google.visualization.arrayToDataTable([
                        ['Дата', 'Купили (${totalSaleWeek})', 'Заказали (${totalOrderWeek})'],
                        <c:forEach var="day" items="${arrayListWeek}">
                            ['${day.date}', ${day.sumSale}, ${day.sumOrder}],
                        </c:forEach>
                        ]
                    );

                    var options = {
                        chart: {
                            title: '${shop1}',
                            subtitle: 'График заказов и продаж за текущую неделю',
                        }
                    };

                    var chart = new google.charts.Bar(document.getElementById('Week'));

                    chart.draw(data, google.charts.Bar.convertOptions(options));
                }
            google.charts.setOnLoadCallback(drawChartWeekProfit);
                function drawChartWeekProfit() {
                    var data = google.visualization.arrayToDataTable([
                        ['Дата', 'Оборот (${totalSaleMoneyWeek})'],
                        <c:forEach var="day" items="${arrayListWeek}">
                            ['${day.date}', ${day.sumSaleMoney}],
                        </c:forEach>
                        ]);

                    var options = {
                        chart: {
                            title: '${shop1}',
                            subtitle: 'График вознаграждений за текущую неделю',
                        }
                    };
                    var chart = new google.charts.Bar(document.getElementById('WeekProfit'));
                    chart.draw(data, google.charts.Bar.convertOptions(options));
                }
            google.charts.setOnLoadCallback(drawChartMonth);
                function drawChartMonth() {
                    var data = google.visualization.arrayToDataTable([
                        ['Дата', 'Купили (${totalSaleMonth})', 'Заказали (${totalOrderMonth})'],
                        <c:forEach var="day" items="${arrayListMonth}">
                            ['${day.date}', ${day.sumSale}, ${day.sumOrder}],
                        </c:forEach>
                        ]
                    );

                    var options = {
                        chart: {
                            title: '${shop1}',
                            subtitle: 'График заказов и продаж за текущий месяц',
                        }
                    };

                    var chart = new google.charts.Bar(document.getElementById('Month'));

                    chart.draw(data, google.charts.Bar.convertOptions(options));
                }
            google.charts.setOnLoadCallback(drawChartMonthProfit);
                function drawChartMonthProfit() {
                    var data = google.visualization.arrayToDataTable([
                        ['Дата', 'Оборот (${totalSaleMoneyMonth})'],
                        <c:forEach var="day" items="${arrayListMonth}">
                            ['${day.date}', ${day.sumSaleMoney}],
                        </c:forEach>
                        ]);

                    var options = {
                        chart: {
                            title: '${shop1}',
                            subtitle: 'График вознаграждений за текущий месяц',
                        }
                    };
                    var chart = new google.charts.Bar(document.getElementById('MonthProfit'));
                    chart.draw(data, google.charts.Bar.convertOptions(options));
                }
            google.charts.setOnLoadCallback(drawChartAllTime);
                function drawChartAllTime() {
                    var data = google.visualization.arrayToDataTable([
                        ['Дата', 'Купили (${totalSaleAllTime})', 'Заказали (${totalOrderAllTime})'],
                        <c:forEach var="day" items="${arrayListAllTime}">
                            ['${day.date}', ${day.sumSale}, ${day.sumOrder}],
                        </c:forEach>
                        ]
                    );

                    var options = {
                        chart: {
                            title: '${shop1}',
                            subtitle: 'График заказов и продаж за три месяца',
                        }
                    };

                    var chart = new google.charts.Bar(document.getElementById('AllTime'));

                    chart.draw(data, google.charts.Bar.convertOptions(options));
                }
            google.charts.setOnLoadCallback(drawChartAllTimeProfit);
                function drawChartAllTimeProfit() {
                    var data = google.visualization.arrayToDataTable([
                        ['Дата', 'Оборот (${totalSaleMoneyAllTime})'],
                        <c:forEach var="day" items="${arrayListAllTime}">
                            ['${day.date}', ${day.sumSaleMoney}],
                        </c:forEach>
                        ]);

                    var options = {
                        chart: {
                            title: '${shop1}',
                            subtitle: 'График вознаграждений за три месяца',
                        }
                    };
                    var chart = new google.charts.Bar(document.getElementById('AllTimeProfit'));
                    chart.draw(data, google.charts.Bar.convertOptions(options));
                }
            google.charts.load("current", {packages:["corechart"]});
            google.charts.setOnLoadCallback(drawChart2);
                function drawChart2() {
                    var data = google.visualization.arrayToDataTable([
                        ['Task', 'Hours per Day'],
                            <c:forEach var="item" items="${arrayListSoldProductsOfTheWeek}">
                                ['${item.subject} (арт. ${item.supplierArticle})', ${item.order}],
                            </c:forEach>
                        ]);

                    var options = {
                        title: 'Выручка',
                        is3D: true,
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d_profit'));
                    chart.draw(data, options);
                }



        </script>


    <meta name="generator" content="Nicepage 4.7.1, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">






    <script type="application/ld+json">{
		"@context": "http://schema.org",
		"@type": "Organization",
		"name": "",
		"logo": "images/default-logo.png"
}</script>
    <meta name="theme-color" content="#478ac9">
    <meta property="og:title" content="SOKOL0VE">
    <meta property="og:type" content="website">
  </head>
  <body class="u-body u-overlap u-xl-mode"><header class="u-clearfix u-header" id="sec-320d"><div class="u-align-left u-clearfix u-sheet u-sheet-1">
        <h3 class="u-align-left-xs u-headline u-hover-feature u-text u-text-body-alt-color u-text-1">
          <a href="/${shop2}">Перейти на ${title2}</a>
        </h3>
        <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
          <div class="menu-collapse" style="text-transform: uppercase; font-size: 0.75rem; letter-spacing: 2px; font-weight: 700;">
            <a class="u-btn-text u-button-style u-nav-link u-text-body-alt-color" href="#" style="padding: 4px 24px; font-size: calc(1em + 8px);">
              <svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 302 302" style="undefined"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-a760"></use></svg>
              <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="svg-a760" x="0px" y="0px" viewBox="0 0 302 302" style="enable-background:new 0 0 302 302;" xml:space="preserve" class="u-svg-content"><g><rect y="36" width="302" height="30"></rect><rect y="236" width="302" height="30"></rect><rect y="136" width="302" height="30"></rect>
</g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g><g></g></svg>
            </a>
          </div>
          <div class="u-custom-menu u-nav-container">
            <ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-body-alt-color" href="/${shop1}" data-page-id="36011200">Статистика</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-body-alt-color" href="/stock?shop=${shop1}" data-page-id="36011200">Склад</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-body-alt-color" href="/information?shop=${shop1}" data-page-id="36011200">Информация</a>
</li></ul>
          </div>
          <div class="u-custom-menu u-nav-container-collapse">
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
              <div class="u-inner-container-layout u-sidenav-overflow">
                <div class="u-menu-close"></div>
                <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/${shop1}" data-page-id="36011200">Статистика</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/stock?shop=${shop1}" data-page-id="36011200">Склад</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/information?shop=${shop1}" data-page-id="36011200">Информация</a>
</li></ul>
              </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
          </div>
        </nav>
      </div></header>
    <section class="skrollable u-clearfix u-image u-parallax u-section-1" src="" id="sec-59a1">
      <div class="u-clearfix u-sheet u-sheet-1">
        <h1 class="u-text u-text-body-alt-color u-title u-text-1">Ваш день на ${title1}</h1>
        <div class="u-expanded-width u-list u-list-1">
          <div class="u-repeater u-repeater-1">
            <div class="u-align-center u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-valign-top u-container-layout-1">
                <h1 class="u-align-left u-hover-feature u-text u-text-body-alt-color u-text-default u-text-2">Заказы</h1>
                <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-3">${sumOrder}</h1>
              </div>
            </div>
            <div class="u-align-center u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-valign-top u-container-layout-2">
                <h1 class="u-align-left u-hover-feature u-text u-text-body-alt-color u-text-default u-text-4">Продажи</h1>
                <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-5">${sumSale}</h1>
              </div>
            </div>
            <div class="u-align-center u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-valign-top u-container-layout-3">
                <h1 class="u-align-left u-hover-feature u-text u-text-body-alt-color u-text-default u-text-6">Оборот</h1>
                <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-7">${sumSaleMoney} р.</h1>
              </div>
            </div>
          </div>
        </div>
        <p class="u-large-text u-text u-text-body-alt-color u-text-variant u-text-8">Это хороший день</p>
        <a href="/${shop1}#carousel_de3a" class="u-btn u-btn-round u-button-style u-gradient u-radius-10 u-btn-1">Узнать больше</a>
        <h1 class="u-text u-text-body-alt-color u-text-default u-title u-text-9"> Товар дня</h1>
        <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-10">${productPopular.subject} (арт. ${productPopular.supplierArticle})</h1>
        <div class="u-expanded-width u-list u-list-2">
          <div class="u-repeater u-repeater-2">
            <div class="u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-container-layout-4">
                <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-11">Заказали</h1>
                <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-12">${productPopular.order}</h1>
              </div>
            </div>
            <div class="u-container-style u-list-item u-repeater-item">
              <div class="u-container-layout u-similar-container u-container-layout-5">
                <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-13">Купили</h1>
                <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-14">${productPopular.sale}</h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <h1 class="u-text u-text-default u-text-1">Статистика за день</h1>
        <div class="u-expanded-width u-table u-table-responsive u-table-1">
          <table class="u-table-entity u-table-entity-1">
            <colgroup>
              <col width="25%">
              <col width="25%">
              <col width="25%">
              <col width="25%">
            </colgroup>
            <thead class="u-palette-1-light-2 u-table-header u-table-header-1">
              <tr style="height: 45px;">
                <th class="u-table-cell"><a href="/${shop1}?category=statday&value=name">Наименование</a></th>
                <th class="u-table-cell"><a href="/${shop1}?category=statday&value=order">Заказали</a></th>
                <th class="u-table-cell"><a href="/${shop1}?category=statday&value=sale">Купили</a></th>
                <th class="u-table-cell"><a href="/${shop1}?category=statday&value=forpay">Вознаграждение</a></th>
              </tr>
            </thead>
            <tbody class="u-table-alt-palette-1-light-3 u-table-body">
                <c:forEach var="count" items="${arrayListSoldProductsOfTheDay}">
                    <tr style="height: 65px;">
                        <td class="u-table-cell"><a href="/information?shop=${shop1}&subject=${count.subject}">${count.subject}</a> (арт. <a href="/information?shop=${shop1}&supplierArticle=${count.supplierArticle}">${count.supplierArticle}</a>)</td>
                        <td class="u-table-cell">${count.order}</td>
                        <td class="u-table-cell">${count.sale}</td>
                        <td class="u-table-cell">${count.forPay}</td>
                    </tr>
                </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </section>
    <section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
          <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
            <h1 class="u-text u-text-default u-text-1">Статистика за неделю</h1>
            <div class="u-expanded-width u-table u-table-responsive u-table-1">
              <table class="u-table-entity u-table-entity-1">
                <colgroup>
                  <col width="25%">
                  <col width="25%">
                  <col width="25%">
                  <col width="25%">
                </colgroup>
                <thead class="u-palette-1-light-2 u-table-header u-table-header-1">
                  <tr style="height: 45px;">
                    <th class="u-table-cell"><a href="/${shop1}?category=statweek&value=name">Наименование</a></th>
                    <th class="u-table-cell"><a href="/${shop1}?category=statweek&value=order">Заказали</a></th>
                    <th class="u-table-cell"><a href="/${shop1}?category=statweek&value=sale">Купили</a></th>
                    <th class="u-table-cell"><a href="/${shop1}?category=statweek&value=forpay">Вознаграждение</a></th>
                  </tr>
                </thead>
                <tbody class="u-table-alt-palette-1-light-3 u-table-body">
                    <c:forEach var="count" items="${arrayListSoldProductsOfTheWeek}">
                        <tr style="height: 65px;">
                            <td class="u-table-cell"><a href="/information?shop=${shop1}&subject=${count.subject}">${count.subject}</a> (арт. <a href="/information?shop=${shop1}&supplierArticle=${count.supplierArticle}">${count.supplierArticle}</a>)</td>
                            <td class="u-table-cell">${count.order}</td>
                            <td class="u-table-cell">${count.sale}</td>
                            <td class="u-table-cell">${count.forPay}</td>
                        </tr>
                    </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
    </section>
    <section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
        <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
            <h1 class="u-text u-text-default u-text-1">Динамика за неделю</h1>
            <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <div id="Week" <div id="air" style="width: 600px; height: 300px;"></div></div>
                <div id="WeekProfit" <div id="air" style="width: 600px; height: 300px;"></div></div>
            </div>
        </div>
    </section>
    <!--<section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
        <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
            <h1 class="u-text u-text-default u-text-1">Доли за неделю</h1>
            <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <div id="piechart_3d_profit" style="width: 900px; height: 500px;"></div>
                <div id="piechart_3d_sale" style="width: 900px; height: 500px;"></div>
                <div id="piechart_3d_order" style="width: 900px; height: 500px;"></div>
            </div>
        </div>
    </section> -->
    <section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
              <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <h1 class="u-text u-text-default u-text-1">Статистика за месяц</h1>
                <div class="u-expanded-width u-table u-table-responsive u-table-1">
                  <table class="u-table-entity u-table-entity-1">
                    <colgroup>
                      <col width="25%">
                      <col width="25%">
                      <col width="25%">
                      <col width="25%">
                    </colgroup>
                    <thead class="u-palette-1-light-2 u-table-header u-table-header-1">
                      <tr style="height: 45px;">
                        <th class="u-table-cell"><a href="/${shop1}?category=statmonth&value=name">Наименование</a></th>
                        <th class="u-table-cell"><a href="/${shop1}?category=statmonth&value=order">Заказали</a></th>
                        <th class="u-table-cell"><a href="/${shop1}?category=statmonth&value=sale">Купили</a></th>
                        <th class="u-table-cell"><a href="/${shop1}?category=statmonth&value=forpay">Вознаграждение</a></th>
                      </tr>
                    </thead>
                    <tbody class="u-table-alt-palette-1-light-3 u-table-body">
                        <c:forEach var="count" items="${arrayListSoldProductsOfTheMonth}">
                            <tr style="height: 65px;">
                                <td class="u-table-cell"><a href="/information?shop=${shop1}&subject=${count.subject}">${count.subject}</a> (арт. <a href="/information?shop=${shop1}&supplierArticle=${count.supplierArticle}">${count.supplierArticle}</a>)</td>
                                <td class="u-table-cell">${count.order}</td>
                                <td class="u-table-cell">${count.sale}</td>
                                <td class="u-table-cell">${count.forPay}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </section>
    <section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
        <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
            <h1 class="u-text u-text-default u-text-1">Динамика за месяц</h1>
                <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                    <div id="Month" <div id="air" style="width: 800px; height: 300px;"></div></div>
                    <div id="MonthProfit" <div id="air" style="width: 800px; height: 300px;"></div></div>
                </div>
        </div>
    </section>
<section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
              <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                <h1 class="u-text u-text-default u-text-1">Статистика за три месяца</h1>
                <div class="u-expanded-width u-table u-table-responsive u-table-1">
                  <table class="u-table-entity u-table-entity-1">
                    <colgroup>
                      <col width="25%">
                      <col width="25%">
                      <col width="25%">
                      <col width="25%">
                    </colgroup>
                    <thead class="u-palette-1-light-2 u-table-header u-table-header-1">
                      <tr style="height: 45px;">
                        <th class="u-table-cell"><a href="/${shop1}?category=statalltime&value=name">Наименование</a></th>
                        <th class="u-table-cell"><a href="/${shop1}?category=statalltime&value=order">Заказали</a></th>
                        <th class="u-table-cell"><a href="/${shop1}?category=statalltime&value=sale">Купили</a></th>
                        <th class="u-table-cell"><a href="/${shop1}?category=statalltime&value=forpay">Вознаграждение</a></th>
                      </tr>
                    </thead>
                    <tbody class="u-table-alt-palette-1-light-3 u-table-body">
                        <c:forEach var="count" items="${arrayListSoldProductsOfTheAllTime}">
                            <tr style="height: 65px;">
                                <td class="u-table-cell"><a href="/information?shop=${shop1}&subject=${count.subject}">${count.subject}</a> (арт. <a href="/information?shop=${shop1}&supplierArticle=${count.supplierArticle}">${count.supplierArticle}</a>)</td>
                                <td class="u-table-cell">${count.order}</td>
                                <td class="u-table-cell">${count.sale}</td>
                                <td class="u-table-cell">${count.forPay}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </section>
    <section class="u-align-center u-clearfix u-section-2" id="carousel_de3a">
        <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
            <h1 class="u-text u-text-default u-text-1">Динамика за три месяца</h1>
                <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
                    <div id="AllTime" <div id="air" style="width: 1200px; height: 300px;"></div></div>
                    <div id="AllTimeProfit" <div id="air" style="width: 1200px; height: 300px;"></div></div>
                </div>
        </div>
    </section>
    <section class="u-clearfix u-palette-1-light-3 u-section-4" id="sec-c82f">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-clearfix u-expanded-width u-gutter-20 u-layout-wrap u-layout-wrap-1">
          <div class="u-layout">
            <div class="u-layout-row">
              <div class="u-size-30 u-size-60-md">
                <div class="u-layout-row">
                  <div class="u-container-style u-image u-layout-cell u-left-cell u-size-60 u-image-1" data-image-width="1280" data-image-height="720">
                    <div class="u-container-layout"></div>
                  </div>
                </div>
              </div>
              <div class="u-size-30 u-size-60-md">
                <div class="u-layout-col">
                  <div class="u-size-30">
                    <div class="u-layout-row">
                      <div class="u-container-style u-layout-cell u-size-30 u-layout-cell-2">
                        <div class="u-container-layout">
                          <h1 class="u-text u-text-default u-text-palette-1-base u-text-1">Склады WB</h1>
                          <p class="u-text u-text-default u-text-2">
                            <a class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-link u-button-style u-none u-text-palette-1-base u-btn-1" href="https://yandex.ru/maps/org/logisticheskiy_terminal_mlp_utkina_zavod_korpus_1/100550415199/?ll=30.529526%2C59.864618&amp;z=14.4"> Логистический терминал МЛП Уткина заводь, корпус № 1</a>
                          </p>
                          <p class="u-text u-text-default u-text-2">
                            <a class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-link u-button-style u-none u-text-palette-1-base u-btn-1" href="https://yandex.ru/maps/2/saint-petersburg/house/moskovskoye_shosse_153k2/Z0kYcgNlTkIEQFtjfXt5cntkYA==/">Санкт-Петербург, Пушкинский район, посёлок Шушары, Московское шоссе, 153, корп. 2</a>
                          </p>
                          <h1 class="u-text u-text-default u-text-palette-1-base u-text-4">24/7</h1>
                        </div>
                      </div>
                      <div class="u-container-style u-layout-cell u-right-cell u-size-30 u-layout-cell-3">
                        <div class="u-container-layout u-container-layout-3">
                          <h1 class="u-text u-text-default u-text-palette-1-base u-text-5">Склад ПЭК</h1>
                          <p class="u-text u-text-default u-text-2">
                            <a class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-link u-button-style u-none u-text-palette-1-base u-btn-1" href="https://yandex.ru/maps/2/saint-petersburg/?ll=30.434977%2C59.913783&amp;mode=search&amp;sctx=ZAAAAAgBEAAaKAoSCbdhFASPhz5AETfEeM2r7k1AEhIJ9UwvMZbptz8RMuTYeoZwnD8iBgABAgMEBSgKOABAvYIGSAFiK3JlYXJyPXNjaGVtZV9Mb2NhbC9HZW8vRW5hYmxlQmVhdXR5RmlsdGVyPTFqAnJ1nQHNzEw9oAEAqAEAvQGXsb%2BawgGGAcay6bmcBbaPmKUEoPDzg4cB1N%2Bf%2F%2BUCg8TbxM4Bnf%2FbroMB%2FebhnsoCs4eV8agD4tn0lskGgurel%2FgDsviHmdMGwcyYt%2FAEmP3gtE2527%2FboQKTgoGihwOks%2BqP6wHbnovQePjFz%2FKbBafPzfqjAYT82%2FJQhMmzhv8B2bXR7bYE1ODPk%2BgE6gEA8gEA%2BAEAggIR0YHQutC70LDQtCDQn9Ct0JqKAgCSAgCaAgxkZXNrdG9wLW1hcHM%3D&amp;sll=30.434977%2C59.913783&amp;sspn=0.314187%2C0.093277&amp;text=%D1%81%D0%BA%D0%BB%D0%B0%D0%B4%20%D0%9F%D0%AD%D0%9A&amp;whatshere%5Bpoint%5D=30.440487%2C59.943144&amp;whatshere%5Bzoom%5D=16&amp;z=12.65"> Якорная улица, 17</a>
                          </p>
                          <h1 class="u-text u-text-default u-text-palette-1-base u-text-7">пн-пт: 08:00-20:00</h1>
                          <h1 class="u-text u-text-default u-text-palette-1-base u-text-8">сб-вс: 10:00-16:00</h1>
                        </div>
                      </div>
                      <div class="u-container-style u-layout-cell u-size-30 u-layout-cell-2">
                        <div class="u-container-layout">
                            <h1 class="u-text u-text-default u-text-palette-1-base u-text-1">Склад OZON</h1>
                            <p class="u-text u-text-default u-text-2">
                                <a class="u-border-1 u-border-active-palette-2-base u-border-hover-palette-1-base u-btn u-button-link u-button-style u-none u-text-palette-1-base u-btn-1" href="https://yandex.ru/maps/2/saint-petersburg/house/sofiyskaya_ulitsa_118k4s1/Z0kYcgBoTkMHQFtjfXRwcnthYg==/?ll=30.478759%2C59.813727&z=17"> Софийская ул., 118, корп. 4, стр. 1, посёлок Петро-Славянка</a>
                            </p>
                            <h1 class="u-text u-text-default u-text-palette-1-base u-text-4">24/7</h1>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="u-size-30">
                    <div class="u-layout-row">
                      <div class="u-container-style u-layout-cell u-right-cell u-size-60 u-layout-cell-4">
                        <div class="u-container-layout"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="u-clearfix u-palette-1-light-3 u-section-5" id="sec-9aa4">
      <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
        <div class="u-clearfix u-expanded-width u-gutter-30 u-layout-wrap u-layout-wrap-1">
          <div class="u-layout">
            <div class="u-layout-row">
              <div class="u-align-left-xs u-container-style u-layout-cell u-left-cell u-size-60 u-layout-cell-1">
                <div class="u-container-layout u-container-layout-1">
                  <p class="u-text u-text-palette-1-base u-text-1">
                    <a class="u-active-none u-border-none u-btn u-button-link u-button-style u-hover-none u-none u-text-palette-1-base u-btn-1" href="https://seller.wildberries.ru/login/ru?redirect_url=/">WB Seller</a>
                  </p>
                  <p class="u-text u-text-palette-1-base u-text-2">
                    <a class="u-active-none u-border-none u-btn u-button-link u-button-style u-hover-none u-none u-text-palette-1-base u-btn-2" href="https://www.wildberries.ru/">WB интернет-магазин</a>
                  </p>
                  <p class="u-text u-text-palette-1-base u-text-3">
                    <a class="u-active-none u-border-none u-btn u-button-link u-button-style u-hover-none u-none u-text-palette-1-base u-btn-3" href="https://www.wildberries.ru/seller/371119">WB магазин поставщика</a>
                  </p>

                  <p class="u-text u-text-palette-1-base u-text-1">
                    <a class="u-active-none u-border-none u-btn u-button-link u-button-style u-hover-none u-none u-text-palette-1-base u-btn-1" href="https://seller.ozon.ru/">OZON Seller</a>
                  </p>
                  <p class="u-text u-text-palette-1-base u-text-2">
                    <a class="u-active-none u-border-none u-btn u-button-link u-button-style u-hover-none u-none u-text-palette-1-base u-btn-2" href="https://www.ozon.ru/">OZON интернет-магазин</a>
                  </p>
                  <p class="u-text u-text-palette-1-base u-text-3">
                    <a class="u-active-none u-border-none u-btn u-button-link u-button-style u-hover-none u-none u-text-palette-1-base u-btn-3" href="https://www.ozon.ru/seller/sokol0ve-341256/">OZON магазин поставщика</a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>


    <footer class="u-clearfix u-footer u-grey-80" id="sec-9702"><div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
            <a href="https://nicepage.com" class="u-image u-logo u-image-1" data-image-width="80" data-image-height="40">
              <img src="images/default-logo.png" class="u-logo-image u-logo-image-1">
            </a>
            <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
              <div class="menu-collapse">
                <a class="u-button-style u-nav-link" href="#">
                  <svg class="u-svg-link" viewBox="0 0 24 24"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use></svg>
                  <svg class="u-svg-content" version="1.1" id="menu-hamburger" viewBox="0 0 16 16" x="0px" y="0px" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns="http://www.w3.org/2000/svg"><g><rect y="1" width="16" height="2"></rect><rect y="7" width="16" height="2"></rect><rect y="13" width="16" height="2"></rect>
    </g></svg>
                </a>
              </div>
              <div class="u-nav-container">
                <ul class="u-nav u-unstyled"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/wb">WB</a>
    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/ozon">OZON</a>
    </li></ul>
              </div>
              <div class="u-nav-container-collapse">
                <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
                  <div class="u-inner-container-layout u-sidenav-overflow">
                    <div class="u-menu-close"></div>
                    <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/wb">WB</a>
    </li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="/ozon">OZON</a>
    </li></ul>
                  </div>
                </div>
                <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
              </div>
            </nav>
          </div></footer>
  </body>
</html>
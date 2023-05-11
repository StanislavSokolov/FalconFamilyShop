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
    <title>Информация о товаре на ${title1}</title>
    <link rel="stylesheet" href="nicepage.css" media="screen">
<link rel="stylesheet" href="SOKOL0VE.css" media="screen">
    <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
            <script type="text/javascript">
              google.charts.load('current', {'packages':['bar']});
              google.charts.setOnLoadCallback(drawChart);
              function drawChart() {
                var data = google.visualization.arrayToDataTable([
                    ['Дата', 'Купили (${totalSaleWeek})', 'Заказали (${totalOrderWeek})'],
                    <c:forEach var="day" items="${week}">
                        ['${day.date}', ${day.sumSale}, ${day.sumOrder}],
                    </c:forEach>
                ]);

                var options = {
                    chart: {
                        title: '${product.subject}',
                        subtitle: 'График заказов и продаж за текущую неделю',
                    }
                };

                var chart = new google.charts.Bar(document.getElementById('air'));

                chart.draw(data, google.charts.Bar.convertOptions(options));
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
        <h1 class="u-text u-text-body-alt-color u-title u-text-1">${product.subject}</h1>

        <h1 class="u-text u-text-body-alt-color u-text-default u-title u-text-9">Реализация за неделю</h1>
        <div class="u-expanded-width u-list u-list-1">
            <div class="u-repeater u-repeater-1">
                <div class="u-align-center u-container-style u-list-item u-repeater-item">
                    <div class="u-container-layout u-similar-container u-valign-top u-container-layout-1">
                        <h1 class="u-align-left u-hover-feature u-text u-text-body-alt-color u-text-default u-text-2">Заказы</h1>
                        <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-3">${totalOrderWeek}</h1>
                    </div>
                </div>
                <div class="u-align-center u-container-style u-list-item u-repeater-item">
                    <div class="u-container-layout u-similar-container u-valign-top u-container-layout-2">
                        <h1 class="u-align-left u-hover-feature u-text u-text-body-alt-color u-text-default u-text-4">Продажи</h1>
                        <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-5">${totalSaleWeek}</h1>
                    </div>
                </div>
                <div class="u-align-center u-container-style u-list-item u-repeater-item">
                    <div class="u-container-layout u-similar-container u-valign-top u-container-layout-3">
                        <h1 class="u-align-left u-hover-feature u-text u-text-body-alt-color u-text-default u-text-6">Оборот</h1>
                        <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-7">${totalSaleMoneyWeek}</h1>
                    </div>
                </div>
            </div>
        </div>
        <h1 class="u-text u-text-body-alt-color u-text-default u-title u-text-9">Динамика продаж</h1>
        <h1 class="u-text u-text-body-alt-color u-text-default u-title u-text-9"> </h1>
        <div id="air" <div id="air" style="width: 600px; height: 400px;"></div></div>
      </div>
    </section>

    <section class="u-align-center u-clearfix u-section-3" id="sec-e0bf">
        <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">
            <h1 class="u-text u-text-default u-text-1">Наличие на складах</h1>
            <div class="u-expanded-width u-table u-table-responsive u-table-1">
              <table class="u-table-entity u-table-entity-1">
                <colgroup>
                  <col width="50%">
                  <col width="50%">
                </colgroup>
                <thead class="u-palette-1-light-2 u-table-header u-table-header-1">
                  <tr style="height: 45px;">
                    <th class="u-table-cell">Наименование</th>
                    <th class="u-table-cell">Остаток</th>
                  </tr>
                </thead>
                <tbody class="u-table-alt-palette-1-light-3 u-table-body">
                    <tr style="height: 65px;">
                        <td class="u-table-cell">Санкт-Петербург (Уткина Заводь)</td>
                        <td class="u-table-cell">${product.saintPetersburg}</td>
                    </tr>
                    <tr style="height: 65px;">
                        <td class="u-table-cell">Санкт-Петербург (Шушары)</td>
                        <td class="u-table-cell">${product.saintPetersburg2}</td>
                    </tr>
                    <tr style="height: 65px;">
                        <td class="u-table-cell">Коледино</td>
                        <td class="u-table-cell">${product.koledino}</td>
                    </tr>
                    <tr style="height: 65px;">
                        <td class="u-table-cell">Электросталь</td>
                        <td class="u-table-cell">${product.electrostal}</td>
                    </tr>
                    <tr style="height: 65px;">
                        <td class="u-table-cell">Казань</td>
                        <td class="u-table-cell">${product.kazan}</td>
                    </tr>
                    <tr style="height: 65px;">
                        <td class="u-table-cell">Другое</td>
                        <td class="u-table-cell">${product.other}</td>
                    </tr>
                    <tr style="height: 65px;">
                        <td class="u-table-cell">ИТОГО</td>
                        <td class="u-table-cell">${product.quantity}</td>
                    </tr>
                    <tr style="height: 65px;">
                        <td class="u-table-cell">В ПУТИ</td>
                        <td class="u-table-cell">${product.quantityFull - product.quantity}</td>
                    </tr>
                </tbody>
              </table>
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
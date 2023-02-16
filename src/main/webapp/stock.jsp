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
    <title>Склад ${title1}</title>
    <link rel="stylesheet" href="nicepage.css" media="screen">
<link rel="stylesheet" href="SOKOL0VE.css" media="screen">
    <script class="u-script" type="text/javascript" src="jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="nicepage.js" defer=""></script>

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
          google.charts.load("current", {packages:["corechart"]});
          google.charts.setOnLoadCallback(drawChart);
          function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Task', 'Hours per Day'],
                <c:forEach var="item" items="${stock}">
                    ['${item.subject} (арт. ${item.supplierArticle})', ${item.quantity}],
                </c:forEach>
            ]);

            var options = {
              title: 'Остатки',
              is3D: true,
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart_3d_stock'));
            chart.draw(data, options);
          }
        </script>

        <script type="text/javascript">
                  google.charts.load("current", {packages:["corechart"]});
                  google.charts.setOnLoadCallback(drawChart);
                  function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Task', 'Hours per Day'],
                        <c:forEach var="item" items="${stock}">
                            ['${item.subject} (арт. ${item.supplierArticle})', ${item.quantityFull - item.quantity}],
                        </c:forEach>
                    ]);

                    var options = {
                      title: 'В пути',
                      is3D: true,
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d_toway'));
                    chart.draw(data, options);
                  }
                </script>

                <script type="text/javascript">
                          google.charts.load("current", {packages:["corechart"]});
                          google.charts.setOnLoadCallback(drawChart);
                          function drawChart() {
                            var data = google.visualization.arrayToDataTable([
                                ['Task', 'Hours per Day'],
                                <c:forEach var="item" items="${stock}">
                                    ['${item.subject} (арт. ${item.supplierArticle})', ${item.total}],
                                </c:forEach>
                            ]);

                            var options = {
                              title: 'Выручка',
                              is3D: true,
                            };

                            var chart = new google.visualization.PieChart(document.getElementById('piechart_3d_money'));
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
    <meta property="og:title" content="DATA">
    <meta property="og:type" content="website">
  </head>
  <body class="u-body u-overlap u-xl-mode"><header class="u-clearfix u-header" id="sec-320d"><div class="u-align-left u-clearfix u-sheet u-sheet-1">
        <h3 class="u-align-left-xs u-headline u-hover-feature u-text u-text-body-alt-color u-text-1">
          <a href="/stock?shop=${shop2}">Перейти на ${title2}</a>
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
            <h1 class="u-text u-text-body-alt-color u-title u-text-1">Склад ${title1}</h1>

            <h1 class="u-text u-text-body-alt-color u-text-default u-title u-text-15">Товаров на складе на сумму</h1>
            <h1 class="u-hover-feature u-text u-text-body-alt-color u-text-default u-text-16">${total} р.</h1>

            <div id="piechart_3d_stock" style="width: 900px; height: 500px;"></div>

          </div>
    </section>

    <section class="u-align-center u-clearfix u-section-3" id="sec-e0bf">
          <div class="u-clearfix u-sheet u-valign-middle u-sheet-1">

                      <div id="piechart_3d_toway" style="width: 900px; height: 500px;"></div>
                      <div id="piechart_3d_money" style="width: 900px; height: 500px;"></div>
            <h1 class="u-text u-text-default u-text-1">Склад</h1>
            <div class="u-expanded-width u-table u-table-responsive u-table-1">
              <table class="u-table-entity u-table-entity-1">
                <colgroup>
                  <col width="25%">
                  <col width="15%">
                  <col width="15%">
                  <col width="15%">
                  <col width="15%">
                  <col width="15%">
                </colgroup>
                <thead class="u-palette-1-light-2 u-table-header u-table-header-1">
                  <tr style="height: 45px;">
                    <th class="u-table-cell"><a href="/stock?shop=${shop1}&value=name">Наименование</a></th>
                    <th class="u-table-cell"><a href="/stock?shop=${shop1}&value=remain">Остаток</a></th>
                    <th class="u-table-cell"><a href="/stock?shop=${shop1}&value=ontheway">В пути</a></th>
                    <th class="u-table-cell"><a href="/stock?shop=${shop1}&value=profit">Прибыль</a></th>
                    <th class="u-table-cell"><a href="/stock?shop=${shop1}&value=price">Цена</a></th>
                    <th class="u-table-cell"><a href="/stock?shop=${shop1}&value=discount">Скидка</a></th>
                  </tr>
                </thead>
                <tbody class="u-table-alt-palette-1-light-3 u-table-body">
                  <c:forEach var="count" items="${stock}">
                    <tr style="height: 65px;">
                        <td class="u-table-cell">${count.subject} (арт. ${count.supplierArticle})</td>
                        <td class="u-table-cell">${count.quantity}</td>
                        <td class="u-table-cell">${count.quantityFull - count.quantity}</td>
                        <td class="u-table-cell">${count.total}</td>
                        <td class="u-table-cell">${count.price}</td>
                        <td class="u-table-cell">${count.discount}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </section>
    <section class="u-clearfix u-palette-1-light-3 u-section-2" id="sec-9aa4">
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
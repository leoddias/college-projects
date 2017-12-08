<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Pedido"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Cliente"%>

<body>
    <div id="wrapper">
        <jsp:include page="../WEB-INF/incluir/menu.jsp" />
        <div id="page-wrapper">
            <div class="container-fluid" id="rota">
                <div class="row">
                    <div class="col-lg-10">
                        <h1 class="page-header">
                            Rota <small>-</small> Pedido Nº: <%=request.getParameter("codPedido")%>
                        </h1>
                    </div>
                </div>
                <div class="row" >
                    <style>
                        #mapa { width: 600px; height: 400px; }
                    </style>
                    <div class="col-lg-3 col-md-6" id="mapa">                      
                    </div>
                    <div class="col-lg-3 col-md-6">
                        <p>Cliente: <%=request.getAttribute("nomeCliente")%></p>                        
                        <p>Produtos: <%=request.getAttribute("produtos")%></p>
                        <p>Valor dos Produtos: R$ <%=request.getAttribute("valorProdutos")%></p>
                        <p>Valor da Entrega: R$ <%=request.getAttribute("valorEntrega")%></p>
                        <p>Valor do Desconto: R$ <%=request.getAttribute("valorDesconto")%></p>
                        <p>Valor Total: R$ <%=request.getAttribute("valorTotal")%></p>
                        
                    </div>
                </div>
            </div>
         <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
        </div>
    </div>
    <script src="http://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script>
                var map;
                var directionsDisplay;
                var directionsService = new google.maps.DirectionsService();
                function printContent(el) {
                    var restorepage = $('body').html();
                    var printcontent = $('#' + el).clone();
                    $('body').empty().html(printcontent);
                    window.print();
                    $('body').html(restorepage);
                }               
                function initialize() {
                    directionsDisplay = new google.maps.DirectionsRenderer();
                    var latlng = new google.maps.LatLng(-21.78313032, -46.56889522);
                    var enderecoPartida = "-21.78313032, -46.56889522"
                    //var enderecoChegada = "-21.78313032, -46.55688301"
                    //var enderecoPartida = 
                    var enderecoChegada = "<%=request.getAttribute("rotaChegada")%>"
                    var options = {
                        zoom: 5,
                        center: latlng,
                        mapTypeId: google.maps.MapTypeId.ROADMAP
                    };
                    var request = {
                        origin: enderecoPartida,
                        destination: enderecoChegada,
                        travelMode: google.maps.TravelMode.DRIVING
                    };
                    map = new google.maps.Map(document.getElementById("mapa"), options);
                    directionsService.route(request, function (result, status) {
                        if (status == google.maps.DirectionsStatus.OK) {
                            directionsDisplay.setDirections(result);
                        }
                    });
                    directionsDisplay.setMap(map);
                }
                initialize();
    </script>
    <script type="text/javascript">
        //http://stackoverflow.com/questions/2255291/print-the-contents-of-a-div
    </script>
</body>
<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Produto"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Pedido"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.dao.PedidoDAO"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.dao.ClienteDAO"%>
<%  ClienteDAO clientedao = new ClienteDAO((MySqlController) session.getAttribute("conexao"));
    PedidoDAO pedidodao = new PedidoDAO((MySqlController) session.getAttribute("conexao"));
    Pedido listaPedidos[] = null;

    if (request.getAttribute("tipoConsulta") == null) {
        listaPedidos = pedidodao.carregaPedidos("fechado");
%><meta http-equiv="refresh" content="30" /><%
    } else {
        switch (request.getAttribute("tipoConsulta").toString()) {
            case "todos":
                listaPedidos = pedidodao.carregaPedidos("todos");
                break;
            case "buscar":
                listaPedidos = pedidodao.buscaPedido(Integer.parseInt(request.getAttribute("codBusca").toString()));
                break;
        }
    }%>
<body>
    <div id="wrapper">
        <jsp:include page="../WEB-INF/incluir/menu.jsp" />
        <div id="page-wrapper">
            <div class="container-fluid">                
                <div class="row">
                    <div class="col-lg-10">
                        <h1 class="text-center page-header">
                            <i class="fa fa-desktop"></i>  Monitoramento de Pedidos
                        </h1>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-8">
                        <form method="post" action="PedidoController">
                            <div class="form-group">
                                <div class="col-md-4">
                                    <input id="codPedido" name="codPedido" placeholder="Digite o codigo do pedido" class="form-control input-group" type="text">
                                </div>                                
                                <button id="buscar" name="button" value="buscar" class="btn btn-primary">Buscar</button>                                                       
                            </div>
                        </form>    
                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-lg-2">
                        <form method="post" action="PedidoController">
                            <button id="listar" name="button" value="listar" class="btn btn-primary">Listar Todos Pedidos</button>
                        </form>
                    </div>                    
                </div>
                <div class="row">
                    <style>
                        table {width: 100%; font-size: 14px; font-family: tahoma, arial, sans-serif;}
                        table thead th {background-color: #8b8; padding: 5px 8px;}
                        table td {background-color: #ddd; padding: 5px 8px;}
                        table.sorting-table {cursor: move;}
                        table tr.sorting-row td {background-color: #8b8;}
                    </style>

                    <table id="tabelaPedidos">
                        <thead>
                            <tr>
                                <th>Codigo </th>
                                <th>Cliente </th>
                                <th>Produtos </th>
                                <th>Horario </th>
                                <th>Valor Total </th>
                                <th>Status </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Pedido pedido : listaPedidos) {
                            %>
                            <tr>
                                <td><%=pedido.getCodPedido()%></td>
                                <td><%=clientedao.consultaCliente(pedido.getCodCliente())%></td>
                                <td>
                                    <button autofocus="true" type="button" class="btn btn-info btn-block" data-toggle="modal" data-target="#listarModal<%=pedido.getCodPedido()%>">Exibir</button>
                                </td>
                                <td><%=pedido.getDataPedidoStr()%></td>
                                <td>R$ <%=pedido.getValorTotal()%></td>
                                <td><button autofocus="true" type="button" class="abrir-alterarModal btn btn-success btn-block" data-toggle="modal" data-target="#alterarModal" data-id="<%=pedido.getCodPedido()%>" ><%=pedido.getStatusPedido()%></button></td>
                            </tr>                      
                            <%}%>                            
                        </tbody>  
                    </table>                                                            
                </div>
                <% for (Pedido pedido : listaPedidos) {
                %>
                <div id="listarModal<%=pedido.getCodPedido()%>" class="modal fade" role="dialog">
                    <div class="modal-dialog">                            
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Produtos - Pedido Nº <%=pedido.getCodPedido()%></h4>
                            </div>
                            <div class="modal-body"> 
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <th>Quantidade</th>
                                                <th>Produto</th>
                                                <th>Preço Unid.</th>
                                                <th>Preço Total</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% Produto listaProdutos[] = pedidodao.carregaItemPedido(pedido.getCodPedido());
                                                for (Produto prod : listaProdutos) {%>
                                            <tr>
                                                <td><%=pedidodao.carregaQtdPedido(pedido.getCodPedido(), prod.getCodProduto())%></td>
                                                <td><%=prod.getNomeProduto()%></td>
                                                <td>R$ <%=prod.getPrecoProduto()%></td>
                                                <td>R$ <%=pedidodao.carregaQtdPedido(pedido.getCodPedido(), prod.getCodProduto())*prod.getPrecoProduto()%></td>
                                            </tr>
                                            <%}%>
                                        </tbody>
                                    </table>
                                </div>                                                                                                
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
                <div id="alterarModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">                            
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Alterar Status do Pedido</h4>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal" method="post" action="PedidoController">
                                    <select id="alterarCombo" name="alterarCombo" class="form-control">
                                        <option value="Realizado">Realizado</option>
                                        <option value="Processando">Processando</option>
                                        <option value="Enviado">Enviado</option>
                                        <option value="Fechado">Fechado</option>
                                    </select>                                            
                                    <input type="hidden" name="codPedido" id="codPedido" value=""/>
                                    <div class="btn center-block">
                                        <button id="alterar" name="button" value="alterar" class="btn btn-primary">Alterar Status</button>
                                    </div>
                                </form>           

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
                <script type="text/javascript">

                    $(document).on("click", ".abrir-alterarModal", function () {
                        var codPedido = $(this).data('id');
                        $(".modal-body #codPedido").val(codPedido);
                    });
                    RowSorter("#tabelaPedidos");
                </script>

            </div>
        </div>

    </div>
</div>
</body>


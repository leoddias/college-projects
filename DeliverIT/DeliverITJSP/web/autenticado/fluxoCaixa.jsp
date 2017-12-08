<%@page import="br.pucpcaldas.inf.lc.deliverit.model.FluxoCaixa"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.dao.FluxoCaixaDAO"%>
<%  String movimentacao;
    float fluxo = 0;
    FluxoCaixaDAO fluxodao = new FluxoCaixaDAO((MySqlController) session.getAttribute("conexao"));
    FluxoCaixa listaRegistros[] = null;
    String listaDatas[] = fluxodao.carregaDatas();
    String dataFluxo = "";
    if(request.getAttribute("dataBusca") == null){
        listaRegistros= fluxodao.carregaRegistros(listaDatas[0]);
        dataFluxo = listaDatas[0];
    }else{
        listaRegistros = fluxodao.carregaRegistros(request.getAttribute("dataBusca").toString());
        dataFluxo = request.getAttribute("dataBusca").toString();
    }
%>
<body>
    <div id="wrapper">
        <jsp:include page="../WEB-INF/incluir/menu.jsp" />
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Fluxo de Caixa <small>-</small> <%=dataFluxo%>
                        </h1>
                    </div>
                </div>
                <form class="form-horizontal" method="post" action="FluxoCaixaController">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="textinput">Descrição:</label>  
                            <div class="col-md-3">
                                <input id="cadastrar" name="descricao" placeholder="Descreva a operação realizada" class="form-control input-md" required="" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="movimentacao">Movimentação:</label>
                            <div class="col-lg-2">
                                <select id="movimentacao" name="movimentacao" class="form-control">
                                    <option value="entrada">Entrada</option>
                                    <option value="saida">Saída</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="textinput">Valor(R$):</label>  
                            <div class="col-md-3">
                                <input id="valor" name="valor" placeholder="Valor de entrada/saída" class="form-control input-md" required="" type="text">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-4 control-label" for="registrar"></label>
                            <div class="col-md-4">
                                <button id="registrar" name="button" value="registrar" class="btn btn-success">Registrar</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
                <div class="row">
                    <form  method="post" action="FluxoCaixaController">
                        <div class="form-group">
                            <div class="col-lg-9"></div>
                            <div class="col-md-2">                                
                                <select id="dataBusca" name="dataBusca" class="form-control" required="">                                    
                                    <%
                                        for (String date : listaDatas) {
                                    %>
                                    <option value="<%=date%>"><%=date%></option>
                                    <%}%>
                                </select>
                            </div>
                            <button id="visualizar" name="button" value="visualizar" class="btn btn-primary">Visualizar</button>
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
                <table id="tabelaFluxo">
                    <thead>
                        <tr>
                            <th>Horário </th>
                            <th>Movimentação </th>
                            <th>Valor </th>
                            <th>Descrição </th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for (FluxoCaixa registro : listaRegistros) {
                                if (registro.getMovimentacao() == true) {
                                    movimentacao = "Entrada";
                                    fluxo += registro.getValor();
                                } else {
                                    movimentacao = "Saída";
                                    fluxo -= registro.getValor();
                                }
                        %>
                        <tr>
                            <td><%=registro.getData()%></td>
                            <td><%=movimentacao%></td>
                            <td>R$ <%=registro.getValor()%></td>
                            <td><%=registro.getDescricao()%></td>
                        </tr>
                        <%}%>
                    </tbody>
                </table>
                <h3>Total: R$ <%=fluxo%></h3>
            </div>
        </div>
        <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
    </div>
</div>
<script>
    $('.collapse').collapse();

</script>
</body>

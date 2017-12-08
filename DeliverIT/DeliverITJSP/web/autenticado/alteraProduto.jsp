<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Categoria"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.dao.CategoriaDAO"%>
<body>
    <%  CategoriaDAO categoriadao = new CategoriaDAO((MySqlController) session.getAttribute("conexao"));
        Categoria listaCategorias[] = categoriadao.carregaCategorias();
    %>
    <div id="wrapper">
        <jsp:include page="../WEB-INF/incluir/menu.jsp" />
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <form class="form-horizontal" method="post" action="ProdutoController">
                        <fieldset>                           
                            <legend>Alterar Produto</legend>                            
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="nomeProduto">Nome do Produto</label>  
                                <div class="col-md-4">                                                                   
                                    <input id="nomeProduto" name="nomeProduto" placeholder="Digite o nome do produto" class="form-control input-md" required="" type="text" value="<%=request.getAttribute("nomeProduto")%>">
                                </div>
                            </div>                            
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="categoriaProduto">Categoria</label>
                                <div class="col-md-4">
                                    <select id="categoriaProduto" name="categoriaProduto" class="form-control" required="" >
                                        <%
                                            for (Categoria cat : listaCategorias) {
                                        %>
                                        <option value="<%=cat.getCodigo()%>"><%=cat.getNome()%></option>
                                        <%}%>
                                    </select>
                                </div>
                            </div>                            
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="precoProduto">Preço</label>
                                <div class="col-md-4">
                                    <div class="input-group">
                                        <span class="input-group-addon">R$</span>
                                        <input id="precoProduto" name="precoProduto" class="form-control" placeholder="" required="" type="number" min="0" step="1" data-bind="value:replyNumber" value="<%=request.getAttribute("precoProduto")%>">
                                    </div>
                                </div>
                            </div>                            
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="quantidadeProduto">Quantidade</label>  
                                <div class="col-md-4">
                                    <input id="quantidadeProduto" name="quantidadeProduto" placeholder="Digite a quantidade em estoque" class="form-control input-md" required="" type="text" value="<%=request.getAttribute("quantidadeProduto")%>">
                                </div>    
                            </div>
                            <div class="col-md-5"></div>
                            <div class="col-md-4 ">      
                                <input type="hidden" name="codProduto" value="<%=request.getAttribute("codProduto")%>">
                                <button id="alterar" name="button" value="alterar" class="btn btn-primary">Alterar</button>
                                <a href="produto.jsp" class="btn btn-success">Voltar</a>
                            </div>
                        </fieldset>
                    </form>                   
                </div>
                <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />  <br /><br />
            </div>

        </div>

    </div>    

    <script>
        $('.collapse').collapse()
    </script>
</body>


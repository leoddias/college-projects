<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Produto"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Categoria"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.dao.CategoriaDAO"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.dao.ProdutoDAO"%>
<body>
    <%   CategoriaDAO categoriadao = new CategoriaDAO((MySqlController) session.getAttribute("conexao"));
        ProdutoDAO produtodao = new ProdutoDAO((MySqlController) session.getAttribute("conexao"));
        Categoria listaCategorias[] = categoriadao.carregaCategorias();
        Produto listaProduto[] = produtodao.carregaProduto();
    %>
    <div id="wrapper">
        <jsp:include page="../WEB-INF/incluir/menu.jsp" />
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <form class="form-horizontal" method="post" action="ProdutoController">
                        <fieldset>                            
                            <legend>Cadastro de Produto</legend>                            
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="nomeProduto">Nome do Produto</label>  
                                <div class="col-md-4">
                                    <input id="nomeProduto" name="nomeProduto" placeholder="Digite o nome do produto" class="form-control input-md" required="" type="text">
                                </div>
                            </div>                            
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="categoriaProduto">Categoria</label>
                                <div class="col-md-4">
                                    <select id="categoriaProduto" name="categoriaProduto" class="form-control" required="">
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
                                        <input id="precoProduto" name="precoProduto" class="form-control" placeholder="" required="" type="number" min="0" step="1" data-bind="value:replyNumber">
                                    </div>
                                </div>
                            </div>                            
                            <div class="form-group">
                                <label class="col-md-4 control-label" for="quantidadeProduto">Quantidade</label>  
                                <div class="col-md-4">
                                    <input id="quantidadeProduto" name="quantidadeProduto" placeholder="Digite a quantidade em estoque" class="form-control input-md" required="" type="text">
                                </div>
                            </div> 
                            <div class="form-group">
                                <label class="col-md-4 control-label">Imagem do produto 300x180</label>  
                                <div class="input-group col-md-4">
                                    <input id="imagemProduto" name="imagemProduto" class="file" required="" type="file">
                                </div>
                            </div>
                            <div class="control-label col-md-6">   
                                <button id="cadastrar" name="button" value="cadastrar" class="btn btn-primary">Cadastrar</button>
                            </div>
                        </fieldset>
                    </form>
                    <br /><br />
                    <legend>Produtos</legend>
                    <% for (Produto prod : listaProduto) {%>
                    <div class="col-md-4 portfolio-item">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><%=prod.getNomeProduto()%></h3>
                            </div>
                            <div class="panel-body">
                                <a href="#">
                                    <img class="img-responsive img-thumbnail" src="http://placehold.it/300x180" alt="">
                                </a>

                                <p>Categoria: <%=categoriadao.consultaCategoria(prod.getCodCategoria())%> </p>                        
                                <p>Preço: <%=prod.getPrecoProduto()%></p>
                                <p>Estoque: <%=prod.getQtdEstoque()%></p>
                                <a href="ProdutoController?action=Alterar&codProduto=<%=prod.getCodProduto()%>&nomeProduto=<%=prod.getNomeProduto()%>&precoProduto=<%=prod.getPrecoProduto()%>&quantidadeProduto=<%=prod.getQtdEstoque()%>&categoriaProduto=<%=prod.getCodCategoria()%>" class="btn btn-success">Alterar</a>
                                <a href="ProdutoController?action=Excluir&codProduto=<%=prod.getCodProduto()%>" class="btn btn-danger">Excluir</a>
                            </div>
                        </div>
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </div> 
    <script>
        $('.collapse').collapse()
    </script>
</body>
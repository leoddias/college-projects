<%@page import="br.pucpcaldas.inf.lc.deliverit.model.Categoria"%>
<%@page import="br.pucpcaldas.inf.lc.deliverit.dao.CategoriaDAO"%>
<body>
    <%
        CategoriaDAO categoriadao = new CategoriaDAO((MySqlController) session.getAttribute("conexao"));
        Categoria listaCategorias[] = categoriadao.carregaCategorias();
    %>
    <div id="wrapper">
        <jsp:include page="../WEB-INF/incluir/menu.jsp" />
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Categoria <small>-</small>
                        </h1>
                    </div>
                </div>
                <div class="col-md-1"></div>
                <div class="col-md-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Nova Categoria</h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" method="post" action="CategoriaController">
                                <fieldset>                                
                                    <input id="cadastrar" name="cadastrar" placeholder="Insira o nome da categoria" class="form-control input-md" required="" type="text"> <br />
                                    <button id="cadastrar" name="button" value="cadastrar" class="btn btn-primary">Cadastrar</button>                                                                         
                                </fieldset>
                            </form>
                        </div>
                    </div>                                    
                </div>
                <div class="col-md-2"></div>
                <div class="col-md-4">
                    <div class="panel panel-red">
                        <div class="panel-heading">
                            <h3 class="panel-title">Alterar Categoria</h3>
                        </div>
                        <div class="panel-body">
                            <form class="form-horizontal" method="post" action="CategoriaController">
                                <fieldset>
                                    <select id="alterarCombo" name="alterarCombo" class="form-control">
                                        <%
                                            for (Categoria cat : listaCategorias) {
                                        %>
                                        <option value="<%=cat.getCodigo()%>"><%=cat.getNome()%></option>
                                        <%}%>
                                    </select>
                                    <input id="novoNome" name="novoNome" placeholder="Insira o novo nome" class="form-control input-md" type="text">                                    
                                    <br />                                       
                                    <button id="alterar" name="button" value="alterar" class="btn btn-primary">Alterar</button>
                                    <button id="remover" name="button" value="remover" class="btn btn-danger">Remover</button>

                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>                                        
            </div>
                                    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />  
        </div>
        <script>
            $('.collapse').collapse()
        </script>
</body>
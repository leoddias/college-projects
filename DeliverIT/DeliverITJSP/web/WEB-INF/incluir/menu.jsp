<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="index.jsp">Deliver IT!</a>
    </div>        
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%=session.getAttribute("usuario")%> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#"><i class="fa fa-fw fa-gear"></i> Configurações</a>
                </li>
                <li class="divider"></li>
                <li>
                    <form action="LogoutController" method="post"><i class="fa fa-fw fa-power-off"></i><input type="submit" value="Logout"></form>
                </li>
            </ul>
        </li>
    </ul>        
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <li>
                <a href="index.jsp"><i class="fa fa-fw fa-desktop"></i> Pedidos</a>
            </li>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#cad"><i class="fa fa-fw fa-arrows-v"></i> Cadastro<i class="fa fa-fw fa-caret-down"></i></a>
                <ul id="cad" class="collapse">
                    <li>
                        <a href="categoria.jsp">Categoria de Produtos</a>
                    </li>
                    <li>
                        <a href="produto.jsp">Produtos</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-edit"></i> Regras de Negócio</a>
            </li>
            <li>
                <a href="fluxoCaixa.jsp"><i class="fa fa-fw fa-wrench"></i> Fluxo de Caixa</a>
            </li>      
            <li>
                <a href="#"><i class="fa fa-fw fa-bar-chart-o"></i> Relatórios</a>
            </li>
            <li>
                <a href="#"><i class="fa fa-fw fa-file"></i> ITEM</a>
            </li>
        </ul>
    </div>        
</nav>

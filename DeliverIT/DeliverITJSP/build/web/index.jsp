<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>- Deliver IT! -</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="resources/bootstrap/css/login.css" rel="stylesheet">             
    </head>
    <body>
        <style>body{
                background: url(resources/bootstrap/images/back.png);
                background-color: #444;
                background: url(resources/bootstrap/images/pinlayer2.png),url(resources/bootstrap/images/pinlayer1.png),url(resources/bootstrap/images/back.png);    
            }
        </style>
        <div class="container">            
            <form class="form-login" method="post" action="LoginController">
                <h2 class="form-login-heading">Deliver IT!</h2>                                    
                <div class="form-group">
                    <input name="usuario" type="usuario" id="inputUsuario" class="form-control" placeholder="Usuario" required autofocus>                    
                </div>
                <div class="form-group">
                    <input name="senha" type="password" id="inputSenha" class="form-control" placeholder="Senha" required>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit"> Login </button>
            </form>
        </div>
    </body>
</html>

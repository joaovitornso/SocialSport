<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
    <link rel="stylesheet" href="https://cdn.es.gov.br/fonts/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/admin.css">
</head>
<body>
    <div class="backgroundInterface" style="position: absolute; top: 0px; width:100%; height: autopx; display:block;">
        <div class="container">
            <div class="container_content">  
                <div class="container_mainInner">
                    <div class="container_main">
                        <div class="cabecalho">
                            <h1 class="logo_social-sport">Social Sport - Admin</h1>
                           
                        </div>
                        
                        <div class="img-admin"> <img src="imagens/admin-icon2.png"> <a href="ControllerLogout"><button>Sair</button></a></div>
                        <div class="container_main-content">
                            <h1>Gerenciamento de locais</h1>
                            <p>Gerencie os locais em que as partidas acontecerão, crie novos locais.<br>Se deseja gerenciar locais, clique no botão</p>
                            <a href="local"><button>Gerenciar locais</button></a>
                            <h1>Gerenciamento de jogadores</h1>
                            <p>Se deseja gerenciar jogadores, clique no botão</p>
                            <a href="selecionarJogador"><button>Gerenciar Jogadores</button></a>
                           <!--  <h1>Gerenciamento de Times</h1>
                            <p>Se deseja gerenciar times, clique no botão </p>
                            <a href="time"><button>Gerenciar Times</button></a>
                            <h1>Gerenciamento de Campeonatos</h1>
                            <p>Se deseja gerenciar campeonatos, clique no botão </p>
                            <button>Gerenciar Campeonatos</button>
                            <h1>Gerenciamento de Ligas</h1>
                            <p>Se deseja gerenciar ligas, clique no botão </p>
                            <button>Gerenciar Ligas</button> -->
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    
</body>
</html>
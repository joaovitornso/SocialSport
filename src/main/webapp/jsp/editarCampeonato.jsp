<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Campeonato</title>
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="css/stile.css">
</head>
<body>
	<h1>Editar Campeonato</h1>
	<form name="frmCampeonato" action="updateCampeonato">
		<fieldset id="usuario">
			<legend> Dados do Campeonato</legend>
			<p>
				<label for=""> Id do campeonato: </label><input type="text" name="idCampeonato"
					class="Caixa1" readonly value="<%out.print(request.getAttribute("idCampeonato"));%>">
			</p>
			<p>
				<label for=""> Nome: </label><input type="text" name="nome"
					class="Caixa1" value="<%out.print(request.getAttribute("nome"));%>">
			</p>
			<p>
				<label for=""> Medalha: </label><input type="text"
					name="medalha" class="Caixa1" value="<%out.print(request.getAttribute("medalha"));%>">
			</p>
			
		</fieldset>
		<input class="Botao" type="button" value="Salvar"
			onclick="validarCampeonato()">
	</form>
	<script src="scripts/validador.js"></script>
	

</body>
</html>
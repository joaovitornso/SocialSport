<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar | Time</title>
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="css/form.css">
<script src="scripts/validador.js"></script>
</head>
<body>
	<h1>Editar Time</h1>
	<form name="frmTime" action="update2">
		<fieldset id="usuario">
			<legend> Dados do Time</legend>
			<p>
				<label></label><input type="text" name="idTime"
					id="caixa2" size="15" maxlength="30" readonly value="<%out.print(request.getAttribute("idTime"));%>">
			</p>
			<p>
				<label for="cNome"></label><input type="text" name="nome"
					id="cNome" size="15" maxlength="30" value="<%out.print(request.getAttribute("nome"));%>">
			</p>
			<p>
				<label for="cQntJogadores"></label><input
					type="number" name="qntJogadores" id="cQntJogadores" size="8"
					maxlength="8" value="<%out.print(request.getAttribute("qntJogadores"));%>">
			</p>
		</fieldset>
		<input class="botao1" type="button" value="Salvar" onclick="validarTime()">
	</form>
</body>
</html>
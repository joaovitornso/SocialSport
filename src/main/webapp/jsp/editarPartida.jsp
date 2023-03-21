<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar | Partida</title>
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="css/form.css">
</head>
<body>
	<h1>Editar Local</h1>
	<form name="frmPartida" action="updatePartida">
		<fieldset id="usuario">
			<legend> Dados da Partida</legend>
			<p>
				<label></label><input type="text" name="idPartida" id="caixa2"
					readonly value="<%out.print(request.getAttribute("idPartida"));%>">
			</p>
			<p>
				<label for="cData"></label><input type="text" name="data" id="cData"
					value="<%out.print(request.getAttribute("data"));%>">
			</p>
			<p>
				<label for="cHorario"></label><input type="text" name="horario" id="cHorario"
					value="<%out.print(request.getAttribute("horario"));%>">
			</p>
		</fieldset>
		<input class="botao1" type="button" value="Salvar"
			onclick="validarPartida()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>
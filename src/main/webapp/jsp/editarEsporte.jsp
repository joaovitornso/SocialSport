<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar | Esporte</title>
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="css/form.css">
</head>
<body>
	<h1>Editar Esporte</h1>
	<form name="frmEsporte" action="updateEsporte">
		<fieldset id="usuario">
			<legend> Dados da Esporte</legend>
			<p>
				<label></label><input type="text" name="idEsporte" id="caixa2"
					readonly value="<%out.print(request.getAttribute("idEsporte"));%>">
			</p>
			<p>
				<label for="cNome"></label><input type="text" name="nome" id="cNome"
					value="<%out.print(request.getAttribute("nome"));%>">
			</p>
			<p>
				<label for="cDuracao"></label><input type="text" name="duracao" id="cDuracao"
					value="<%out.print(request.getAttribute("duracao"));%>">
			</p>
			<p>
				<label for="cDescricao"></label><input type="text" name="descricao" id="cDescricao"
				 maxlength="200"value="<%out.print(request.getAttribute("descricao")) ;%>">
			</p>
		</fieldset>
		<input class="botao1" type="button" value="Salvar"
			onclick="validarEsporte()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>
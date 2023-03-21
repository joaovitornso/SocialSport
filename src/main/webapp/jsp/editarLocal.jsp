<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Editar | Local</title>
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="css/form.css">
</head>
<body>
	<h1>Editar Local</h1>
	<form name="frmLocal" action="updateLocal">
		<fieldset id="usuario">
			<legend> Dados do Local</legend>
			<p>
				<label></label><input type="text" name="idLocal" id="caixa2"
					readonly value="<%out.print(request.getAttribute("idLocal"));%>">
			</p>
			<p>
				<label for="cRua"></label><input type="text" name="rua" id="cRua"
					value="<%out.print(request.getAttribute("rua"));%>">
			</p>
			<p>
				<label for="cBairro"></label><input type="text" name="bairro" id="cBairro"
					value="<%out.print(request.getAttribute("bairro"));%>">
			</p>
			<p>
				<label for="cCidade"></label><input type="text" name="cidade" id="cCidade"
					value="<%out.print(request.getAttribute("cidade"));%>">
			</p>
			<p>
				<label for="cTipo"></label><input type="text" name="tipo" id="cTipo"
					value="<%out.print(request.getAttribute("tipo"));%>">
			</p>
		</fieldset>
		<input class="botao1" type="button" value="Salvar"
			onclick="validarLocal()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>
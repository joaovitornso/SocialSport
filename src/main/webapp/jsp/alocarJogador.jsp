<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Jogador"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Time"%>
<%
ArrayList<Jogador> lista = (ArrayList<Jogador>) request.getAttribute("jogadores"); // recebe o objeto lista
%>
<%
ArrayList<Time> lista2 = (ArrayList<Time>) request.getAttribute("times"); // recebe o objeto lista
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Jogador | Alocar Jogador</title>
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="css/form.css">
<script src="scripts/validador.js"></script>
<style type="text/css">
#select_left{
	margin-left: 100px;
}
</style>
</head>
<body>
	<form name="frmAlocacao" action="alocar">
		<h1>Alocar Jogador em um time</h1>
		<fieldset id="usuario">
			<legend> Alocação </legend>
			<select name="jogadorAlocado" class="select" required="required">
				<option value="" disabled selected>Selecione o jogador</option>
				<%
				for (int i = 0; i < lista.size(); i++) {
				%>
				<option value="<%=lista.get(i).getIdjogador()%>"><%=lista.get(i).getNome()%></option>
				<%
				}
				%>

			</select> <select id = "select_left"name="timeAlocado" class="select" required="required">
				<option value="" disabled selected>Selecione o time</option>
				<%
				for (int j = 0; j < lista2.size(); j++) {
				%>
				<option value="<%=lista2.get(j).getIdTime()%>"><%=lista2.get(j).getNome()%></option>
				<%
				}
				%>

			</select>
		</fieldset>
		<button type="submit">Alocar</button>
		<!-- <input class="botao1" type="button" value="Alocar" onclick="validarAlocacao()"> -->
	</form>
</body>
</html>




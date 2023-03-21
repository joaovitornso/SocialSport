<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Liga"%>

<%
Liga liga = (Liga) request.getAttribute("liga");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exibição | Liga</title>
<link rel="stylesheet" href="css/stile.css">
<style>
.negrito {
	color: black;
}
</style>
</head>
<body>
	<div>
		<h1>DADOS DO CAMPEONATO</h1>

		<h2>NOME:</h2>
		<h2 class="negrito">
			<%=liga.getNome()%>
		</h2>
		<h2>Medalha:</h2>
		<h2 class="negrito">
			<%=liga.getMedalhas()%>
		</h2>

		<h1>PARTIDA 1</h1>
		<h2>O TIME:</h2>
		<h2 class="negrito"><%=liga.getTimes().get(0).getNome()%>
		</h2>
		<h2>JOGARA CONTRA:</h2>
		<h2 class="negrito">
			<%=liga.getTimes().get(1).getNome()%>
		</h2>
		<h1>PARTIDA 2</h1>
		<h2>O TIME:</h2>
		<h2 class="negrito"><%=liga.getTimes().get(2).getNome()%></h2>
		<h2>JOGARA CONTRA O TIME</h2>
		<h2 class="negrito"><%=liga.getTimes().get(3).getNome()%></h2>

		<h1>PARTIDA 3</h1>
		<h2>O TIME:</h2>
		<h2 class="negrito"><%=liga.getTimes().get(4).getNome()%></h2>
		<h2>JOGARA CONTRA O TIME</h2>
		<h2 class="negrito"><%=liga.getTimes().get(5).getNome()%></h2>

		<h1>PARTIDA 4</h1>
		<h2>O TIME:</h2>
		<h2 class="negrito"><%=liga.getTimes().get(6).getNome()%></h2>
		<h2>JOGARA CONTRA O TIME</h2>
		<h2 class="negrito"><%=liga.getTimes().get(7).getNome()%></h2>

		<p>
			<a href="liga" class="botaoNovo">Voltar pra página inicial </a>
		</p>

	</div>

</body>
</html>
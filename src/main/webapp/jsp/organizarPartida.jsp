<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Esporte"%>
<%@ page import="model.Partida"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Time"%>
<%
ArrayList<Esporte> listaEsporte = (ArrayList<Esporte>) request.getAttribute("esportes"); // recebe o objeto lista
%>
<%
ArrayList<Time> listaTime = (ArrayList<Time>) request.getAttribute("times"); // recebe o objeto lista
%>
<%
ArrayList<Time> listaTime2 = (ArrayList<Time>) request.getAttribute("times2"); // recebe o objeto lista
%>
<%
ArrayList<Partida> listaPartida = (ArrayList<Partida>) request.getAttribute("partidas"); // recebe o objeto lista
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Partida | Organizar Partida</title>
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="css/form.css">
<script src="scripts/validador.js"></script>
</head>
<body>
	<h1>Organizar uma Partida</h1>
	<form name="frmOrganizacao" action="organizarPartida">

		<fieldset id="usuario">
			<legend> Organização</legend>
			<select name="time1" class="select" required="required">
				<option value="" disabled selected>Selecione o primeiro
					time</option>
				<%
				for (int i = 0; i < listaTime.size(); i++) {
				%>
				<option value="<%=listaTime.get(i).getIdTime()%>"><%=listaTime.get(i).getNome()%></option>
				<%
				}
				%>
			</select> <select name="time2" class="select" required="required">
				<option value="" disabled selected>Selecione o segundo time</option>
				<%
				for (int i = 0; i < listaTime2.size(); i++) {
				%>
				<option value="<%=listaTime2.get(i).getIdTime()%>"><%=listaTime2.get(i).getNome()%></option>
				<%
				}
				%>
			</select> <select name="esporte" class="select" required="required">
				<option value="" disabled selected>Selecione um esporte</option>
				<%
				for (int j = 0; j < listaEsporte.size(); j++) {
				%>
				<option value="<%=listaEsporte.get(j).getIdEsporte()%>"><%=listaEsporte.get(j).getNome()%></option>
				<%
				}
				%>
			</select> <select name="partida" class="select" required="required">
				<option value="" disabled selected>Selecione o id da
					partida</option>
				<%
				for (int k = 0; k < listaPartida.size(); k++) {
				%>
				<option value="<%=listaPartida.get(k).getIdPartida()%>"><%=listaPartida.get(k).getIdPartida()%></option>
				<%
				}
				%>
			</select>
		</fieldset>
		<input class="botao1" type="button" value="Organizar"
			onclick="validarOrganizacao()">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
int idLiga = (int) request.getAttribute("idLiga");
%>
<%-- <%String aux1 = (String) request.getAttribute("aux1"); %>
<%String aux2 = (String) request.getAttribute("aux2"); %>

<% String idTimeVencedorPartida1 = (String) session.getAttribute("timeVencedorPartida1");
String idTimeVencedorPartida2 = (String) session.getAttribute("timeVencedorPartida2"); --%>
<%-- <%if(aux1 != null && aux1.equals("1")){ %>

	<%idTimeVencedorCasaPartida1 =  (String) request.getAttribute("idTimeVencedorCasaPartida1");%>
	<%idTimeVencedorForaPartida1  =  (String) request.getAttribute("idTimeVencedorFora");%>
	
	<%System.out.print( "id 1:"+ idTimeVencedorCasaPartida1); %>
	<%System.out.print("id 2:" + idTimeVencedorCasaPartida2); %>

<%}else if(aux2 != null && aux2.equals("2")){%>

	<%idTimeVencedorCasaPartida2 =  (String) request.getAttribute("idTimeVencedorCasaPartida2");%>
	<%idTimeVencedorForaPartida2  =  (String) request.getAttribute("idTimeVencedorForaPartida2");%>

<%}%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/stile.css">
<title>Liga | Partidas</title>
</head>
<body>
	<a href="ControllerLogout">Sair</a>
	<h1>Organizar Partidas Quartas de final</h1>
	<p>
		<a href="organizarPrimeiraPartidaLiga?idLiga=<%=idLiga%>"
			class="BotaoAcesso">Organizar Primeira Partida</a>
	</p>
	<p>
		<a href="organizarSegundaPartidaLiga?idLiga=<%=idLiga%>"
			class="BotaoAcesso">Organizar Segunda Partida</a>
	</p>
	<p>
		<a href="organizarTerceiraPartidaLiga?idLiga=<%=idLiga%>"
			class="BotaoAcesso">Organizar Terceira Partida</a>
	</p>
	<p>
		<a href="organizarQuartaPartidaLiga?idLiga=<%=idLiga%>"
			class="BotaoAcesso">Organizar Quarta Partida</a>
	</p>
	<h1>Semi Final</h1>
	<p>
		<a href="organizarQuintaPartidaLiga?idLiga=<%=idLiga%>"
			class="BotaoAcesso">Organizar Quinta Partida</a>
	</p>
	<p>
		<a href="organizarSextaPartidaLiga?idLiga=<%=idLiga%>" class="BotaoAcesso">Organizar
			Segunda Partida</a>
	</p>

	<h1>Final</h1>

	<p>
		<a class="BotaoAcesso" href="organizarPartidaFinalLiga?idLiga=<%=idLiga%>">Organizar
			Final do Liga</a>
	</p>

</body>
</html>
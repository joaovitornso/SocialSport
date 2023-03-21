<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% int idCampeonato = (int) request.getAttribute("idCampeonato");%>
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
<title>Campeonato | Partidas</title>
</head>
<body>
	<a href="ControllerLogout">Sair</a>
	<h2>Organizar Partidas</h2>
	<p>
		<a href="organizarPrimeiraPartida?idCampeonato=<%=idCampeonato%>"
			class="BotaoAcesso">Organizar Primeira Partida</a>
	</p>
	<p>
		<a href="organizarSegundaPartida?idCampeonato=<%=idCampeonato%>"
			class="BotaoAcesso">Organizar Segunda Partida</a>
	</p>

	<p >
	<a class="BotaoAcesso" 
			href="organizarPartidaFinal?idCampeonato=<%=idCampeonato%>">Organizar Final do Campeonato</a>
	</p>
		<p >
	<a class="BotaoAcesso" 
			href="aplicarPremiacao?idCampeonato=<%=idCampeonato%>">Aplicar as Premiação ao vencedor</a>
	</p>

</body>
</html>
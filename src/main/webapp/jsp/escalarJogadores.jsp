<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Jogador"%>
<%@ page import="java.util.ArrayList"%>
<%-- <%@ page import="model.Time"%> --%>
<%@ page import="model.Partida"%>
<%-- <%@ page import="model.Esporte"%> --%>
<%-- <% String idLocal = (String) request.getAttribute("idLocal");%> --%>
<%
Partida partida = (Partida) request.getAttribute("partida");
%>
<%-- <% String esporteTime = (String) request.getAttribute("esporteTime");%> --%>
<%-- <% Time time1 = (Time) request.getAttribute("time1"); %>
<% Time time2 = (Time) request.getAttribute("time2"); %> --%>
<%
ArrayList<Jogador> listaJogadoresTime1 = (ArrayList<Jogador>) request.getAttribute("jogadoresTime1"); // recebe o objeto lista
%>
<%
ArrayList<Jogador> listaJogadoresTime2 = (ArrayList<Jogador>) request.getAttribute("jogadoresTime2"); // recebe o objeto lista
%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Escalação</title>
<link rel="stylesheet" href="css/styleCheckBox.css">
<link rel="stylesheet" href="css/formNovo.css">
<script src="scripts/validador.js"></script>
</head>
<body >
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Escalar jogadores</div>
			<form name="frmEscalacao" action="validar">
				<div class="user-details user-detail_checkbox">
					<div class="container_checkbox">
							
							<h2><%=partida.getTime1().getNome()%></h2>
						<ul class="ks-cboxtags">
							<%
							//ArrayList<String> ListaJogadoresTime1Selecionados = new ArrayList<String>();
							%>
							<%
							for (int i = 0; i < listaJogadoresTime1.size(); i++) {
							%>
							<li><input
								id="<%=listaJogadoresTime1.get(i).getIdjogador()%>"
								type="checkbox" name="playersTime1"
								value="<%=listaJogadoresTime1.get(i).getIdjogador()%>"><label
								for="<%=listaJogadoresTime1.get(i).getIdjogador()%>"><%=listaJogadoresTime1.get(i).getNome()%></label></li>
						<%
						}
						%>
						</ul>
					</div>
					<div class="container_checkbox">
							<h2><%=partida.getTime2().getNome()%></h2>
						<ul class="ks-cboxtags">
							<%
							for (int i = 0; i < listaJogadoresTime2.size(); i++) {
							%>
							<li><input
								id="<%=listaJogadoresTime2.get(i).getIdjogador()%>"
								type="checkbox" name="playersTime2"
								class="checkboxJogadoresTime2"
								value="<%=listaJogadoresTime2.get(i).getIdjogador()%>"><label
								for="<%=listaJogadoresTime2.get(i).getIdjogador()%>"><%=listaJogadoresTime2.get(i).getNome()%></label></li>
						<%
						}
						%>
						</ul>
					</div>
				</div>
				<%-- <input type="hidden" name="time1" value="<%=time1.getIdTime()%>"></input>
			<input type="hidden" name="time2" value="<%=time2.getIdTime()%>"></input> --%>

				<input type="hidden" name="idPartida"
					value="<%=partida.getIdPartida()%>"></input>

				<%-- <input type="hidden" name="aux1" value="<%=aux1%>"></input>
			
			<input type="hidden" name="aux2" value="<%=aux2%>"></input> --%>
				<%-- <input type="hidden" name="idEsporteTime" value="<%=esporteTime%>"></input> --%>
				<%-- <input type="hidden" name="idLocal" value="<%=idLocal%>"></input> --%>

				<%-- <%System.out.println(esporteTime); %>
			<%System.out.println(idLocal); %>
			<%System.out.println(idPartida); %> --%>
				<div class="button-form">
					<input class="button_input" type="button" value="Escalar"
						onclick="validarEscalacao()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
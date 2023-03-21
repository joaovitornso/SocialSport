<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Time"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Time> listaTime1 = (ArrayList<Time>) request.getAttribute("esporteTimes");
%>
<%-- <%String idEsporte = (String) request.getAttribute("idEsporte"); %> --%>
<%-- <%String idLocal = (String) request.getAttribute("idLocal"); %> --%>
<%
String idPartida = (String) request.getAttribute("idPartida");
%>
<%
int idCampeonato = (int) request.getAttribute("idCampeonato");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Selecionar Times | Campeonato</title>
<link rel="stylesheet" href="css/form.css">
<link rel="stylesheet" href="css/formNovo.css">
<script src="scripts/validador.js"></script>
</head>
<body>
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Seleção de times do campeonato</div>
			<form name="frmTimesCampeonato" action="efetivarCampeonato">
				<div class="user-details">
					<div class="input-box">
						<span class="details">Primeiro Time</span>
						<div class="select_form">
							<select name="time1" class="select" required="required">
								<option value="" disabled selected>Selecione o primeiro
									time</option>
								<%
								for (int i = 0; i < listaTime1.size(); i++) {
								%>
								<option value="<%=listaTime1.get(i).getIdTime()%>"><%=listaTime1.get(i).getNome()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
					<div class="input-box">
						<span class="details">Segundo Time</span>
						<div class="select_form">
							<select name="time2" class="select" required="required">
								<option value="" disabled selected>Selecione o segundo
									time</option>
								<%
								for (int j = 0; j < listaTime1.size(); j++) {
								%>
								<option value="<%=listaTime1.get(j).getIdTime()%>"><%=listaTime1.get(j).getNome()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
					<div class="input-box">
						<span class="details">Terceiro Time</span>
						<div class="select_form">
							<select name="time3" class="select" required="required">
								<option value="" disabled selected>Selecione o terceiro
									time</option>
								<%
								for (int k = 0; k < listaTime1.size(); k++) {
								%>
								<option value="<%=listaTime1.get(k).getIdTime()%>"><%=listaTime1.get(k).getNome()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
					<div class="input-box">
						<span class="details">Quarto Time</span>
						<div class="select_form">
							<select name="time4" class="select" required="required">
								<option value="" disabled selected>Selecione o quarto
									time</option>
								<%
								for (int l = 0; l < listaTime1.size(); l++) {
								%>
								<option value="<%=listaTime1.get(l).getIdTime()%>"><%=listaTime1.get(l).getNome()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
				</div>
				<input name="idCampeonato" type="hidden" value="<%=idCampeonato%>">
				<%-- input name = "idLocal" type = "hidden" value="<%=idLocal%>">  --%>
				<%-- <input name = "idPartida" type = "hidden" value="<%=idPartida%>"> --%>
				<!-- <input class="Botao" type="button" value="Adicionar"
			onclick="validarTime()"> 
 -->
				<div class="button-form">
					<input class="button_input" type="button" value="Organizar"
						onclick="validarTimesCampeonato()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
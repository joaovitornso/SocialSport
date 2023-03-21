<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Esporte"%>
<%@ page import="model.Local"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Esporte> listaEsporte = (ArrayList<Esporte>) request.getAttribute("listaEsportes");
%>
<%
ArrayList<Local> listaLocais = (ArrayList<Local>) request.getAttribute("listaLocal");
%>
<%
String idPartida = (String) request.getAttribute("idPartida");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Selecionar Esporte e Local da partida</title>
<link rel="stylesheet" href="css/formNovo.css">
<script src="scripts/validador.js"></script>
</head>
<body>

	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Selecione o esporte e o local da partida</div>
			<form name="frmEsporteLocal" action="selecionarTimes">
				<div class="user-details">
					<div class="input-box">
						<span class="details">Esporte da Partida</span>
						<div class="select_form">
							<select name="esporteTime" class="select" required="required">
								<option value="" disabled selected>Selecione o Esporte</option>
								<%
								for (int i = 0; i < listaEsporte.size(); i++) {
								%>
								<option value="<%=listaEsporte.get(i).getIdEsporte()%>"><%=listaEsporte.get(i).getNome()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
					<div class="input-box">
						<span class="details">Local da Partida</span>
						<div class="select_form">
							<select id="select_left" name="local" class="select"
								required="required">
								<option value="" disabled selected>Selecione o Local</option>
								<%
								for (int j = 0; j < listaLocais.size(); j++) {
								%>
								<option value="<%=listaLocais.get(j).getIdLocal()%>"><%=listaLocais.get(j).getTipo()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
				</div>
				<input name="idPartida" type="hidden" value="<%=idPartida%>">
				<div class="button-form">
					<input class="button_input" type="submit" value="Adicionar"
						onclick="validarEsporteLocal()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
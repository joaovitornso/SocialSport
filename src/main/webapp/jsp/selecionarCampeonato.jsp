<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Campeonato"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Campeonato> lista = (ArrayList<Campeonato>) request.getAttribute("campeonatos"); // recebe o objeto lista
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Campeonato | Selecionar Campeonato</title>
<link rel="stylesheet" href="css/formNovo.css">
<script src="scripts/validador.js"></script>
</head>
<body>
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Selecionar o campeonato</div>
			<form name="frmSelecaoCampeonato" action="realizarCampeonato">
				<div class="user-details">
					<div class="input-box">
						<span class="details">Campeonato</span>
						<div class="select_form">
							<select name="campeonatoRealizado" class="select"
								required="required">
								<option value="" disabled selected>Selecione o
									Campeonato</option>
								<%
								for (int i = 0; i < lista.size(); i++) {
								%>
								<option value="<%=lista.get(i).getId()%>"><%=lista.get(i).getNome()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
				</div>
				<div class="button-form">
					<input class="button_input" type="button" value="Escolher Campeonato"
						onclick="validarCampeonatoRealizado()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
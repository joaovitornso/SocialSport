<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Liga"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Liga> lista = (ArrayList<Liga>) request.getAttribute("ligas"); // recebe o objeto lista
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liga | Selecionar Liga</title>
<link rel="stylesheet" href="css/formNovo.css">
<script src="scripts/validador.js"></script>
</head>
<body>
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Selecionar a liga</div>
			<form name="frmSelecaoLiga" action="realizarLiga">
				<div class="user-details">
					<div class="input-box">
						<span class="details">Liga</span>
						<div class="select_form">
							<select name="ligaRealizada" class="select" required="required">
								<option value="" disabled selected>Selecione a liga</option>
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
					<input class="button_input" type="submit" value="Escolher Liga"
						onclick="validarLigaRealizado()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
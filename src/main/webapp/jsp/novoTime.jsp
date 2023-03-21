<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Esporte"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Esporte> listaEsporte = (ArrayList<Esporte>) request.getAttribute("esportes");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Novo time</title>
<link rel="stylesheet" href="css/formNovo.css">
<script src="scripts/validador.js"></script>

</head>
<body>
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Registro de time</div>
			<form name="frmTime" action="insert2">
				<div class="user-details">
					<div class="input-box">
						<span class="details">Nome</span> <input type="text" name="nome"
							id="cNome" placeholder="Nome do time">
					</div>
					<div class="input-box">
						<span class="details">Esporte do Time</span>
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
				</div>
				<div class="button-form">
					<input class="button_input" value="Adicionar"
						onclick="validarTime()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>


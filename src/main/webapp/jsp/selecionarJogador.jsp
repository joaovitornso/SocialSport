<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Jogador"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Jogador> lista = (ArrayList<Jogador>) request.getAttribute("listaJogadores"); // recebe o objeto lista
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/admin-punicao.css">
<title>Punir Jogodaor</title>
</head>
<body>
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Registro de Punições</div>
			<form name="frmPunicao" action="aplicarPunicao">
				<div class="user-details button_input-adm">
					<div class="input-box">
						<span class="details">Selecione o jogador a ser punido</span>
						<p>A cada cinco punições o jogador é expulso do time ao qual
							pertence</p>
						<div class="select_form">
							<select name="jogadorPunido" class=select required="required">
								<option value="" disabled selected>Selecione o jogador</option>
								<%
								for (int i = 0; i < lista.size(); i++) {
								%>
								<option value="<%=lista.get(i).getIdjogador()%>"><%=lista.get(i).getNome()%></option>
								<%
								}
								%>
							</select>
						</div>
					</div>
				</div>
				<div class="button-form">
					<input class="button_input" class="button_input-adm" type="submit"
						value="Aplicar Punição">
				</div>

			</form>
		</div>
	</div>
</body>
</html>









</body>
</html>
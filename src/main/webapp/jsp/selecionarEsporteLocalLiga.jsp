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
int idLiga = (int) request.getAttribute("idLiga");
%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Selecionar esporte e local da liga</title>
<link rel="stylesheet" href="css/formNovo.css">
<script src="scripts/validador.js"></script>
</head>
<body>
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Selecione o esporte, local e as datas</div>
			<form name="frmEsporteLocalLiga" action="selecionarTimesLiga">
				<div class="user-details">
					<div class="input-box">
						<span class="details">Esporte da Liga</span>
						<div class="select_form">
							<select name="esporteLiga" class="select" required="required">
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
						<span class="details">Local da Liga</span>
						<div class="select_form">
							<select name="localLiga" class="select" required="required">
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

					<input name="idLiga" type="hidden" value="<%=idLiga%>">

					<div class="input-box">
						<span class="details">Data da primeira partida</span> <input
							name="dataLiga" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da primeira partida</span> <input
							name="horarioLiga" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da segunda partida</span> <input
							name="dataLiga2" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da segunda partida</span> <input
							name="horarioLiga2" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da terceira partida</span> <input
							name="dataLiga3" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da terceira partida</span> <input
							name="horarioLiga3" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da quarta partida</span> <input
							name="dataLiga4" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da quarta partida</span> <input
							name="horarioLiga4" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da quinta partida</span> <input
							name="dataLiga5" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da quinta partida</span> <input
							name="horarioLiga5" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da sexta partida</span> <input
							name="dataLiga6" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da sexta partida</span> <input
							name="horarioLiga6" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da partida final</span> <input
							name="dataLigaFinal" type="date">
					</div>

					<div class="input-box">
						<span class="details">Horario da partida final</span> <input
							name="horarioLigaFinal" type="text" placeholder="horario">
					</div>
				</div>
				<div class="button-form">
					<input class="button_input" type="submit" value="Adicionar"
						onclick="validarEsporteLocalLiga()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
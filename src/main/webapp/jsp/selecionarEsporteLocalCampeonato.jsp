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
int idCampeonato = (int) request.getAttribute("idCampeonato");
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
<style type="text/css">
</style>
</head>
<body>
	<div class="container_main-form">
		<div class="container_form">
			<div class="title">Selecione o esporte, local e as datas</div>
			<form name="frmEsporteLocalCampeonato"
				action="selecionarTimesCampeonato">
				<div class="user-details">
					<div class="input-box">
						<span class="details">Esporte do Campeonato</span>
						<div class="select_form">
							<select name="esporteCampeonato" class="select"
								required="required">
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
						<span class="details">Local do Campeonato</span>
						<div class="select_form">
							<select id="left" name="localCampeonato" class="select"
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
					<input name="idCampeonato" type="hidden" value="<%=idCampeonato%>">
					<div class="input-box">
						<span class="details">Data da primeira partida</span> <input
							name="dataCampeonato" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da primeira partida</span>
						<input name="horarioCampeonato" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da segunda partida</span>
							<input name="dataCampeonato2" type="date">
					</div>
					<div class="input-box">
						<span class="details">Horario da segunda partida</span>
						<input name="horarioCampeonato2" type="text" placeholder="horario">
					</div>
					<div class="input-box">
						<span class="details">Data da partida final</span> <input
							name="dataCampeonatoFinal" type="date">
					</div>

					<div class="input-box">
						<span class="details">Horario da partida final</span>
						<input name="horarioCampeonatoFinal" type="text"
							placeholder="horario">
					</div>
				</div>
				<div class="button-form">
					<input class="button_input" type="button" value="Adicionar"
						onclick="validarEsporteLocalCampeonato()">
				</div>
			</form>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.Partida"%>
<%-- <%@page import="model.Time"%> --%>
<%-- <%@page import="model.Esporte"%> --%>
<%-- <%@page import="model.Local"%> --%>
<%@page import="model.Jogador"%>
<%@page import="java.util.ArrayList"%>
<%
Partida partida = (Partida) request.getAttribute("partida");
%>
<%
ArrayList<Jogador> jogadoresTime1 = (ArrayList<Jogador>) request.getAttribute("jogadoresTime1");
%>
<%
ArrayList<Jogador> jogadoresTime2 = (ArrayList<Jogador>) request.getAttribute("jogadoresTime2");
%>

<%-- <%
String aux1 = (String) request.getAttribute("aux1");
%>
<%
String aux2 = (String) request.getAttribute("aux2");
%> --%>
<%-- <%
Time time1 = (Time) request.getAttribute("time1");
%>
<%
Time time2 = (Time) request.getAttribute("time2");
%>

<%
Local local = (Local) request.getAttribute("local");
%>
<%
Esporte esporte = (Esporte) request.getAttribute("esporte");
%> --%>

<!DOCTYPE html>
<html lang="pt-br">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SocialSport</title>
<link rel="stylesheet" href="css/styleExibir.css">
<link rel="stylesheet" href="css/formExibir.css">
<link rel="stylesheet"
	href="https://cdn.es.gov.br/fonts/font-awesome/css/font-awesome.min.css">

<!-- <link rel="stylesheet" href="styles.css"> -->
<script src="https://kit.fontawesome.com/022b4f414f.js"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/6d7f608b99.js"
	crossorigin="anonymous"></script>
</head>

<input type="checkbox" id="check">
<label for="check" class="label_sidebar"> <i
	class="fa-solid fa-bars" id="btn"></i> <i class="fa-solid fa-xmark"
	id="cancel"></i>
</label>
<div class="sidebar">
	<header>Social Sport Menu</header>
	<a href="index.html" class="active"> <span>Home</span> <i
		class="fa-solid fa-house"></i>
	</a> <a href="#"> <i class="fa-solid fa-user"></i> <span>Jogadores</span>
	</a> <a href="#"> <i class="fa-solid fa-people-group"></i> <span>Time</span>
	</a> <a href="#"> <i class="fa-solid fa-volleyball"></i> <span>Esporte</span>
	</a> <a href="#"> <i class="fa-solid fa-flag-checkered"></i> <span>Partida</span>
	</a> <a href="#"> <i class="fa-solid fa-medal"></i> <span>Campeonato</span>
	</a> <a href="#"> <i class="fa-solid fa-trophy"></i> <span>Liga</span>
	</a> <a href="#"> <i class="fa-solid fa-circle-user"></i> <span>Login</span>
	</a>
</div>

<body style="overflow: auto">
	<div class="background__interface"
		style="position: absolute; top: 0px; width: 100%; height: 1500px; display: block;">

		<header class="header">
			<div class="header_content">
				<button class="btn_icon_header">
					<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
						fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                            <path fill-rule="evenodd"
							d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
                        </svg>
				</button>
				<a class="header_logo">Social Sport </a>

				<div class="navigation_header">
					<button class="btn_icon_header">
						<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
							fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                                <path
								d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                            </svg>
					</button>
					<!-- <h1>Menu Principal</h1> -->

					<a href="index.html" class="header_item"> Home <i
						class="fa-solid fa-house-chimney"></i></a> <a href="jogador.html"
						class="header_item"> Jogador <i class="fa-solid fa-user"></i></a>
					<a href="time.html" class="header_item"> Time <i
						class="fa-solid fa-people-group"></i></i></a> <a href="esporte.html"
						class="header_item"> Esporte <i class="fa-solid fa-volleyball"></i></i>
					</a> <a href="partida.html" class="header_item"> Partida <i
						class="fa-solid fa-flag-checkered"></i></a> <a href="campeonato.html"
						class="header_item"> Campeonato &nbsp;<i
						class="fa-solid fa-medal icon-User"></i></a> <a href="liga.html"
						class="header_item "> Liga<i class="fa-solid fa-trophy"></i></a>

				</div>
				<div class="header_admin header_admin--user">
					<div class="header_button header_button--user">
						<a href="#" class="header_item-login"> Login <i
							class="fa-solid fa-circle-user"></i></a>
					</div>
				</div>
				<div class="header_menu_toggle">
					<a href="#"> IconeMenuToggle</a>
				</div>
			</div>

		</header>

		<nav class="menuListSports">
			<div class="menuListSports__content"></div>

		</nav>
		<div class="container">
			<div class="container__content content">
				<div class="container_mainInner" style="display: grid;">
					<aside class="container_myMenu">
						<div class="menu minhas-ligas leftMenu meusTimes">
							<div class="leftMenu__head">
								<i class="fa-solid fa-trophy leftMenu__icon"></i> <span
									class="leftMenu_title">Minhas Ligas</span>
								<!-- <svg class="leftMenu_icon leftMenu_icon--star">
                                    <use xlink:href="/res/_fs/image/13_symbols/action.svg#star"></use>
                                </svg> -->
							</div>
							<div class="minha_lista_ligas">
								<div class="leftMenu_item" title="BRASIL: Liga Super Salão">
									<a class="leftMenu_href" href="#"> <span
										class="leftMenu_flag flag fl_brazil"></span> <span
										class="leftMenu_text">Liga Super Salão</span>
									</a> <span title="Favoritar liga" class="toggleMinhaLiga active">
										<svg class="leftMenu_star star">
                                        </svg>
									</span>
								</div>
							</div>

						</div>

					</aside>
					<main class="container_noticia_principal sport_page">
						<div class="container_liveTable container_liveTable_placar">
							<section>
								<div
									class="container_cabecalho_artigo cabecalho_artigo_placarPage">
									<div class="cabecalho">
										<hgroup>
											<!-- <h3> Esportes > SocialSport</h3> -->
											<h1>Informações sbre a Partida</h1>
											<h2>
												Partida entre "<%=partida.getTime1().getNome()%>" x "<%=partida.getTime2().getNome()%>"
											</h2>
											<!-- <h3 class="direita">Atualizado em</h3> -->
										</hgroup>
									</div>
									<div class="calendario calendario_placar">
										<button class="calendario_navigation" aria-expanded="false"
											href="#" title="Dia anterior">
											<i class="fa-solid fa-arrow-left"></i>
										</button>
										<button class="calendarioMenu" aria-expanded="false"
											class="calendario_datepicker">
											<i class="fa-regular fa-calendar"></i>
											<script>
												date = new Date()
														.toLocaleDateString();
												document.write(date);
											</script>
										</button>
										<button class="calendario_navigation" aria-expanded="false"
											href="#" title="Dia seguinte">
											<i class="fa-solid fa-arrow-right"></i>
										</button>
										<!-- </div> -->
									</div>

									<h2>
										O time:
										<%=partida.getTime1().getNome()%></h2>
									<h2>
										Joga contra:
										<%=partida.getTime2().getNome()%></h2>
									<div class="placarPartida">
										<h1>&nbsp;&nbsp;Placar da partida</h1>
										<form name="frmContagemGols" action="contagemGols">

											<div class="points">
												<h2><%=partida.getTime1().getNome()%></h2>
												<input class="mini_container_golsPartida" type="number"
													name="golsCasa" min="0" max="15" step="1" value="0"
													class="add_point"> X
												<input class="mini_container_golsPartida" type="number"
													name="golsFora" min="0" max="15" step="1" value="0"
													class="add_point">
												<h2><%=partida.getTime2().getNome()%></h2>
											</div>
											<input name="timeCasa" type="hidden"
												value="<%=partida.getTime1().getIdTime()%>"> <input
												name="timeFora" type="hidden"
												value="<%=partida.getTime2().getIdTime()%>">
											<%--	 <input
				 	type="hidden" name="aux1" value="<%=aux1%>"> <input
					type="hidden" name="aux2" value="<%=aux2%>">  --%>
											
											<input type="hidden" name="idPartida"
												value="<%=partida.getIdPartida()%>">
											<div class="button-form">
												<input class="button_input" type="submit"
													value="Finalizar Partida">
											</div>
										</form>
									</div>
								</div>
							</section>
							<div class="container_noticia container_noticia_placar">
								<main class="container_tex">
									<div class="container_jogadores container_jogadores_placar">

										<h1>&nbsp;&nbsp;Escalações</h1>
										<div class="container_escalados">
											<div class="container_escalados_time1">

												<h2><%=partida.getTime1().getNome()%></h2>
												<p>
													<%
													for (int i = 0; i < jogadoresTime1.size(); i++) {
													%>
													<br><%=jogadoresTime1.get(i).getNome()%><br>
													<%
													}
													%>
												</p>
											</div>
											<div class="container_escalados_time2">
												<h2><%=partida.getTime2().getNome()%></h2>
												<p>
													<%
													for (int i = 0; i < jogadoresTime2.size(); i++) {
													%>
													<br><%=jogadoresTime2.get(i).getNome()%><br>
													<%
													}
													%>
												</p>
											</div>
										</div>
									</div>
								</main>
							</div>
						</div>
					</main>

					<div class="container_bannerZone">
						<div class="meusBanners">
							<img class="imgBanner" src="imagens/banners/bannerINTxMIL.png"
								alt="Inter de Melões x Milano - 02 FEV 23 16:30" vspace="15px"
								hspace="10px">

						</div>
					</div>
				</div>
			</div>
		</div>
		<footer id="rodape">
			<p>
				Copyright &copy; 2022 - by Adauto Benevides, João Vitor Nascimento
				and Higo Alves<br> <a href="http://facebook.com/"
					target="_blank"> Facebook </a> | <a href="" target="_blank">
					Instagram </a>
			</p>
		</footer>
	</div>
</body>

</html>
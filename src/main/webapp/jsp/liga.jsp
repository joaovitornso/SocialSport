<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.Liga"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<Liga> listaLigas = (ArrayList<Liga>) request.getAttribute("ligas"); // recebe o objeto lista
// teste de recebimento da lista
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Ligas</title>
<link rel="icon" href="images/favicon.png">
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="css/style.css">
</head>


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
						class="fa-solid fa-house-chimney"></i></a> <a href="jogador"
						class="header_item"> Jogador <i class="fa-solid fa-user"></i></a>
					<a href="time" class="header_item"> Time <i
						class="fa-solid fa-people-group"></i></i></a> <a href="esporte"
						class="header_item"> Esporte <i class="fa-solid fa-volleyball"></i></i>
					</a> <a href="partida" class="header_item"> Partida <i
						class="fa-solid fa-flag-checkered"></i></a> <a href="campeonato"
						class="header_item"> Campeonato &nbsp;<i
						class="fa-solid fa-medal icon-User"></i></a> <a href="liga"
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
								<!-- <svg class="leftMenu__icon leftMenu__icon--star">
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
						<div class="container_liveTable">
							<div class="container_Table_Player">
								<p>
									<a href="novaLiga.html" class="BotaoNovo">Nova Liga</a> <a
										href="selecionarLiga" id="BotaoAlocar">Realizar Liga</a>
								</p>
								<div class="container_tabela">
									<table>
										<thead>
											<tr>
												<th>Nome</th>
												<th>Medalha</th>
												<th>Troféu</th>
												<th>Edit</th>
												<th>Cancel</th>
												<th>Organizar</th>
											</tr>
										</thead>
										<tbody>
											<%
											for (int i = 0; i < listaLigas.size(); i++) {
											%>
											<tr>
												<td><%=listaLigas.get(i).getNome()%></td>
												<td><%=listaLigas.get(i).getMedalhas()%></td>
												<td><%=listaLigas.get(i).getTrofeu()%></td>
												<td><a
													href="selectLiga?idLiga=<%=listaLigas.get(i).getId()%>"
													class="BotaoEditar">Edit</a></td>
												<td><a
													href="javascript: cancelarLiga(<%=listaLigas.get(i).getId()%>)"
													class="BotaoDeletar">Cancel</a></td>
												<td><a
													href="selecionarEsporteLocalLiga?idLiga=<%=listaLigas.get(i).getId()%>"
													class="BotaoEditar">Organizar</a></td>
											</tr>
											<%
											}
											%>
										</tbody>
									</table>
								</div>
								<script src="scripts/confirmador.js"></script>
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
	</div>
	<footer id="rodape">
		<p>
			Copyright &copy; 2022 - by Adauto Benevides, João Vitor Nascimento
			and Higo Alves<br> <a href="http://facebook.com/"
				target="_blank"> Facebook </a> | <a href="" target="_blank">
				Instagram </a>
		</p>
	</footer>
</body>

</html>



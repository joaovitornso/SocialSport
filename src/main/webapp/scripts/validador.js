/**
 * Validação de formulário
 * @author Adauto Benevides
 */

function validar() {
	let nome = frmJogador.nome.value
	let idade = frmJogador.idade.value
	let nmrCamisa = frmJogador.nmrCamisa.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmJogador.nome.focus()
		return false
	} else if (idade === "" || idade > 20) {
		alert('O campo idade não está preenchido ou é maior que 20 anos. Verifique e tente novamente')
		frmJogador.idade.focus()
		return false
	} else if (nmrCamisa === "" || nmrCamisa > 99) {
		alert('Preencha o campo Número da Camisa ou Preencha com um número menor que 100')
		frmJogador.nmrCamisa.focus()
		return false
	}
	else {
		document.forms["frmJogador"].submit()
	}
}
function validarTime() {
	let nome = frmTime.nome.value
	let qntJogadores = frmTime.qntJogadores.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmTime.nome.focus()
		return false
	} else if (qntJogadores === "") {
		alert('Preencha o campo quantidade de jogadores')
		frmTime.idade.focus()
		return false
	} else {
		document.forms["frmTime"].submit()
	}
}
function validarAlocacao() {
	let jogadorAlocado = frmAlocacao.jogadorAlocado.value
	let timeAlocado = frmAlocacao.timeAlocado.value

	if (jogadorAlocado === "") {
		alert('Preencha o campo Jogador')
		frmAlocacao.jogadorAlocado.focus()
		return false
	} else if (timeAlocado === "") {
		alert('Preencha o campo Time')
		frmAlocacao.timeAlocado.focus()
		return false
	} else {
		document.forms["frmAlocacao"].submit()
	}
}

function validarLocal() {
	let rua = frmLocal.rua.value
	let bairro = frmLocal.bairro.value
	let cidade = frmLocal.cidade.value
	let tipo = frmLocal.tipo.value
	if (rua === "") {
		alert('Preencha o campo Rua')
		frmLocal.rua.focus()
		return false
	} else if (bairro === "") {
		alert('Preencha o campo Bairro')
		frmLocal.bairro.focus()
		return false
	} else if (cidade === "") {
		alert('Preencha o campo Cidade')
		frmLocal.cidade.focus()
		return false
	} else if (tipo === "") {
		alert('Preencha o campo Tipo')
		frmLocal.tipo.focus()
		return false
	} else {
		document.forms["frmLocal"].submit()
	}
}
function validarPartida() {
	let data = frmPartida.data.value
	let horario = frmPartida.horario.value
	let pontuacao = frmPartida.pontuacao.value

	if (data === "") {
		alert('Preencha o campo Data')
		frmPartida.data.focus()
		return false
	}
	if (horario === "") {
		alert('Preencha o campo Horario')
		frmPartida.horario.focus()
		return false
	}
	if (pontuacao === "") {
		alert('Preencha o campo Pontuacao')
		frmPartida.pontuacao.focus()
		return false
	} else {
		document.forms["frmPartida"].submit()
	}
}
function validarEsporte() {
	let nome = frmEsporte.nome.value
	let duracao = frmEsporte.duracao.value
	let descricao = frmEsporte.descricao.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmEsporte.nome.focus()
		return false
	}
	if (duracao === "") {
		alert('Preencha o campo Duração')
		frmEsporte.duracao.focus()
		return false
	}
	if (descricao === "") {
		alert('Preencha o campo Descrição')
		frmEsporte.descricao.focus()
		return false
	}
	else {
		document.forms["frmEsporte"].submit()
	}
}

function validarOrganizacao() {
	let time1 = frmOrganizarPartida.time1.value
	let time2 = frmOrganizarPartida.time2.value

	if (time1 === "") {
		alert('Preencha o campo do Primeiro Time')
		frmOrganizarPartida.time1.focus()
		return false
	} else if (time2 === "") {
		alert('Preencha o campo do Segundo Time')
		frmOrganizarPartida.time2.focus()
		return false
	} else if (time1 != "" && time1 === time2) {
		alert('Os campos primeiro e segundo time não podem ser iguais')
		return false
	}
	else {
		document.forms["frmOrganizarPartida"].submit()
	}
}
function validarEscalacao() {
	document.forms["frmEscalacao"].submit()
}
function validarPlacar() {
	let golsCasa = frmContegemGols.golsCasa.value
	let golsFora = frmContagemGols.golsFora.value

	if (golsCasa === "") {
		alert('Preencha o campo gols em casa')
		return false
	}
	if (golsFora === "") {
		alert('Preencha o campo gols fora de casa')
		return false
	}
	else {
		document.forms["frmContagemGols"].submit()
	}
}
function validarEsporteLocal() {
	document.forms["frmEsporteLocal"].submit()
}
function validarPunicao() {
	let jogadorPunido = frmPunicao.jogadorPunido.value
	if (jogadorPunido === "") {
		alert('Preencha o campo jogador a ser punido')
		return false
	} else {
		document.forms["frmPunicao"].submit()
	}
}
function validarCampeonato() {
	let nome = frmCampeonato.nome.value
	let medalha = frmCampeonato.medalha.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmCampeonato.nome.focus()
		return false
	} else if (medalha === "") {
		alert('Preencha o campo medalha')
		frmCampeonato.medalha.focus()
		return false
	} else {
		document.forms['frmCampeonato'].submit()
	}
}
function validarLiga() {
	let nome = frmLiga.nome.value
	let medalha = frmLiga.medalha.value
	let trofeu = frmLiga.trofeu.value

	if (nome === "") {
		alert('Preencha o campo Nome')
		frmLiga.nome.focus()
		return false
	} else if (medalha === "") {
		alert('Preencha o campo medalha')
		frmLiga.medalha.focus()
		return false
	} else if(trofeu === ""){
		alert('Preencha o campo trofeu')
		frmLiga.trofeu.focus()
		return false
	
	}else {
		document.forms['frmLiga'].submit()
	}
}

function validarTimesCampeonato() {
	
	let time1 = frmTimesCampeonato.time1.value
	let time2 = frmTimesCampeonato.time2.value
	let time3 = frmTimesCampeonato.time3.value
	let time4 = frmTimesCampeonato.time4.value
	
	if (time1 === "") {
		alert('Selecione o campo primeiro time')
		frmTimesCampeonato.time1.focus()
		return false
	} else if (time2 === "") {
		alert('Selecione o campo segundo time')
		frmTimesCampeonato.time2.focus()
		return false
	} else if (time3 === "") {
		alert('Selecione o campo terceiro time')
		frmTimesCampeonato.time3.focus()
		return false
	} else if (time4 === "") {
		alert('Selecione o campo quarto time')
		frmTimesCampeonato.time4.focus()
		return false
	}
	else {
		document.forms['frmTimesCampeonato'].submit()
	}
}
function validarEsporteLocalCampeonato(){
	
	let dataCampeonato = frmEsporteLocalCampeonato.dataCampeonato.value
	let dataCampeonato2 = frmEsporteLocalCampeonato.dataCampeonato2.value
	let dataCampeonatoFinal = frmEsporteLocalCampeonato.dataCampeonatoFinal.value
	let horarioCampeonato = frmEsporteLocalCampeonato.horarioCampeonato.value
	let horarioCampeonato2 = frmEsporteLocalCampeonato.horarioCampeonato2.value
	let horarioCampeonatoFinal = frmEsporteLocalCampeonato.horarioCampeonatoFinal.value
	
	let esporteCampeonato = frmEsporteLocalCampeonato.esporteCampeonato.value
	let localCampeonato = frmEsporteLocalCampeonato.localCampeonato.value	
	
	
	if(esporteCampeonato === ""){
		alert('Selecione o esporte')
		frmEsporteLocalCampeonato.esporteCampeonato.focus()
		return false
	}else if(localCampeonato === ""){
		alert('Selecione o local')
		frmEsporteLocalCampeonato.localCampeonato.focus()
		return false
	}else if (dataCampeonato === "") {
		alert('Selecione o campo de seleção de data da primeira partida')
		frmEsporteLocalCampeonato.dataCampeonato.focus()
		return false
	} else if (dataCampeonato2 === "") {
		alert('Selecione o campo de seleção de data da segunda partida')
		frmEsporteLocalCampeonato.dataCampeonato2.focus()
		return false
	} else if (horarioCampeonato === "") {
		alert('Selecione o campo de seleção de horário da primeira partida')
		frmEsporteLocalCampeonato.horarioCampeonato.focus()
		return false
	} else if (horarioCampeonato2 === "") {
		alert('Selecione o campo de seleção de horário da segunda partida')
		frmEsporteLocalCampeonato.horarioCampeonato2.focus()
		return false
	}else if (dataCampeonatoFinal === "") {
		alert('Selecione o campo de seleção de data da Partida Final')
		frmEsporteLocalCampeonato.dataCampeonatoFinal.focus()
		return false
	} else if (horarioCampeonatoFinal === "") {
		alert('Selecione o campo de seleção de horário da Partida Final')
		frmEsporteLocalCampeonato.horarioCampeonatoFinal.focus()
		return false
	} else if (dataCampeonato != "" && dataCampeonato === dataCampeonato2 || dataCampeonato === dataCampeonatoFinal || dataCampeonato2 === dataCampeonatoFinal) {
		alert('As partidas não podem acontecer na mesma data')
		frmEsporteLocalCampeonato.dataCampeonato.focus()
		return false
	}else{
		document.forms['frmEsporteLocalCampeonato'].submit()
	}
}
function validarEsporteLocalLiga(){
	
	let dataLiga = frmEsporteLocalLiga.dataLiga.value
	let dataLiga2= frmEsporteLocalLiga.dataLiga2.value
	let dataLigaFinal= frmEsporteLocalLiga.dataLigaFinal.value
	let horarioLiga = frmEsporteLocalLiga.horarioLiga.value
	let horarioLiga2 = frmEsporteLocalLiga.horario2.value
	let horarioLigaFinal = frmEsporteLocalLiga.horarioLiga3.value
	
	let esporteLiga = frmEsporteLocalCampeonato.esporteCampeonato.value
	let localLiga = frmEsporteLocalCampeonato.localCampeonato.value	
	
	
	if(esporteLiga === ""){
		alert('Selecione o esporte')
		frmEsporteLocalLiga.esporteLiga.focus()
		return false
	}else if(localLiga=== ""){
		alert('Selecione o local')
		frmEsporteLocalLiga.localLiga.focus()
		return false
	}else if (dataLiga === "") {
		alert('Selecione o campo de seleção de data da primeira partida')
		frmEsporteLocalLiga.dataLiga.focus()
		return false
	} else if (dataLiga2 === "") {
		alert('Selecione o campo de seleção de data da segunda partida')
		frmEsporteLocalLiga.dataLiga2.focus()
		return false
	} else if (horarioLiga === "") {
		alert('Selecione o campo de seleção de horário da primeira partida')
		frmEsporteLocalLiga.horarioLiga.focus()
		return false
	} else if (horarioLiga2 === "") {
		alert('Selecione o campo de seleção de horário da segunda partida')
		frmEsporteLocalLiga.horarioLiga2.focus()
		return false
	}else if (dataLigaFinal === "") {
		alert('Selecione o campo de seleção de data da Partida Final')
		frmEsporteLocalLiga.dataLigaFinal.focus()
		return false
	} else if (horarioLigaFinal === "") {
		alert('Selecione o campo de seleção de horário da Partida Final')
		frmEsporteLocalLiga.horarioLigaFinal.focus()
		return false
	} else if (dataLiga != "" && dataLiga === dataLiga2 || dataLiga === dataLigaFinal || dataLiga2 === dataLigaFinal) {
		alert('As partidas não podem acontecer na mesma data')
		frmEsporteLocalLiga.dataLiga.focus()
		return false
	}else{
		document.forms['frmEsporteLocalLiga'].submit()
	}
}

function validarCampeonatoRealizado() {
	let campeonato = frmSelecaoCampeonato.campeonatoRealizado.value

	if (campeonato === "") {
		alert('Selecione o campo campeonato')
		frmSelecaoCampeonato.campeonatoRealizado.focus()
		return false
	}
	else {
		document.forms['frmSelecaoCampeonato'].submit()
	}
}


/**
 * Confirmação de exclusão de um contato
 * @author Adauto Benevides
 */

function confirmar(idjogador) {
	let resposta = confirm("Confirma a exclusão deste jogador ?")
	if (resposta === true) {
		//alert(idjogador)
		window.location.href = "delete?idjogador=" + idjogador
		/*window.location.href no javaScript é usado para fazer um redirecionamento
		faz com que o usuario saia desse documento e seja encaminhado para outro local
		o local é definido pela palavra delete, quem trata as requisições é o servlet
		?idjogador= + idjogadoralem de encaminhar mando tmb um parametro e a esse parametro 
		é atribuido o valor  do idjogador que o javaScript recebeu */
	}
}
function confirmarTime(idTime) {
	let resposta2 = confirm("Confirma a exclusão deste time?")
	if (resposta2 === true) {
		window.location.href = "delete2?idTime=" + idTime
	}
}
function confirmarLocal(idLocal) {
	let respostaLocal = confirm("Confirma a exclusão deste Local?")
	if (respostaLocal === true) {
		window.location.href = "deleteLocal?idLocal=" + idLocal
	}
}
function confirmarPartida(idPartida) {
	let respostaPartida = confirm("Confirma a exclusão dessa partida?")
	if (respostaPartida === true){
		window.location.href = "deletePartida?idPartida=" + idPartida
	}

}
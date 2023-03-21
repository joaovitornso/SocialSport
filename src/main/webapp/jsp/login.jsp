<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login | Administrador</title>
<link rel="stylesheet" href="../css/stile.css">
<link rel="stylesheet" href="css/stile.css">
<link rel="stylesheet" href="../css/form.css">
<link rel="stylesheet" href="css/form.css">
</head>
<body>
	<%
	String erro = (String) request.getAttribute("erro");
	%>

	<div class="main">
		<input type="checkbox" id="chk" aria-hidden="true">
		<div class="signup">
			<form>
				<label for="chk" aria-hidden="true"><a class="logo_login">Social
						Sport</a></label>
				<div class="img-admin">
					<img src="../imagens/admin-icon2.png"> 
					<!-- <img src="imagens/admin-icon2.png"> -->
				</div>
				<!-- <button>Sign up</button> -->
			</form>
		</div>
		<div class="login">
			<form method="POST"
				action="<%if (erro != null){%>ControllerLogin 
			<%} else {%>../ControllerLogin<%}%>">
				
				<label for="chk" aria-hidden="true">Login</label> <input
					type="text" name="nome" placeholder="Username" required="required">
				<input type="password" name="senha" placeholder="Password"
					required="required">
				<button>Login</button>
			</form>
		</div>
	</div>
	<p>
		<%
		if (erro != null)
			out.print(erro);
		%>
	</p>
























	</form>

</body>
</html>
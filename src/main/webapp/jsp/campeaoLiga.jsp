<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.Time"%>
<%
Time time = (Time) request.getAttribute("timeCampeao");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- <link rel="stylesheet" href="form.css"> -->
    <script src="https://kit.fontawesome.com/022b4f414f.js" crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/6d7f608b99.js" crossorigin="anonymous"></script>
    <title>Registro de Partida</title>
    <style>
        * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Kristi", cursive;
      }

      html {
        font-size: 62.5%;
      }

      .main_header {
        width: 100vw;
        height: 100vh;
        /* background-color: hsl(0, 0%, 94%); */
        /* background: linear-gradient(to bottom, #010A0F, #00141E, #010407); */
        background: linear-gradient(to bottom, #4e8697, #295268, #0a1016);
        
        display: grid;
        place-items: center;
      }

      .center_div {
        /* position: fixed; */
        min-width: 50vw;
        height: 70vh;
        color: #fff;
        text-shadow: 0 0 2rem rgba(255, 255, 255, 0.5);
        font-size: 4rem;
        display: flex;
        justify-content: center;
        align-items: center;
        font-weight: bold;
        text-align:center;
      }
      .center_div p{
        position: absolute;
      }
      .img img{
        position: absolute;
        margin-top: 20px;
        width: 128px;
      }
      .img{
        justify-content: center;
        display: flex;
      }
      .medal{
        align-items: center;
        width: 128px;
        position: absolute;
      }
      .medal img{
        position: absolute;
        width: 128px;
      }

 @media (max-width: 768px) {
        html {
          font-size: 35%;
        }
      }
    
      #my-canvas{
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        height: 100vh;
        z-index: 100000;
      }
      .button_input{
        width: 60%;
        height: 40px;
        margin: 10px auto;
        justify-content: center;
        /* display: block; */
        color: #fff;
        background: #d6033f;
        font-size: 1em;
        font-weight: bold;
        margin-top: 20px;
        outline: none;
        border: none;
        border-radius: 5px;
        transition: .2s ease-in;
        cursor: pointer;
        align-items: center;
    }
    a{
        text-decoration: none;
        cursor: pointer;
    }
    .button_input:hover{
        background: #b9254f;
    }
    .input-box{
        display: block;
        font-weight: 500;
        margin-bottom: 5px;
        align-items: center;
        justify-content: center;
    }
    .input-box a{
        height: 45px;
        width: 100%;
        outline: none;
        border-radius: 5px;
        border: 1px slid #ccc;
        padding: 15px;
        font-size: 15px;
        border-bottom-width: 2px;
        transition: all 0.3s ease;
    }
    .input-box a:focus,
    .input-box a:valid{
        border-color: #9b59b6;
}
    </style>
</head>
<body>
    <div class="img">
        <img src="./imagens/icons/congratulation2.png">
    </div>
    
    <div class="main_header">
        <div>
            <div class="center_div">
                <p>
                    PARABÉNS! O NOVO VENCEDOR DA LIGA SUPER SALÃO!
                    <%=time.getNome()%>
                    <br><br>
                    <img class="medal" src="imagens/icons/gold-medal.png">
                    <br><br>
                    <img class="medal" src="imagens/icons/football.png">
                </p>
            
            </div>
        </div>
        <div class="input-box">
            <!-- <input class="button_input" type="submit" placeholder="VOLTAR AO ÍNICIO" value="VOLTAR AO ÍNICIO"> -->
            <a class="button_input" href="index.html">VOLTAR AO ÍNICIO</a> 
        </div>
            <!-- <input class="button_input" type="submit" placeholder="VOLTAR AO ÍNICIO" value="VOLTAR AO ÍNICIO"> -->
            <!-- <button href="#" class="button_input">VOLTAR AO ÍNICIO</button> -->
    </div>
    <canvas id="my-canvas"></canvas>
    <script src="scripts/index.min.js"></script>
    <script>
        var confettiSettings = { target: 'my-canvas' };
        var confetti = new ConfettiGenerator(confettiSettings);
        confetti.render();
    </script>
</body>
</html>
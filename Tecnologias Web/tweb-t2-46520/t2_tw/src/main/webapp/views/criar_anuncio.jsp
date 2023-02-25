<%@ page language="java" session="true"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="../static/css/style.css ">


<head>
    <title>Anuncios-Alojamentos AE</title>

    <div id="cab">
        <h1>ALOJAMENTOS AE</h1>
        <h2>DE ALUNOS PARA ALUNOS</h2>
        <h3>AQUI VAIS ENCONTRAR UM LAR PARA OS MELHORES ANOS DA TUA VIDA </h3>
    </div>

     <div id="barra_admin">
        <ul>
            <li><a href="${pageContext.request.contextPath}/user_interface">HOME</a></li>
            <li><a href="${pageContext.request.contextPath}/anuncios_user">ANUNCIOS</a></li>
            <li><a href="${pageContext.request.contextPath}/criar_anuncio">CRIAR ANUNCIO </a></li>
            <li><a href="${pageContext.request.contextPath}/gestao_anuncios">GESTAO DE ANUNCIOS </a></li>
            <li><a href="<c:url value='logout'/>">LOGOUT</a></li>
        </ul>
    </div>
</head>
        
<body>
    <div id="c_form">
        <form name="formAnuncio" method="POST" action="/regist_anun">

            <fieldset>
                <legend>Informacao do alojamento</legend>
                <p>Tipo:
                    <a><input type="radio" name="tipo" value="procura" checked> Procura</a>
                    <a><input type="radio" name="tipo" value="oferta"> Oferta</a>
                </p>
                <p><input type="hidden" id="estado" name="estado" value="inativo"></p>

                <p>Tipo de alojamento:
                    <a><input type="radio" name="tipo_alojamento" value="quarto" checked> Quarto</a>
                    <a><input type="radio" name="tipo_alojamento" value="casa"> Casa</a>
                </p>
                <p>Tipo de arrendatario:
                    <a><input type="radio" name="genero" value="maculino" checked> Masculino</a>
                    <a><input type="radio" name="genero" value="feminino"> Feminino</a>
                    <a><input type="radio" name="genero" value="indiferente"> Indiferente</a>
                </p>
                <p>Preco: <input type="number" name="preco" required></p>
                <p>Descricao: </p><textarea id="detalhes" name="detalhes" rows="10" cols="50" required></textarea>
            </fieldset>

            <fieldset>
                <legend>Informacao do anunciante: </legend>
                <input type="hidden" name="user_name" value=${pageContext.request.userPrincipal.name}>
                <p>Contacto: <input type="number" name="contacto" required></p>
                <p>Zona: <input type="text" name="zona" required></p>
            </fieldset>
            
            <div id="butoes">
                <input type="reset" value="RESET">
                <input type="submit" value="SUBMIT">
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

        </form>
    </div>

</body>
        
<div id="bottom">
    <div class="footer" id="cont">
        <h3>Contactos-Sede</h3>
        <p>Telefone:(+351) 266 000 000</p>
        <p>Fax:(+351) 266 000 001</p>
        <p>E-mail:aalojamento@uevora.pt</p>
        <a href="http://facebook.com"><img src="../static/imagens/face.jpg" alt="Facebook"></img>
        </a>
        <a href="http://instagram.com"><img src="../static/imagens/insta.png" alt="Instagram"></img>
        </a>
        <a href="http://twitter.com"><img src="../static/imagens/twitter.jpg" alt="Instagram"></img>
        </a>
    </div>
    <div class="footer" id="pat">
        <h3>Patrocinadores</h3>
        <a href="http://uevora.pt" target><img src="../static/imagens/uevora.png" alt="Uevora"></img>
        </a>
    </div>
    <div class="footer" id="cred">
        <h3>Creditos</h3>
        <p>CARLOS PALMA 46520</p>
        <p>l46520@alunos.uevora.pt</p>
    </div>
</div>


</html>
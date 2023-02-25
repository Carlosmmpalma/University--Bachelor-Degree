<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

     <div id="barra_home">
        <ul>
            <li><a href="${pageContext.request.contextPath}/index">HOME</a></li>
            <li><a href="${pageContext.request.contextPath}/anuncios">ANUNCIOS</a></li>
            <li><a href="${pageContext.request.contextPath}/login">LOGIN</a></li>
            <li><a href="${pageContext.request.contextPath}/newuser">SIGN-UP</a></li>
        </ul>
    </div>
</head>

        <body>
            <div id="login-box" align="center">
                <form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
                    <h2>LOGIN</h2>
                   <p>
                   Username:
                    <input type='text' name='username' value=''>
                   </p>
                   <p>
                   Password:
                   <input type='password' name='password' />
                   </p>
                   <div id="butoes">
                    <input type="reset" value="RESET">
                    <input type="submit" value="SUBMIT">
                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${not empty msg}">
                        <div class="msg">${msg}</div>
                    </c:if>
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
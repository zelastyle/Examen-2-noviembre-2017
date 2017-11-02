<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilos.css"/>
<title>LOGEO EXAMEN</title>
</head>
<body>
	
	
	<div id="datos">
            <%
            if(session.getAttribute("id") != null && session.getAttribute("usuario") != null && session.getAttribute("clave") != null ){	
            %>
            <div>
             <h1> Sesion iniciada con exito</h1>
             
             <p>BIENVENIDO, <%= session.getAttribute("usuario") %></p>
			
			 <input type="button" value="Cerrar sesion" id="cerrar" onclick="window.open('/Examen/Hola?action=Logout','_self')"/>
        	</div>
      </div>
            <%
            } else { %>
      		<form action="/Examen/Hola?action=Logeo" id="form" method="post">
        		<h2>INICIAR SESION</h2>
        		<div id="input-container">
        			<input type="text" placeholder="Usuario" name="user"/>
        			<input type="password" placeholder="Contraseña" name="pass"/>
        		</div>
        		<div id="submit-container">
        			<input type="submit" name="enviar" value="Iniciar sesion" id="btn-submit"/>
        		</div>
        	</form>
        	<%
            }
            %>
     
</body>
</html>
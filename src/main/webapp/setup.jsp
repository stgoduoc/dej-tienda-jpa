<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Instalación</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous" />
        <jsp:include page="/WEB-INF/jspf/header.jsp" />
    </head>
    <body>
        <div class="container">
            <jsp:include page="/WEB-INF/jspf/menu.jsp" />

            <!-- errores -->
            <c:if test="${!empty errores}">
                <div class="alert alert-danger" role="alert">
                    <ul>
                        <c:forEach items="${errores}" var="e">
                            <li>${e}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <!-- mensajes -->
            <c:if test="${!empty mensajes}">
                <div class="alert alert-info" role="alert">
                    <ul>
                        <c:forEach items="${mensajes}" var="m">
                            <li>${m}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <%--
            <form method="get" action="SetupServlet">
                <div class="row justify-content-center">
                    <div class="col">
                        <div class="card" style="width: 20rem;">
                            <img class="card-img-top" src="img/install.png" alt="Instalar">
                            <div class="card-body">
                                <h4 class="card-title">Instalación</h4>
                                <p class="card-text">Crea las tablas y datos necesarios en la base de datos del programa.</p>
                                <button type="submit" name="operacion" value="instalar" class="btn btn-success">Instalar</button>
                            </div>
                        </div>
                    </div><!-- end col -->
                    <div class="col">
                        <div class="card" style="width: 20rem;">
                            <img class="card-img-top" src="img/uninstall.png" alt="Desinstalar">
                            <div class="card-body">
                                <h4 class="card-title">Desinstalación</h4>
                                <p class="card-text">Borra las tablas y datos en la base de datos del programa.</p>
                                <button type="submit" name="operacion" value="desinstalar" class="btn btn-danger">Desinstalar</button>
                            </div>
                        </div>
                    </div><!-- end col -->
                    <div class="col">
                        <div class="card" style="width: 20rem;">
                            <img class="card-img-top" src="img/carga.png" alt="Carga datos">
                            <div class="card-body">
                                <h4 class="card-title">Carga datos</h4>
                                <p class="card-text">Crea registros de prueba en las tablas de la base de datos del programa.</p>
                                <button type="submit" name="operacion" value="cargar" class="btn btn-primary">Cargar Datos</button>
                            </div>
                        </div>
                    </div><!-- end col -->
                </div><!-- end row-->
            </form>
            --%>
            <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
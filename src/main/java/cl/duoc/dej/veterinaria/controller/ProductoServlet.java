package cl.duoc.dej.veterinaria.controller;

import cl.duoc.dej.veterinaria.entity.Categoria;
import cl.duoc.dej.veterinaria.entity.Producto;
import cl.duoc.dej.veterinaria.exception.ProductoNoEncontradoException;
import cl.duoc.dej.veterinaria.service.CategoriaService;
import cl.duoc.dej.veterinaria.service.ProductoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductoServlet", urlPatterns = {"/catalogo"})
public class ProductoServlet extends HttpServlet {

    @EJB
    ProductoService productoService;
    @EJB
    CategoriaService categoriaService;

    private final String JSP_LISTA_PRODUCTOS = "/WEB-INF/jsp/producto/listar.jsp";
    Logger logger = Logger.getLogger(this.getClass().getSimpleName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operacion = request.getParameter("op");
        operacion = operacion != null ? operacion : "";
        switch (operacion) {
            case "buscar":
                buscar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            case "listar":
            default:
                listar(request, response);
        }
    }

    private void listar(HttpServletRequest request, HttpServletResponse response, List<Producto> productos) throws ServletException, IOException {
        List<Categoria> categorias = categoriaService.getCategorias();

        request.setAttribute("productos", productos);
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher(JSP_LISTA_PRODUCTOS).forward(request, response);
    }

    private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Producto> productos = productoService.getProductos();
        listar(request, response, productos);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> errores = new ArrayList<>();
        List<String> mensajes = new ArrayList<>();
        String error = "";
        String mensaje = "";

        String stringId = request.getParameter("id");
        Long productoId = 0L;
        try {
            productoId = Long.parseLong(stringId);
            productoService.eliminarProducto(productoId);
            mensaje = String.format("Se ha eliminado correctamente el producto con ID %s", productoId);
            logger.log(Level.INFO, mensaje);
            request.setAttribute("mensajes", mensajes);
            mensajes.add(mensaje);
        } catch (NumberFormatException nfe) {
            error = String.format("Formato de ID inválido");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        } catch (ProductoNoEncontradoException ex) {
            error = String.format("No se pudo encontrar el producto con el ID especificado");
            logger.log(Level.SEVERE, error);
            errores.add(error);
        }

        listar(request, response);
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productoBuscado = request.getParameter("producto");
        String stringCategoria = request.getParameter("categoria");
        Long categoriaId = null;
        try {
            if (stringCategoria != null) {
                categoriaId = Long.parseLong(stringCategoria);
            }
        } catch (NumberFormatException nfe) {
            logger.log(Level.INFO, "La Categoría ID entregada no corresponde a un ID válido");
        }
        List<Producto> productos = productoService.buscarProducto(productoBuscado, categoriaId);
        listar(request, response, productos);
    }

}

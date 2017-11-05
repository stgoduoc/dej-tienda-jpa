package cl.duoc.dej.veterinaria.controller;

import cl.duoc.dej.veterinaria.entity.Categoria;
import cl.duoc.dej.veterinaria.service.CategoriaService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CategoriaServlet", urlPatterns = {"/categorias"})
public class CategoriaServlet extends HttpServlet {

    @EJB
    CategoriaService categoriaService;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categoria> categorias = categoriaService.getCategorias();
        request.setAttribute("categorias", categorias);
        request.getRequestDispatcher("/WEB-INF/jsp/categoria/listar.jsp").forward(request, response);
    }

    
    
}

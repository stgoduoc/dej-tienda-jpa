package cl.duoc.dej.veterinaria.controller;

import cl.duoc.dej.veterinaria.entity.Categoria;
import cl.duoc.dej.veterinaria.entity.Producto;
import cl.duoc.dej.veterinaria.service.CategoriaService;
import cl.duoc.dej.veterinaria.service.ProductoService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SetupServlet", urlPatterns = {"/setup"})
public class SetupServlet extends HttpServlet {

    @EJB
    CategoriaService categoriaService;

    @EJB
    ProductoService productoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> mensajes = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        try {

            String imagen = "http://freedomfitnessandworkout.com/wp-content/uploads/2014/08/anillas.jpg";
            String descripcion = "- Color negro\n"
                    + "- Madera\n"
                    + "- Rango de ruptura de 250 kg.\n"
                    + "- Superficie texturizada\n"
                    + "- 3 cm. de grosor y 23 cm. de diámetro externo.\n"
                    + "- Cintas de polyester color negro de alta resistencia de 2.5 cm de ancho por\n"
                    + "450 cm de longitud, con capacidad máxima de resistencia de 1720 kg.\n"
                    + "- Hebilla de seguridad de aluminio, con capacidad máxima de resistencia de 544 kg.\n"
                    + "- Fácil instalación.";

            Categoria categoria = new Categoria("Ropa Deportiva", "");
            categoria = categoriaService.crearCategoria(categoria);
            mensajes.add( String.format("Categoría %s creada con ID %s", categoria.getNombre(), categoria.getId()) );
            
            categoria = new Categoria("Deportes", "");
            categoria = categoriaService.crearCategoria(categoria);
            mensajes.add( String.format("Categoría %s creada con ID %s", categoria.getNombre(), categoria.getId()) );

            Producto p = new Producto("Anillas gimnasia", imagen, descripcion, 29990L, categoria);
            p = productoService.crearProducto(p);
            mensajes.add( String.format("Producto %s creado con ID %s", p.getNombre(), p.getId()) );

            descripcion = "BICICLETA SPINNING MAGNÉTICA LAHSEN HM 4620\n"
                    + "\n"
                    + "Modelo: HM-4620\n"
                    + "Tensión magnética\n"
                    + "Resistencia regulable\n"
                    + "Rueda volante de 13 Kg.\n"
                    + "Computador (tiempo/distancia/velocidad/consumo de calorias/pulso/scan)\n"
                    + "Manubrio ajustable\n"
                    + "Piñon Libre \n"
                    + "Regulación de altura y posición\n"
                    + "Asiento prostatico confort\n"
                    + "Peso maximo usuario de 100 Kg\n"
                    + "Estructura marco: 600 x 300 x 15 mm\n"
                    + "\n"
                    + "Dimensiones:\n"
                    + "Tamaño armada 116 x 57 x 126 cm\n"
                    + "Peso: 37.6 Kg.";
            imagen = "http://media.yogajournal.com/wp-content/uploads/201506-yjmag-spinning.jpg";
            p = new Producto("Bicicleta de Spinning", imagen, descripcion, 199000L, categoria);
            p = productoService.crearProducto(p);

            mensajes.add( String.format("Producto %s creado con ID %s", p.getNombre(), p.getId()) );
        } catch (Exception e) {
            errores.add(e.getMessage());
        }

        request.setAttribute("mensajes", mensajes);
        request.setAttribute("errores", errores);
        request.getRequestDispatcher("/setup.jsp").forward(request, response);
    }

}

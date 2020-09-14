package com.petSmile;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petSmile.Dueno;
import com.petSmile.DuenoDAO;

/**
 * Servlet implementation class DuenoController
 */
@WebServlet(description = "administra peticiones para la tabla dueno", urlPatterns = { "/duenos" })
public class DuenoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DuenoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if (opcion.equals("crear")) {
			System.out.println("Usted ha presionado la opcion crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("listar")) {

			DuenoDAO duenoDAO = new DuenoDAO();
			List<Dueno> lista = new ArrayList<>();
			try {
				lista = duenoDAO.obtenerDuenos();
				for (Dueno dueno : lista) {
					System.out.println(dueno);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted ha presionado la opcion listar");
		} else if (opcion.equals("meditar")) {
			int rut = Integer.parseInt(request.getParameter("rut"));
			System.out.println("Editar rut: " + rut);
			DuenoDAO duenoDAO = new DuenoDAO();
			Dueno d = new Dueno();
			try {
				d = duenoDAO.obtenerDueno(rut);
				System.out.println(d);
				request.setAttribute("dueno", d);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (opcion.equals("eliminar")) {
			DuenoDAO duenoDAO = new DuenoDAO();
			int rut=Integer.parseInt(request.getParameter("rut"));
			try {
				duenoDAO.eliminar(rut);
				System.out.println("Registro eliminado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		

		if (opcion.equals("guardar")) {
			DuenoDAO duenoDAO = new DuenoDAO();
			Dueno dueno = new Dueno();
			dueno.setRut(Integer.parseInt(request.getParameter("rut")));
			dueno.setNombre(request.getParameter("nombre"));
			dueno.setApellido(request.getParameter("apellido"));
			dueno.setDireccion(request.getParameter("direccion"));
			dueno.setCorreo(request.getParameter("correo"));
			dueno.setTelefono(Integer.parseInt(request.getParameter("telefono")));
			dueno.setNombreMascota(request.getParameter("correo"));
			try {
				duenoDAO.guardar(dueno);
				System.out.println("Registro guardado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("editar")) {
			Dueno dueno = new Dueno();
			DuenoDAO duenoDAO = new DuenoDAO();
			dueno.setRut(Integer.parseInt(request.getParameter("rut")));
			dueno.setNombre(request.getParameter("nombre"));
			dueno.setApellido(request.getParameter("apellido"));
			dueno.setDireccion(request.getParameter("direccion"));
			dueno.setCorreo(request.getParameter("correo"));
			dueno.setTelefono(Integer.parseInt(request.getParameter("telefono")));
			dueno.setNombreMascota(request.getParameter("correo"));
			try {
				duenoDAO.editar(dueno);
				System.out.println("Registro editado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

}

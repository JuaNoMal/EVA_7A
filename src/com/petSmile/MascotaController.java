package com.petSmile;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petSmile.Mascota;
import com.petSmile.MascotaDAO;

/**
 * Servlet implementation class MascotaController
 */
@WebServlet(description = "administra peticiones para la tabla mascotas", urlPatterns = { "/mascotas" })
public class MascotaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MascotaController() {
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
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/agregarmascota.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("listar")) {

			MascotaDAO mascotaDAO = new MascotaDAO();
			List<Mascota> lista = new ArrayList<>();
			try {
				lista = mascotaDAO.obtenerMascotas();
				for (Mascota mascota : lista) {
					System.out.println(mascota);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listamascotas.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted ha presionado la opcion listar");
		} else if (opcion.equals("meditar")) {
			int idMascota = Integer.parseInt(request.getParameter("idMascota"));
			System.out.println("Editar id: " + idMascota);
			MascotaDAO mascotaDAO = new MascotaDAO();
			Mascota m = new Mascota();
			try {
				m = mascotaDAO.obtenerMascota(idMascota);
				System.out.println(m);
				request.setAttribute("mascota", m);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editarmascota.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (opcion.equals("eliminar")) {
			MascotaDAO mascotaDAO = new MascotaDAO();
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				mascotaDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listamascotas.jsp");
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
		//Date fechaActual = new Date();

		if (opcion.equals("guardar")) {
			MascotaDAO mascotaDAO = new MascotaDAO();
			Mascota mascota = new Mascota();
			mascota.setRutDueno(Integer.parseInt(request.getParameter("rutD")));
			mascota.setTipoMascota(request.getParameter("tipo"));
			mascota.setEdad(Integer.parseInt(request.getParameter("edad")));
			mascota.setNombreMascota(request.getParameter("nombre"));
			try {
				
				mascotaDAO.guardar(mascota);
				System.out.println("Registro guardado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/agregarmascota.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("editar")) {
			MascotaDAO mascotaDAO = new MascotaDAO();
			Mascota mascota = new Mascota();

			mascota.setIdMascota(Integer.parseInt(request.getParameter("id")));
			mascota.setRutDueno(Integer.parseInt(request.getParameter("rutD")));
			mascota.setTipoMascota(request.getParameter("tipo"));
			mascota.setEdad(Integer.parseInt(request.getParameter("edad")));
			mascota.setNombreMascota(request.getParameter("nombre"));
			try {
				mascotaDAO.editar(mascota);
				System.out.println("Registro editado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editarmascotas.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}

}

package com.petSmile;

import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.petSmile.Agenda;
import com.petSmile.AgendaDAO;;

/**
 * Servlet implementation class AgendaController
 */
@WebServlet(description = "administra peticiones para la tabla agenda", urlPatterns = { "/agendas" })
public class AgendaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendaController() {
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

			AgendaDAO agendaDAO = new AgendaDAO();
			List<Agenda> lista = new ArrayList<>();
			try {
				lista = agendaDAO.obtenerAgendas();
				for (Agenda agenda : lista) {
					System.out.println(agenda);
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
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			AgendaDAO agendaDAO = new AgendaDAO();
			Agenda a = new Agenda();
			try {
				a = agendaDAO.obtenerAgenda(id);
				System.out.println(a);
				request.setAttribute("agenda", a);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (opcion.equals("eliminar")) {
			AgendaDAO agendaDAO = new AgendaDAO();
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				agendaDAO.eliminar(id);
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
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		//Date fechaActual = new Date();

		if (opcion.equals("guardar")) {
			AgendaDAO agendaDAO = new AgendaDAO();
			Agenda agenda = new Agenda();
			agenda.setIdMascota(Integer.parseInt(request.getParameter("idmascota")));
			agenda.setRutDueno(Integer.parseInt(request.getParameter("rut")));
			agenda.setNombreMascota(request.getParameter("nombremascota"));
			//agenda.setHora(new java.sql.Date(fechaActual.getTime()));
			agenda.setHora(new java.sql.Date(Date.parse(request.getParameter("hora"))));
			agenda.setFecha(new java.sql.Date(Date.parse(request.getParameter("fecha"))));
			//agenda.setFecha(new java.sql.Date(Calendar.parse(request.getParameter("fecha"))));
			try {
				agendaDAO.guardar(agenda);
				System.out.println("Registro guardado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("editar")) {
			Agenda agenda = new Agenda();
			AgendaDAO agendaDAO = new AgendaDAO();

			agenda.setId(Integer.parseInt(request.getParameter("id")));
			agenda.setIdMascota(Integer.parseInt(request.getParameter("idmascota")));
			agenda.setRutDueno(Integer.parseInt(request.getParameter("rut")));
			agenda.setNombreMascota(request.getParameter("nombremascota"));
			//agenda.setHora(new java.sql.Date(fechaActual.getTime()));
			agenda.setHora(new java.sql.Date(Date.parse(request.getParameter("hora"))));
			agenda.setFecha(new java.sql.Date(Date.parse(request.getParameter("fecha"))));
			try {
				agendaDAO.editar(agenda);
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

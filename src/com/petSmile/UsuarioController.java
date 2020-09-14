package com.petSmile;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.petSmile.Usuario;
import com.petSmile.UsuarioDAO;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet(description = "administra peticiones para la tabla usuarios", urlPatterns = { "/usuarios" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcion");

		if (opcion.equals("crear")) {
			System.out.println("Usted ha presionado la opcion crear");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/agregarusuario.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equals("listar")) {

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = new ArrayList<>();
			try {
				lista = usuarioDAO.obtenerUsuarios();
				for (Usuario usuario : lista) {
					System.out.println(usuario);
				}

				request.setAttribute("lista", lista);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listausuarios.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Usted ha presionado la opcion listar");
		} else if (opcion.equals("meditar")) {
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario u = new Usuario();
			try {
				u = usuarioDAO.obtenerUsuario(id);
				System.out.println(u);
				request.setAttribute("usuario", u);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editarusuario.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if (opcion.equals("eliminar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				usuarioDAO.eliminar(id);
				System.out.println("Registro eliminado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		//Date fechaActual = new Date();

		if (opcion.equals("guardar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();
			usuario.setNombreUsuario(request.getParameter("name"));
			usuario.setPassword(request.getParameter("password"));
			try {
				
				usuarioDAO.guardar(usuario);
				System.out.println("Registro guardado satisfactoriamente...");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/agregarusuario.jsp");
				requestDispatcher.forward(request, response);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (opcion.equals("editar")) {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();

			usuario.setId(Integer.parseInt(request.getParameter("id")));
			usuario.setNombreUsuario(request.getParameter("name"));
			usuario.setPassword(request.getParameter("cantidad"));
			try {
				usuarioDAO.editar(usuario);
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

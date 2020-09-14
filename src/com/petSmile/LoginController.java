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
import javax.servlet.http.HttpSession;

import com.petSmile.Usuario;
import com.petSmile.UsuarioDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(description = "administra peticiones para la tabla usuarios", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();
			String nombreUsuario= request.getParameter("name");
			String password= request.getParameter("pass");
			System.out.println("datos recibidos: user: "+nombreUsuario+" pass: "+password);
			try {
				usuario = usuarioDAO.usuarioLogin(nombreUsuario, password);
				System.out.println(usuario);
				if(nombreUsuario.isEmpty()&&password.isEmpty()) {
					System.out.println("Los campos no pueden estar vacios");
			      }else {
				      if(usuario.getNombreUsuario().length()==0) {
				    	  System.out.println("Usuario o contraseña incorrecto");
					    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
							requestDispatcher.forward(request, response);
				    	 
				        
				        
				      }else {
				    	  System.out.println("Usuario encontrado: user: "+usuario.getNombreUsuario()+" pass: "+usuario.getPassword());
				    	  response.sendRedirect("home.jsp");
				        //HttpSession session = request.getSession();
				      }
				
				
			}} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} 
	

}

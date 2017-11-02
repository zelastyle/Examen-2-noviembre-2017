package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Controller;
import modelo.Usuario;

/**
 * Servlet implementation class Servlet
 */
//@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	
	final String MOSTRAR = "SELECT * FROM usuarios WHERE usuario=? and clave=? ";
	final String COGER = "SELECT * FROM usuarios WHERE usuario=? ";


	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion = request.getSession();
		Controller ctr = new Controller();
		String base = "/jsp/";
		String url = base + "index.jsp";
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String usuario = request.getParameter("usuario");
		String clave = request.getParameter("clave");
		// recuperar datamanager del contexto

		if (action != null) {
			switch (action) {
			
			case "Ejercicio1":
				url = base + "ejercicio1.jsp";
				break;
			
			case "Ejercicio2":
				url = base + "ejercicio2.jsp";
				break;

			case "Logout":
				sesion.invalidate();
				url = base + "index.jsp";
				break;

			case "Logeo":
				if (request.getParameter("enviar") != null) {
					
					//System.out.println(request.getParameter("user"));
					personaInicio(request);
					url = base + "ejercicio2.jsp";
					break;
				}

				url = base + "ejercicio2.jsp";
				break;

			}
		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}
	
	public boolean inicioSesion(String usuario, String clave) {

		try {
			Controller ctr = new Controller();
			Connection con = ctr.conexion();
			PreparedStatement ps = con.prepareStatement(MOSTRAR);
			ResultSet rs;
			ps.setString(1, usuario);
			ps.setString(2, clave);
			rs = ps.executeQuery();
			return rs.next();

		}

		catch (SQLException e) {
		}
		return false;
	}

	public void personaInicio(HttpServletRequest request) {
		Usuario usr = new Usuario();
		// Controller ctr = new Controller();
		HttpSession sesion = request.getSession();

		boolean logeado = inicioSesion(request.getParameter("user"), request.getParameter("pass"));
		 //System.out.println(logeado);
		if (logeado == true) {
			usr = obtenerDatosUsuario(request.getParameter("user"));
			//System.out.println("caca");
			if (usr != null) {
				
				sesion.setAttribute("id", usr.getId());
				sesion.setAttribute("usuario", usr.getUsuario());
				sesion.setAttribute("clave", usr.getClave());
				
			}
		}
	}

	public Usuario obtenerDatosUsuario(String usuario) {
		try {
			Controller ctr = new Controller();
			Connection con = ctr.conexion();
			PreparedStatement ps = con.prepareStatement(COGER);
			ResultSet rs;
			ps.setString(1, usuario);
			rs = ps.executeQuery();
			boolean existe = rs.next();
			if (!existe) {
				// System.out.println("dentro");
				return null;
			}
			Usuario user = new Usuario(rs.getInt("id"), rs.getString("usuario"),rs.getString("clave"));		
			return user;
		} catch (SQLException e) {
		}
		return null;
	}

}

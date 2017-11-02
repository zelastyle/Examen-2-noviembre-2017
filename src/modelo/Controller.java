package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Controller {
	
	
	final String URL_BD = "jdbc:mysql://localhost:3306/examen";
	final String USUARIO = "root";
	final String PASS = "Alejandro97";
	final String CLASSE_DRIVER = "com.mysql.jdbc.Driver";
	
	public Connection conexion() {
		try {
			Class.forName(CLASSE_DRIVER);
			return DriverManager.getConnection(URL_BD, USUARIO, PASS);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof ClassNotFoundException) {
				System.err.println("VERIFIQUE Si EL DRIVER DE LA BD ESTA EN  CLASSPATH");
			} else {
				System.err.println("ESTA ARRANCANDO MYSQL ?, lAS CREDENCIALES ESTÁN BIEN ?");
			}
			System.exit(0);
			// el programa termina y devuelve el control al S.O.
			return null;
		}
	}
	


	
}

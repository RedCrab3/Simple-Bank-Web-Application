package com.luv2code.testdb;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Servlet implementation class TestDBServlet
 */
@RunWith(MockitoJUnitRunner.class)
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbcURL="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String user="CRMProject";
		String pwd="CRMProject";
		String driver="com.mysql.jdbc.Driver";
		boolean res = false; 
		try {
			PrintWriter out=response.getWriter();
			out.println("Connecting to DB "+jdbcURL);
			Class.forName(driver);
			Connection mycon=DriverManager.getConnection(jdbcURL,user,pwd);
			when(DriverManager.getConnection(jdbcURL,user,pwd)).thenReturn(mycon);
			out.println("Connection successful");
			mycon.close();
			res=true;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		finally {
			System.out.println(res);
			assertTrue(res);
		}
	}

}

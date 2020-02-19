package com.cptpackage.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = -6932316655961898247L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("HEY", "I SAID HEY HEY!!");
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
}

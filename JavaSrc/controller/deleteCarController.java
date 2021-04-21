package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarDao;
@WebServlet(urlPatterns = {"/deleteCar"})
public class deleteCarController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			CarDao carDao = new CarDao();
			String lisp = req.getParameter("licensePlate");
			carDao.deleteCars(lisp);
			resp.sendRedirect("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
}

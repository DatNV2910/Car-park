package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ParkingLotDao;
@WebServlet(urlPatterns = {"/deletePark"})
public class deleteParkingLotController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ParkingLotDao pd = new ParkingLotDao();
			  int id = Integer.parseInt(req.getParameter("id"));
			  pd.deleteParkingLot(id);
			  resp.sendRedirect("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	  
	  
	}
}

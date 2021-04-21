package controller;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ParkingLot;
import dao.ParkingLotDao;
@WebServlet(urlPatterns = {"/addParkinglot"})
public class AddParkingLotController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ParkingLotDao pd = new ParkingLotDao();
			String parkingName = req.getParameter("parkingName"); 
			String parkingPlace = req.getParameter("parkingPlace"); 
			long parkingArea = Long.parseLong(req.getParameter("parkingArea"));
			long parkingPrice = Long.parseLong(req.getParameter("parkingPrice")); 
			String status = "Blank"; 
			ParkingLot parkinglot = new ParkingLot(parkingArea, parkingName, parkingPlace, parkingPrice, status); 
			pd.addParkinglot(parkinglot); 
			resp.sendRedirect(""); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

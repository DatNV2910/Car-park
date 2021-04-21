package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ParkingLot;
import dao.ParkingLotDao;
@WebServlet(urlPatterns = {"/updatePark"})
public class UpdateParkingLotController extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		ParkingLotDao pd = new ParkingLotDao();
		String parkName = req.getParameter("parkingName"); 
		String parkPlace = req.getParameter("parkingPlace"); 
		long parkArea = Long.parseLong(req.getParameter("parkingArea")); 
		long parkPrice = Long.parseLong(req.getParameter("parkingPrice")); 
		String parkStatus = req.getParameter("parkingStatus"); 
		ParkingLot parkingLot = new ParkingLot(parkArea, parkName, parkPlace, parkPrice, parkStatus);
		pd.updateParkingLot(parkingLot); 
		resp.sendRedirect("");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}

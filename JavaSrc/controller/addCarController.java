package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Car;
import Model.ParkingLot;
import dao.CarDao;
@WebServlet(urlPatterns = {"/addCar"})
public class addCarController extends HttpServlet{
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	try {
		CarDao carDao = new CarDao();
		String licensePlate = req.getParameter("licensePlate"); 
		String carType = req.getParameter("carType"); 
		String carColor = req.getParameter("carColor"); 
		String company = req.getParameter("companny");
		long parkId = Long.parseLong(req.getParameter("parkId")); 
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setParkId(parkId);
		Car car = new Car(licensePlate, carColor, carType, company, parkingLot);
		carDao.addCar(car); 
		resp.sendRedirect("");
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}
}

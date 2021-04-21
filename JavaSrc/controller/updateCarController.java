package controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Car;
import Model.ParkingLot;
import dao.CarDao;
@WebServlet(urlPatterns = {"/updateCar"})
public class updateCarController extends HttpServlet{
protected void process(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
	try {
		CarDao carDao  = new CarDao();
		String lincesPlate = request.getParameter("licensePlate"); 
		String carColor = request.getParameter("carColor"); 
		String carType = request.getParameter("carType"); 
		String company = request.getParameter("company"); 
		long parkId = Long.parseLong(request.getParameter("parkId"));
		ParkingLot parkingLot = new ParkingLot();
		parkingLot.setParkId(parkId);
		Car car = new Car(lincesPlate, carColor, carType, company, parkingLot); 
		carDao.UpdateCar(car); 
		response.sendRedirect("");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}

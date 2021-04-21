package controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Trip;
import dao.TripDao;
@WebServlet(urlPatterns = {"/addTrip"})
public class addTripController extends HttpServlet  {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			TripDao td = new TripDao(); 
			String destination = req.getParameter("destination");
			LocalTime departureTime = Time.valueOf(req.getParameter("departureTime")).toLocalTime();
			String driver = req.getParameter("driver"); 
			String carType = req.getParameter("carType"); 
			int maximumOnlineTicketNumber = Integer.parseInt(req.getParameter("maximumOnlineTicketNumber")); 
			LocalDate departureDate = Date.valueOf(req.getParameter("departureDate")).toLocalDate();
			Random random = new Random();
			int bookedTicketNumber = random.nextInt();
			Trip trip = new Trip(bookedTicketNumber, carType, departureDate, departureTime, destination, driver, maximumOnlineTicketNumber);
			td.UpdateTrip(trip);
			resp.sendRedirect("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TicketDao;
@WebServlet(urlPatterns = {"/deleteTicket"})
public class DeleteTicketController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			TicketDao td = new TicketDao();
			int id = Integer.parseInt(req.getParameter("id"));
			td.deleteTicket(id); 
			resp.sendRedirect("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

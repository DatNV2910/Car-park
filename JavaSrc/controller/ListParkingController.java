package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.ParkingLot;
import dao.CarDao;
import dao.ParkingLotDao;
@WebServlet(urlPatterns = {"/listParkingController"})
public class ListParkingController extends HttpServlet{
protected void process(HttpServletRequest request , HttpServletResponse response, boolean type) throws ServletException, IOException{
	List<ParkingLot> listPark = null;
	String input = request.getParameter("searchKey"); 
	System.out.println(input + "this is timing");
	String raw_searchBy = request.getParameter("searchBy"); 
	String raw_pindex = request.getParameter("pindex"); 
	String typeSearch = request.getParameter("typeSearch"); 
	int pindex = -1 ; 
	int searchBy = -1; 
	try {
		pindex = raw_pindex == null ? 1 : Integer.parseInt(raw_pindex); 
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	int psize = 1; 
	int totalPage = -1; 
	int numberRecord = -1; 
	try {
		if(type && typeSearch == null) {
			numberRecord = ParkingLotDao.countRecord(); 
		} else if(!type || typeSearch != null) {
			try {
				searchBy = Integer.parseInt(raw_searchBy);
			} catch (Exception e) {
				e.printStackTrace();
				return; 
			}
			numberRecord = ParkingLotDao.countRecordFilter(searchBy, input);
		}
		if(numberRecord == -1 ) {
			return;
		}
		totalPage = numberRecord % psize == 0 ? (numberRecord / psize) : ((numberRecord / psize)+ 1);
		if(type && typeSearch == null) {
			listPark = ParkingLotDao.pagingSearch(pindex, psize); 
			
		}else if(!type || typeSearch != null) {
			listPark = ParkingLotDao.searchByFilter(searchBy, input, pindex, psize);
			for (ParkingLot parkingLot : listPark) {
				System.out.println(parkingLot);
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	request.setAttribute("searchBy", searchBy);
	request.setAttribute("searchKey", input);
	request.setAttribute("listPark", listPark);
	request.setAttribute("pindex", pindex);
	request.setAttribute("totalPage", totalPage);
	request.setAttribute("typeSearch", typeSearch);
	request.getRequestDispatcher("").forward(request,response);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}

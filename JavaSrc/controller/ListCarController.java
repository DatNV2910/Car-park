package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Car;
import dao.CarDao;
@WebServlet(urlPatterns = {"/listCar"})
public class ListCarController  extends HttpServlet{
  protected void process(HttpServletRequest req, HttpServletResponse resp, boolean type) throws ServletException, IOException{
	  List<Car> listCars = null;
		String input = req.getParameter("searchKey"); 
		System.out.println(input + "this is timing");
		String raw_Index = req.getParameter("pindex");
	    String raw_SearchBy = req.getParameter("searchBy"); 
	    String typeSearch = req.getParameter("typeSearch"); 
	    int pindex = -1; 
	    int searchBy = -1; 
	    try {
			pindex = raw_Index == null ? 1 : Integer.parseInt(raw_Index); 
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	    int psize = 1; 
	    int totalPage = -1; 
	    int numberRecord = -1; 
	    try {
			if(type && typeSearch == null) {
				numberRecord = CarDao.countRecord();
				
			}else if(!type || typeSearch != null) {
				try {
					searchBy = Integer.parseInt(raw_SearchBy);
				} catch (Exception e) {
					e.printStackTrace();
					return; 
				}
				numberRecord = CarDao.countRecordFilter(searchBy, input);
				
				
			}
			if(numberRecord == -1) {
				return; 
			}
			totalPage = numberRecord % psize == 0 ? (numberRecord/psize) : ((numberRecord/psize)+1);
			if(type && typeSearch == null) {
				listCars = CarDao.pagingSearch(pindex, psize); 
				
			} else if(!type || typeSearch != null) {
				listCars = CarDao.searchByFilter(searchBy, input, pindex, psize);
				for (Car car : listCars) {
					System.out.println(car);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    req.setAttribute("searchBy", searchBy);
	    req.setAttribute("searchKey", input);
	    req.setAttribute("listCar", listCars);
	    req.setAttribute("pindex", pindex);
	    req.setAttribute("totalPage", totalPage);
	    req.setAttribute("typeSearch", typeSearch);
	    req.getRequestDispatcher("").forward(req, resp);
  
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

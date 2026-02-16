package Health.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DAOFactory;
import Health.DiagnosticBean;
import Health.DiagnosticDAO;
import Health.DiagnosticDaoImpl;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/diagnostics")
public class Diagnostics extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private DiagnosticDaoImpl diagnosticDAO;
	
    public Diagnostics() {
        super();
    }
    
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.diagnosticDAO = daoFactory.getDiagnosticDAO();
	}
	
	private void renderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	getServletContext().getRequestDispatcher("/WEB-INF/views/health/diagnostics.jsp").forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean userBean = userDAO.auth(request);
		
		if(userBean == null) {
			response.sendRedirect("login");
			return;
		}
		
		try {
			
			ArrayList<DiagnosticBean> list = diagnosticDAO.all(userBean.getID());
			request.setAttribute("list", list);
			
			renderForm(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

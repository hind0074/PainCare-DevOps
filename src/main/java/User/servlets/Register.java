package User.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Database.DAOFactory;
import User.UserDaoImpl;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
	}
	
    public Register() {
        super();
    }
    
    private void renderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	getServletContext().getRequestDispatcher("/WEB-INF/views/users/register.jsp").forward(request, response);
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		renderForm(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		if(!password.equals(password2)) {
			request.setAttribute("form_error", "The two password are not idetical");

			renderForm(request, response);
			return;
		}
		
		try {
			userDAO.register(username, email, password);
			response.sendRedirect("login");
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}

}

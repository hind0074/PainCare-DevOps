package User.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
	}  
	
    public Login() {
        super();
    }
    
    private void renderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	getServletContext().getRequestDispatcher("/WEB-INF/views/users/login.jsp").forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		renderForm(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(email.equals("") || password.equals("")) {
			request.setAttribute("form_error", "Please fill all fields");

			renderForm(request, response);
			return;
		}
		
		try {
			UserBean userBean = userDAO.login(email, password);
			
			if(userBean == null) {
				request.setAttribute("form_error", "Unvalid credentails");

				renderForm(request, response);
				return;
			}
			
			HttpSession session = request.getSession();
			
			session.setAttribute("user", userBean);
			
			response.sendRedirect("home");
		} catch (SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}

}

package Blog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Blog.BlogBean;
import Blog.BlogDaoImpl;
import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

/**
 * Servlet implementation class UserBlog
 */
@WebServlet("/manage_blog")
public class UserBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private BlogDaoImpl blogDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.blogDAO = daoFactory.getBlogDAO();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   try {
			    UserBean userBean = userDAO.auth(request);
	            // Get the current page from the request parameter, default to 1 if not provided
			 
	            int currentPage = Integer.parseInt(request.getParameter("page") != null ?
	                    request.getParameter("page") : "1");

	            // Set the desired page size
	            int pageSize = 4;

	            // Retrieve total records from the DAO or other source
	            int totalRecords =blogDAO.Count(userBean.getID()); // Get the total number of records from your DAO or other source

	            // Calculate total pages
	            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
              
	            // Set attributes for JSP
	            request.setAttribute("blogs", blogDAO.getBlogUser(currentPage, pageSize, userBean.getID()));
	            request.setAttribute("totalPages", totalPages);
	            request.setAttribute("currentPage", currentPage);
	            request.setAttribute("userBean", userBean);
		         
	         
	            // Forward the request to your JSP page
	            getServletContext().getRequestDispatcher("/WEB-INF/views/users/userBlog.jsp").forward(request, response);
	        } catch (SQLException | NumberFormatException e) {
	            e.printStackTrace();
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write(e.getMessage());
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

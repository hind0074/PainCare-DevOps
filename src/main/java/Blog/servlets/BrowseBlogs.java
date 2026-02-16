package Blog.servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Blog.BlogDaoImpl;
import Database.DAOFactory;
import User.UserDaoImpl;

@WebServlet("/blogs")
public class BrowseBlogs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private BlogDaoImpl blogDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.blogDAO = daoFactory.getBlogDAO();
	}
	
    public BrowseBlogs() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the current page from the request parameter, default to 1 if not provided
            int currentPage = Integer.parseInt(request.getParameter("page") != null ?
                    request.getParameter("page") : "1");

            // Set the desired page size
            int pageSize = 6;

            // Retrieve total records from the DAO or other source
            int totalRecords =blogDAO.CountAll(); // Get the total number of records from your DAO or other source

            // Calculate total pages
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // Set attributes for JSP
            request.setAttribute("blogs", blogDAO.getAllWithPagination(currentPage, pageSize));
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("currentPage", currentPage);

            // Forward the request to your JSP page
            getServletContext().getRequestDispatcher("/WEB-INF/views/comunity/blogs.jsp").forward(request, response);
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(e.getMessage());
        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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
import Blog.CommentBean;
import Blog.CommentDaoImpl;
import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/blog")
public class Blog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private BlogDaoImpl blogDAO;
	private CommentDaoImpl commentDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.blogDAO = daoFactory.getBlogDAO();
		this.commentDAO = daoFactory.getCommentDAO();
	} 
	
    public Blog() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			 UserBean userBean = userDAO.auth(request);
			String id = request.getParameter("id");
			 request.setAttribute("blogs", blogDAO.three());
			BlogBean blog = blogDAO.one(Integer.parseInt(id));
			
			if(blog == null) {
				response.setStatus(404);
				return;
			}
			
			ArrayList<CommentBean> comments = commentDAO.blogComments(Integer.parseInt(id));
			
			
			request.setAttribute("blog", blog);
			request.setAttribute("comments", comments);
			request.setAttribute("userBean", userBean);
			getServletContext().getRequestDispatcher("/WEB-INF/views/comunity/blog.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (NumberFormatException e) {
			
			response.setStatus(400);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

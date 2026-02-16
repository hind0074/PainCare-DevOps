package Blog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Blog.BlogBean;
import Blog.BlogDaoImpl;
import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/remove_blog")
public class DeleteBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private BlogDaoImpl blogDAO;
	
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.blogDAO = daoFactory.getBlogDAO();
		
	}
	
    public DeleteBlog() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    UserBean userBean = userDAO.auth(request);
	    
	    if(userBean == null) {
	        response.sendRedirect("login");
	        return;
	    }
	    
	    String blog_id = request.getParameter("blog_id");
	    
	    try {
	        BlogBean blog = blogDAO.one(Integer.parseInt(blog_id));
	        
	        if(blog == null) {
	            response.sendRedirect("UserBlog?id=" + blog.getUserID());
	            return;
	        }
	        
	        if(blog.getUserID() != userBean.getID()) {
	            response.sendError(403);
	            return;
	        }
	        
	        blogDAO.delete(Integer.parseInt(blog_id));
	        
	        response.sendRedirect("UserBlog?id=" + blog.getUserID());
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	        response.setStatus(500);
	        response.getWriter().write(e.getMessage());
	    } catch (NumberFormatException e) {
	        response.sendError(400);
	    }
	}

}

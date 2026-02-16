package Blog.servlets;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Blog.CommentDaoImpl;
import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private CommentDaoImpl commentDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.commentDAO = daoFactory.getCommentDAO();
	} 
	
    public Comment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean userBean = userDAO.auth(request);
		
		if(userBean == null) {
			response.sendRedirect("login");
			return;
		}
		
		String blog_id = request.getParameter("blog_id");
		String content = request.getParameter("content");
		
		try {
			commentDAO.create(userBean.getID(), Integer.parseInt(blog_id), content);
			
			response.sendRedirect("blog?id=" + blog_id);
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}

}

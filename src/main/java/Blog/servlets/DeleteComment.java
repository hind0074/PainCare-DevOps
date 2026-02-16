package Blog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import Blog.CommentBean;
import Blog.CommentDaoImpl;
import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/remove_comment")
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private CommentDaoImpl commentDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.commentDAO = daoFactory.getCommentDAO();
	}
    
    public DeleteComment() {
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
		
		String comment_id = request.getParameter("comment_id");
		
		
		try {
			CommentBean comment = commentDAO.one(Integer.parseInt(comment_id));
			
			if(comment == null) {
				response.sendRedirect("blog?id=" + comment.getBlogID());
				return;
			}
			
			if(comment.getUserID() != userBean.getID()) {
				response.sendError(403);
				return;
			}
			
			commentDAO.delete(Integer.parseInt(comment_id));
			
			response.sendRedirect("blog?id=" + comment.getBlogID());
		} catch (SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		} catch (NumberFormatException e) {
			response.sendError(400);
		}
	}
}

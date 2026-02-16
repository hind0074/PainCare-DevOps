package Pages;

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


@WebServlet("/home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Home() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		BlogDaoImpl dao = new BlogDaoImpl(daoFactory); // Replace with actual class names
		ArrayList<BlogBean> listOfBlogs = null;
		

		try {
			listOfBlogs = dao.three();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}

		request.setAttribute("listOfBlogs", listOfBlogs);
		getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

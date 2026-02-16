package Blog.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import Blog.BlogDaoImpl;
import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/publish")
@MultipartConfig
public class Publish extends HttpServlet {
	private static final String BLOGS_IMAGES_DIR = "assets/blogs-images/";
	private static final long serialVersionUID = 1L;
	private DAOFactory daoFactory;
	private UserDaoImpl userDAO;
	private BlogDaoImpl blogDAO;
	
	public void init() throws ServletException {
		this.daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.blogDAO = daoFactory.getBlogDAO();
	}
	
    public Publish() {
        super();
    }
    
    private static String getMeidaExt(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                
                return filename.substring(filename.lastIndexOf('.') + 1);
            }
        }
        return null;
    }
    
    private String savePart(Part image) {
		long id = System.currentTimeMillis();
		String ext = getMeidaExt(image);
		String uploadPath = this.daoFactory.WEB_CONTENT_FOLDER + BLOGS_IMAGES_DIR + id + "." + ext;
				
        try (InputStream input = image.getInputStream();
             OutputStream output = new FileOutputStream(uploadPath)) {
        	
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return BLOGS_IMAGES_DIR + id + "." + ext;
	}
    
    private void renderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	getServletContext().getRequestDispatcher("/WEB-INF/views/comunity/publish.jsp").forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean userBean = userDAO.auth(request);
		
		if(userBean == null) {
			response.sendRedirect("login");
			return;
		}
		
		renderForm(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean userBean = userDAO.auth(request);
		
		if(userBean == null) {
			response.sendRedirect("login");
			return;
		}
		
		Part image = request.getPart("image");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		
		if(image == null ||  image.getSize() == 0 || title.equals("") || description.equals("")) {
			request.setAttribute("form_error", "Please fill all fields");
			renderForm(request, response);
			return;
		}
		
		// save blog image in our blogs-images folder
		String imageLink = savePart(image);
		
		try {
			blogDAO.create(userBean.getID(), title, description, imageLink);
			
			response.sendRedirect("blogs");
		} catch (SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}

}

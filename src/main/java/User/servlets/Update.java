package User.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.imageio.ImageIO;

import Database.DAOFactory;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/update_profile")
@MultipartConfig
public class Update extends HttpServlet {
	private static final String AVATARS_DIR = "assets/avatars/";
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private DAOFactory daoFactory;
	
	public void init() throws ServletException {
		this.daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
	}
	
    public Update() {
        super();
    }
    
    private void renderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	getServletContext().getRequestDispatcher("/WEB-INF/views/users/update.jsp").forward(request, response);
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
		String uploadPath = this.daoFactory.WEB_CONTENT_FOLDER + AVATARS_DIR + id + "." + ext;
				
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
        
        return AVATARS_DIR + id + "." + ext;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean bean = userDAO.auth(request);
		
		if(bean == null) {
			response.sendRedirect("login");
			return;
		}
		
		renderForm(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean bean = userDAO.auth(request);
		
		if(bean == null) {
			response.sendRedirect("login");
			return;
		}
		
		Part image = request.getPart("avatar");
		String name = request.getParameter("name");
		String birthDay = request.getParameter("birth-day");
				
		if(image == null || name.equals("") || birthDay.equals("")) {
			request.setAttribute("form_error", "Please fill all fields");
			renderForm(request, response);
			return;
		}
		
		if(image.getSize() > 0) {
			// save avatar in our avatars folder
			String avatar = savePart(image);
			
			// setup our user bean
			bean.setAvatar(avatar);
		}
		
		// convert birthday date to sql date
		LocalDate localDate = LocalDate.parse(birthDay);
        Date sqlDate = java.sql.Date.valueOf(localDate);
		
		bean.setName(name);
		bean.setBirthDay(sqlDate);
		
		// save our bean
		try {
			userDAO.update(bean);
			
			response.sendRedirect("profile");
		} catch (SQLException e) {
			e.printStackTrace();
			
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}

}

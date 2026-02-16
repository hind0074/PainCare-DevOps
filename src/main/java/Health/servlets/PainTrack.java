package Health.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DAOFactory;
import Health.PainTrackDaoImpl;
import Health.PainTrackParamDaoImpl;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/track_pain")
public class PainTrack extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private PainTrackDaoImpl painTrackDAO;
	private PainTrackParamDaoImpl painTrackParamDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.painTrackDAO = daoFactory.getPainTrackDAO();
		this.painTrackParamDAO = daoFactory.getPainTrackParamDAO();
	}  
       
    public PainTrack() {
        super();
    }
    
    private void renderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	getServletContext().getRequestDispatcher("/WEB-INF/views/health/pain_track.jsp").forward(request, response);
    }
    
    private String mapParamValue(String param, int value) throws ServletException {
    	switch(param) {
    		case "locations": switch(value) {
    			case 0 : return "Abdomen";
    			case 1 : return "Back";
    			case 2 : return "Chest";
    			case 3 : return "Head";
    			case 4 : return "Neck";
    			case 5 : return "Hips";
    		}; break;
    		case "symptoms": switch(value) {
				case 0 : return "Cramps";
				case 1 : return "Tender breasts";
				case 2 : return "Headache";
				case 3 : return "Acne";
				case 4 : return "Fatigue";
				case 5 : return "Bloating";
				case 6 : return "Craving";
			}; break;
    		case "worse_pain": switch(value) {
				case 0 : return "Lack of sleep";
				case 1 : return "Sitting";
				case 2 : return "Standing";
				case 3 : return "Stress";
				case 4 : return "Walking";
				case 5 : return "Exercise";
				case 6 : return "Urination";
			}; break;
    		case "feelings": switch(value) {
				case 0 : return "Anxious";
				case 1 : return "Depressed";
				case 2 : return "Dizzy";
				case 3 : return "Vomiting";
				case 4 : return "Diarrhea";
			}; break;
    	}
    	
    	throw new ServletException("undefined indetifier for param : " + param + " and value : " + value);
    }
    
    private ArrayList<String> mapParamValues(String param, String values) throws NumberFormatException, ServletException {
        String[] valuesList = values.split(",");

        ArrayList<String> parsedValuesList = new ArrayList<>();

        for (String value : valuesList) {
        	parsedValuesList.add(mapParamValue(param, Integer.parseInt(value)));
        }
        
        return parsedValuesList;
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
		
		String level = request.getParameter("level");
		String locations = request.getParameter("locations");
		String symptoms = request.getParameter("symptoms");
		String worsePain = request.getParameter("worse_pain");
		String feelings = request.getParameter("feelings");
		
		try {
			int painTrackID = painTrackDAO.create(Integer.parseInt(level), userBean.getID());
			
			if(!locations.equals("")) painTrackParamDAO.create(painTrackID, "locations",  mapParamValues("locations",  locations));
			if(!symptoms.equals(""))  painTrackParamDAO.create(painTrackID, "symptoms",   mapParamValues("symptoms",   symptoms));
			if(!worsePain.equals("")) painTrackParamDAO.create(painTrackID, "worse_pain", mapParamValues("worse_pain", worsePain));
			if(!feelings.equals(""))  painTrackParamDAO.create(painTrackID, "feelings",   mapParamValues("feelings",   feelings));
			
			response.sendRedirect("profile");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}

}

package Pages;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import Database.DAOFactory;
import Health.DiagnosticDaoImpl;
import Health.PainTrackDaoImpl;
import Health.PainTrackParamDaoImpl;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDAO;
	private DiagnosticDaoImpl diagnosticDAO;
	private PainTrackDaoImpl painTrackDAO;
	private PainTrackParamDaoImpl painTrackParamDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.diagnosticDAO = daoFactory.getDiagnosticDAO();
		this.painTrackDAO = daoFactory.getPainTrackDAO();
		this.painTrackParamDAO = daoFactory.getPainTrackParamDAO();
	}
	
    public Profile() {
        super();
    }
    
	private String mapToJsonString(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("\"").append(entry.getKey()).append("\": ").append(entry.getValue());
            first = false;
        }

        sb.append("}");
        return sb.toString();
    }
	
	private String arrayListToJsonArray(ArrayList<Integer> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        boolean first = true;
        for (Integer value : arrayList) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(value);
            first = false;
        }

        sb.append("]");
        return sb.toString();
    }
	
	private String arrayListOfDatesToJsonArray(ArrayList<Date> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        boolean first = true;
        for (Date value : arrayList) {
            if (!first) {
                sb.append(", ");
            }
            sb.append("\"" + value.toString() + "\"");
            first = false;
        }

        sb.append("]");
        return sb.toString();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean userBean = userDAO.auth(request);
		
		if(userBean == null) {
			response.sendRedirect("login");
			return;
		}
		
		try {
			ArrayList<Integer> painStats = painTrackDAO.painLevelStats(userBean.getID());
			ArrayList<Date> painDates = painTrackDAO.painLevelDates(userBean.getID());
			
			Map<String, Integer> locationsMap = painTrackParamDAO.paramStats("locations", userBean.getID());
			Map<String, Integer> symptomsMap = painTrackParamDAO.paramStats("symptoms", userBean.getID());
			Map<String, Integer> worsePainMap = painTrackParamDAO.paramStats("worse_pain", userBean.getID());
			Map<String, Integer> feelingsMap = painTrackParamDAO.paramStats("feelings", userBean.getID());
			
			request.setAttribute("painArray", arrayListToJsonArray(painStats));
			request.setAttribute("painDatesArray", arrayListOfDatesToJsonArray(painDates));
			
			request.setAttribute("locationsObject", mapToJsonString(locationsMap));
			request.setAttribute("symptomsObject", mapToJsonString(symptomsMap));
			request.setAttribute("worsePainObject", mapToJsonString(worsePainMap));
			request.setAttribute("feelingsObject", mapToJsonString(feelingsMap));
			
			request.setAttribute("diagnosticBean", diagnosticDAO.latest(userBean.getID()));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		getServletContext().getRequestDispatcher("/WEB-INF/views/users/profile.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

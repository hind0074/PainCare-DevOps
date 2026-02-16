package Health.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import Health.MemoryLeakSimulator;


import Database.DAOFactory;
import Health.DiagnosticBean;
import Health.DiagnosticDaoImpl;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/diagnostic")
public class Diagnostic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final Object[][] questionsBank = {
	    {"radio", "When do you start your period ?", new String[]{
    		"Before 11 years old",
    		"Above 11 years old"
	    }, 1},
	    {"radio", "Your menstrual cycle length average ?", new String[]{
    		"Less than 27 days",
    		"More than 27 days",
    		"Not sure"
    	}, 1},
	    {"radio", "Do you have a familly history of endometriosis ?", new String[]{
    		"Yes",
    		"No"
    	}, 1},
	    {"radio", "Did you give birth ?", new String[]{
    		"Yes",
    		"No"
    	}, 1},
	    {"radio", "Do you have trouble getting pregnant ?", new String[]{
    		"Yes",
    		"No"
    	}, 1},
	    {"number", "Body mass index: calculate your BMI", new String[]{
    		"Enter your weight in kg",
    		"Enter your height in cm"
    	}, 3},
	    {"radio", "What is your abdominal/pelvic pain intensity ?", new String[]{
    		"0-2",
    		"3-5",
    		"6-8",
    		"9-10"
    	}, 1},
	    {"checkbox", "When do you experience abdominal or pelvic pain ?", new String[]{
    		"Related to period",
    		"Related to ovulation",
    		"Unrelated to period and ovulation"
    	}, 1},
	    {"radio", "Severity of pain during intercourse ?", new String[]{
    		"0-2",
    		"3-5",
    		"6-8",
    		"9-10"
    	}, 1},
	    {"checkbox", "What makes your pain worse ?", new String[]{
    		"Orgasm",
    		"Bowel movement",
    		"Full bladder",
    		"Urination",
    		"Not related to anything"
    	}, 1},
	    {"radio", "Duration of period ?", new String[]{
    		"1-7 days",
    		"More than 7 days"
    	}, 1},
	    {"radio", "Nature of periods ?", new String[]{
    		"Heavy",
    		"Moderate",
    		"Light"
    	}, 1},
	    {"checkbox", "What is your menstrual cycle pattern ?", new String[]{
    		"Regular",
    		"Irregular",
    		"Bleeding or spotting between periods"
    	}, 1},
	    {"checkbox", " Do you have ?", new String[]{
    		"Blood in your stool",
    		"Diarrhea",
    		"Constipation",
    		"Abdominal Bloating"
    	}, 1},
	    {"radio", "Have you ever been victim of physical abuse or sexual abuse ?", new String[]{
    		"Yes",
    		"No",
    	}, 1},
	};
	private UserDaoImpl userDAO;
	private DiagnosticDaoImpl diagnosticDAO;
	
	public void init() throws ServletException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		this.userDAO = daoFactory.getUserDAO();
		this.diagnosticDAO = daoFactory.getDiagnosticDAO();
	}  
       
    public Diagnostic() {
        super();
    }
    
    private void renderForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setAttribute("questionsBank", questionsBank);
    	getServletContext().getRequestDispatcher("/WEB-INF/views/health/diagnostic.jsp").forward(request, response);
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
		/*
		if (Math.random() < 0.2) {
        throw new RuntimeException("Random failure");
        }
		*/

		if(userBean == null) {
			response.sendRedirect("login");
			return;
		}
		
        // Simuler la fuite mémoire à chaque requête
        MemoryLeakSimulator.leak();

		String answers = request.getParameter("answers");
		 System.out.println("POST received, answers: " + answers);
		
		try {
			diagnosticDAO.create(answers, userBean.getID());
			
			DiagnosticBean diagnosticBean = diagnosticDAO.latest(userBean.getID());
			
			request.setAttribute("diagnosticBean", diagnosticBean);
			
			renderForm(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.getWriter().write(e.getMessage());
		}
	}

}

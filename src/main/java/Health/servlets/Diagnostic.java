package Health.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import Health.MemoryLeakSimulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import Health.MemoryMetrics;                      


import Database.DAOFactory;
import Health.DiagnosticBean;
import Health.DiagnosticDaoImpl;
import User.UserBean;
import User.UserDaoImpl;

@WebServlet("/diagnostic")
public class Diagnostic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(Diagnostic.class);
   

   //pour fuite mem
/*
    private static final PrometheusMeterRegistry registry = DiagnosticDaoImpl.getRegistry();
    private static final Counter memoryLeakCounter =
    Counter.builder("memory_leak_incidents_total")
           .description("Nombre d'incidents de fuite mémoire détectés")
           .register(registry);
   */

    // erreur intermittente

	private static final PrometheusMeterRegistry registry = DiagnosticDaoImpl.getRegistry();

    private static final Counter intermittentErrorCounter =
    Counter.builder("intermittent_errors_total")
           .description("Nombre total d'erreurs intermittentes")
           .register(registry);

    private static final Timer requestTimer =
    Timer.builder("diagnostic_request_duration_seconds")
         .description("Temps de traitement des requêtes diagnostic")
         .register(registry);


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
		this.diagnosticDAO = new DiagnosticDaoImpl(daoFactory);
		/*pour l incident fuite mem
	    MemoryMetrics.registerMemoryMetrics(registry);*/
		
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    long startTime = System.currentTimeMillis(); 
    String requestId = java.util.UUID.randomUUID().toString();
    
    /* Simulation fuite mémoire
    MemoryLeakSimulator.leak();
    logger.info("MEMORY_LEAK simulated for requestId={}", requestId);
    memoryLeakCounter.increment();
    */
    try {
        UserBean userBean = userDAO.auth(request);

        if (Math.random() < 0.2) {
        intermittentErrorCounter.increment(); 
        logger.error("INTERMITTENT_ERROR requestId={}", requestId);
        throw new RuntimeException("Random failure");
        }

        if (userBean == null) {
            logger.warn("UNAUTHORIZED requestId={}", requestId);
            response.sendRedirect("login");
            return;
        }

        String answers = request.getParameter("answers");
        diagnosticDAO.create(answers, userBean.getID());

        DiagnosticBean diagnosticBean = diagnosticDAO.latest(userBean.getID());
        request.setAttribute("diagnosticBean", diagnosticBean);

        long duration = System.currentTimeMillis() - startTime;
       //pour erreur intermettente
        requestTimer.record(duration, java.util.concurrent.TimeUnit.MILLISECONDS);
        renderForm(request, response);

    } catch (SQLException e) {
        
        long duration = System.currentTimeMillis() - startTime;
        logger.error("SQL_ERROR requestId={} duration={}ms", requestId, duration, e);
        response.setStatus(500);
        response.getWriter().write(e.getMessage());

    } catch (Exception e) {
      
        long duration = System.currentTimeMillis() - startTime;
        logger.error("REQUEST_FAILED requestId={} duration={}ms", requestId, duration, e);
        throw e;
    }
}

}

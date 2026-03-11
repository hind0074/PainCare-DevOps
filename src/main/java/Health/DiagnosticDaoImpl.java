package Health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Timer;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;

import Database.DAOFactory;

public class DiagnosticDaoImpl implements DiagnosticDAO {
	private DAOFactory daoFactory;
	private static final Logger logger = LoggerFactory.getLogger(DiagnosticDaoImpl.class);
    private static final PrometheusMeterRegistry registry =
        new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);
        
    //pour req lourd
    private final Counter alertCounter = registry.counter("diagnostic_alert_total");
    private final Timer slowRequestTimer = registry.timer("diagnostic_slow_request_duration_seconds");
    private final Counter slowRequestCounter = registry.counter("diagnostic_slow_request_total");

    public DiagnosticDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
       
    }
    public static PrometheusMeterRegistry getRegistry() {
        return registry;
    }

    private static DiagnosticBean getBean(ResultSet res) throws SQLException {
    	DiagnosticBean bean = new DiagnosticBean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setUserID(res.getInt("user_id"));
    	bean.setAnswers(res.getString("answers"));
    	bean.setDate(res.getDate("date"));
    	
    	return bean;
    }
    
	@Override
	public void create(String answers, int user_id) throws SQLException {

    Connection conn = daoFactory.getConnection();

    long startTime = System.currentTimeMillis();  // Début total

    // ---- Requête inutile / lourde ----
    String slowSQL = 
    "SELECT d1.id, d2.id " +
    "FROM diagnostics d1 " +
    "JOIN diagnostics d2 ON d1.id <> d2.id " +
    "LIMIT 4000";  // assez pour faire patienter un peu la DB

    PreparedStatement slowStmt = conn.prepareStatement(slowSQL);
    slowStmt.executeQuery();
    slowStmt.close();

    // ---- Requête normale ----
    String SQL = "INSERT INTO diagnostics (answers, user_id) VALUES(?, ?);";
    PreparedStatement statement = conn.prepareStatement(SQL);
    statement.setString(1, answers);
    statement.setInt(2, user_id);
    statement.execute();
    statement.close();

    long duration = System.currentTimeMillis() - startTime;  // Fin totale

    if (duration > 5000) {
        slowRequestCounter.increment();  // On compte l'incident
        slowRequestTimer.record(duration, java.util.concurrent.TimeUnit.MILLISECONDS);
        logger.warn("SLOW_DAO_OPERATION detected: total execution took {} ms", duration);
        alertCounter.increment();  // compteur des alertes
        
    } else {
        logger.info("DAO operation executed in {} ms", duration);
    }

    conn.close();
}
	
	@Override
	public DiagnosticBean latest(int user_id) throws SQLException {
		Connection conn = daoFactory.getConnection();
		String SQL = "SELECT * FROM diagnostics WHERE user_id = ? ORDER BY id DESC LIMIT 1;";
		PreparedStatement statement = conn.prepareStatement(SQL);
		
		statement.setInt(1, user_id);
		
		ResultSet res = statement.executeQuery();
		DiagnosticBean bean = res.next() ? getBean(res) : null;
		
		statement.close();
		conn.close();
		
		return bean;
	}
	
	@Override
	public ArrayList<DiagnosticBean> all(int user_id) throws SQLException {
        Connection conn = daoFactory.getConnection();
        String SQL = "SELECT * FROM diagnostics WHERE user_id = ? ORDER BY id DESC LIMIT 20;";
        
        PreparedStatement statement = conn.prepareStatement(SQL);
        
        statement.setInt(1, user_id);
        
        ResultSet res = statement.executeQuery();
        
        ArrayList<DiagnosticBean> list = new ArrayList<DiagnosticBean>();
        
        while (res.next()) list.add(getBean(res));
        
        res.close();
        statement.close();
        conn.close();
        
        return list;
	}
}

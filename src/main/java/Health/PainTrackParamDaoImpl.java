package Health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Database.DAOFactory;

public class PainTrackParamDaoImpl implements PainTrackParamDAO {
	private DAOFactory daoFactory;

    public PainTrackParamDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static PainTrackParamBean getBean(ResultSet res) throws SQLException {
    	PainTrackParamBean bean = new PainTrackParamBean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setPainTrackID(res.getInt("paintrack_id"));
    	bean.setParam(res.getString("param"));
    	bean.setValue(res.getString("value"));
    	
    	return bean;
    }
    
	@Override
	public void create(int paintrack_id, String param, ArrayList<String> values) throws SQLException {
		Connection conn = daoFactory.getConnection();
    	String SQL = "INSERT INTO paintrackparams (paintrack_id, param, value) VALUES(?, ?, ?);";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	for (String value : values) {
    		statement.setInt(1, paintrack_id);
    		statement.setString(2, param);
        	statement.setString(3, value);
        	statement.addBatch();
        }
    	
    	statement.executeBatch();
    	    	
    	statement.close();
    	conn.close();
	}
	
	@Override
	public Map<String, Integer>  paramStats(String param, int user_id) throws SQLException {
		Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT ptp.value, COUNT(ptp.value) AS count "
    			+ "FROM paintrackparams ptp "
    			+ "JOIN paintracks pt ON ptp.paintrack_id = pt.id "
    			+ "WHERE pt.user_id = ? "
    			+ "AND ptp.param = ? "
    			+ "GROUP BY ptp.param, ptp.value;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, user_id);
    	statement.setString(2, param);
    	
    	ResultSet res = statement.executeQuery();
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	
    	while(res.next()) map.put(res.getString("value"), res.getInt("count"));
    	
		return map;
	}
}

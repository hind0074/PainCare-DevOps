package Health;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DAOFactory;

public class PainTrackDaoImpl implements PainTrackDAO {
	private DAOFactory daoFactory;

    public PainTrackDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static PainTrackBean getBean(ResultSet res) throws SQLException {
    	PainTrackBean bean = new PainTrackBean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setUserID(res.getInt("user_id"));
    	bean.setLevel(res.getInt("level"));
    	
    	return bean;
    }
    
    @Override
    public int create(int level, int user_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();

		try {
				 System.out.println("CREATE diagnostic called for user " + user_id );
        Thread.sleep(5000); // ← INCIDENT SQL LENT ARTIFICIEL
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    	String deleteSQL = "DELETE FROM paintracks WHERE date = (CURRENT_DATE);";
    	String createSQL = "INSERT INTO paintracks (level, user_id) VALUES(?, ?);";
    	
    	PreparedStatement deleteStatement = conn.prepareStatement(deleteSQL);
    	PreparedStatement createStatement = conn.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);
    	
    	createStatement.setInt(1, level);
    	createStatement.setInt(2, user_id);
    	
    	deleteStatement.execute();
    	
    	createStatement.executeUpdate();
    	ResultSet generatedRow = createStatement.getGeneratedKeys();
    	int id = generatedRow.next() ? generatedRow.getInt(1) : null;
    	
    	deleteStatement.close();
    	createStatement.close();
    	conn.close();
    	
    	return id;
    }
    
    @Override
    public ArrayList<Integer> painLevelStats(int user_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT level FROM paintracks WHERE user_id = ? ORDER BY date DESC LIMIT 7;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, user_id);
    	
    	ResultSet res = statement.executeQuery();
    	ArrayList<Integer> list = new ArrayList<Integer>();
    	
    	while(res.next()) list.add(res.getInt("level"));
    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return list;
    }
    
    public ArrayList<Date> painLevelDates(int user_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT date FROM paintracks WHERE user_id = ? ORDER BY date DESC LIMIT 7;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, user_id);
    	
    	ResultSet res = statement.executeQuery();
    	ArrayList<Date> list = new ArrayList<Date>();
    	
    	while(res.next()) list.add(res.getDate("date"));
    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return list;
    }
}

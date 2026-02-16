package User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.DAOFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserDaoImpl implements UserDAO {
	private DAOFactory daoFactory;

    public UserDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static UserBean getBean(ResultSet res) throws SQLException {
    	UserBean bean = new UserBean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setName(res.getString("name"));
    	bean.setAvatar(res.getString("avatar"));
    	bean.setBirthDay(res.getDate("birthDay"));
    	bean.setEmail(res.getString("email"));
    	
    	return bean;
    }
    
    @Override
    public void update(UserBean bean) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "UPDATE users SET name = ?, email = ?, avatar = ?, birthDay = ? WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, bean.getName());
    	statement.setString(2, bean.getEmail());
    	statement.setString(3, bean.getAvatar());
    	statement.setDate(4, bean.getBirthDay());
    	statement.setInt(5, bean.getID());
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    @Override
    public void delete(int id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "DELETE FROM users WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, id);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    @Override
    public UserBean login(String email, String password) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT * FROM users WHERE email = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, email);
    	
    	ResultSet res = statement.executeQuery();
    	UserBean bean = res.next() ? getBean(res) : null;
    	
    	if(bean != null) {
    		BCrypt.Result verifyResult = BCrypt.verifyer().verify(password.toCharArray(), res.getString("password"));

    		if(!verifyResult.verified) return null;
    	}
    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return bean;
    }
    
    @Override
    public void register(String username, String email, String password) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "INSERT INTO users (name, email, password) VALUES (?, ?, ?);";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    	
    	statement.setString(1, username);
    	statement.setString(2, email);
    	statement.setString(3, hashedPassword);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    @Override
    public UserBean auth(HttpServletRequest request) {
    	HttpSession session = request.getSession();
    	UserBean bean = (UserBean) session.getAttribute("user");
    	
    	return bean;
    }
}

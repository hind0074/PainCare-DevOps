package User;

import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;

public interface UserDAO {
	public void update(UserBean bean) throws SQLException;
	public void delete(int id) throws SQLException;
	public UserBean login(String email, String password) throws SQLException;
	public void register(String usernmae, String email, String password) throws SQLException;
	public UserBean auth(HttpServletRequest request);
}

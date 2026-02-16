package Health;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DiagnosticDAO {
	public void create(String answers, int user_id) throws SQLException;
	public DiagnosticBean latest(int user_id) throws SQLException;
	public ArrayList<DiagnosticBean> all(int user_id) throws SQLException;
}

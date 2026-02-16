package Health;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PainTrackParamDAO {
	public void create(int paintrack_id, String param, ArrayList<String> values) throws SQLException;
	public Map<String, Integer> paramStats(String param, int user_id) throws SQLException;
}

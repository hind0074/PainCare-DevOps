package Health;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PainTrackDAO {
	public int create(int level, int user_id) throws SQLException;
	public ArrayList<Integer> painLevelStats(int user_id) throws SQLException;
}

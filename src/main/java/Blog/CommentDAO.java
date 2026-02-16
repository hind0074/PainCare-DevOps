package Blog;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommentDAO {
	public void create(int user_id, int blog_id, String content) throws SQLException;
	public void delete(int comment_id) throws SQLException;
	public ArrayList<CommentBean> blogComments(int blog_id) throws SQLException;
}
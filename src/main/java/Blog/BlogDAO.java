package Blog;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BlogDAO {
	public void create(int user_id, String title, String description, String image) throws SQLException;
	public void update(BlogBean blog) throws SQLException;
	public void delete(int blog_id) throws SQLException;
	public BlogBean one(int blog_id) throws SQLException;
	public ArrayList<BlogBean> getAllWithPagination(int page, int pageSize) throws SQLException ;
	public ArrayList<BlogBean> three() throws SQLException;
	public ArrayList<BlogBean> getBlogUser(int page, int pageSize,int id) throws SQLException ;
	public int Count(int id) throws SQLException;
}


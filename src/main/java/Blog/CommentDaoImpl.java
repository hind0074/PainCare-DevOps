package Blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DAOFactory;

public class CommentDaoImpl implements CommentDAO {
	private DAOFactory daoFactory;

    public CommentDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static CommentBean getBean(ResultSet res, boolean user_info) throws SQLException {
    	CommentBean bean = new CommentBean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setUserID(res.getInt("user_id"));
    	bean.setBlogID(res.getInt("blog_id"));
    	bean.setContent(res.getString("content"));
    	bean.setDate(res.getTimestamp("date"));
    	
    	if(user_info) {
        	bean.setUserName(res.getString("user_name"));
        	bean.setAvatar(res.getString("avatar"));	
    	}
    	
    	return bean;
    }
    
    @Override
    public void create(int user_id, int blog_id, String content) throws SQLException {
    	Connection conn = daoFactory.getConnection();
		String SQL = "INSERT INTO comments (user_id, blog_id, content) VALUES(?, ?, ?);";
		PreparedStatement statement = conn.prepareStatement(SQL);
    	
		statement.setInt(1, user_id);
		statement.setInt(2, blog_id);
		statement.setString(3, content);
		
		statement.execute();
		
		statement.close();
		conn.close();
    }
    
    @Override
    public void delete(int comment_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "DELETE FROM comments WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, comment_id);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    @Override
    public ArrayList<CommentBean> blogComments(int blog_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = 
    		"SELECT comments.*, users.name AS user_name, users.avatar as avatar "
			+ "FROM comments "
			+ "JOIN users ON comments.user_id = users.id "
			+ "JOIN blogs ON comments.blog_id = blogs.id "
			+ "WHERE blogs.id = ? ORDER BY comments.id DESC;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, blog_id);
    	
    	ResultSet res = statement.executeQuery();
    	ArrayList<CommentBean> list = new ArrayList<CommentBean>();
    	
    	while(res.next()) list.add(getBean(res, true));
    	
    	statement.close();
    	conn.close();
    	
    	return list;
    }
    
    public CommentBean one(int comment_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT * FROM comments WHERE id = ? ;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, comment_id);
    	
    	ResultSet res = statement.executeQuery();
    	CommentBean bean = res.next() ? getBean(res, false) : null;
    	    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return bean;
    }
}

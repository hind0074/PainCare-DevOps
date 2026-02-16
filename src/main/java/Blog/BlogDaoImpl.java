package Blog;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Database.DAOFactory;

public class BlogDaoImpl implements BlogDAO {
	private DAOFactory daoFactory;

    public BlogDaoImpl( DAOFactory daoFactory ) {
        this.daoFactory = daoFactory;
    }
    
    private static BlogBean getBean(ResultSet res) throws SQLException {
    	BlogBean bean = new BlogBean();
    	
    	bean.setID(res.getInt("id"));
    	bean.setUserID(res.getInt("user_id"));
    	bean.setTitle(res.getString("title"));
    	bean.setDescription(res.getString("description"));
    	bean.setImage(res.getString("image"));
    	bean.setDate(res.getDate("date"));
    	bean.setUserName(res.getString("user_name"));
    	bean.setUserImage(res.getString("user_avatar"));
    	
    	return bean;
    }
    
    @Override
    public void create(int user_id, String title, String description, String image) throws SQLException {
    	Connection conn = daoFactory.getConnection();
		String SQL = "INSERT INTO blogs (user_id, title, description, image) VALUES(?, ?, ?, ?);";
		PreparedStatement statement = conn.prepareStatement(SQL);
    	
		statement.setInt(1, user_id);
		statement.setString(2, title);
		statement.setString(3, description);
		statement.setString(4, image);
		
		
		statement.execute();
		
		statement.close();
		conn.close();
    }
    
    @Override
    public void delete(int blog_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String deleteCommentsSQL = "DELETE FROM comments WHERE blog_id = ?";
    	PreparedStatement  deleteCommentsStatement = conn.prepareStatement(deleteCommentsSQL);
        deleteCommentsStatement.setInt(1, blog_id);
        deleteCommentsStatement.execute();
        
    	String SQL = "DELETE FROM blogs WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, blog_id);
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    
    @Override
    public void update(BlogBean bean) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "UPDATE blogs SET title = ?, description = ?, image = ? WHERE id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setString(1, bean.getTitle());
    	statement.setString(2, bean.getDescription());
    	statement.setString(3, bean.getImage());
    	statement.setInt(4, bean.getID());
    	
    	statement.execute();
    	
    	statement.close();
    	conn.close();
    }
    public int Count(int id) throws SQLException{
        Connection conn = daoFactory.getConnection();
        String SQL = "select count(*) AS blogCount from blogs where user_id=?;\n"
        		+ "";
        PreparedStatement statement=conn.prepareStatement(SQL);
        statement.setInt(1, id);
        ResultSet res = statement.executeQuery();
        
     
        if (res.next()) {
            // Move the cursor to the first row and retrieve the count
            int blogCount = res.getInt("blogCount");
            res.close();
            statement.close();
            conn.close();
            return blogCount;
        } else {
            // Handle the case where no result is found (optional)
            return 0;
        }
    }
        public int CountAll() throws SQLException{
            Connection conn = daoFactory.getConnection();
            String SQL = "select count(*) AS BlogCount from blogs";
            PreparedStatement statement=conn.prepareStatement(SQL);
          
            ResultSet res = statement.executeQuery();
            
         
            if (res.next()) {
                // Move the cursor to the first row and retrieve the count
                int blogCount = res.getInt("blogCount");
                res.close();
                statement.close();
                conn.close();
                return blogCount;
            } else {
                // Handle the case where no result is found (optional)
                return 0;
            }

        
    	
    }
    
    @Override
    public ArrayList<BlogBean> getAllWithPagination(int page, int pageSize) throws SQLException {
        Connection conn = daoFactory.getConnection();

        // Calculate the offset based on the page number and page size
        int offset = (page - 1) * pageSize;

        String SQL = "SELECT blogs.*, users.name AS user_name, users.avatar AS user_avatar "
                + "FROM blogs "
                + "JOIN users ON blogs.user_id = users.id "
                + "ORDER BY id DESC "
                + "LIMIT ? OFFSET ?";
        
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, pageSize);  // Set the limit
        statement.setInt(2, offset);    // Set the offset

        ResultSet res = statement.executeQuery();
        ArrayList<BlogBean> list = new ArrayList<>();

        while (res.next()) {
            list.add(getBean(res));
        }

        res.close();
        statement.close();
        conn.close();

        return list;
    }

    
    @Override
    public BlogBean one(int blog_id) throws SQLException {
    	Connection conn = daoFactory.getConnection();
    	String SQL = "SELECT blogs.*, users.name AS user_name ,users.avatar AS user_avatar "
    			+ "FROM blogs "
    			+ "JOIN users ON blogs.user_id = users.id "
    			+ "WHERE blogs.id = ?;";
    	PreparedStatement statement = conn.prepareStatement(SQL);
    	
    	statement.setInt(1, blog_id);
    	
    	ResultSet res = statement.executeQuery();
    	BlogBean bean = res.next() ? getBean(res) : null;
    	    	
    	res.close();
    	statement.close();
    	conn.close();
    	
    	return bean;
    }
    @Override
    public ArrayList<BlogBean> three() throws SQLException {
        Connection conn = daoFactory.getConnection();
        String SQL = "SELECT blogs.*, users.name AS user_name, users.avatar AS user_avatar " +
                     "FROM blogs " +
                     "JOIN users ON blogs.user_id = users.id " +
                     "ORDER BY id DESC LIMIT 3";
        
        PreparedStatement statement = conn.prepareStatement(SQL);
        ResultSet res = statement.executeQuery();
        ArrayList<BlogBean> list = new ArrayList<BlogBean>();
        
        while (res.next()) {
            list.add(getBean(res));
        }
        
        res.close();
        statement.close();
        conn.close();
        
        return list;
    }
    @Override
    public ArrayList<BlogBean> getBlogUser(int page, int pageSize,int id) throws SQLException {
        Connection conn = daoFactory.getConnection();

        // Calculate the offset based on the page number and page size
        int offset = (page - 1) * pageSize;

        String SQL = "SELECT blogs.*, users.name AS user_name, users.avatar AS user_avatar "
                + "FROM blogs "
                + "JOIN users ON blogs.user_id = users.id where blogs.user_id = ? "
                + "ORDER BY blogs.id DESC "
                + "LIMIT ? OFFSET ? ";


        
        PreparedStatement statement = conn.prepareStatement(SQL);
        statement.setInt(1, id);
        statement.setInt(2, pageSize);  // Set the limit
        statement.setInt(3, offset);
      // Set the offset

        ResultSet res = statement.executeQuery();
        ArrayList<BlogBean> list = new ArrayList<>();

        while (res.next()) {
            list.add(getBean(res));
        }

        res.close();
        statement.close();
        conn.close();

        return list;
    }


}

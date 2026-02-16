package Database;

import java.io.IOException;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import Blog.BlogDaoImpl;
import Blog.CommentDaoImpl;
import Health.DiagnosticDaoImpl;
import Health.PainTrackDaoImpl;
import Health.PainTrackParamDaoImpl;
import User.UserDaoImpl;


public class DAOFactory {
	private static final String CONFIG_LOCATION   = "/Database/dao.docker.properties";
	
    private static final String PROPERTY_URL      = "url";
    private static final String PROPERTY_DRIVER   = "driver";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_WEB_CONTENT_FOLDER = "web_content_folder";
    
    private String url;
    private String username;
    private String password;
    
    public String WEB_CONTENT_FOLDER;
    
    public DAOFactory(String url, String username, String password, String WEB_CONTENT_FOLDER) {
    	this.url = url;
        this.username = username;
        this.password = password;
        this.WEB_CONTENT_FOLDER = WEB_CONTENT_FOLDER;
    }

    public static DAOFactory getInstance() {
    	Properties properties = new Properties();
    	
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fileProperties = classLoader.getResourceAsStream( CONFIG_LOCATION );
        
        if ( fileProperties == null ) {
        	throw new DAOFactoryException("Cannot found propreties file.");
        }
        
        try {
			properties.load(fileProperties);
			String driver = properties.getProperty( PROPERTY_DRIVER );
			String url = properties.getProperty( PROPERTY_URL );
			String username = properties.getProperty( PROPERTY_USERNAME );
			String password = properties.getProperty( PROPERTY_PASSWORD );
			String web_content_folder = properties.getProperty(PROPERTY_WEB_CONTENT_FOLDER);
			
	        try {
	            Class.forName( driver );
	        } catch ( ClassNotFoundException e ) {
	            throw new DAOFactoryException( "The target driver is not exist.", e );
	        }
	        
	        DAOFactory instance = new DAOFactory( url, username, password, web_content_folder );
	        return instance;
	        
		} catch (IOException e) {
			throw new DAOFactoryException("Cannot read propreties file.", e);
		}
        
    }
    
    public Connection getConnection() throws SQLException {
		
		return DriverManager.getConnection( url, username, password );
	}
    
    public UserDaoImpl getUserDAO() {
    	return new UserDaoImpl(this);
    }
    
    public DiagnosticDaoImpl getDiagnosticDAO() {
    	return new DiagnosticDaoImpl(this);
    }
    
    public PainTrackDaoImpl getPainTrackDAO() {
    	return new PainTrackDaoImpl(this);
    }
    
    public PainTrackParamDaoImpl getPainTrackParamDAO() {
    	return new PainTrackParamDaoImpl(this);
    }
    
    public BlogDaoImpl getBlogDAO() {
    	return new BlogDaoImpl(this);
    }
    
    public CommentDaoImpl getCommentDAO() {
    	return new CommentDaoImpl(this);
    }
}

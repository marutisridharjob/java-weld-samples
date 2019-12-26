package mikra.jta.persistence;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import com.arjuna.ats.jdbc.TransactionalDriver;

/**
 * delegates the transactionManager's call getConnection() to the H2DataSource.
 * The reference to the datasource is provided by the container.
 * @author mk
 *
 */

@ApplicationScoped
public class H2TransactionalDataSourceDelegate implements DataSource {
	
	private TransactionalDriver transactionalDriver;
	
	@Inject
	H2DataSourceWrapper wrapper;
	
		
	@PostConstruct
	private void init() throws SQLException {
		this.transactionalDriver = new TransactionalDriver();
		DriverManager.registerDriver(this.transactionalDriver);
	}
	
	
	@Override
	public Connection getConnection() throws SQLException{
		Properties props = new Properties();
		props.setProperty(TransactionalDriver.poolConnections,"true");
		// eval eclipselink pooling
		props.setProperty(TransactionalDriver.maxConnections,"15");
		props.put(TransactionalDriver.XADataSource,this.wrapper);
		return DriverManager.getConnection(TransactionalDriver.arjunaDriver,props);
	}

		
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
          return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
           return false;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
       return this.getConnection();
	}

	
}

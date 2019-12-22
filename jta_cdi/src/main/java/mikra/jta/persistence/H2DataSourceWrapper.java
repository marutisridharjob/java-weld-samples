package mikra.jta.persistence;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.sql.ConnectionEventListener;
import javax.sql.DataSource;
import javax.sql.StatementEventListener;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

import org.h2.jdbcx.JdbcDataSource;

import com.arjuna.ats.jdbc.TransactionalDriver;

@ApplicationScoped
public class H2DataSourceWrapper implements XADataSource,DataSource {

	public static final String jndi_DataSourceName = "java:/dsName";
	
	@Inject
	BeanManager beanManager;
	
	private TransactionalDriver transactionalDriver;
	private JdbcDataSource h2DataSource;
	
	@Resource(lookup = H2DataSourceWrapper.jndi_DataSourceName)
	private String datasourceJndiName;
	
	@PostConstruct
	private void init() {
		this.transactionalDriver = new TransactionalDriver();
	    this.h2DataSource = new JdbcDataSource();
	    this.h2DataSource.setUrl("jdbc:h2:mem:test_db;MODE=Oracle;DB_CLOSE_DELAY=-1");
        this.h2DataSource.setUser("sa");
        this.h2DataSource.setPassword("");
	}
	
	@Override
	public Connection getConnection() throws SQLException{
		Properties props = new Properties();
		props.setProperty(TransactionalDriver.userName,"sa");
		props.setProperty(TransactionalDriver.password,"");
		props.setProperty(TransactionalDriver.poolConnections,"false");
		// eval eclipselinks pooling
		props.setProperty(TransactionalDriver.maxConnections,"5");
		return this.transactionalDriver.connect("jdbc:arjuna:" + datasourceJndiName, props);
	}

	public void scriptDatabase(String filename) throws SQLException{
		Connection c = this.h2DataSource.getConnection();
		c.createStatement().execute("SCRIPT COLUMNS TO '"+filename+"' ");
		c.close();
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
		return this.h2DataSource.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.h2DataSource.isWrapperFor(iface);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
       return this.getConnection();
	}

	@Override
	public XAConnection getXAConnection() throws SQLException {
	    return this.h2DataSource.getXAConnection();		
	}

	@Override
	public XAConnection getXAConnection(String user, String password) throws SQLException {
		return this.getXAConnection();
	}

	
}

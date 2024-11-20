package DataBase;

import ErrorHandling.CouponSystemException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

import static ErrorHandling.Errors.SQL_ERROR;
import static ErrorHandling.Errors.THREAD_ERROR;

/**
 * Connection Pool singleton class - used to create and manage connections to DB
 */
public class ConnectionPool {
    //Singleton class - connection pool
    //Number of connection to mySQL (maximum is 20, default is 10)
    private static final int NUMBER_OF_CONNECTION=10;
    public static ConnectionPool instance=null;
    private final Stack<Connection> connections = new Stack<>();

    /**
     * Singleton constructor - opens all connections
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    private ConnectionPool() throws CouponSystemException {
        openAllConnections();
    }

    /**
     * Opens all connections when Connection Pool is created
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    private void openAllConnections() throws CouponSystemException {
        //Create connections according to NUMBER_OF_CONNECTION variable
        for (int counter=0;counter<NUMBER_OF_CONNECTION;counter++){
            //Create a new connection using the DriverManger
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DBmanager.URL,DBmanager.SQL_USER,DBmanager.SQL_PASSWORD);
            } catch (SQLException e) {
                throw new CouponSystemException(SQL_ERROR);
            }
            connections.push(connection);
        }
    }

    /**
     * Returns the single connection pool instance or creates one if it doesn't exist - with double check
     * @return Connection Pool instance (singleton)
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    public static ConnectionPool getInstance() throws CouponSystemException {
        //Check if instance is null
        if (instance==null){
            synchronized (ConnectionPool.class){
                //Double check - to verify that no other thread creates an instance
                if (instance==null){
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    /**
     * Closes all connections upon system close
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    public void closeAllConnections() throws CouponSystemException {
        //lock connections, that we will not give any new connection
        synchronized (connections){
            while (connections.size()<NUMBER_OF_CONNECTION){
                //wait until all connections are done.
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    throw new CouponSystemException(THREAD_ERROR);
                }
            }
            connections.removeAllElements();
        }
    }

    /**
     * Provides a connection to the DB from the connection pool
     * @return A connection to the DB
     * @throws CouponSystemException  If we get any exception.  Details are provided
     */
    public Connection getConnection() throws CouponSystemException {
        //lock the connections
        synchronized (connections){
            //check if we have connection to give
            if (connections.isEmpty()){
                //wait until somebody will return a connection
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    throw new CouponSystemException(THREAD_ERROR);
                }
            }
            return connections.pop();
        }
    }

    /**
     * Returns a connection to the connection pool
     * @param connection connection for returning to connection pool
     */
    public void returnConnection(Connection connection){
        synchronized (connections){
            //return the connection to the stack collection
            connections.push(connection);
            //notify that we got back a connection from a user
            connections.notify();
        }
    }

}

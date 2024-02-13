package DataBase;

import ErrorHandling.CouponSystemException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

import static ErrorHandling.Errors.SQL_ERROR;
import static ErrorHandling.Errors.THREAD_ERROR;

public class ConnectionPool {
    //Singleton class - connection pool
    //Number of connection to mySQL (maximum is 20, default is 10)
    private static final int NUMBER_OF_CONNECTION=10;
    public static ConnectionPool instance=null;
    private final Stack<Connection> connections = new Stack<>();

    private ConnectionPool() throws CouponSystemException {
        //Todo - remove sout below:
        System.out.println("We created new connection pool instance");
        openAllConnections();
    }

    private void openAllConnections() throws CouponSystemException {
        //Create connections according to NUMBER_OF_CONNECTION variable
        for (int counter=0;counter<NUMBER_OF_CONNECTION;counter++){
            //Create a new connection using the DriverManger
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DBmanager.URL,DBmanager.SQL_USER,DBmanager.SQL_PASSWORD);
            } catch (SQLException e) {
                throw new CouponSystemException(SQL_ERROR.getMessage() + e);
            }
            connections.push(connection);
        }
    }

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

    public void closeAllConnections() throws CouponSystemException {
        //lock connections, that we will not give any new connection
        synchronized (connections){
            while (connections.size()<NUMBER_OF_CONNECTION){
                //wait until all connections are done.
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    throw new CouponSystemException(THREAD_ERROR.getMessage() + e);
                }
            }
            connections.removeAllElements();
        }
    }

    public Connection getConnection() throws CouponSystemException {
        //lock the connections
        synchronized (connections){
            //check if we have connection to give
            if (connections.isEmpty()){
                //wait until somebody will return a connection
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    throw new CouponSystemException(THREAD_ERROR.getMessage() + e);
                }
            }
            return connections.pop();
        }
    }

    public void returnConnection(Connection connection){
        synchronized (connections){
            //return the connection to the stack collection
            connections.push(connection);
            //notify that we got back a connection from a user
            connections.notify();
        }
    }

}

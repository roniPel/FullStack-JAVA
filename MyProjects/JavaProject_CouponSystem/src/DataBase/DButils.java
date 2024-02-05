package DataBase;

import DataBase.CRUD.Create;
import ErrorHandling.CouponSystemException;
import ErrorHandling.Errors;

import java.sql.*;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

import static ErrorHandling.Errors.*;

public class DButils {

    /**
     * Prepares 'param' map for login check
     * @param email login email
     * @param password login password
     * @return Map<Integer, Object> params if succeeded, null if failed.
     */
    public static Map<Integer, Object> PrepareParamsForLoginCheck(String email, String password) {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,email);
        params.put(2,password);
        return params;
    }

    /**
     * Creates an SQL statement to insert multiple values into an 'IN' statement, in the DB
     * @param numberOfRows number of times to 'repeat' the insert line
     * @param sql string containing an 'IN' command with a section to be repeated
     * @return String statement if succeeded, null if failed.
     */
    public static String sqlInsertMultiple_IN_Values(String sql, int numberOfRows) {
        String basicCommand;
        String sectionToRepeat;
        String updatedCommand = "";

        // Remove ");" chars at the end of the SQL command
        basicCommand = sql.substring(0,sql.length()-2);

        // Find section to repeat
        int startIdx = basicCommand.lastIndexOf("(")+1;
        int endIdx = basicCommand.length();
        sectionToRepeat = basicCommand.substring(startIdx,endIdx);
        for (int i = 1; i < numberOfRows; i++) {
            updatedCommand += (", ");
            updatedCommand += sectionToRepeat;
        }
        updatedCommand += (");");
        return basicCommand.concat(updatedCommand);
    }


    /**
     * Creates an SQL statement to insert multiple values into DB
     * @param numberOfRows number of times to 'repeat' the insert line
     * @param type string, explaining which type of 'insert' is needed
     * @return String statement if succeeded, null if failed.
     */
    public static String sqlInsertMultipleValues(int numberOfRows, String type) {
        String updatedCommand = switch (type) {
            case "Coupon" -> createRepeatStatement(Create.insertCoupon, numberOfRows);
            case "Company" -> createRepeatStatement(Create.insertCompany, numberOfRows);
            case "Category" -> createRepeatStatement(Create.insertCategory, numberOfRows);
            case "Customer" -> createRepeatStatement(Create.insertCustomer, numberOfRows);
            case "CustomerVsCoupon" -> createRepeatStatement(Create.insertCustomerVsCoupon,numberOfRows);
            default -> null;
        };
        return updatedCommand;
    }


    /**
     * Finds and concatenates relevant section to repeat
     * @param sql original SQL statement for single line insert to DB
     * @param numberOfRows number of times to 'repeat' the insert line
     * @return concatenated String statement if succeeded, null if failed.
     */
    private static String createRepeatStatement(String sql, int numberOfRows) {
        String basicCommand;
        String sectionToRepeat;
        String updatedCommand = "";

        // Remove ";" char at the end of the SQL command
        basicCommand = sql.substring(0,sql.length()-1);

        // Find section to repeat
        int startIdx = basicCommand.lastIndexOf("(");
        int endIdx = basicCommand.length();
        sectionToRepeat = basicCommand.substring(startIdx,endIdx);
        for (int i = 1; i < numberOfRows; i++) {
            updatedCommand += (", ");
            updatedCommand += sectionToRepeat;
        }
        updatedCommand += (";");
        return basicCommand.concat(updatedCommand);
    }


    /**
     * Runs a query on the DB using an SQL statement as param and a map with parameters to insert into the statement
     * @param sql SQL statement to send to DB
     * @param params Map with parameters to insert into the SQL statement
     * @return true if succeeded running query, false if failed.
     */
    public static boolean runQueryWithMap(String sql, Map<Integer, Object> params) throws CouponSystemException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (params.isEmpty()) {
                ConnectionPool.getInstance().returnConnection(connection);
                throw new CouponSystemException(EMPTY_OR_NULL.getMessage());
            }
            else {
                preparedStatement = fillInPreparedStatementFromParams(preparedStatement,params);
            }
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * Takes a prepared SQL statement and updates it with values from the 'params' map
     * @param preparedStatement SQL prepared statements that needs to be updated
     * @param params Map<Integer,Object> used to update the prepared statement
     * @return PreparedStatement statement with values from the 'params' map
     */
    private static PreparedStatement fillInPreparedStatementFromParams(PreparedStatement preparedStatement,Map<Integer, Object> params) throws CouponSystemException {
        for (Map.Entry<Integer, Object> entry : params.entrySet()) {
            Integer key = entry.getKey();
            Object value = entry.getValue();
            try {
                if (value instanceof Integer) {
                    preparedStatement.setInt(key, (Integer) value);
                } else if (value instanceof String) {
                    preparedStatement.setString(key, String.valueOf(value));
                } else if (value instanceof Date) {
                    preparedStatement.setDate(key, (Date) value);
                } else if (value instanceof Double) {
                    preparedStatement.setDouble(key, (Double) value);
                } else if (value instanceof Boolean) {
                    preparedStatement.setBoolean(key, (Boolean) value);
                } else if (value instanceof Float) {
                    preparedStatement.setFloat(key, (Float) value);
                }
            } catch (SQLException e) {
                throw new CouponSystemException(SQL_ERROR.getMessage() + e);
            }
        }
        return preparedStatement;
    }


    /**
     * Runs a query on the DB using an SQL statement as param and a map with parameters to insert into the statement.
     * Returns a result set taken from DB.
     * @param sql SQL statement to send to DB
     * @param params Map with parameters to insert into the SQL statement
     * @return 'ResultSet' objects with results if succeeded running query, null if failed.
     */
    public static ResultSet runQueryForResult(String sql, Map<Integer,Object> params) throws CouponSystemException {
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (params.isEmpty()) {
                ConnectionPool.getInstance().returnConnection(connection);
                throw new CouponSystemException(EMPTY_OR_NULL.getMessage());
            }
            else  {
                for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                    Integer key = entry.getKey();
                    Object value = entry.getValue();
                    try {
                        if (value instanceof Integer) {
                            preparedStatement.setInt(key, (Integer) value);
                        } else if (value instanceof String) {
                            preparedStatement.setString(key, String.valueOf(value));
                        } else if (value instanceof Date) {
                            preparedStatement.setDate(key, (Date) value);
                        } else if (value instanceof Double) {
                            preparedStatement.setDouble(key, (Double) value);
                        } else if (value instanceof Boolean) {
                            preparedStatement.setBoolean(key, (Boolean) value);
                        } else if (value instanceof Float) {
                            preparedStatement.setFloat(key, (Float) value);
                        }
                    } catch (SQLException e) {
                        throw new CouponSystemException(SQL_ERROR.getMessage());
                    }
                }
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }


    /**
     * Checks whether login results mean the user exists or doesn't in DB
     * @param results ResultSet with data from DB after login check
     * @return true if user exists (login results are true), false if the email + password combo are incorrect.
     * @throws CouponSystemException If we get any SQL exception.  Details are provided
     */
    public static boolean CheckLoginResults(ResultSet results) throws CouponSystemException {
        int numberOfReturns = -1;
        try {
            if(results.next()) {
                numberOfReturns = results.getInt(1);
            }
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage()+e);
        }
        return numberOfReturns == 1;
    }
}

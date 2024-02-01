package DataBase;

import DataBase.CRUD.Create;
import ErrorHandling.CouponSystemException;

import java.sql.*;
import java.util.ConcurrentModificationException;
import java.util.Map;

import static ErrorHandling.Errors.*;

public class DButils {

    /**
     * Creates an SQL statement to insert multiple values into DB
     * @param numberOfRows number of times to 'repeat' the insert line
     * @param type which type of 'insert' is needed
     * @return String statement if succeeded, null if failed.
     */
    public static String sqlInsertMultipleValues(int numberOfRows, String type) {
        String updatedCommand = switch (type) {
            case "Coupon" -> createRepeatStatement(Create.insertCoupon, numberOfRows);
            case "Company" -> createRepeatStatement(Create.insertCompany, numberOfRows);
            case "Category" -> createRepeatStatement(Create.insertCategory, numberOfRows);
            case "Customer" -> createRepeatStatement(Create.insertCustomer, numberOfRows);
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
        basicCommand = sql.substring(0,sql.length()-1);
        // Remove ";" char at the end of the SQL command
        basicCommand = basicCommand.substring(0,basicCommand.length());
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
                params.forEach((key, value) -> {
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
                        try {
                            throw new CouponSystemException(SQL_ERROR.getMessage() + e + e.getMessage());
                        } catch (CouponSystemException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
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

}

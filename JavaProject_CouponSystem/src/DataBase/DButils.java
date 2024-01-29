package DataBase;

import ErrorHandling.CouponSystemException;

import java.sql.*;
import java.util.ConcurrentModificationException;
import java.util.Map;

import static ErrorHandling.Errors.*;

public class DButils {

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
                            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
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
            //Todo - for testing only, delete when finished:
            System.out.println(preparedStatement);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new CouponSystemException(SQL_ERROR.getMessage() + e);
        } finally {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

}

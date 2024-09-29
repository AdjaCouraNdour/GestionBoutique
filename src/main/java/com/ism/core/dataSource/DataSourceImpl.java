package com.ism.core.dataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class DataSourceImpl implements DataSource{
 
    private final String url = "jdbc:postgresql://localhost:5432/gestion-boutique";
    private final String user = "postgres"; 
    private final String mdp = "008088";
    protected PreparedStatement ps;
    protected Connection conn = null;
   

    @Override
    public Connection connexion() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, mdp);

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Échec de la connexion à la BD: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void closeConnexion(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
    
    @Override
    public void preparedStatement(String sql) throws SQLException {
        this.connexion();
        if (sql.toUpperCase().trim().startsWith("INSERT")) {
            ps=conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        } else {
            ps=conn.prepareStatement(sql);
        }
    }

    @Override
    public ResultSet executeQuery(String query, PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    @Override
    public int executeUpdate(String query, PreparedStatement ps) throws SQLException {
        return ps.executeUpdate();
    }

    @Override
    public String generateSQL(String baseQuery, Map<String, Object> conditions) {
        StringBuilder queryBuilder = new StringBuilder(baseQuery);
        if (conditions != null && !conditions.isEmpty()) {
            queryBuilder.append(" WHERE ");
            conditions.forEach((key, value) -> {
                queryBuilder.append(key).append(" = ?");
                queryBuilder.append(" AND ");
            });
            queryBuilder.setLength(queryBuilder.length() - 5);
        }
        return queryBuilder.toString();
    }

    @Override
    public void setFields(PreparedStatement ps, List<Object> params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.size(); i++) {
                Object param = params.get(i);
                if (param instanceof Integer) {
                    ps.setInt(i + 1, (Integer) param);
                } else if (param instanceof String) {
                    ps.setString(i + 1, (String) param);
                } else if (param instanceof Double) {
                    ps.setDouble(i + 1, (Double) param);
                } else if (param instanceof Float) {
                    ps.setFloat(i + 1, (Float) param);
                } else if (param instanceof Boolean) {
                    ps.setBoolean(i + 1, (Boolean) param);
                } else {
                    ps.setObject(i + 1, param);
                }
            }
        }
    }

   


}
package com.ism.core.Repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.ism.core.dataSource.DataSourceImpl;

public abstract class RepositoryBDImpl<T> extends DataSourceImpl implements Repository<T> {

    protected abstract void setInsertParameters(PreparedStatement ps, T object) throws SQLException;
    protected abstract String generateInsertQuery();
    protected abstract String getTableName();
    protected abstract T converToObject(ResultSet rs) throws SQLException;
    protected abstract void setIdFromResultSet(T object, ResultSet rs) throws SQLException;
    protected String tableName;

    @Override
    public boolean insert(T object) {
        String query = generateInsertQuery();
        try (Connection connection = connexion(); 
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setInsertParameters(ps, object);
            int nbre = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                setIdFromResultSet(object,rs);
            }
            return nbre > 0;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion : " + e.getMessage());
            return false;
        }
    }

    protected String generateSelectQuery() {
        return "SELECT * FROM " + getTableName();
    }

    @Override
        public List<T> selectAll() {
            List<T> list = new ArrayList<>();
            String query = generateSelectQuery();
            try (Connection connection = connexion();
                 PreparedStatement ps = connection.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                // Parcours des résultats
                while (rs.next()) {
                    T entity = converToObject(rs);
                    list.add(entity);
                }
            } catch (SQLException e) {
                System.out.println("Erreur lors de la récupération des données : " + e.getMessage());
                e.printStackTrace();
            }
            return list;
        } 
     
}

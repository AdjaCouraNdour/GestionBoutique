package com.ism.data.repository.bd;

import java.sql.*;

import com.ism.core.Repository.RepositoryBDImpl;
import com.ism.data.entities.User;
import com.ism.data.enums.UserRole;
import com.ism.data.repository.interfaces.ClientRepositoryI;
import com.ism.data.repository.interfaces.UserRepositoryI;

public class UserRepositoryBD extends RepositoryBDImpl<User> implements UserRepositoryI {
 
    ClientRepositoryI clientRepository;

    public UserRepositoryBD() {
        this.tableName="public.user";
    }
    public void setClientRepository(ClientRepositoryI clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, User object) throws SQLException {
        ps.setObject(1, object.getEmail()); 
        ps.setObject(2, object.getLogin());
        ps.setObject(3, object.getPassword()); 
        ps.setInt(4, object.getUserRole().ordinal()+1);
        ps.setBoolean(5, true);
        if (object.getClient() != null) {
            ps.setInt(6, object.getClient().getId());
        } else {
            ps.setNull(6, java.sql.Types.INTEGER);
        }    
    }

    @Override
    protected String generateInsertQuery() {
        return String.format("INSERT INTO %s (email, login, password, userRoleid, clientId, userEtat) VALUES (?, ?, ?, ?, ?, ?)", this.tableName );
    }

    @Override
    protected String getTableName() {
       return this.tableName ;
    }

    @Override
    protected User converToObject(ResultSet rs) throws SQLException {
        User user = new User();
            user.setId(rs.getInt("id")); 
            user.setEmail(rs.getString("email")); 
            user.setLogin(rs.getString("login")); 
            user.setPassword(rs.getString("password"));
            user.setUserEtat(rs.getObject("userEtat") == null || rs.getBoolean("userEtat"));
            user.setUserRole(UserRole.getUserRoleId(rs.getInt("userRoleId")));
            user.setClient(clientRepository.selectById(rs.getInt("clientId")));
            
        return user; 
    }
    
   @Override
    protected void setIdFromResultSet(User object, ResultSet rs) throws SQLException {
        object.setId(rs.getInt(1)); 
    }

    @Override
    public User selectById(int id) {
        User user = new User();
        try {
            Connection connection = connexion();
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM %s WHERE id = ?", this.tableName ));
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    converToObject(rs); 
                }
            } else {
                System.out.println("erreur connexion de la BD");
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return user;
    }

    
    @Override
    public User selectBy(UserRole role) {
        User user = new User();
        try {
            Connection connection = connexion();
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM %s WHERE userRoleId = ?", this.tableName ));
                ps.setInt(1,UserRole.getUserRoleIdAsInt(role));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    converToObject(rs); 
                }
            } else {
                System.out.println("erreur connexion de la BD");
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return user;
    }

    @Override
    public User selectLogin(String login) {
        User user = new User();
        try {
            Connection connection = connexion();
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(String.format("SELECT * FROM %s WHERE login = ?", this.tableName ));
                ps.setString(1, login);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    converToObject(rs); 
                }
            } else {
                System.out.println("erreur connexion de la BD");
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return user;
    }
    @Override
    public User selectByUserEtat(boolean etat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByUserEtat'");
    }

 
}

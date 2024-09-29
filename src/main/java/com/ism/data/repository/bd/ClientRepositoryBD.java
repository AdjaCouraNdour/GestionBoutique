package com.ism.data.repository.bd;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
import java.sql.*;

import com.ism.core.Repository.RepositoryBDImpl;
import com.ism.data.entities.Client;
import com.ism.data.entities.User;
import com.ism.data.repository.interfaces.ClientRepositoryI;
import com.ism.data.repository.interfaces.UserRepositoryI;

public class ClientRepositoryBD extends RepositoryBDImpl<Client> implements ClientRepositoryI {
    
    UserRepositoryI userRepository;

    public ClientRepositoryBD(UserRepositoryI userRepository) {
		this.userRepository = userRepository;
        this.tableName = "public.client";
	}

    @Override
    protected void setInsertParameters(PreparedStatement ps, Client object) throws SQLException {
        ps.setObject(1, object.getSurname()); 
        ps.setObject(2, object.getTelephone());
        ps.setObject(3, object.getAddress());
        if (object.getUser() != null) {
            ps.setInt(4, object.getUser().getId());
        } else {
            ps.setNull(4, java.sql.Types.INTEGER);
        }   
    }

    @Override
    protected String generateInsertQuery() {
        return String.format("INSERT INTO %s (surname, telephone, address, userId) VALUES (?,?,?,?) ",this.tableName);    
       
    }

    @Override
    protected String getTableName() {  
     return this.tableName;
    }

    @Override
    protected Client converToObject(ResultSet rs) throws SQLException {
            Client client = new Client();
            
            client.setId(rs.getInt("id")); 
            client.setSurname(rs.getString("surname")); 
            client.setTelephone(rs.getString("telephone")); 
            client.setAddress(rs.getString("address"));
            //1---- client.setUser(this.userRepository.selectById(rs.getInt("userId")));
            //2---- int userId=rs.getInt("userId");
                    // User user =this.userRepository.selectById(userId);
                    // client.setUser(user);
            int userId = rs.getInt("userId");
            User user = null;
            if (userId != 0) { 
                user = this.userRepository.selectById(userId);
            }
            client.setUser(user);
            return client;
        }

    @Override
    protected void setIdFromResultSet(Client object, ResultSet rs) throws SQLException {
        object.setId(rs.getInt(1)); 
    }
   
    @Override
    public Client selectById(int id) {
        Client client = new Client();
        try {
            if (connexion() != null) {
                PreparedStatement ps = connexion().prepareStatement(String.format("SELECT * FROM %s WHERE id = ?", this.tableName));
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    converToObject(rs);
                }
                rs.close();
            } else {
                System.out.println("erreur connexion de la BD");
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return client;
    }

    @Override
    public Client selectByNumero(String numero) {
        Client client=null;
        try {
            if (connexion() != null) {
                PreparedStatement ps = connexion().prepareStatement(String.format("SELECT * FROM %s WHERE telephone = ?", this.tableName ));
                ps.setString(1, numero);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    converToObject(rs);
                }
                rs.close();
            } else {
                System.out.println("erreur connexion de la BD");
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return client;
    }

    @Override
    public Client selectBySurname(String surname) {
        Client client=null;
        try {
            if (connexion() != null) {
                PreparedStatement ps = connexion().prepareStatement(String.format("SELECT * FROM %s WHERE surname = ?", this.tableName ));
                ps.setString(1, surname);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    converToObject(rs);
                }
                rs.close();
            } else {
                System.out.println("erreur connexion de la BD");
            }
        } catch (SQLException e) {
            System.out.println("Erreur requête : " + e.getMessage());
        }
        return client;
    }

}


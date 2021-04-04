package ensta.dao.impl;

import ensta.dao.MembreDao;
import ensta.exception.DaoException;
import ensta.model.*;
import ensta.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDaoImpl implements MembreDao {
    private static MembreDaoImpl instance;
    private MembreDaoImpl() { }
    public static MembreDao getInstance() {
        if(instance == null) {
            instance = new MembreDaoImpl();
        }
        return instance;
    }

    private static final String SELECT_ALL_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
    private static final String SELECT_ONE_QUERY = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM membre WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM membre;";

    @Override
    public List<Membre> getList() throws DaoException {
        List<Membre> membres = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                Membre membre = new Membre(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("adresse"), res.getString("email"), res.getString("telephone"), Abonnement.valueOf(res.getString("abonnement")));
                membres.add(membre);
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la récupération de la liste des membres", e);
        }
        return membres;
    }

    @Override
    public Membre getById(int id) throws DaoException {
        Membre membre = new Membre();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()) {
                membre.setId(res.getInt("id"));
                membre.setNom(res.getString("nom"));
                membre.setPrenom(res.getString("prenom"));
                membre.setAdresse(res.getString("adresse"));
                membre.setEmail(res.getString("email"));
                membre.setTelephone(res.getString("telephone"));
                membre.setAbonnement(Abonnement.valueOf(res.getString("abonnement")));
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la récupération du membre: id = " + id, e);
        }
        return membre;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        int id = -1;
        try {
            Connection connection= ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, adresse);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, telephone);
            preparedStatement.setObject(6, Abonnement.BASIC.toString());
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();

            if(res.next()){
                id = res.getInt(1);
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new DaoException("Problème lors de la création du membre: " + nom + " " + prenom , e);
        }
        return id;
    }

    @Override
    public void update(Membre membre) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, membre.getNom());
            preparedStatement.setString(2, membre.getPrenom());
            preparedStatement.setString(3, membre.getAdresse());
            preparedStatement.setString(4, membre.getEmail());
            preparedStatement.setString(5, membre.getTelephone());
            preparedStatement.setString(6, membre.getAbonnement().toString()); //FIXED
            preparedStatement.setInt(7, membre.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la mise à jour du membre: " + membre, e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la suppression du membre id: " + id, e);
        }
    }

    @Override
    public int count() throws DaoException {
        int count = -1;
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()){
                count = res.getInt("count");
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors du comptage de membres", e);
        }
        return count;
    }
}

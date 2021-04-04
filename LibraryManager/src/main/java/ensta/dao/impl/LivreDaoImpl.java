package ensta.dao.impl;

import ensta.dao.LivreDao;
import ensta.exception.DaoException;
import ensta.model.Livre;
import ensta.persistence.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements LivreDao {
    private static LivreDaoImpl instance;
    private LivreDaoImpl() { }
    public static LivreDao getInstance() {
        if(instance == null) {
            instance = new LivreDaoImpl();
        }
        return instance;
    }

    private static final String SELECT_ALL_QUERY = "SELECT id, titre, auteur, isbn FROM livre;";
    private static final String SELECT_ONE_QUERY = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
    private static final String CREATE_QUERY = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM livre WHERE id = ?;";
    private static final String COUNT_QUERY = "SELECT COUNT(id) AS count FROM livre;";

    @Override
    public List<Livre> getList() throws DaoException {
        List<Livre> livres = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                Livre livre = new Livre(res.getInt("id"), res.getString("titre"), res.getString("auteur"), res.getString("isbn"));
                livres.add(livre);
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la récupération de la liste des livres", e);
        }
        return livres;
    }

    @Override
    public Livre getById(int id) throws DaoException {
        Livre livre = new Livre();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()) {
                livre.setId(res.getInt("id"));
                livre.setTitre(res.getString("titre"));
                livre.setAuteur(res.getString("auteur"));
                livre.setIsbn(res.getString("isbn"));
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la récupération du livre: id = " + id, e);
        }
        return livre;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        int id = -1;
        try {
            Connection connection= ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, titre);
            preparedStatement.setString(2, auteur);
            preparedStatement.setString(3, isbn);
            preparedStatement.executeUpdate();
            ResultSet res = preparedStatement.getGeneratedKeys();

            if(res.next()){
                id = res.getInt(1);
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new DaoException("Problème lors de la création du livre: " + titre , e);
        }
        return id;
    }

    @Override
    public void update(Livre livre) throws DaoException {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getIsbn());
            preparedStatement.setInt(4, livre.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la mise à jour du livre: " + livre, e);
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
            throw new DaoException("Problème lors de la suppression du livre id: " + id, e);
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
            throw new DaoException("Problème lors du comptage de livres.", e);
        }
        return count;
    }
}

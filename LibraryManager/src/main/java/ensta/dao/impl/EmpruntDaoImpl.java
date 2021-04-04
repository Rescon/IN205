package ensta.dao.impl;

import ensta.dao.*;
import ensta.exception.DaoException;
import ensta.model.Emprunt;
import ensta.persistence.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDaoImpl implements EmpruntDao {
    private static EmpruntDaoImpl instance;
    private EmpruntDaoImpl() { }
    public static EmpruntDao getInstance() {
        if(instance == null) {
            instance = new EmpruntDaoImpl();
        }
        return instance;
    }

    private final String SELECT_ALL_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
    private final String SELECT_CURRENT_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
    private final String SELECT_CURRENT_BY_MEMBRE_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
    private final String SELECT_CURRENT_BY_BOOK_QUERY = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
    private final String SELECT_BY_ID_QUERY = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
    private final String CREATE_QUERY = "INSERT INTO Emprunt (idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
    private final String UPDATE_QUERY = "UPDATE Emprunt SET idMembre=?, idLivre=?,dateEmprunt=?, dateRetour=? WHERE id=?;";
    private final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM emprunt WHERE idMembre IN (SELECT id FROM membre) and idLivre IN (SELECT id FROM livre);";


    @Override
    public List<Emprunt> getList() throws DaoException {
        List<Emprunt> emprunts = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY);
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao bookDao = LivreDaoImpl.getInstance();
                emprunts.add(new Emprunt(res.getInt("id"), membreDao.getById(res.getInt("idMembre")), bookDao.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
        }
        return emprunts;
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        List<Emprunt> currentEmprunts = new ArrayList<>();

        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CURRENT_QUERY);
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao bookDao = LivreDaoImpl.getInstance();
                currentEmprunts.add(new Emprunt(res.getInt("id"), membreDao.getById(res.getInt("idMembre")), bookDao.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
        }
        return currentEmprunts;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        List<Emprunt> currentEmpruntsByMembre = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CURRENT_BY_MEMBRE_QUERY);
            preparedStatement.setInt(1, idMembre);
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao bookDao = LivreDaoImpl.getInstance();
                currentEmpruntsByMembre.add(new Emprunt(res.getInt("id"), membreDao.getById(res.getInt("idMembre")), bookDao.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
        }
        return currentEmpruntsByMembre;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        List<Emprunt> currentEmpruntsByLivre = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CURRENT_BY_BOOK_QUERY);
            preparedStatement.setInt(1, idLivre);
            ResultSet res = preparedStatement.executeQuery();

            while(res.next()) {
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao bookDao = LivreDaoImpl.getInstance();
                currentEmpruntsByLivre.add(new Emprunt(res.getInt("id"), membreDao.getById(res.getInt("idMembre")), bookDao.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate()));
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Problème lors de la récupération de la liste des emprunts", e);
        }
        return currentEmpruntsByLivre;
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        Emprunt emprunt = new Emprunt();
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setInt(1, id);
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()) {
                MembreDao membreDao = MembreDaoImpl.getInstance();
                LivreDao bookDao = LivreDaoImpl.getInstance();
                emprunt = new Emprunt(res.getInt("id"), membreDao.getById(res.getInt("idMembre")), bookDao.getById(res.getInt("idLivre")), res.getDate("dateEmprunt").toLocalDate(), res.getDate("dateRetour") == null ? null : res.getDate("dateRetour").toLocalDate());
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Problème lors de la récupération du emprunt: id = " + id, e);
        }
        return emprunt;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);
            preparedStatement.setInt(1, idMembre);
            preparedStatement.setInt(2, idLivre);
            preparedStatement.setString(3, dateEmprunt + "");
            preparedStatement.setDate(4, null);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new DaoException("Problème lors de la création du emprunt.", e);
        }
    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        try{
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setInt(1, emprunt.getMembre().getId());
            preparedStatement.setInt(2, emprunt.getLivre().getId());
            preparedStatement.setString(3, emprunt.getDateEmprunt()+"");
            preparedStatement.setString(4, emprunt.getDateRetour()+"");
            preparedStatement.setInt(5, emprunt.getId());
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Problème lors de la mise à jour du emprunt.", e);
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
                count = res.getInt(1);
            }

            res.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            throw new DaoException("Problème lors du comptage des emprunts.", e);
        }
        return count;
    }
}

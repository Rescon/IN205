package ensta.service.impl;

import ensta.dao.LivreDao;
import ensta.dao.impl.LivreDaoImpl;
import ensta.exception.*;
import ensta.model.Livre;
import ensta.service.*;

import java.util.ArrayList;
import java.util.List;

public class LivreServiceImpl implements LivreService {
    private static LivreServiceImpl instance;
    private LivreServiceImpl(){}
    public static LivreServiceImpl getInstance(){
        if (instance == null) {
            instance = new LivreServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Livre> getList() throws ServiceException {
        List<Livre> Livres;
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            Livres = livreDao.getList();
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste!", e);
        }
        return Livres;
    }

    @Override
    public List<Livre> getListDispo() throws ServiceException {
        List<Livre> livresDispo  = new ArrayList<>();
        LivreDao livreDao = LivreDaoImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        try {
            List<Livre> livres = livreDao.getList();
            for (Livre livre : livres) {
                if (empruntService.isLivreDispo(livre.getId())) {
                    livresDispo.add(livre);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste des livres actuellement disponibles à l’emprunt", e);
        }
        return livresDispo;
    }

    @Override
    public Livre getById(int id) throws ServiceException {
        Livre livre;
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            livre = livreDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException("Impossible d'obtenir le livre: id = " + id, e);
        }
        return livre;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws ServiceException {
        int id = -1;
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            if (titre == null || titre.equals("")){
                throw new ServiceException("Titre vide!");
            } else {
                id = livreDao.create(titre, auteur, isbn);
            }
        } catch (DaoException e) {
            throw new ServiceException("Impossible de créer le livre", e);
        }
        return id;
    }

    @Override
    public void update(Livre livre) throws ServiceException {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            if (livre.getTitre() == null || livre.getTitre().equals("")){
                throw new ServiceException("Titre vide!");
            } else {
                livreDao.update(livre);
            }
        } catch (DaoException e) {
            throw new ServiceException("Impossible de mettre à jour ce livre.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            livreDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Impossible de supprimer ce livre.", e);
        }
    }

    @Override
    public int count() throws ServiceException {
        int count = -1;
        LivreDao livreDao = LivreDaoImpl.getInstance();
        try {
            count = livreDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Impossible de compter les livres.", e);
        }

        return count;
    }
}

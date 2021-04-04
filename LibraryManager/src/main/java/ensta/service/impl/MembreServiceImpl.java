package ensta.service.impl;

import ensta.dao.MembreDao;
import ensta.dao.impl.MembreDaoImpl;
import ensta.exception.DaoException;
import ensta.exception.ServiceException;
import ensta.model.Membre;
import ensta.service.EmpruntService;
import ensta.service.MembreService;

import java.util.ArrayList;
import java.util.List;

public class MembreServiceImpl implements MembreService {
    private static MembreServiceImpl instance;
    private MembreServiceImpl(){}
    public static MembreServiceImpl getInstance(){
        if (instance == null) {
            instance = new MembreServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Membre> getList() throws ServiceException {
        List<Membre> membres;
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            membres = membreDao.getList();
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste!", e);
        }
        return membres;
    }

    @Override
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
        List<Membre> MembresPossible  = new ArrayList<>();
        MembreDao membreDao = MembreDaoImpl.getInstance();
        EmpruntService empruntService = EmpruntServiceImpl.getInstance();
        try {
            List<Membre> membres = membreDao.getList();
            for (Membre membre : membres) {
                if (empruntService.isEmpruntPossible(membre)) {
                    MembresPossible.add(membre);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste des membres possible à l’emprunt", e);
        }
        return MembresPossible;
    }

    @Override
    public Membre getById(int id) throws ServiceException {
        Membre membre;
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            membre = membreDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir le membre: id = " + id, e);
        }
        return membre;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException {
        int id = -1;
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            if (nom == null || nom.equals("") || prenom == null || prenom.equals("")){
                throw new ServiceException("Nom ou prénom incorrect!");
            } else {
                nom = nom.toUpperCase();
                prenom = prenom.toUpperCase();
                id = membreDao.create(nom.toUpperCase(), prenom.toUpperCase(), adresse, email, telephone);
            }
        } catch (DaoException e) {
            throw new ServiceException("Impossible de créer le membre", e);
        }
        return id;
    }

    @Override
    public void update(Membre membre) throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            if (membre.getNom() == null || membre.getNom().equals("") || membre.getPrenom() == null || membre.getPrenom().equals("")){
                throw new ServiceException("Nom ou prénom incorrect!");
            } else {
                membreDao.update(membre);
            }
        } catch (DaoException e) {
            throw new ServiceException("Impossible de mettre à jour ce membre.", e);
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            membreDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Impossible de supprimer ce membre.", e);
        }
    }

    @Override
    public int count() throws ServiceException {
        int count = -1;
        MembreDao membreDao = MembreDaoImpl.getInstance();
        try {
            count = membreDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Impossible de compter les membres.", e);
        }

        return count;
    }
}

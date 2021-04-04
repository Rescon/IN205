package ensta.service.impl;

import ensta.dao.EmpruntDao;
import ensta.dao.impl.EmpruntDaoImpl;
import ensta.exception.*;
import ensta.model.*;
import ensta.service.EmpruntService;

import java.time.LocalDate;
import java.util.List;

public class EmpruntServiceImpl implements EmpruntService {
    private static EmpruntServiceImpl instance;
    private EmpruntServiceImpl(){}
    public static EmpruntServiceImpl getInstance(){
        if (instance == null) {
            instance = new EmpruntServiceImpl();
        }
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws ServiceException {
        List<Emprunt> emprunts;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            emprunts = empruntDao.getList();
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste!", e);
        }
        return emprunts;
    }

    @Override
    public List<Emprunt> getListCurrent() throws ServiceException {
        List<Emprunt> empruntsCurrent;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            empruntsCurrent = empruntDao.getListCurrent();
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste!", e);
        }
        return empruntsCurrent;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
        List<Emprunt> empruntsCurrent;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            empruntsCurrent = empruntDao.getListCurrentByMembre(idMembre);
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste!", e);
        }
        return empruntsCurrent;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
        List<Emprunt> empruntsCurrent;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            empruntsCurrent = empruntDao.getListCurrentByLivre(idLivre);
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir la liste!", e);
        }
        return empruntsCurrent;
    }

    @Override
    public Emprunt getById(int id) throws ServiceException {
        Emprunt emprunt;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            emprunt = empruntDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException("Impossible de récupérer du emprunt: id = " + id, e);
        }
        return emprunt;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            empruntDao.create(idMembre, idLivre, dateEmprunt);
        } catch (Exception e) {
            throw new ServiceException("Impossible de créer du emprunt.", e);
        }
    }

    @Override
    public void returnBook(int id) throws ServiceException {
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            Emprunt update = empruntDao.getById(id);
            update.setDateRetour(LocalDate.now());
            empruntDao.update(update);
        } catch (DaoException e) {
            throw new ServiceException("Le livre ne peut pas être retourné.", e);
        }
    }

    @Override
    public int count() throws ServiceException {
        int count = -1;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            count = empruntDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Impossible de compter des emprunts.", e);
        }
        return count;
    }

    @Override
    public boolean isLivreDispo(int idLivre) throws ServiceException {
        boolean dispo = false;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            dispo = empruntDao.getListCurrentByLivre(idLivre).isEmpty();
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir le statut du livre: id = " + idLivre, e);
        }
        return dispo;
    }

    @Override
    public boolean isEmpruntPossible(Membre membre) throws ServiceException {
        boolean possible = false;
        EmpruntDao empruntDao = EmpruntDaoImpl.getInstance();
        try {
            int num = empruntDao.getListCurrentByMembre(membre.getId()).size();

            Abonnement abonnement = membre.getAbonnement();
            int max;
            if (abonnement == Abonnement.VIP){
                max = 20;
            } else if (abonnement == Abonnement.PREMIUM){
                max = 5;
            } else {
                max = 2;
            }

            if(num < max){
                possible = true;
            }
        } catch (DaoException e) {
            throw new ServiceException("Impossible d'obtenir le statut du membre: id = " + membre.getId(), e);
        }
        return possible;
    }
}

package ensta.service;

import java.util.List;

import ensta.exception.ServiceException;
import ensta.model.Membre;

public interface MembreService {
    public List<Membre> getList() throws ServiceException;
    public List<Membre> getListMembreEmpruntPossible() throws ServiceException;
    public Membre getById(int id) throws ServiceException;
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException;
    public void update(Membre membre) throws ServiceException;
    public void delete(int id) throws ServiceException;
    public int count() throws ServiceException;

}


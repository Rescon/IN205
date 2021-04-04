package ensta.model;

import java.time.LocalDate;

public class Emprunt {
    private Integer id;
    private Membre membre;
    private Livre livre;
    LocalDate dateEmprunt;
    LocalDate dateRetour;

    public Emprunt(){
        super();
    }
    public Emprunt(Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour) {
        this();
        this.membre = membre;
        this.livre = livre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }
    public Emprunt(Integer id, Membre membre, Livre livre, LocalDate dateEmprunt, LocalDate dateRetour){
        this(membre, livre, dateEmprunt, dateRetour);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public Membre getMembre() {
        return membre;
    }
    public Livre getLivre() {
        return livre;
    }
    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }
    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setMembre(Membre membre) {
        this.membre = membre;
    }
    public void setLivre(Livre livre) {
        this.livre = livre;
    }
    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }
    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", membre=" + membre +
                ", livre=" + livre +
                ", dateEmprunt=" + dateEmprunt +
                ", dateRetour=" + dateRetour +
                '}';
    }
}

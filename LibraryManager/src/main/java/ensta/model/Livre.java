package ensta.model;

public class Livre {
    private Integer id;
    private String titre;
    private String auteur;
    private String isbn;

    public Livre(){
        super();
    }
    public Livre(String titre, String auteur, String isbn){
        this();
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
    }
    public Livre(Integer id, String titre, String auteur, String isbn){
        this(titre, auteur, isbn);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public String getTitre() {
        return titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

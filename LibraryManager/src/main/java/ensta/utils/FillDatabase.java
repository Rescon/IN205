package ensta.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;

import ensta.persistence.ConnectionManager;

public class FillDatabase {
    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~", "libraryManagerDatabase", true);
            insertWithPreparedStatement();
            System.out.println("Success !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement createPreparedStatement = null;

        List<String> createTablesQueries = new ArrayList<>();
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS livre(id INT primary key auto_increment, titre VARCHAR(100), auteur VARCHAR(100), isbn VARCHAR(20))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS membre(id INT primary key auto_increment, nom VARCHAR(100), prenom VARCHAR(100), adresse TEXT, email VARCHAR(100), telephone VARCHAR(30), abonnement ENUM('BASIC', 'PREMIUM', 'VIP') DEFAULT 'BASIC')");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS emprunt(id INT primary key auto_increment, idMembre INT, idLivre INT, dateEmprunt DATETIME, dateRetour DATETIME)");

        try {
            connection.setAutoCommit(false);

            for (String createQuery : createTablesQueries) {
                createPreparedStatement = connection.prepareStatement(createQuery);
                createPreparedStatement.executeUpdate();
                createPreparedStatement.close();
            }

            // Ajout de plusieurs enregistrement avec Statement

            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Les oiseaux migrateurs', 'Patrick FICHTER', '978-2817704876')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Le génie des oiseaux', 'Jennifer ACKERMAN', '978-2501124881')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Dans la forêt', 'Jean HEGLAND', '978-2351781425')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Les Séries télé pour les nuls', 'Marjolaine BOUTET', '978-2754009126')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('L''univers des séries TV', 'Jurgen MULLER', '978-3836542746')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Tartes pour tous', 'Jean-François PIÈGE', '978-2017042778')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Les enquêtes de l''inspecteur Higgins - Tome 32 : Jack l''éventreur, le retour', 'Christian JACQ', '978-2374481395')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('M, le bord de l''abîme', 'Bernard MINIER', '978-2374481210')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Van Gogh, Maître de la couleur', 'Gérard DENIZEAU', '978-2035941800')");
            stmt.execute("INSERT INTO livre(titre, auteur, isbn) VALUES('Catalogue vasarely/partage des formes/catalogue de l''exposition', 'Michel GAUTHIER', '978-2844268396')");

            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('CHERIF', 'Kader', '2 rue Sadi Carnot', 'kcherif@email.com', '0243585672', 'PREMIUM')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('MONGEVILLE', 'Antoine', '8 rue Frédéric Chopin', 'amongeville@email.com', '0426813579', 'VIP')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('RENOIR', 'Candice', '26 rue de l''Epeule', 'crenoir@email.com', '0485724694', 'BASIC')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('MAGELLAN', 'Simon', '48 rue du Château', 'smagellan@email.com', '0423496545', 'BASIC')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('MARLEAU', 'Sylvie', '20 rue de l''Aigle', 'smarleau@email.com', '', 'BASIC')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('BALTHAZAR', 'Raphaël', '42 rue du Clair Bocage', 'rbalthazar@email.com', '0654821968', 'PREMIUM')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('CHASSAGNE', 'Florence', '28 rue Michel Ange', 'fchassagne@email.com', '0154829537', 'VIP')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('WEISS', 'Lauren', '49 rue St Ferréol', 'lweiss@email.com', '0349302501', 'PREMIUM')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('LEBOWITZ', 'Paule', '75 avenue Jean Portalis', 'plebowitz@email.com', '0165483283', 'BASIC')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('MUNCHOWSKI', 'Gabrielle', '92 rue Gustave Eiffel', 'gmunchowski@email.com', '0157499347', 'PREMIUM')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('CAÏN', 'Frédéric', '43 route de Lyon', 'fcain@email.com', '0629831280', 'BASIC')");
            stmt.execute("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES('CASSANDRE', 'Florence', '63 rue Joseph Vernet', 'fcassandre@email.com', '0721137437', 'BASIC')");

            stmt.execute("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES('2', '1', '2021-02-12', '2021-02-19')");
            stmt.execute("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES('2', '2', '2021-03-07', '2021-03-10')");
            stmt.execute("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES('5', '3', '2021-03-01', NULL)");
            stmt.execute("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES('7', '10', '2021-03-02', '2021-03-05')");
            stmt.execute("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES('5', '2', '2021-03-02', NULL)");
            stmt.execute("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES('4', '8', '2021-03-11', NULL)");

            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}

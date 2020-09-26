package td2.dao.ListeMemoireDAO;

import java.time.LocalDate;
import java.util.*;
import td2.dao.interfaces.CommandeDAO;
import td2.pojo.*;

public class ListeMemoireCommandeDAO implements CommandeDAO {

    private static ListeMemoireCommandeDAO instance;
    private List<Commande> donnees;

    public static ListeMemoireCommandeDAO getInstance() {
        if (instance == null) {
            instance = new ListeMemoireCommandeDAO();
        }
        return instance;
    }

    public boolean insert(Commande objet) {
        objet.setId(1);
        while (this.donnees.contains(objet)) {
            objet.setId(objet.getId() + 1);
        }
        boolean ok = this.donnees.add(objet);
        return ok;
    }

    public boolean delete(Commande objet) {
        boolean ok;
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
        } else {
            ok = this.donnees.remove(objet);
        }
        return ok;
    }

    public boolean update(Commande objet) {
        int idx = this.donnees.indexOf(objet);
        if (idx == -1) {
            throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
        } else {
            this.donnees.set(idx, objet);
        }
        return true;
    }

    public Commande getById(int id) {
        int idx = this.donnees.indexOf(new Commande(id, LocalDate.now(), 0));
        if (idx == -1) {
            throw new IllegalArgumentException("Aucune categorie ne possede cet id");
        } else {
            return this.donnees.get(idx);
        }
    }
}
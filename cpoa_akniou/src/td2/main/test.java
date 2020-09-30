package td2.main;

import java.time.LocalDate;


import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public class test {
    
    public static void main(String args[]){
       Produit p1 = new Produit(2, "Pomme","Pomme verte",1,"Pomme.jpg",3);
       Produit p2 = new Produit(3, "Pomme","Pomme verte",1,"Pomme.jpg",3);
       Produit p3 = new Produit(4, "Pomme","Pomme verte",1,"Pomme.jpg",3);
       Produit p4 = new Produit(5, "Pomme","Pomme verte",1,"Pomme.jpg",3);
       Commande c1 = new Commande(1,LocalDate.now(),2);
       LigneCommande l1 = new LigneCommande(3, 1);
       c1.ajouterLigne(p1, l1);
       c1.ajouterLigne(p2, l1);
       c1.ajouterLigne(p3, l1);
       c1.ajouterLigne(p4, l1);
       //c1.afficher();
       Produit cle = c1.getKeys();
       System.out.println(cle.getId());
    }
}





       
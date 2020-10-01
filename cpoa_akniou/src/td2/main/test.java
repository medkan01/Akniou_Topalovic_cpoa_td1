package td2.main;

import java.time.LocalDate;
import java.util.ArrayList;

import td2.pojo.Commande;
import td2.pojo.LigneCommande;
import td2.pojo.Produit;

public class test {
    
    public static void main(String args[]){
       Produit p1 = new Produit(2, "Pomme","Pomme verte",1,"Pomme.jpg",3);
       Produit p2 = new Produit(3, "Pommmme","Pomme verte",1,"Pomme.jpg",3);
       Produit p3 = new Produit(4, "Pommmmmmmmmme","Pomme verte",1,"Pomme.jpg",3);
       Produit p4 = new Produit(5, "Pommmmmmmmmmmmmmmme","Pomme verte",1,"Pomme.jpg",3);
       Commande c1 = new Commande(1,LocalDate.now(),2);
       LigneCommande l1 = new LigneCommande(2, 1);
       LigneCommande l2 = new LigneCommande(3, 1);
       LigneCommande l3 = new LigneCommande(4, 1);
       LigneCommande l4 = new LigneCommande(5, 1);
       c1.ajouterLigne(p1, l1);
       c1.ajouterLigne(p2, l2);
       c1.ajouterLigne(p3, l3);
       c1.ajouterLigne(p4, l4);
       c1.afficher();
       ArrayList<LigneCommande> Tv = new ArrayList<LigneCommande>();
       ArrayList<Integer> Tk = new ArrayList<Integer>();
       c1.getKeys(Tk);
       c1.getValues(Tv);
       System.out.println(Tk.get(0));
       System.out.println(Tk.get(1));
       System.out.println(Tk.get(2));
       System.out.println(Tk.get(3));
       System.out.println(Tv.get(0));
       System.out.println(Tv.get(1));
       System.out.println(Tv.get(2));
       System.out.println(Tv.get(3));
}
}





       
package td2.tests.daolistememoiretests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daolistememoire.ListeMemoireLigneCommandeDAO;
import td2.pojo.LigneCommande;

public class ListeMemoireLigneCommandeDAOTest {

    private ListeMemoireLigneCommandeDAO instance;
    private LigneCommande ligneCommande;

    @BeforeEach
    public void setUp() {
        this.instance = ListeMemoireLigneCommandeDAO.getInstance();
        this.ligneCommande = new LigneCommande(3667,3449);
    }

	@Test
	void testInsert() {
		try{
		    assertTrue(this.instance.insert(1,1,this.ligneCommande));
		} catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
    }
    
    @Test
    void testDelete() {
        try {
            this.instance.insert(1,1,this.ligneCommande);
            assertTrue(this.instance.delete(1, 1));
        } catch(Exception e) {
            System.out.println("Erreur:\n" +e.getMessage());
        }
    }

    @Test
    void testUpdate() {
        try {
            assertTrue(this.instance.update(1, 1, this.ligneCommande));
        } catch(Exception e) {
            System.out.println("Erreur:\n" + e.getMessage());
        }
    }

 /*   @Test
    void testGetAll(){
        instance.getAll(ligneCommande);
    }*/
}
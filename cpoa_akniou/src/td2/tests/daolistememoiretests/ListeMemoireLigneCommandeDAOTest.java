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

    @Test
    void testGetAll(){
        try {
            assertNotNull(instance.getAll(2550));
        } catch(Exception e) {
            System.out.println("Erreur:\n" + e.getMessage());
        }
    }

    //Test delete avec des ids negatifs

    @Test
    void testDeleteIdNegatif(){
        try {
            this.instance.delete(-1, -1);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testDeleteIdCommandeNegatif(){
        try {
            this.instance.delete(-1, 10);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testDeleteIdProduitNegatif(){
        try {
            this.instance.delete(10, -1);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }
//Test update avec des ids negatifs
    @Test
    void testUpdateIdNegatif(){
        try {
            this.instance.update(-1, -1, ligneCommande);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testUpdateIdCommandeNegatif(){
        try {
            this.instance.update(-1, 10, ligneCommande);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testUpdateIdProduitNegatif(){
        try {
            this.instance.update(10, -1, ligneCommande);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    //Test des delete avec des ids inexistants
    @Test
    void testDeleteIdInexistant(){
        try {
            this.instance.delete(9999, 9999);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testDeleteIdCommandeInexistant(){
        try {
            this.instance.delete(9999, 10);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testDeleteIdProduitInexistant(){
        try {
            this.instance.delete(10, 9999);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }
//Test update avec des ids inexistants
    @Test
    void testUpdateIdInexistant(){
        try {
            this.instance.update(9999, 9999, ligneCommande);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testUpdateIdCommandeInexistant(){
        try {
            this.instance.update(9999, 10, ligneCommande);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }

    @Test
    void testUpdateIdProduitInexistant(){
        try {
            this.instance.update(10, 9999, ligneCommande);
            fail("Exception manquante alors que l'id est negatif");
        } catch (Exception e) {
            assertFalse(e.getMessage().isEmpty());
        }
    }
}
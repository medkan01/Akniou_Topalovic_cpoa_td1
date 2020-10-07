package td2.tests.daolistememoiretests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daolistememoire.ListeMemoireProduitDAO;
import td2.pojo.Produit;

public class ListeMemoireProduitDAOTest {

    private ListeMemoireProduitDAO instance;
    private Produit produit;

    @BeforeEach
    public void setUp() {
        this.instance = ListeMemoireProduitDAO.getInstance();
        this.produit = new Produit(2558, "Test", "Test", 222.222, "test.png", 2556);
    }

	@Test
	void testInsert() {
		try{
		    assertTrue(this.instance.insert(this.produit));
		} catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
    }
    
    @Test
    void testDelete() {
        try {
            this.instance.insert(this.produit);
            assertTrue(this.instance.delete(this.produit));
        } catch(Exception e) {
            System.out.println("Erreur:\n" +e.getMessage());
        }
    }

    @Test
    void testUpdate() {
        try {
            this.instance.insert(this.produit);
            assertTrue(this.instance.update(this.produit));
        } catch(Exception e) {
            System.out.println("Erreur:\n" + e.getMessage());
        }
    }

    @Test
	void testGetByIdOK() {
		try{
			instance.insert(this.produit);
			assertEquals(this.produit, this.instance.getById(2528));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
    }
    
    @Test
    void testGetAll(){
        try{
            assertNotNull(instance.getAll());
        }catch (Exception e){
            System.out.println("Erreur : \n" + e.getMessage());
        }
    }
}
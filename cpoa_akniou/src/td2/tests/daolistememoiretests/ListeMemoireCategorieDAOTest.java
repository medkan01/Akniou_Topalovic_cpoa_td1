package td2.tests.daolistememoiretests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daolistememoire.ListeMemoireCategorieDAO;
import td2.pojo.Categorie;

public class ListeMemoireCategorieDAOTest {
    
	private ListeMemoireCategorieDAO instance;
	private Categorie commande;
	
	@BeforeEach
	public void setUp() {
		this.instance = ListeMemoireCategorieDAO.getInstance();
		this.commande = new Categorie(1, "test","test.jpg");
	}

	@Test
	void testInsertOK() {
		try{
			assertTrue(this.instance.insert(this.commande));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
	}

	@Test
	void testDeleteOK() {
		try{
			this.instance.insert(this.commande);
			assertTrue(this.instance.delete(this.commande));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
	}

	@Test
	void testUpdateOK() {
		try{
			this.instance.insert(this.commande);
			assertTrue(this.instance.update(this.commande));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
	}

	@Test
	void testGetByIdOK() {
		try{
			instance.insert(this.commande);
			assertEquals(this.commande, this.instance.getById(2528));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
	}

	@Test
	void testGetAllOK(){
		try{
			this.instance.getAll();
		}catch (Exception e){
			System.out.println("Erreur \n" + e.getMessage());	
		}
	}

	void testGetAll(){
        try{
            assertNotNull(instance.getAll());
        }catch (Exception e){
            System.out.println("Erreur : \n" + e.getMessage());
        }
	}
	
	@Test
	void testDeleteIdNegatif(){
		try {
			this.instance.delete(new Categorie(-10, "testIdNeg", "testIdNeg.png"));
			fail("pas d'exception alors que l'id est negatif");
		} catch(Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}

	@Test
	void testDeleteIdInexistant(){
		try {
			this.instance.delete(new Categorie(9999, "testIdInexistant", "testIdInexistant.png"));
			fail("pas d'exception alors que l'id est inexistant");
		} catch (Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}

	@Test
	void testUpdateIdNegatif(){
		try {
			this.instance.update(new Categorie(-10, "testIdNeg", "testIdNeg.png"));
			fail("Pas d'exception alors que l'id est negatif");
		} catch(Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}

	@Test
	void testUpdateIdInexistant() {
		try {
			this.instance.update(new Categorie(9999, "testIdInexistant", "testIdInexistant.png"));
			fail("pas d'exception alors que l'id est inexistant");
		} catch(Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}
}

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
}

package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLCategorieDAO;
import td2.pojo.Categorie;

public class MySQLCategorieDAOTest {

	private MySQLCategorieDAO instance;
	private Categorie commande;
	
	@BeforeEach
	public void setUp() {
		this.instance = MySQLCategorieDAO.getInstance();
		this.commande = new Categorie(1, "test","test.jpg");
	}

	@Test
	void testInsertOK() {
		try{
			assertTrue(this.instance.insert(this.commande));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testInsertFalse() {
		try{
			assertTrue(this.instance.insert(this.commande));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteOK() {
		try{
			this.instance.insert(this.commande);
			assertTrue(this.instance.delete(this.commande));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateOK() {
		try{
			this.instance.insert(this.commande);
			assertTrue(this.instance.update(this.commande));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testGetByIdOK() {
		try{
			instance.insert(this.commande);
			assertEquals(this.commande, this.instance.getById(2528));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testGetAllOK(){
		try{
			this.instance.getAll();
		}catch (SQLException sqle){
			System.out.println("Erreur \n" + sqle.getMessage());	
		}
	}

	void testGetAll(){
        try{
            assertNotNull(instance.getAll());
        }catch (SQLException sqle){
            System.out.println("Erreur : \n" + sqle.getMessage());
        }
    }
}

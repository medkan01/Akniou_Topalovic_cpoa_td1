package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLProduitDAO;
import td2.pojo.Produit;

public class MySQLProduitDAOTest {

    private MySQLProduitDAO instance;
    private Produit produit;

    @BeforeEach
    public void setUp() {
        this.instance = MySQLProduitDAO.getInstance();
        this.produit = new Produit(2558, "Test", "Test", 222.222, "test.png", 2556);
    }

	@Test
	void testInsert() {
		try{
		    assertTrue(this.instance.insert(this.produit));
		} catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
    }
    
    @Test
    void testDelete() {
        try {
            this.instance.insert(this.produit);
            assertTrue(this.instance.delete(this.produit));
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" +sqle.getMessage());
        }
    }

    @Test
    void testUpdate() {
        try {
            this.instance.insert(this.produit);
            assertTrue(this.instance.update(this.produit));
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" + sqle.getMessage());
        }
    }

    @Test
	void testGetByIdOK() {
		try{
			instance.insert(this.produit);
			assertEquals(this.produit, this.instance.getById(2528));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
    }
    
    @Test
    void testGetAll(){
        try{
            assertNotNull(instance.getAll());
        }catch (SQLException sqle){
            System.out.println("Erreur : \n" + sqle.getMessage());
        }
    }

    @Test
	void testDeleteIdNegatif(){
		try {
			assertFalse(this.instance.delete(new Produit(-10, "Test", "Test", 222.222, "test.png", 2556)));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteIdInexistant(){
		try {
			assertFalse(this.instance.delete(new Produit(9999, "Test", "Test", 222.222, "test.png", 2556)));
		} catch (SQLException sqle) {
			System.out.println("Erreur sql:\n" + sqle.getMessage());
		}
	}
	
	@Test
	void testUpdateIdNegatif(){
		try {
			assertFalse(this.instance.update(new Produit(-10, "Test", "Test", 222.222, "test.png", 2556)));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateIdInexistant() {
		try {
			assertFalse(this.instance.update(new Produit(9999,  "Test", "Test", 222.222, "test.png", 2556)));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

}
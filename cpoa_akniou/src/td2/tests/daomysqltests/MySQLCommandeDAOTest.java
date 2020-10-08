package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLCommandeDAO;
import td2.pojo.Commande;

public class MySQLCommandeDAOTest {
   
    private MySQLCommandeDAO instance;
    private Commande commande;

    @BeforeEach
    public void setUp() {
        this.instance = MySQLCommandeDAO.getInstance();
        this.commande = new Commande(2567,LocalDate.now() ,2550);
    }

	@Test
	void testInsert() {
		try{
		    assertTrue(this.instance.insert(this.commande));
		} catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
    }
    
    @Test
    void testDelete() {
        try {
            this.instance.insert(this.commande);
            assertTrue(this.instance.delete(this.commande));
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" +sqle.getMessage());
        }
    }

    @Test
    void testUpdate() {
        try {
            this.instance.insert(this.commande);
            assertTrue(this.instance.update(this.commande));
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" + sqle.getMessage());
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
			assertFalse(this.instance.delete(new Commande(-10,LocalDate.now() ,2550)));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteIdInexistant(){
		try {
			assertFalse(this.instance.delete(new Commande(9999,LocalDate.now() ,2550)));
		} catch (SQLException sqle) {
			System.out.println("Erreur sql:\n" + sqle.getMessage());
		}
	}
	
	@Test
	void testUpdateIdNegatif(){
		try {
			assertFalse(this.instance.update(new Commande(-10, LocalDate.now() ,2550)));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateIdInexistant() {
		try {
			assertFalse(this.instance.update(new Commande(9999, LocalDate.now() ,2550)));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
    }
    
}

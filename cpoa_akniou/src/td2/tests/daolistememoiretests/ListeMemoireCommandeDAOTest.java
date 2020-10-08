package td2.tests.daolistememoiretests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daolistememoire.ListeMemoireCommandeDAO;
import td2.pojo.Commande;

public class ListeMemoireCommandeDAOTest {
   
    private ListeMemoireCommandeDAO instance;
    private Commande commande;

    @BeforeEach
    public void setUp() {
        this.instance = ListeMemoireCommandeDAO.getInstance();
        this.commande = new Commande(2567,LocalDate.now() ,2550);
    }

	@Test
	void testInsert() {
		try{
		    assertTrue(this.instance.insert(this.commande));
		} catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
    }
    
    @Test
    void testDelete() {
        try {
            this.instance.insert(this.commande);
            assertTrue(this.instance.delete(this.commande));
        } catch(Exception e) {
            System.out.println("Erreur:\n" +e.getMessage());
        }
    }

    @Test
    void testUpdate() {
        try {
            this.instance.insert(this.commande);
            assertTrue(this.instance.update(this.commande));
        } catch(Exception e) {
            System.out.println("Erreur:\n" + e.getMessage());
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
			this.instance.delete(new Commande(-10,LocalDate.now() ,2550));
			fail("pas d'exception alors que l'id est inexistant");
		} catch(Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}

	@Test
	void testDeleteIdInexistant(){
		try {
			this.instance.delete(new Commande(9999,LocalDate.now() ,2550));
			fail("pas d'exception alors que l'id est inexistant");
		} catch(Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}
	
	@Test
	void testUpdateIdNegatif(){
		try {
			this.instance.update(new Commande(-10, LocalDate.now() ,2550));
			fail("pas d'exception alors que l'id est inexistant");
		} catch(Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
	}

	@Test
	void testUpdateIdInexistant() {
		try {
			this.instance.update(new Commande(9999, LocalDate.now() ,2550));
			fail("pas d'exception alors que l'id est inexistant");
		} catch(Exception e) {
			assertFalse(e.getMessage().isEmpty());
		}
    }
}

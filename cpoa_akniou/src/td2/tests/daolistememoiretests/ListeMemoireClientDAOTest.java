package td2.tests.daolistememoiretests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daolistememoire.ListeMemoireClientDAO;
import td2.pojo.Client;

public class ListeMemoireClientDAOTest {

	private ListeMemoireClientDAO instance;
	private Client client;

    @BeforeEach
	public void setUp() {
		this.instance = ListeMemoireClientDAO.getInstance();
		this.client = new Client(2550, "TEST", "Test", "tst", "Tst123", "0", "Rue du test", "00000", "Test", "Test");
    }
    
    @Test
	void testInsertOK() {
		try{
			assertTrue(this.instance.insert(this.client));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
	}

	@Test
	void testDeleteOK() {
		try{
			this.instance.insert(this.client);
			assertTrue(this.instance.delete(this.client));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
	}

	@Test
	void testUpdateOK() {
		try{
			this.instance.insert(this.client);
			assertTrue(this.instance.update(this.client));
		}catch (Exception e) {
			System.out.println("Erreur \n" + e.getMessage());
		}
	}

	@Test
	void testGetByIdOK() {
		try{
			instance.insert(this.client);
			assertEquals(this.client, this.instance.getById(2528));
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

package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLClientDAO;
import td2.pojo.Client;

public class MySQLClientDAOTest {

	private MySQLClientDAO instance;
	private Client client;

    @BeforeEach
	public void setUp() {
		this.instance = MySQLClientDAO.getInstance();
		this.client = new Client(2550, "TEST", "Test", "tst", "Tst123", "0", "Rue du test", "00000", "Test", "Test");
    }
    
    @Test
	void testInsertOK() {
		try{
			assertTrue(this.instance.insert(this.client));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteOK() {
		try{
			this.instance.insert(this.client);
			assertTrue(this.instance.delete(this.client));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateOK() {
		try{
			this.instance.insert(this.client);
			assertTrue(this.instance.update(this.client));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testGetByIdOK() {
		try{
			instance.insert(this.client);
			assertEquals(this.client, this.instance.getById(2528));
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
			assertFalse(this.instance.delete(new Client(-10, "TEST", "Test", "tst", "Tst123", "0", "Rue du test", "00000", "Test", "Test")));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteIdInexistant(){
		try {
			assertFalse(this.instance.delete(new Client(9999, "TEST", "Test", "tst", "Tst123", "0", "Rue du test", "00000", "Test", "Test")));
		} catch (SQLException sqle) {
			System.out.println("Erreur sql:\n" + sqle.getMessage());
		}
	}
	
	@Test
	void testUpdateIdNegatif(){
		try {
			assertFalse(this.instance.update(new Client(-10, "TEST", "Test", "tst", "Tst123", "0", "Rue du test", "00000", "Test", "Test")));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateIdInexistant() {
		try {
			assertFalse(this.instance.update(new Client(9999, "TEST", "Test", "tst", "Tst123", "0", "Rue du test", "00000", "Test", "Test")));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}
}

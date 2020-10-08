package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLCategorieDAO;
import td2.pojo.Categorie;

public class MySQLCategorieDAOTest {

	private MySQLCategorieDAO instance;
	private Categorie categorie;
	
	@BeforeEach
	public void setUp() {
		this.instance = MySQLCategorieDAO.getInstance();
		this.categorie = new Categorie(1, "test","test.jpg");
	}

	@Test
	void testInsertOK() {
		try{
			assertTrue(this.instance.insert(this.categorie));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testInsertFalse() {
		try{
			assertTrue(this.instance.insert(this.categorie));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteOK() {
		try{
			this.instance.insert(this.categorie);
			assertTrue(this.instance.delete(this.categorie));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateOK() {
		try{
			this.instance.insert(this.categorie);
			assertTrue(this.instance.update(this.categorie));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testGetByIdOK() {
		try{
			instance.insert(this.categorie);
			assertEquals(this.categorie, this.instance.getById(2528));
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
	

	@Test
	void testDeleteIdNegatif(){
		try {
			assertFalse(this.instance.delete(new Categorie(-10, "testIdNeg", "testIdNeg.png")));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteIdInexistant(){
		try {
			assertFalse(this.instance.delete(new Categorie(9999, "testIdInexistant", "testIdInexistant.png")));
		} catch (SQLException sqle) {
			System.out.println("Erreur sql:\n" + sqle.getMessage());
		}
	}
	
	@Test
	void testUpdateIdNegatif(){
		try {
			assertFalse(this.instance.update(new Categorie(-10, "testIdNeg", "testIdNeg.png")));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateIdInexistant() {
		try {
			assertFalse(this.instance.update(new Categorie(9999, "testIdInexistant", "testIdInexistant.png")));
		} catch(SQLException sqle) {
			System.out.println("Erreur SQL:\n" + sqle.getMessage());
		}
	}
}

package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLCategorieDAO;
import td2.pojo.Categorie;

class MySQLCategorieDAOTest {

	@Test
	void testInsertOK() {
		try{
			Categorie c1 = new Categorie(1, "test","test.jpg");
			MySQLCategorieDAO instance = MySQLCategorieDAO.getInstance();
			assertEquals(true, instance.insert(c1));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testDeleteOK() {
		try{
			Categorie c1 = new Categorie(12, "","");
			MySQLCategorieDAO instance = MySQLCategorieDAO.getInstance();
			assertTrue(instance.delete(c1));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testUpdateOK() {
		try{
			Categorie c1 = new Categorie(2, "Pull","Pull.png");
			MySQLCategorieDAO instance = MySQLCategorieDAO.getInstance();
			assertTrue(instance.update(c1));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testGetByIdOK() {
		try{
			MySQLCategorieDAO instance = MySQLCategorieDAO.getInstance();
			Categorie c1 = new Categorie(2528, "test", "test.png");
			instance.insert(c1);
			assertEquals(c1, instance.getById(2528));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

	@Test
	void testGetAllOK(){
		try{
			MySQLCategorieDAO instance = MySQLCategorieDAO.getInstance();
		}catch (SQLException sqle){
			System.out.println("Erreur \n" + sqle.getMessage());	
		}
	}

}

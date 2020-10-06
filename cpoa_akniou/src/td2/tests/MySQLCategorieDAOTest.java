package td2.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLCategorieDAO;
import td2.pojo.Categorie;

class MySQLCategorieDAOTest {

	@Test
	void testInsert() {
		try{
		Categorie c1 = new Categorie(1, "test","test.jpg");
		MySQLCategorieDAO instance = MySQLCategorieDAO.getInstance();
		assertEquals(true, instance.insert(c1));
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}

}

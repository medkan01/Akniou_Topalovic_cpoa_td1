package td2.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLCategorieDAO;
import td2.pojo.Categorie;

class MySQLCategorieDAOTest {

	@Test
	void testInsert() {
		Categorie c1 = new Categorie(1, "test","test.jpg");
		assertEquals(true,insert(c1));
	}

}

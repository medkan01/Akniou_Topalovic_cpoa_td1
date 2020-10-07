package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.dao.daomysql.MySQLLigneCommandeDAO;
import td2.pojo.LigneCommande;

public class MySQLLigneCommandeDAOTest {

    private MySQLLigneCommandeDAO instance;
    private LigneCommande ligneCommande;

    @BeforeEach
    public void setUp() {
        this.instance = MySQLLigneCommandeDAO.getInstance();
        this.ligneCommande = new LigneCommande(3667,3449);
    }

	@Test
	void testInsert() {
		try{
		    assertTrue(this.instance.insert(1,1,this.ligneCommande));
		} catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
    }
    
    @Test
    void testDelete() {
        try {
            this.instance.insert(1,1,this.ligneCommande);
            assertTrue(this.instance.delete(1, 1));
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" +sqle.getMessage());
        }
    }

    @Test
    void testUpdate() {
        try {
            assertTrue(this.instance.update(1, 1, this.ligneCommande));
        } catch(SQLException sqle) {
            System.out.println("Erreur:\n" + sqle.getMessage());
        }
    }

    @Test
    void testGetAll(){
        try {
        assertNotNull(instance.getAll(2550));
    } catch(SQLException sqle) {
        System.out.println("Erreur:\n" + sqle.getMessage());
    }
}
}
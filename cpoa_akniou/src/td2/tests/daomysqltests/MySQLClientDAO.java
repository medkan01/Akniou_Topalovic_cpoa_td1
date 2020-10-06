package td2.tests.daomysqltests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import td2.pojo.Client;

public class MySQLClientDAO {

    private Object instance;

    @BeforeEach
	public void setUp() {
		Client
    }
    
    @Test
	void testInsertOK() {
		try{
			
		}catch (SQLException sqle) {
			System.out.println("Erreur \n" + sqle.getMessage());
		}
	}
}

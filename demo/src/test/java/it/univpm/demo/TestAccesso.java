package it.univpm.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import model.ristorante.*;;

@SpringBootTest
public class TestAccesso {

	Ristorante ristorante = new Ristorante();
			
			
			@BeforeEach
			 void setUp() {
				ristorante.database.creaAccount("Giovanni", "ciao");
			}
			
			@AfterEach
			void tearDown() {
				
			}

			@Test
			void test() {
				assertTrue(ristorante.database.login("Giovanni", "ciao"));
			}
			
			@Test
			void test1() {
				assertFalse(ristorante.database.login("Giovanni", "hello"));
			}

		}

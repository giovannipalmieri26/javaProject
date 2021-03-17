package it.univpm.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import it.univpm.demo.model.api.UtilizzoApi;
import it.univpm.demo.model.luogo.Previsioni;



@SpringBootTest
public class TestPrevisioni {
	UtilizzoApi a = new UtilizzoApi() ;
	Previsioni previsioneOggi;
	Previsioni previsioneGiorno;
	LocalDate oggi = LocalDate.now();
	LocalDate giornoPassato =  LocalDate.of(2021, 03, 18);
	
	@BeforeEach
	 void setUp() {
		previsioneOggi = a.valorizzaPrevisione(oggi);
		previsioneGiorno = a.valorizzaPrevisione(giornoPassato);
	}
	
	@AfterEach
	void tearDown() {
		
	}

	@Test
	void test() {
		assertEquals("Bel tempo",previsioneOggi.getPrincipale());
	}
	
	@Test
	void test1() {
		assertEquals("Previsioni non disponibili",previsioneGiorno.getPrincipale());
	}

}

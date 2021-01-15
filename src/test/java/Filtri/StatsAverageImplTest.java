package Filtri;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

class StatsAverageImplTest {
	
	private Vector<Double> vett;
	private StatsAverageImpl error;

	@BeforeEach
	void setUp() throws Exception {
		vett.add(12.5);
		vett.add(10.2);
		vett.add(16.2);
		vett.add(-14.3);
		vett.add(-4.5);
		vett.add(20.5);
		vett.add(13.3);
		vett.add(18.1);
		vett.add(20.5);
		vett.add(17.5);
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetMedia() {
		assertEquals(error.getMedia(vett), 11);
	}
}
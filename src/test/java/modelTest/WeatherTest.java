package modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Weather;

class WeatherTest {
	
	Weather wet;
	
	@BeforeEach
	void setUp() throws Exception {
		wet=new Weather();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		wet.setLat(51.51);
		wet.setLon(-0.13);
		wet.setTempMin(2.40);
		wet.setTempMax(3.02);
		wet.setTemp(3.00);
		wet.setPressione(1029);
		wet.setDataLettura("15-01-2021, 16:21");
		wet.setGiorno("15-01-2021 18:00");
		wet.setNome("Londra");
		
		assertEquals(51.51,wet.getLat());
		assertEquals(-0.13,wet.getLon());
		assertEquals(2.40,wet.getTempMin());
		assertEquals(3.02,wet.getTempMax());
		assertEquals(3.00,wet.getTempPercepita());
		assertEquals(1029,wet.getPressione());
		assertEquals("15-01-2021, 16:21",wet.getDataLettura());
		assertEquals("15-01-2021 18:00",wet.getGiorno());
		assertEquals("Londra",wet.getNome());
		
	}

}

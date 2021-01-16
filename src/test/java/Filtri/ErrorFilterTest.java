package Filtri;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Weather;

/**
 * @author Marco Pasquale Martino
 *
 */
class ErrorFilterTest {

	private Weather actual;
	private Vector<Weather> wet;
	private ErrorFilter error;
	
	@BeforeEach
	void setUp() throws Exception {
		error=new ErrorFilter();
		actual=new Weather(51.51,-0.13,2.40,3.02,3.00,1029,1610733600,"15-01-2021, 16:21","15-01-2021 18:00","Londra");
		wet.add(new Weather(51.51,-0.13,2.36,3.02,3.02,1031,1610733600,"15-01-2021, 16:21","15-01-2021 18:00","Londra"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSetGetWeather() {
		error.setWeather(wet);
		assertEquals(error.getWeather(),wet);
	}
	
	@Test
	void testCalculateError() {
		assertEquals(1.35,error.calculateError(wet.get(0), actual));
	}
	
}

package filtriTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import filtri.StatsMaxMin;
import model.Weather;
import model.WeatherPress;
import java.util.Vector;

/**
 * @author Massimo Mecarelli
 *
 */

class StatsMaxMinTest {
	
	Vector<WeatherPress> vett = new Vector<WeatherPress>();
	WeatherPress press;
	Weather wet;
	StatsMaxMin m = new StatsMaxMin();

	@BeforeEach
	void setUp() {
		for (int i=1; i<=5; i++) {
			wet = new Weather();
			wet.setPressione(i);
			press = new WeatherPress();
			press.setter(wet);
			vett.add(press);
		}
	}
	
	@AfterEach
	void tearDown() {}

	@Test
	void testSetMinVectorOfWeatherPress() {
		m.setMin(vett);
		assertEquals(1, m.getMin());
	}

	@Test
	void testSetMaxVectorOfWeatherPress() {
		m.setMax(vett);
		assertEquals(5, m.getMax());
	}

}

package Filtri;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import model.Weather;
import model.WeatherPress;
import java.util.Vector;

class StatsMaxMinTest {
	
	Vector<WeatherPress> vett;
	WeatherPress press;
	Weather wet;
	StatsMaxMin m;

	@BeforeEach
	void setUp() {
		for (int i=1; i<=5; i++) {
			if (i%2==0)
				wet.setPressione(i*2);
			else wet.setPressione(i*3);
			press.setter(wet);
			vett.add(press);
		}
	}
	
	@AfterEach
	void tearDown() {}

	@Test
	void testSetMinVectorOfWeatherPress() {
		m.setMin(vett);
		assertEquals(m.getMin(), 3);
	}

	@Test
	void testSetMaxVectorOfWeatherPress() {
		m.setMax(vett);
		assertEquals(m.getMax(), 15);
	}

}

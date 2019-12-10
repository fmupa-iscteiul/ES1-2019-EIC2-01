package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import auxAvaliarDefeitos.JTableSample;

class TestJTableSample {

	JTableSample sample;
	@BeforeEach
	void setUp() throws Exception {
		sample = new JTableSample();
	}

	@Test
	final void testIsDCI() {
		assertTrue(sample.isADCI(true, true));
		assertFalse(sample.isDCI(false, true));
		assertFalse(sample.isDCI(true, false));
	}

	@Test
	final void testIsDII() {
		assertTrue(sample.isADCI(true, true));
		assertFalse(sample.isDCI(false, true));
		assertFalse(sample.isDCI(true, false));
	}

	@Test
	final void testIsADCI() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testIsADII() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetRuleEvaluationRegraIntInt() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetRuleEvaluationRegraIntDouble() {
		fail("Not yet implemented"); // TODO
	}

}

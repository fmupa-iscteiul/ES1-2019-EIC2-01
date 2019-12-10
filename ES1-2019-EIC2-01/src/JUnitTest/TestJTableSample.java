package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import auxAvaliarDefeitos.JTableSample;
import projetoES1.Regra;

class TestJTableSample {

	JTableSample sample;
	@BeforeEach
	void setUp() throws Exception {
		sample = new JTableSample();
	}

	@Test
	final void testIsDCI() {
		assertTrue(sample.isDCI(true, true));
		assertFalse(sample.isDCI(false, true));
		assertFalse(sample.isDCI(true, false));
		assertFalse(sample.isDCI(false, false));
	}

	@Test
	final void testIsDII() {
		assertTrue(sample.isDII(false, true));
		assertFalse(sample.isDII(false, false));
		assertFalse(sample.isDII(true, false));
		assertFalse(sample.isDII(true, true));
	}

	@Test
	final void testIsADCI() {
		assertTrue(sample.isADCI(false, false));
		assertFalse(sample.isADCI(false, true));
		assertFalse(sample.isADCI(true, false));
		assertFalse(sample.isADCI(true, true));
	}

	@Test
	final void testIsADII() {
		assertTrue(sample.isADII(true, false));
		assertFalse(sample.isADII(false, true));
		assertFalse(sample.isADII(false, false));
		assertFalse(sample.isADII(true, true));
	}

	@Test
	final void testGetRuleEvaluationRegraIntInt() {
		Regra regra1 = new Regra("Regra1", "LOC", ">=", "AND","CYCLO" , ">=", 10, 20);
		assertFalse(sample.getRuleEvaluation(regra1, 30, 40));
	}

	@Test
	final void testGetRuleEvaluationRegraIntDouble() {
		Regra regra1 = new Regra("Regra1", "LOC", ">=", "AND","CYCLO" , ">=", 10, 20);
		assertFalse(sample.getRuleEvaluation(regra1, 30, 40.0));
	}

}

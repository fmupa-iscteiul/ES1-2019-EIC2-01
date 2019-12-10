package JUnitTest;

import org.junit.runner.RunWith;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.runner.JUnitPlatform;

@RunWith(JUnitPlatform.class)
@SelectClasses({TestRegra.class})
public class AllTests {
	
}

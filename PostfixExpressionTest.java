package cs2321;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PostfixExpressionTest {

	@Test
	void test() throws Exception{
		String input = "4 20 5 + * 6 -";
		int expected = 94;
		int actual = PostfixExpression.evaluate(input);
		assertEquals(actual, expected);
	}

	@Test
	void test2() throws Exception{
		String input = "5 20 5 2 * + 3 / -";
		int expected = -5;
		int actual = PostfixExpression.evaluate(input);
		assertEquals(actual, expected);
	}

	@Test
	void test3() throws Exception{
		String input = "3 4 5 * -";
		int expected = -17;
		int actual = PostfixExpression.evaluate(input);
		assertEquals(actual, expected);
	}
}

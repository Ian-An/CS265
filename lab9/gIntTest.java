import junit.framework.*;

public class gIntTest extends TestCase { 
	
	private gInt sum1, sum2;
	private gInt multiply1, multiply2;
	private gInt norm1, norm2;


	public gIntTest(String name) {
		super(name);
	}

	protected void setUp(){
		sum1 = new gInt(3, 4);
		sum2 = new gInt(5, 5);
		multiply1 = new gInt(2, 8);
		multiply2 = new gInt(5, 3);
		norm1 = new gInt(3, 4);
		norm2 = new gInt(5, 5);

	}
	public void testAdd() {
		gInt test = new gInt(8,9);
		assertNotNull(test);
		gInt sum = sum1.add(sum2);
		assertNotNull(sum);
		assertEquals(sum.real(), test.real());
		assertEquals(sum.imag(),test.imag());
	}

	public void testMultiply() {
		gInt checkM = new gInt(-14, 46);
		assertNotNull(checkM);
		gInt result = multiply1.multiply(multiply2);
		assertNotNull(result);
		assertEquals(result.real(),checkM.real());
		assertEquals(result.imag(), checkM.imag());
	}

	public void testNorm() {
		float check1 = 5;
		assertNotNull(check1);
		float n1 = norm1.norm();
		assertEquals(n1,check1);
		float check2 = (float)Math.sqrt(50);
		assertNotNull(check2);
		float n2 = norm2.norm();
		assertEquals(n2, check2);
		
	}
	public static void main (String args[]) {
		String[] caseName = {gIntTest.class.getName()};
		junit.textui.TestRunner.main(caseName);
	}
}

package pl.edu.agh.mwo;
 
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

//----------------------------------------------------------------------
// CRC Card
//----------------------------------------------------------------------
// Class: Scanner
//----------------------------------------------------------------------
// Collaborators:
//----------------------------------------------------------------------
// Responsibilities:
//	- Scanner is created for specific input text
//	- returns consecutive tokens separated by white chars
//	- ignores sequences of white chars
//  - ignores comments started by '%' and ended up with new line
//	- parentheses are also tokens
//	- the end of processing is signaled by empty token
//----------------------------------------------------------------------

public class ScannerTest extends TestCase {

	Scanner scanner1, scanner2, scanner3, scanner4, scanner5;

	@Before
	protected void setUp() {
		scanner1 = new Scanner("Slowo1 slowo2 SlOwO3");
		scanner2 = new Scanner("element1( \t\n element2)");
		scanner3 = new Scanner("element1 %\t komentarz\nelement2");
		scanner4 = new Scanner("fix \n \t");
		scanner5 = new Scanner(")element1(");
	}

	@Test
	public void testSekwencja() {
		assertEquals("slowo1", scanner1.getToken());
		assertEquals("slowo2", scanner1.getToken());
		assertEquals("slowo3", scanner1.getToken());
		assertEquals("", scanner1.getToken());
	}

	@Test
	public void testBiale() {
		assertEquals("element1", scanner2.getToken());
		assertEquals("(", scanner2.getToken());
		assertEquals("element2", scanner2.getToken());
		assertEquals(")", scanner2.getToken());
		assertEquals("", scanner2.getToken());
	}
	
	@Test
	public void testKomentarz() {
		assertEquals("element1", scanner3.getToken());
		assertEquals("element2", scanner3.getToken());
		assertEquals("", scanner3.getToken());
	}

	@Test
	public void testKoniec() {
		assertEquals("fix", scanner4.getToken());
		assertEquals("", scanner4.getToken());
		assertEquals("", scanner4.getToken());
	}
	
	@Test
	public void testParentheses() {
		assertEquals(")", scanner5.getToken());
		assertEquals("element1", scanner5.getToken());
		assertEquals("(", scanner5.getToken());
		assertEquals("", scanner5.getToken());
	}


}

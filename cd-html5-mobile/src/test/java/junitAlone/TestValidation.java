package junitAlone;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cia.group6.fileimport.FileReader;
import cia.group6.validation.PrimitiveCheck;
import cia.group6.validation.ValidateExcelFile;

public class TestValidation {

	@ Test
	public void testIntegerIsInteger() {
		assertEquals("The string '5' must be an integer.", true, PrimitiveCheck.isInteger("5"));
	}

	@ Test
	public void testDoubleIsNotInteger() {
		assertEquals("The string '5.12' must not be an integer.", false, PrimitiveCheck.isInteger("5.12"));
	}

	@ Test
	public void testLetterIsNotInteger() {
		assertEquals("The string 'ab5c' must not be an integer.", false, PrimitiveCheck.isInteger("ab5c"));
	}

	@ Test
	public void testSymbolIsNotInteger() {
		assertEquals("The string '@!' must not be an integer.", false, PrimitiveCheck.isInteger("@!"));
	}

	@ Test
	public void testLongIsLong() {
		assertEquals("The string '240210000000003' must be an integer.", true, PrimitiveCheck.isLong("240210000000003"));
	}

	@ Test
	public void testDoubleIsNotLong() {
		assertEquals("The string '5.12' must not be an integer.", false, PrimitiveCheck.isLong("5.12"));
	}

	@ Test
	public void testLetterIsNotLong() {
		assertEquals("The string 'ab5c' must not be an integer.", false, PrimitiveCheck.isLong("ab5c"));
	}

	@ Test
	public void testSymbolIsNotLong() {
		assertEquals("The string '@!' must not be an integer.", false, PrimitiveCheck.isLong("@!"));
	}
	
	FileReader goodFileReader;
    FileReader badFileReader;

    @Before
    public void setUp() throws Exception {
        goodFileReader = new FileReader("testA.xls");
        badFileReader = new FileReader("badData.xls");
    }
    
    @Test
    public void isBaseDataValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isBaseDataValid();
        assertTrue(check);
    }
    @Test
    public void isBaseDataValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isBaseDataValid();
        assertFalse(check);
    }
    
    @Test
    public void isEventCauseValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isEventCauseValid();
        assertTrue(check);
    }
    @Test
    public void isEventCauseValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isEventCauseValid();
        assertFalse(check);
    }
    
    @Test
    public void isFailureClassValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isFailureClassValid();
        assertTrue(check);
    }
    @Test
    public void isFailureClassValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isFailureClassValid();
        assertFalse(check);
    }
       
    @Test
    public void isUETableValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isUETableValid();
        assertTrue(check);
    }
    @Test
    public void isUETableValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isUETableValid();
        assertFalse(check);
    }
    
    @Test
    public void isMCCMNCValidTrueTest() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isMCCMNCValid();
        assertTrue(check);
    }
    @Test
    public void isMCCMNCValidFalseTest() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isMCCMNCValid();
        assertFalse(check);
    }
    
    @Test
    public void isXLSValidTrue() {
        ValidateExcelFile validate = new ValidateExcelFile(goodFileReader);
        boolean check = validate.isXLSValid();
        assertTrue(check);
    }
    @Test
    public void isXLSValidFalse() {
        ValidateExcelFile validate = new ValidateExcelFile(badFileReader);
        boolean check = validate.isXLSValid();
        assertFalse(check);
    }
}

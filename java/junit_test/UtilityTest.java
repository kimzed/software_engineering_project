package junit_test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import structural.handlers.StringInformationObject;
import structural.handlers.Utility;
import structural.members.Member;
/**
 * Junit test class which handles the tests regarding the Utility class
 * contains 3 tests
 * @author gianp
 *
 */
public class UtilityTest extends TestCase {
	/**
	 * tests if an element(member) is correctly recognized as duplicate in a test csv
	 * returns false when element already exists
	 */
	public void testElementAlreadyExists_ReturnsFalseWhenNotExists()
	{
		Member member1 = new Member("Jan", "25/04/1995", "fake_password", "email");
		Member member2 = new Member("Jan2", "25/04/1995", "fake_password", "email");
		Member member3 = new Member("Jan3", "25/04/1995", "fake_password", "email");
		
		ArrayList<Member> members = new ArrayList<Member>();
		members.add(member1);
		members.add(member2);
		
		boolean memberExistsAlready = Utility.elementAlreadyExists(member3, members);
		
		assertFalse(memberExistsAlready);

	}
	/**
	 * tests if an element(member) is correctly recognized as duplicate in a test csv,
	 * return true when element already exists
	 */
	public void testElementAlreadyExists_ReturnsTrueWhenExists()
	{
		Member member1 = new Member("Jan", "25/04/1995", "fake_password", "email");
		Member member2 = new Member("Jan2", "25/04/1995", "fake_password", "email");
		Member member3 = new Member("Jan", "25/04/1995", "fake_password", "email");
		
		ArrayList<StringInformationObject> members = new ArrayList<StringInformationObject>();
		members.add(member1);
		members.add(member2);
		
		boolean memberExistsAlready = Utility.elementAlreadyExists(member3, members);
		
		assertTrue(memberExistsAlready);

	}
	/**
	 * tests if a file is correctly deleted from the directory
	 */
	public void testDeleteFile_FileIsCorrectlyDeleted()
	{
		String fakeFile = "data/unit_test/test_file2.txt";
		File file = new File(fakeFile);
		
		
		Utility.createFile(fakeFile);
		
		Utility.deleteFile(fakeFile);
		
		assertFalse(file.delete());
		
	}
	

}


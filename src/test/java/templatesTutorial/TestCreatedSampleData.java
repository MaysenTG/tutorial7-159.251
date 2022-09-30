package templatesTutorial;

import static org.junit.Assert.*;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class TestCreatedSampleData {

	@Test
	public void test() throws IOException {
		XMLDecoder d = new XMLDecoder(
                new BufferedInputStream(
                    new FileInputStream("friends.xml")));
		List<Person> persons = (List<Person>) d.readObject();
		
		assertEquals(8,persons.size());
		
		Person last = persons.get(7);
		
		assertEquals("Kate",last.getFirstName());
		assertEquals("Wang",last.getName());
		assertEquals(Gender.FEMALE,last.getGender());
		assertEquals(12,last.getAddress().getNumber());
		assertEquals("Albert Street",last.getAddress().getStreet());
		assertEquals("Palmerston North",last.getAddress().getTown());
		
		d.close();

	}

}

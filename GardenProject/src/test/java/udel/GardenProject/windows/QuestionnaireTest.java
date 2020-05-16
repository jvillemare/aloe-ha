package udel.GardenProject.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import udel.GardenProject.garden.Controller;
import udel.GardenProject.garden.Model;
import udel.GardenProject.windows.Questionnaire;

public class QuestionnaireTest {
	
	@Test
	public void testQuestionnaire() {
		Controller c = new Controller();
		Model m = new Model(c, 0, 1);
		Questionnaire q = new Questionnaire(m);
		assertTrue(q.getTitle().equals("Questions About Your Garden..."));
	}
}

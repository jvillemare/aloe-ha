package test.udel.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import main.udel.garden.Model;
import main.udel.windows.Questionnaire;

public class QuestionnaireTest {
	
	@Test
	public void testQuestionnaire() {
		Model m = new Model(0, 1);
		Questionnaire q = new Questionnaire(m);
		assertTrue(q.getTitle().equals("Questions About Your Garden..."));
	}
}

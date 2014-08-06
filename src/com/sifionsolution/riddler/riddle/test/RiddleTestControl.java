package com.sifionsolution.riddler.riddle.test;

import static com.sifionsolution.commons.ContentVerifyer.notEmpty;
import static org.joda.time.DateTime.now;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.sifionsolution.riddler.model.RiddleTest;
import com.sifionsolution.riddler.model.RiddleTestAnswer;
import com.sifionsolution.riddler.model.dao.RiddleTestDAO;

@RequestScoped
public class RiddleTestControl {

	@Inject
	private RiddleTestDAO dao;

	private RiddleTest current;

	private boolean solved = false;

	private String clue = null;

	@PostConstruct
	public void init() {
		current = get();
	}

	public RiddleTest getCurrent() {
		return current;
	}

	private RiddleTest get() {
		RiddleTest current = dao.getCurrentTest();

		if (current == null)
			return dao.getNextTest();

		return current;
	}

	public void answer(String answer) {
		RiddleTestAnswer entity = new RiddleTestAnswer(null, current, answer, now());
		dao.save(entity);

		evaluateAnswer(entity);
	}

	public boolean isSolved() {
		return solved;
	}

	public String getClue() {
		return clue;
	}

	private void evaluateAnswer(RiddleTestAnswer entity) {
		solved = entity.isCorrectAnswer();

		if (solved) {
			current.riddleSolved();
			dao.update(current);
		} else {
			checkClues(entity);
		}

	}

	// TODO bad method name here
	private void checkClues(RiddleTestAnswer entity) {
		clue = entity.findClue();
	}

	public void comment(RiddleTest test, String comment) {
		if (notEmpty(comment)) {
			test.setComment(comment);
			dao.update(test);
		}
	}
}

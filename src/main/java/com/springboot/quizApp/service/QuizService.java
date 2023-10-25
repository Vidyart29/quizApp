package com.springboot.quizApp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.quizApp.model.Question;
import com.springboot.quizApp.model.QuestionWrapper;
import com.springboot.quizApp.model.Quiz;
import com.springboot.quizApp.model.Response;
import com.springboot.quizApp.repository.QuestionRepository;
import com.springboot.quizApp.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Question> questions= questionRepository.findRandomQuestionByCategory(category, numQ);
		
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizRepository.save(quiz);
		
		return new ResponseEntity<>("quiz created successfully!", HttpStatus.CREATED);
		   
	}

//	LOGIC FOR GET QUIZ QUESTION BY ID
//	we need to fetch quiz object from database, so we have quizrepo, from that we'll fetch id.
//	what if we are mentioning a quiz id which is not there in db, so that time it will through null error. 
//	so that is why we are giving optional quiz id.
//	so what we are doing here is, we will get the quiz from quiz we'll get the questions(bcoz now the quiz object actly has the question), 
//	but we need to convert those questions into question wrapper
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionById(Integer id) {
		
		Optional<Quiz>quiz =quizRepository.findById(id);
		List<Question> questionsFromDB= quiz.get().getQuestions();
		List<QuestionWrapper>questionsForUser= new ArrayList<>();
		
		for (Question q : questionsFromDB) {
			QuestionWrapper wq= new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUser.add(wq);
		} 
		return new ResponseEntity<>( questionsForUser,  HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizRepository.findById(id).get(); 
		List<Question> questions= quiz.getQuestions();
		int right=0;
		int i=0;
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()));
			right ++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}



}

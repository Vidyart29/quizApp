package com.springboot.quizApp.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.quizApp.model.Question;
import com.springboot.quizApp.model.Response;
import com.springboot.quizApp.service.QuestionService;
import com.springboot.quizApp.service.QuizService;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	QuestionService questionService;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion() {
		return questionService.getAllQuestion();
	}
	
//	@GetMapping("id/{id}")
//	public ResponseEntity<List<Question>> getQuestionById(@PathVariable("id") Integer id) {
//		return questionService.getQuestionById(id);
//	}
	
//	/question/category/java
	@GetMapping("category/{cat}")
	public ResponseEntity<List<Question>>getQuestionByCategory(@PathVariable("cat") String category) {
		return questionService.getQuestionByCategory(category);	
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		System.out.println("your question is....."+ question);
		return questionService.addQuestion(question);	
	}

	@DeleteMapping("deletedId/{id}")
	public ResponseEntity<String> deleteQuestionById(@PathVariable("id")Integer id) {
		return questionService.deleteQuestion(id);

	} 

	
//	ALSO LOGIC CAN BE LIKE THIS
//	@DeleteMapping("deletedId/{id}")
//	public ResponseEntity<String> deleteQuestionById(@PathVariable("id")Integer id) {
//		boolean deleted = questionService.deleteQuestion(id);
//		if(deleted) {
//			return ResponseEntity.ok("question deleted");
//		}
//		else {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("question not found");
//		}
//
//	}
		

	
	
	
	
	
	
	
	
	
}

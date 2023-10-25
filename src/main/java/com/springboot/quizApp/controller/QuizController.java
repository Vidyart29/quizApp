package com.springboot.quizApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.quizApp.model.Question;
import com.springboot.quizApp.model.QuestionWrapper;
import com.springboot.quizApp.model.Response;
import com.springboot.quizApp.service.QuizService;


@RestController
@RequestMapping("quiz")
public class QuizController{
 
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
		return quizService.createQuiz(category, numQ, title);
//		return new ResponseEntity<>("works", HttpStatus.OK);
	} 
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestionById(@PathVariable("id") Integer id) {
		return quizService.getQuizQuestionById(id);
		
	}
	
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
		return quizService.calculateResult(id, responses);
	} 
	
}

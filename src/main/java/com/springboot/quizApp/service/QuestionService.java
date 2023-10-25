package com.springboot.quizApp.service;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.springboot.quizApp.model.Question;
import com.springboot.quizApp.repository.QuestionRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;


@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public ResponseEntity< List<Question>> getAllQuestion() {
			try {
				return new ResponseEntity<> (questionRepository.findAll(), HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace(); 
			}
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
	} 
	
	public ResponseEntity< List<Question>> getQuestionByCategory(String category) {
		try {
		return new ResponseEntity<List<Question>> (questionRepository.findByCategory(category), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_GATEWAY);
	}
	
	public ResponseEntity<String>addQuestion(Question question) {
		questionRepository.save(question);
		return new ResponseEntity<String>("success" , HttpStatus.CREATED);
		
	}
	
	public ResponseEntity<String> deleteQuestion(Integer id) {
		if (questionRepository.existsById(id)) {
			questionRepository.deleteById(id);
			return new ResponseEntity<>("deleted successfully!", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("question not found", HttpStatus.NOT_FOUND);
		}
	}
	
//	public boolean deleteQuestion(Integer id) {
//		if (questionRepository.existsById(id)) {
//			questionRepository.deleteById(id);
//			return true;
//		}
//		else {
//			return false;
//		}	
//
//	}
	public ResponseEntity<String> updateQuestion(Question question, Integer id) {
		
		questionRepository.save(question);
		return new ResponseEntity<>("question updated successfully!", HttpStatus.OK);
	}

//	TO BE UMCOMMENTED AND MAKE IT CORRECT
//	public ResponseEntity<List<Question>> getQuestionById(Integer id) {
//		questionRepository.findById(id);
//		return new ResponseEntity<List<Question>>(HttpStatus.OK);
//	}


}

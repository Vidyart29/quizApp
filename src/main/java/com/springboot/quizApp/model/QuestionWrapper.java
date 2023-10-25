package com.springboot.quizApp.model;

import lombok.Data;
// this class is just to display question to the users, which will be coming for quiz.
//adv of this question class
@Data
public class QuestionWrapper {

	public Integer id;
	public String questionTitle;
	public String option1;
	public String option2;
	public String option3;
	public String option4;

	public QuestionWrapper(Integer id, String questionTitle, String option1, String option2, String option3,
			String option4) {
		super();
		this.id = id;
		this.questionTitle = questionTitle;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
	}
	
}
// 
 
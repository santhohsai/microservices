package com.santhosh.QuizService.service;


import com.santhosh.QuizService.Feign.QuizInterface;
import com.santhosh.QuizService.Repo.QuizRepo;
import com.santhosh.QuizService.model.QuestionWrapper;
import com.santhosh.QuizService.model.Quiz;
import com.santhosh.QuizService.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuizService
{
     @Autowired
     QuizRepo quizRepo;

    @Autowired
    QuizInterface quizInterface;


    public List<Integer> createQuiz(String category, int numq,String title)
    {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numq).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIDs(questions);
        quizRepo.save(quiz);
        return  questions;
    }

    public List<QuestionWrapper> getDataByid(Integer id)
    {
       Quiz quiz =  quizRepo.findById(id)
               .orElseThrow(() -> new RuntimeException("data does not exists with this id " + id));
       List<Integer> qids = quiz.getQuestionIDs() ;

       ResponseEntity<List<QuestionWrapper>> questionWrapper = quizInterface.getQuestionsFromIds(qids);


        return questionWrapper.getBody();
    }

    public int submitData(Integer id, List<Response> responses)
    {

      Quiz quiz = quizRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("no questions exists with this id " + id));

     ResponseEntity<Integer> response = quizInterface.getscore(responses);

     return  response.getBody();



    }
}

package com.santhosh.QuizService.Controller;


import com.santhosh.QuizService.model.QuestionWrapper;
import com.santhosh.QuizService.model.QuizDto;
import com.santhosh.QuizService.model.Response;
import com.santhosh.QuizService.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController
{
    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDto quizDto)
    {
       List<Integer> quiz = quizService.createQuiz(quizDto.getCategory(),quizDto.getNumq(),quizDto.getTitle());

       return ResponseEntity.status(HttpStatus.CREATED).body(quiz) ;

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getDataById(@PathVariable Integer id)
    {
       List<QuestionWrapper> quiz = quizService.getDataByid(id);

       return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<?> submitData(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
        Integer quiz = quizService.submitData(id,responses);
        return ResponseEntity.status(HttpStatus.CREATED).body(quiz);
    }


}

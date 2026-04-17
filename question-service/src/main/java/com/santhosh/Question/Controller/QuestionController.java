package com.santhosh.Question.Controller;


import com.santhosh.Question.model.Question;
import com.santhosh.Question.model.QuestionWrapper;
import com.santhosh.Question.model.Response;
import com.santhosh.Question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("questions")
public class QuestionController
{

    @Autowired
    QuestionService  questionService;

    @Autowired
    Environment environment;

    @GetMapping("/allquestions/")
    public ResponseEntity<List<Question>> getAllquestions()
    {
        List<Question>  questions = questionService.getAllQuestions();

        return ResponseEntity.status(HttpStatus.OK).body(questions);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getByCatgegory(@PathVariable String category )
    {
        List<Question> catquestions =  questionService.getQuestionByCategory(category);

        return ResponseEntity.status(HttpStatus.OK).body(catquestions);
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addData(@RequestBody Question question)
    {
        Question addquestion =  questionService.addData(question);

        return  ResponseEntity.status(HttpStatus.CREATED).body(addquestion);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String category, @RequestParam Integer numQ ){
        List<Integer> QuizQestions = questionService.getQuestionsForQuiz(category, numQ);

        return  ResponseEntity.status(HttpStatus.OK).body(QuizQestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds)
    {
        log.info(environment.getProperty("local.server.port"));
        List<QuestionWrapper> questionsids = questionService.getQuestionFromIds(questionIds);

        return ResponseEntity.status(HttpStatus.CREATED).body(questionsids);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getscore(@RequestBody List<Response> responses)
    {
        Integer score = questionService.getScore(responses);

        return  ResponseEntity.status(HttpStatus.CREATED).body(score);
    }



}

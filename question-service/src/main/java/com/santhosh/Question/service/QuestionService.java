package com.santhosh.Question.service;


import com.santhosh.Question.Repo.QuestionRepo;
import com.santhosh.Question.model.Question;
import com.santhosh.Question.model.QuestionWrapper;
import com.santhosh.Question.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService
{

    @Autowired
    QuestionRepo questionRepo;

    public List<Question> getAllQuestions()
    {
         return questionRepo.findAll();
    }

    public List<Question> getQuestionByCategory(String category)
    {
        return questionRepo.findByCategory(category);
    }


    public Question addData(Question question)
    {
        return questionRepo.save(question);
    }

    public List<Integer> getQuestionsForQuiz(String category, Integer numQ)
    {
        List<Question> questions = questionRepo.findRandomQuestionsByCategory(category,numQ) ;

        return questions.stream()
                .map(Question::getId)
                .toList();
    }

    public List<QuestionWrapper> getQuestionFromIds(List<Integer> questionIds)
    {
        List<QuestionWrapper> wrappers = new ArrayList<>();


        for(Integer q : questionIds)
        {
            Question question = questionRepo.findById(q)
                    .orElseThrow(()-> new RuntimeException("no data esixts with the given id " + q));

            QuestionWrapper qwrapper = new QuestionWrapper();
            qwrapper.setId(question.getId());
            qwrapper.setQuestionTitle(question.getQuestionTitle());
            qwrapper.setOption1(question.getOption1());
            qwrapper.setOption2(question.getOption2());
            qwrapper.setOption3(question.getOption3());
            qwrapper.setOption4(question.getOption4());

            wrappers.add(qwrapper);
        }


        return wrappers;
    }

    public Integer getScore(List<Response> responses) {
        //List<Integer> score = new ArrayList<>();
        int right = 0;

        for(Response r : responses) {

            Question question = questionRepo.findById(r.getId())
                    .orElseThrow(() -> new RuntimeException("quetsion does not exists with this id " +r.getId()));


            if(r.getResponse().equals(question.getRightAnswer()))
            {
                right++;
            }

        }
        return right;
    }
}

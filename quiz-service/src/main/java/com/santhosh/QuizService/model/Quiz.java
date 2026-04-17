package com.santhosh.QuizService.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity
public class Quiz
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int Id;
    private String Title;

   @ElementCollection
    private List<Integer> questionIDs;
}

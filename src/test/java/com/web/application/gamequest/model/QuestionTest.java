package com.web.application.gamequest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    Question question;

    @BeforeEach
    void init () {
        question = new Question(1,"Question", Question.Type.LOST);
    }

    @Test
    void getType() {


        assertEquals(Question.Type.LOST, question.getType());
    }

    @Test
    void setType() {
        question.setType(Question.Type.USUAL);

        assertEquals(Question.Type.USUAL, question.getType());
    }
}
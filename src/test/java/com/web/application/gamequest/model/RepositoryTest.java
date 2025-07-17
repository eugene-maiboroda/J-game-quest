package com.web.application.gamequest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    Repository mock;
    Question q1;
    Question q2;
    Answer answer;

    @BeforeEach
    void creatRepositoryMock() {
        mock = mock(Repository.class);
        q1 = new Question(1, "You’ve lost your memory. Accept the UFO call", Question.Type.LOST);
        q2 = new Question(2, "You accepted the call. Are you heading to the captain’s bridge?", Question.Type.LOST);
        answer =new Answer (1, "Accept the call", q1, q2);
    }

    @Test
    void getQuestionById() {

        when(mock.getQuestionById(1)).thenReturn(q1);

        assertEquals(q1, mock.getQuestionById(1));

    }

    @Test
    void getAnswerById() {

        when(mock.getAnswerById(2)).thenReturn(answer);

        assertEquals(answer, mock.getAnswerById(2));

    }

    @Test
    void getAnswerByFromQuestionId() {

        Collection<Answer> collection = Collections.singletonList(answer);

        when(mock.getAnswerByFromQuestionId(1)).thenReturn(collection);

        assertEquals(collection, mock.getAnswerByFromQuestionId(1));

    }
}
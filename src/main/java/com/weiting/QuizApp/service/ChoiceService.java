package com.weiting.QuizApp.service;

import com.weiting.QuizApp.dao.ChoiceDAO;

import com.weiting.QuizApp.domain.Choice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoiceService {

    private final ChoiceDAO choiceDao;

    @Autowired
    public ChoiceService(ChoiceDAO choiceDao) {
        this.choiceDao = choiceDao;
    }

    public boolean checkRight(Integer selectedChoiceId) {
        return choiceDao.getChoiceById(selectedChoiceId).is_correct();
    }

    public List<Choice> getChoicesByQuestionId(Integer questionId) {
        return choiceDao.getChoicesByQuestionId(questionId);
    }

    public String getChoiceById(Integer choiceId) {
        return choiceDao.getChoiceById(choiceId).getDescription();
    }
    public Choice getRightChoiceByQuestionId(Integer questionId) {
        List<Choice> choices = choiceDao.getChoicesByQuestionId(questionId);
        for(Choice choice : choices) {
            if(choice.is_correct()) {
                return choice;
            }
        }
        return null;
    }

    public void saveChoices(List<Choice> choices) {
        for(Choice choice : choices) {
            choiceDao.saveChoice(choice);
        }
    }

    public void updateChoices(List<Choice> updatedChoices) {
        for(Choice choice : updatedChoices) {
            choiceDao.updateChoice(choice);
        }
    }
}

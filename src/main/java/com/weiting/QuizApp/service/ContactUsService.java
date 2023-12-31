package com.weiting.QuizApp.service;

import com.weiting.QuizApp.dao.ContactUsDAO;
import com.weiting.QuizApp.domain.ContactUsMessage;
import com.weiting.QuizApp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsService {
    private final ContactUsDAO contactUsDAO;

    @Autowired
    public ContactUsService(ContactUsDAO contactUsDAO) {
        this.contactUsDAO = contactUsDAO;
    }

    public void createNewContactUSMessage(ContactUsMessage message) {
        contactUsDAO.saveContactUsMessage(message);
    }

    public List<ContactUsMessage> getAllMessages() {
        return contactUsDAO.getAllMessages();
    }
}

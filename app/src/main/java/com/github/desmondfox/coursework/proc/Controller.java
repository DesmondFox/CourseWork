package com.github.desmondfox.coursework.proc;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.github.desmondfox.coursework.PollActivity;
import com.github.desmondfox.coursework.R;
import com.github.desmondfox.coursework.entities.Phone;
import com.github.desmondfox.coursework.entities.Question;

import java.util.ArrayDeque;
import java.util.Deque;

public class Controller {
    private static QuestionProcessor processor;
    private Deque<Question> questionDeque = new ArrayDeque<>();
    private int checkedPhoneId = -1;
    private Context context;

    public Controller(Context context, QuestionProcessor processor) {
        this.context = context;
        this.processor = processor;
        questionDeque.add(processor.getQuestions().get(0));
    }

    public int dequeSize() {
        return questionDeque.size();
    }

    public Question getQuestion() {
        return questionDeque.getLast();
    }

    private Question find(int id) {
        for (Question question : processor.getQuestions())
            if (question.getId() == id)
                return question;
        return null;
    }

    public static Phone findPhone(int phone_id) {
        for (Phone phone : processor.getPhones())
            if (phone.getId() == phone_id)
                return phone;
        return null;
    }

    public int getCheckedPhoneId() {
        return checkedPhoneId;
    }

    public boolean yes() {
        Question question = questionDeque.getLast();
        if (question.getId_yes() != null) {
            questionDeque.add(find(question.getId_yes()));
            return true;
        } else {
            checkedPhoneId = question.getPhone_yes_id();
            return false;
        }

    }

    public boolean no() {
        Question question = questionDeque.getLast();
        if (question.getId_no() != null) {
            questionDeque.add(find(question.getId_no()));
            return true;
        } else {
            checkedPhoneId = question.getPhone_no_id();
            return false;
        }
    }

    public boolean back() {
        if (questionDeque.size() == 1) {
            Log.d("Controller", "Reached poll root. Deque contains 1 element");
            Toast.makeText(
                    context,
                    context.getResources().getText(R.string.pollmenu_emptyDeque),
                    Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        questionDeque.removeLast();
        Log.d("Controller", "Removed last element from deque");
        return true;
    }
}

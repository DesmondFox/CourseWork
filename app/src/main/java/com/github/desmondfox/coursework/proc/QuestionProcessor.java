package com.github.desmondfox.coursework.proc;

import android.util.Log;

import com.github.desmondfox.coursework.entities.Phone;
import com.github.desmondfox.coursework.entities.Question;
import com.github.desmondfox.coursework.proc.ItemParser;
import com.github.desmondfox.coursework.proc.providers.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class QuestionProcessor {
    private DataProvider provider;
    private static List<Question> questions = new ArrayList<>();
    private static List<Phone> phones = new ArrayList<>();

    public QuestionProcessor(DataProvider provider) {
        this.provider = provider;
        this.provider.addOnDownloadCompleteListener((result, type) -> {
            switch (type) {
                case PHONES:
                    phones = ItemParser.parsePhones(result);
                    break;
                case TREE:
                    questions = ItemParser.parseQuestions(result);
                    break;
            }
        });
    }

    public QuestionProcessor() {

    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public DataProvider getProvider() {
        return provider;
    }

    public void loadData() {
        provider.download();
    }
}

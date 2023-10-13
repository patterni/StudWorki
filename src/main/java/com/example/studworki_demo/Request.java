package com.example.studworki_demo;

import java.io.Serializable;

public class Request implements Serializable {
    private Vacancy vacancy;
    private String text;

    public Request(){

    }
    public Request(Vacancy vacancy, String text) {
        this.vacancy = vacancy;
        this.text = text;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Request{" +
                "vacancy=" + vacancy +
                ", text='" + text + '\'' +
                '}';
    }
}

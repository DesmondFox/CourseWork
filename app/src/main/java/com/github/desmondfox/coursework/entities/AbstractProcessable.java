package com.github.desmondfox.coursework.entities;

public abstract class AbstractProcessable implements Processable{
    protected String name;
    protected String description;
    protected Integer id;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Integer getId() {
        return id;
    }
}

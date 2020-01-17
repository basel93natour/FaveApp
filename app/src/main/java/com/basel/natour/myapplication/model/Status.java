package com.basel.natour.myapplication.model;

public enum Status {

    SUCCESS,
    ERROR,
    LOADING;

    public boolean isSuccess()
    {
        return this==SUCCESS;
    }

    public boolean isLoading()
    {
        return this==LOADING;
    }

    public boolean isFailed()
    {
        return this==ERROR;
    }


}

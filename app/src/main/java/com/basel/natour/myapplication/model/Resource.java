package com.basel.natour.myapplication.model;

import androidx.annotation.Nullable;

public class Resource {

    Status status;
    String errorMessage;

    public Resource(Status status , @Nullable String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public Resource success()
    {
        return new Resource( Status.SUCCESS,null );
    }


    public Resource loading()
    {
        return new Resource( Status.LOADING,null );
    }



    public Resource error(String errorMessage)
    {
        return new Resource( Status.ERROR,errorMessage );
    }



}

package com.basel.natour.myapplication.model;

import androidx.annotation.Nullable;

public class Resource<ResultType> {

    Status status;
    ResultType data;
    String errorMessage;

    public Resource(Status status , @Nullable ResultType data  , @Nullable String errorMessage) {
        this.status = status;
        this.data = data;
        this.errorMessage = errorMessage;
    }

    public Resource<ResultType> success(ResultType data)
    {
        return new Resource<ResultType>( Status.SUCCESS,data,null );
    }

    public Resource<ResultType> loading()
    {
        return new Resource<ResultType>( Status.LOADING,null,null );
    }

    public Resource<ResultType> error(String errorMessage)
    {
        return new Resource<ResultType>( Status.ERROR,null,errorMessage );
    }


    public ResultType getData()
    {
        return data;
    }

}

package com.omer.simplelogin.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {

    @Getter
    @Setter
    private ResponseType responseType;

    @Getter
    @Setter
    private int errorCode;

    @Getter
    @Setter
    private String message;

    @Getter
    @Setter
    private T data;
}

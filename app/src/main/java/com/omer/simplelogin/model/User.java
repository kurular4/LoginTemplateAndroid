package com.omer.simplelogin.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class User {
    @NonNull
    private String id;

    @NonNull
    private String username;

    @NonNull
    private String email;

    private List<Document> documentList;
}

package com.mori.blog.model;

import com.sun.istack.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;



@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @javax.validation.constraints.NotNull
    private Long id;
    @NotNull
    @Size(min=2, max=30, message = "제목은 2자 이상 30자 이하")
    private String title;
    private String content;
}

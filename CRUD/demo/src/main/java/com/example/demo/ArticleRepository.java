package com.example.demo;

import com.example.demo.Model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ArticleRepository {
    private final List<Article> store = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1);


}

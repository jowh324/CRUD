package com.example.demo;


import com.example.demo.Model.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/article")
public class ArticleController {
    private final Map<Long, Article> storage = new ConcurrentHashMap<>();
    private final AtomicLong idG = new AtomicLong(1);

    public Article getById(long id) {
        return storage.get(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = storage.get(id);
        if (article != null) {
            return ResponseEntity.ok(article);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        long id = idG.incrementAndGet();
        article.setId(id);
        storage.put(id, article);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article article1 = storage.get(id);
        if (article1 == null) {
            return ResponseEntity.notFound().build();
        }
        article.setId(id);
        storage.put(id, article1);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        Article article2 = storage.remove(id);
        if (article2 != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}


package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.JokeData;

public interface JokeRepository extends JpaRepository<JokeData, Integer> {
    @Query("SELECT j, count(c.id) as viewCount FROM jokes j JOIN JokeCall c ON j.id = c.joke.id GROUP BY j ORDER BY viewCount DESC")
    Page<JokeData> findTopJokes(Pageable pageable);
}

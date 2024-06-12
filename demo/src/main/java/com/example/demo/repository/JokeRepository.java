package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.JokeData;

public interface JokeRepository extends JpaRepository<JokeData, Integer> {
    @Query(value = "SELECT j.* FROM jokes j LEFT JOIN joke_calls c ON j.id = c.joke_id GROUP BY j.id ORDER BY COUNT(c.id) DESC LIMIT 5", nativeQuery = true)
    List<JokeData> findTopJokes();

    @Query(value = "SELECT * FROM jokes ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    JokeData findRandomJoke();
}

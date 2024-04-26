package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "joke_calls")
public class JokeCall {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "joke_call_seq")
    @SequenceGenerator(name = "joke_call_seq", sequenceName = "joke_call_seq", allocationSize = 1)
    private int id;

    @Column(name = "call_time")
    private LocalDateTime callTime;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "joke_id")
    private JokeData joke;
}

package com.example.Birthdays.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface PersonRepository extends JpaRepository <Person, Integer> {
    @Query("SELECT MAX(p.id) FROM Person p")
    Integer findMaxId();
}

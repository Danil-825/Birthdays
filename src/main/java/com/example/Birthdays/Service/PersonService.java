package com.example.Birthdays.Service;


import com.example.Birthdays.Repository.Person;
import com.example.Birthdays.Repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Integer findMaxId() {
        return personRepository.findMaxId();
    }

    public Person findById(Integer id) {
        return personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void deleteById(Integer id) {
        personRepository.deleteById(id);
    }
}

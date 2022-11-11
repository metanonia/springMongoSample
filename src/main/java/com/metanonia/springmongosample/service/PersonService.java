package com.metanonia.springmongosample.service;

import com.metanonia.springmongosample.model.Person;
import com.metanonia.springmongosample.repostory.PersonRepository;
import com.metanonia.springmongosample.repostory.TemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TemplateRepository templateRepository;

    @Async
    @Scheduled(fixedRate = 2000)
    public void scheduleFixedRateTask() {
        Person p = new Person("Joe", 20);
        personRepository.save(p);
        Person q = new Person("Kim", 30);
        templateRepository.save(q);
        List<Person>l = personRepository.findAll();
        log.info(l.toString());
        personRepository.deleteAll();
    }
}

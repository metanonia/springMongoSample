package com.metanonia.springmongosample;

import com.metanonia.springmongosample.model.Person;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Slf4j
@SpringBootApplication
public class SpringMongoSampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoSampleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "sample");

        Person p = new Person("Joe", 34);
        mongoTemplate.insert(p);
        log.info("Id=" +  p.getId().toString());

        log.info((mongoTemplate.findOne(new Query(where("name").is("Joe")), Person.class)).toString());

        Person pp = mongoTemplate.findById(p.getId(), Person.class);
        log.info("found: " + pp.toString());

        UpdateResult ur = mongoTemplate.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        log.info(ur.toString());
        BasicQuery query = new BasicQuery("{\"name\" : \"Joe\"}");
        log.info((mongoTemplate.findOne(query, Person.class)).toString());

        UpdateResult wr = mongoTemplate.updateMulti(new Query(where("name").is("Joe")), new Update().inc("age", 10), Person.class);
        log.info(wr.toString());

        log.info((mongoTemplate.findOne(new Query(where("name").is("Joe")), Person.class)).toString());

        mongoTemplate.remove(p);

        List<Person>people = mongoTemplate.findAll(Person.class);
        log.info(people.toString());
        log.info("# of Person = " + people.size());
        mongoTemplate.dropCollection("person");
    }
}

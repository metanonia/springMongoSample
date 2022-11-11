package com.metanonia.springmongosample.run;

import com.metanonia.springmongosample.model.Person;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Testable
class MongoTest  {

    @Test
    void testMongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "sample");

        Person p = new Person("Joe", 34);
        mongoTemplate.insert(p);
        System.out.println("Id=" +  p.getId().toString());

        System.out.println((mongoTemplate.findOne(new Query(where("name").is("Joe")), Person.class)).toString());

        Person pp = mongoTemplate.findById(p.getId(), Person.class);
        System.out.println("found: " + pp.toString());

        UpdateResult ur = mongoTemplate.updateFirst(query(where("name").is("Joe")), update("age", 35), Person.class);
        System.out.println(ur.toString());
        BasicQuery query = new BasicQuery("{\"name\" : \"Joe\"}");
        System.out.println((mongoTemplate.findOne(query, Person.class)).toString());

        UpdateResult wr = mongoTemplate.updateMulti(new Query(where("name").is("Joe")), new Update().inc("age", 10), Person.class);
        System.out.println(wr.toString());

        System.out.println((mongoTemplate.findOne(new Query(where("name").is("Joe")), Person.class)).toString());

        mongoTemplate.remove(p);

        List<Person>people = mongoTemplate.findAll(Person.class);
        System.out.println(people.toString());
        System.out.println("# of Person = " + people.size());
        mongoTemplate.dropCollection("person");
    }
}

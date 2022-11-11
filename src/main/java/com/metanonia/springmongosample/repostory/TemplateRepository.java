package com.metanonia.springmongosample.repostory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TemplateRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    public Object save(Object o) {
        return mongoTemplate.insert(o);
    }
}

package com.mcheng.project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomersMongoRepository extends MongoRepository <Customer, String>{

}

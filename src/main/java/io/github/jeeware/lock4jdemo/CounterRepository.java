package io.github.jeeware.lock4jdemo;

import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, String> {
}

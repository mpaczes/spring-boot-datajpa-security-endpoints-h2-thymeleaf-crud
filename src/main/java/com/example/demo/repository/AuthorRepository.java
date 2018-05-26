package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

	List<Author> findAllByOrderByLastNameAscFirstNameAsc();

}
package com.classic.booking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classic.booking.api.model.BookRequest;

@Repository
public interface BookMyShowRepository  extends JpaRepository<BookRequest, Integer>{

}

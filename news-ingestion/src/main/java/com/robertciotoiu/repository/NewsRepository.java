package com.robertciotoiu.repository;

import com.robertciotoiu.model.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    Optional<NewsEntity> findByTitle(String title);
}

package com.cielux.api.repository;

import com.cielux.api.repository.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
    @Query("SELECT f FROM File f WHERE f.name = :name")
    Optional<File> findByNameAndUserId(@Param("name") String name);
}

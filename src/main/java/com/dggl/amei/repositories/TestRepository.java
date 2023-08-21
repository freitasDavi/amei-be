package com.dggl.amei.repositories;

import com.dggl.amei.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository  extends JpaRepository<Test, Long> {
}

package dev.ruzlab.bulk.data.user.repository;

import dev.ruzlab.bulk.data.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE users_tbl", nativeQuery = true)
    void truncateTable();
}

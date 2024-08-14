package com.example.registration.repositories;

import com.example.registration.entities.RegistrationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, String> {

    // Aplicação de filtragem e paginação
    @Query("SELECT r FROM RegistrationEntity r " +
            "WHERE (:courseName IS NULL OR r.course.name LIKE %:courseName%) " +
            "AND (:studentName IS NULL OR r.student.name LIKE %:studentName%)")
    Page<RegistrationEntity> findRegistrationsByFilters(@Param("courseName") String courseName,
                                                        @Param("studentName") String studentName,
                                                        Pageable pageable);


}

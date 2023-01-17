package bymihaj.ecrm.student.rest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<StudentDTO, Long>{

    @Query("SELECT S FROM StudentDTO S WHERE S.phone = ?1 AND S.lessonId = ?2")
    Collection<StudentDTO> findPhoneLesson(String phone, Long lessonId);
    
    @Query("SELECT count(*) FROM StudentDTO S WHERE S.lessonId = ?1")
    Long countInLesson(Long lessonId);
    
    @Query("SELECT S FROM StudentDTO S WHERE S.lessonId = ?1")
    Collection<StudentDTO> getRegisteredStudentForLesson(Long lessonId);
    
}

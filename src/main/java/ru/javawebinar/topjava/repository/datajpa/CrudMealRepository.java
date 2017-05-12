package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * gkislin
 * 02.10.2016
 */
@Transactional
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    List<Meal> findByUserIdOrderByDateTimeDesc(Integer userId);
    List<Meal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(Integer userId, LocalDateTime startDate, LocalDateTime endDate);
}

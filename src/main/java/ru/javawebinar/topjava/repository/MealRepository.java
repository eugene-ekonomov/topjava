package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
@Repository
public interface MealRepository {
    Meal save(int userId, Meal Meal);

    void delete(int userId, int id);

    Meal get(int userId, int id);

    Collection<Meal> getAll(int userId);
}

package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;


    @Override
    public Meal save(int userId, Meal meal) {
        Meal saved = repository.save(userId, meal);
        if(saved==null){
            throw new NotFoundException("Not found.");
        }
        return saved;
    }

    @Override
    public void delete(int userId, int id) throws NotFoundException {
        boolean result = repository.delete(userId, id);
        if(result==false){
            throw new NotFoundException("Not found.");
        }

    }

    @Override
    public Meal get(int userId, int id) throws NotFoundException {
        Meal meal = repository.get(userId, id);
        if(meal==null){
            throw new NotFoundException("Not found.");
        }
        return meal;
    }

    @Override
    public List<MealWithExceed> getAll(int userId) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    @Override
    public void update(int userId, Meal meal) {
        Meal saved = repository.save(userId, meal);
    }
}

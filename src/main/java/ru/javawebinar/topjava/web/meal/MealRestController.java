package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    public Meal get(int id){
        return service.get(AuthorizedUser.id(), id);
    }

    public void delete(int id){
        service.delete(AuthorizedUser.id(), id);
    }

    public List<MealWithExceed> getAll(){
        return service.getAll(AuthorizedUser.id());
    }

    public Meal create(Meal meal){
        return service.save(AuthorizedUser.id(), meal);
    }

    public void update(Meal meal){
        service.update(AuthorizedUser.id(), meal);
    }

}

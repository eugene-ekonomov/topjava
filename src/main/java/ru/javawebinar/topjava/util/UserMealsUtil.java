package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> lumwe = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        for(UserMealWithExceed umwe: lumwe){
            System.out.println(umwe.getDateTime() + " " + umwe.isExceed());
        }

//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mdi = new HashMap<>();
        for(UserMeal meal: mealList){
            if(mdi.containsKey(meal.getDateTime().toLocalDate())){
                Integer calories = mdi.get(meal.getDateTime().toLocalDate());
                calories += meal.getCalories();
                mdi.put(meal.getDateTime().toLocalDate(), calories);
            }else{
                mdi.put(meal.getDateTime().toLocalDate(), meal.getCalories());
            }
        }
        List<UserMealWithExceed> lumwe = new ArrayList<>();
        for(UserMeal meal: mealList){
            if(meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)){
                UserMealWithExceed mealwe = new UserMealWithExceed(meal.getDateTime(),
                                                                   meal.getDescription(),
                                                                   meal.getCalories(),
                                                                   mdi.get(meal.getDateTime().toLocalDate())>caloriesPerDay);
                lumwe.add(mealwe);
            }
        }
        return lumwe;
    }
}

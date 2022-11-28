package com.codermast.takeoutfood.dto;

import com.codermast.takeoutfood.entity.SetMeal;
import com.codermast.takeoutfood.entity.SetMealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetMealDto extends SetMeal {

    private List<SetMealDish> setmealDishes;

    private String categoryName;
}

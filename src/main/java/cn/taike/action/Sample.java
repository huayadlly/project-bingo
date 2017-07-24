package cn.taike.action;

import cn.taike.entity.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by huayadlly on 2017/7/23.
 */
public class Sample {

    public void newIterator() {

        List<Dish> dishList = Arrays.asList(
                new Dish("pizza", true, 400, Dish.Type.VEGETABLE),
                new Dish("fish", false, 500, Dish.Type.FISH),
                new Dish("beefs", false, 800, Dish.Type.BEEF),
                new Dish("chickens", false, 700, Dish.Type.CHICKEN),
                new Dish("rices", true, 700, Dish.Type.OTHER)
        );

        //使用流的方式处理List<Dish>集合
        List<String> list = dishList.stream()
                .filter(item -> item.getCalories() > 500)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
    }
}

package com.teamtreehouse.giflib.data;

import com.teamtreehouse.giflib.model.Category;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryRepository {
    private static final List<Category> ALL_CATEGORIES = Arrays.asList(

            new Category(1,"gif cat 1"),
            new Category(2,"gif cat 2"),
            new Category(3,"gif cat 3")
    );


    public static List<Category> getAllCategories() {
        return ALL_CATEGORIES;
    }

    public Category findByCategory(int id){
        for(Category category : ALL_CATEGORIES){
            if(id == category.getId())
                return category;
        }
        return null;
    }
}

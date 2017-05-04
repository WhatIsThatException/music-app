package com.music.app.category.repository;

import com.music.app.category.model.Category;

import javax.persistence.EntityManager;

/**
 * Created by kpant on 5/4/17.
 */
public class CategoryRepository {
    EntityManager em;
    public Category add(Category category) {
        em.persist(category);
        return category;
    }

    public Category findById(Long id){
        return em.find(Category.class, id);
    }
}

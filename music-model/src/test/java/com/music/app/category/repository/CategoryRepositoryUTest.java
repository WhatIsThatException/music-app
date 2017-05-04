package com.music.app.category.repository;

import com.music.app.category.model.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static com.music.app.commontests.category.CategoryForTestsRepository.english;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by kpant on 5/4/17.
 */
public class CategoryRepositoryUTest {
    private EntityManagerFactory emf;
    private EntityManager em;
    private CategoryRepository categoryRepository;

    @Before
    public void initTestCase(){
        emf = Persistence.createEntityManagerFactory("musicPU");
        em=emf.createEntityManager();
        categoryRepository = new CategoryRepository();
        categoryRepository.em = em;
    }

    @Test
    public void addCategoryAndFindIt(){
        Long categoryAddedId = null;
        try{
            em.getTransaction().begin();
            categoryAddedId = categoryRepository.add(english()).getId();
            assertThat(categoryAddedId, is(notNullValue()));
            em.getTransaction().commit();
            em.clear();
        } catch(Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();

        }
        Category category = categoryRepository.findById(categoryAddedId);
        assertThat(category, is(notNullValue()));
        assertThat(category.getName(), is(equalTo(english().getName())));
    }

    @After
    public void closeEntityManager(){
        em.close();
        emf.close();
    }
}

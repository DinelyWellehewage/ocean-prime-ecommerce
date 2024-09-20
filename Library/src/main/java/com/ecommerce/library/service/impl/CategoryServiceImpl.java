package com.ecommerce.library.service.impl;

import com.ecommerce.library.model.Category;
import com.ecommerce.library.repository.CategoryRepository;
import com.ecommerce.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Override
    public List<Category> findAll() {
        return repo.findAll();
    }


    @Override
    public Category save(Category category) {

        Category categorySave = new Category(category.getName());
        return repo.save(categorySave);

    }

    @Override
    public Category findById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdate = null;
        try {
            categoryUpdate = repo.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setIs_activated(category.isIs_activated());
            categoryUpdate.setIs_deleted(category.isIs_deleted());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repo.save(categoryUpdate);
    }

    @Override
    public void deleteById(Long id) {

        Category category = repo.getById(id);
        category.setIs_deleted(true);
        category.setIs_activated(false);
        repo.save(category);
    }

    @Override
    public void enabledById(Long id) {
        Category category = repo.getById(id);
        category.setIs_activated(true);
        category.setIs_deleted(false);
        repo.save(category);
    }

    @Override
    public List<Category> findAllByActivated() {
        return repo.findAllByActivated();
    }
}

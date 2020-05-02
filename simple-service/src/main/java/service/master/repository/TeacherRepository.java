/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.master.repository;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.List;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import service.master.model.Teacher;


@Singleton
public class TeacherRepository implements TeacherRepositoryInf {
    @PersistenceContext
    private EntityManager manager;

    public TeacherRepository(@CurrentSession EntityManager manager) {
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = true)
    public Long size() {
        Long count = manager.createQuery("select count(*) from Teacher", Long.class).getSingleResult();
        return count;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> findAll(int page, int limit) {
        TypedQuery<Teacher> query = manager
                .createQuery("from Teacher", Teacher.class)
                .setFirstResult(page > 1 ? page * limit - limit : 0)
                .setMaxResults(limit);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher findById(@NotNull Long id) {
        Teacher query = manager.find(Teacher.class, id);
        return query;
    }

    @Override
    @Transactional
    public String save(@NotNull Teacher teacher) {
        try {
            manager.persist(teacher);
            return "{\"status\":\"ok\"}";
        } catch (Exception e) {
            return "{\"status\":\"fail\", \"message\": \""+e.getMessage()+"\"}";
        }
    }

}

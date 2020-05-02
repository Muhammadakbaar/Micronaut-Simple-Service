/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.master.repository;

import java.util.List;
import javax.validation.constraints.NotNull;
import service.master.model.Teacher;


public interface TeacherRepositoryInf {
    Long size();
    List<Teacher> findAll(int page, int limit);
    Teacher findById(@NotNull Long id);
    String save(@NotNull Teacher teacher);

}

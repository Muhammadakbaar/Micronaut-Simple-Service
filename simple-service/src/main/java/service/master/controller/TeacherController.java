/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.master.controller;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import java.util.HashMap;
import java.util.List;
import service.master.model.Teacher;
import service.master.repository.TeacherRepositoryInf;
import com.google.gson.*;


@Controller("/teacher")
public class TeacherController {

    private TeacherRepositoryInf repository;
    private Gson gson;

    TeacherController(TeacherRepositoryInf r) {
        this.repository = r;
        this.gson = new Gson();
    }
    
    @Get(produces = MediaType.APPLICATION_JSON)
    public String index(@QueryValue int page, @QueryValue int limit) {
        List<Teacher> teacher = repository.findAll(page, limit);
        HashMap<String, Object> data = new HashMap<>();
        data.put("page", Math.ceil(repository.size() / limit));
        data.put("data", teacher);
        return gson.toJson(data);
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String index(@PathVariable Long id) {
        return gson.toJson(repository.findById(id));
    }

    @Post(consumes=MediaType.APPLICATION_JSON)
    public String save(@Body Teacher t) {
        return repository.save(t);
    }

}

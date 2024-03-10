package org.example.student.Controller;


import org.example.student.Api.ApiResponse;
import org.example.student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final List<Student> students = new ArrayList<>();



    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student) {
        students.add(student);
        return new ApiResponse("Student added");
    }


    @GetMapping("/name/{index}")
    public ApiResponse getName(@PathVariable int index) {
        if (index >=0 && index < students.size()) {
        return new ApiResponse("student name:"+ students.get(index).getName());
    } else {
            return new ApiResponse("index not found");
        }}


    @GetMapping("/age/{index}")
    public ApiResponse getAge(@PathVariable int index) {
        if (index >=0 && index < students.size()){
        return new ApiResponse("student age"+students.get(index).getAge());
    } else {
            return new ApiResponse("index not found");
        }}


    @GetMapping("/college/degree/{index}")
    public ApiResponse getDegree(@PathVariable int index) {
        if(index >=0&& index< students.size()){
        return new ApiResponse("student degree"+students.get(index).getDegree());
    }else {
            return new ApiResponse("index not found");
        }}


    @GetMapping("/study/status/{index}")
    public ApiResponse getStudyStatus(@PathVariable int index) {
            if (index >= 0&& index < students.size()){
                boolean graduated = students.get(index).isGraduated();
                return new ApiResponse(graduated ? "Graduated" : "Ungraduated");
            }else {
                return new ApiResponse("index not found");
            }
            }



    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateStudent(@PathVariable int index, @RequestBody Student updatedStudent) {
        if (index >= 0 && index < students.size()) {
            students.set(index, updatedStudent);
            return new ApiResponse("Student updated");
        }
        return new ApiResponse("index not found");
    }


    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteStudent(@PathVariable int index) {
        if (index >= 0 && index < students.size()) {
            students.remove(index);
            return new ApiResponse("Student deleted");
        }
        return new ApiResponse("index not found");
    }}





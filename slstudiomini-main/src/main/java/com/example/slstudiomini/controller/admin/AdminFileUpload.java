package com.example.slstudiomini.controller.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.slstudiomini.model.Course;
import com.example.slstudiomini.service.CourseService;

@Controller
@RequestMapping("/admin/upload")
public class AdminFileUpload {
    @Autowired
    private CourseService courseService;
    
    //ファイルの保存先
    private static String UPLOADED_FOLDER = "uploads/images/course/";

    @GetMapping("/{id}")
    public String fileUploadForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return "admin/upload";
    }

    @PostMapping
    public String fileUpload(@RequestParam("id") Long id, @RequestParam(name = "file") MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase() : "";
        if (!(fileExtension.equals("png"))) {
            return "許可されていないファイル形式です";
        }
        try {
            //1.ファイルの保存先（ファイル名を含む）を決定
            Path path = Paths.get(UPLOADED_FOLDER + id + ".png");

            //2.書き出すことで保存
            Files.copy(file.getInputStream(), path,  StandardCopyOption.REPLACE_EXISTING);
            Course course = courseService.findCourseById(id);
            course.setFilePath(UPLOADED_FOLDER+id+".png");
            courseService.save(course);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/courses";
    }
}

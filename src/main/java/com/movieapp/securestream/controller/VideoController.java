package com.movieapp.securestream.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class VideoController {

    @GetMapping("/video/{filename}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String filename) throws IOException {
        Path path = Paths.get("src/main/resources/videos/" + filename);
        Resource resource = new InputStreamResource(Files.newInputStream(path));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + filename)
                .contentType(MediaTypeFactory.getMediaType(path.getFileName().toString()).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resource);
    }

    @GetMapping("/watch/{filename}")
    public String watchPage(@PathVariable String filename, Model model) {
        model.addAttribute("video", filename);
        return "watch";
    }
}

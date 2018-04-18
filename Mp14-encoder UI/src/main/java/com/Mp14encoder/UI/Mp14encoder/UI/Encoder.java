package com.Mp14encoder.UI.Mp14encoder.UI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
public class Encoder {

    private RestTemplate restTemplate;
    private String collect = "";

    public Encoder(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/encoder")
    String page() {
        return "encoder";
    }


    private String name;

    @ModelAttribute("collect")
    String getvalue() {
        return collect;
    }

    @PostMapping("/encoder")
    String sendText(@RequestParam String name, Model model) {
        String morseUrl = "http://localhost:8088/morse";
        collect = Stream.of(name.split(""))
                .map(letter -> restTemplate.postForObject(morseUrl, letter, String.class))
                .collect(Collectors.joining());
        return "redirect:/encoder";
    }


}

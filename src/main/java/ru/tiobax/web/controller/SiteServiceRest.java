package ru.tiobax.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tiobax.web.user.User;

import java.util.List;

@Service @RequiredArgsConstructor
public class SiteServiceRest {
    private final RestTemplate restTemplate;
    private String header;
    String code = "";

    public void findAllUsers() {
        String url = "http://91.241.64.178:7081/api/users";
        ResponseEntity<List<User>>  user =  restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        header = user.getHeaders().get("Set-Cookie").get(0);
    }

    public void addUser() {
        String url = "http://91.241.64.178:7081/api/users";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", header);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        User user = new User(3L, "James", "Brown", (byte)34);
        HttpEntity<User> httpEntity = new HttpEntity(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        code = responseEntity.getBody();
    }

    public void updateUser() {
        String url = "http://91.241.64.178:7081/api/users";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", header);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        User user = new User(3L, "Thomas", "Shelby", (byte)34);
        HttpEntity<User> httpEntity = new HttpEntity(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        code = code + responseEntity.getBody();
    }

    public void deleteUser() {
        String url = "http://91.241.64.178:7081/api/users/3";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", header);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, String.class);
        code = code + responseEntity.getBody();
        System.out.println(code);
    }

}

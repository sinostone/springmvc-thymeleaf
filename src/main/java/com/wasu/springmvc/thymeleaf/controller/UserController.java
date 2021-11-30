package com.wasu.springmvc.thymeleaf.controller;

import com.wasu.springmvc.thymeleaf.dao.UserDao;
import com.wasu.springmvc.thymeleaf.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ZHANGLEI
 * @date 2021/11/24 13:56
 */
@Controller
public class UserController {
    @Resource
    UserDao userDao;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/post")
    public String postPage(String username, String email, String[] hobby) {
        logger.debug("username: {}", username);
        logger.debug("email: {}", email);
        if(hobby != null)
            for(String s: hobby)
                logger.debug("hobby: {}", s);
        return "success";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(String username, String email, String[] hobby) {
        logger.debug("UUID: {}",UUID.randomUUID());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("username", username);
        modelAndView.addObject("email", email);
        modelAndView.addObject("hobby", hobby);
        modelAndView.setViewName("success");

        return modelAndView;
    }

    @RequestMapping("/testForward")
    public String testForward() {
        return "forward:/post";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView listAllUsers(ModelAndView modelAndView) {
        Collection<User> users = userDao.selectAllUsers();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("listUser");
        return modelAndView;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView listUser(ModelAndView modelAndView, @PathVariable("id") Long id) {
        User user = userDao.selectUser(id);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("modUser");
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addUser(User user) {
        userDao.insertUser(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") Long id) {
        userDao.deleteUser(id);
        return "redirect:/user";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String modifyUser(User user) {
        userDao.updateUser(user);
        return "redirect:/user";
    }
}

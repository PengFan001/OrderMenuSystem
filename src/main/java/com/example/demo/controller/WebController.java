package com.example.demo.controller;

import com.example.demo.entities.Food;
import com.example.demo.entities.User;
import com.example.demo.respository.FoodRepository;
import com.example.demo.respository.FormRepository;
import com.example.demo.respository.UserRepository;
import com.example.demo.respository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/menu")
public class WebController {

    private String userPhone = null;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private WorkerRepository workerRepository;


    @RequestMapping("index")
    public String index(){
        if (userPhone!=null)
            return "index";
        else
            return "login";
    }

    @RequestMapping("login")
    public String login(){
        userPhone = null;
        return "login";
    }

    @RequestMapping("register")
    public String register(){
        userPhone = null;
        return "register";
    }

    @RequestMapping("welcome")
    public String menu(){
        return "welcome";
    }

    @RequestMapping("information")
    public String information(){
        return "information";
    }

    @RequestMapping("shopcart")
    public String shopcart(){
        return "shopcart";
    }

    @RequestMapping("form")
    public String form(){
        return "form";
    }

    @RequestMapping("meat")
    public String meat(){
        return "meat";
    }

    @RequestMapping("vegetable")
    public String vegetable(){
        return "vegetable";
    }

    @RequestMapping("noodle")
    public String noodle(){
        return "noodle";
    }

    @RequestMapping("rice")
    public String rice(){
        return "rice";
    }

    @RequestMapping("drink")
    public String drink(){
        return "drink";
    }

    //用户注册
    @RequestMapping("sign_up")
    public String sign_up(@RequestParam(value = "phone")String phone, @RequestParam(value = "password")String password,
                             @RequestParam(value = "username")String username, @RequestParam(value = "destination")String destination,
                             @RequestParam(value = "points")int points){

        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        user.setUsername(username);
        user.setDestination(destination);
        user.setPoints(points);
        userRepository.save(user);
        System.out.println(user.toString());

        return "login";
    }

    //用户登录
    @RequestMapping("sign_in")
    public String sign_in(@RequestParam(value = "phone")String phone, @RequestParam(value = "password")String password){

        User user = new User();
        userPhone = phone;
        System.out.println(userPhone);
        user = userRepository.findAllByPhone(phone);
        if(user.getPassword().equals(password))
            return "index";
        else
            return "login";
    }

    //个人信息
    @RequestMapping("getInformation")
    @ResponseBody
    public User get_information(Model model){
        User user = new User();
        if(userPhone!=null){
            user = userRepository.findAllByPhone(userPhone);
        }
        else{
            user = null;
        }
        model.addAttribute(user);

        return user;
    }

    //菜品信息
    @RequestMapping("getFoodInformation")
    @ResponseBody
    public ArrayList<Food> get_food_information(){
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList = foodRepository.findAll();
        for(int i=0;i<foodArrayList.size();i++)
            System.out.println(foodArrayList.get(i));
        return foodArrayList;
    }

    //荤菜
    @RequestMapping("Meat")
    @ResponseBody
    public ArrayList<Food> meat_food_information(){
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList = foodRepository.findAllByType("荤菜");
        for(int i=0;i<foodArrayList.size();i++)
            System.out.println(foodArrayList.get(i));
        return foodArrayList;
    }

    //素菜
    @RequestMapping("Vegetable")
    @ResponseBody
    public ArrayList<Food> vegetable_food_information(){
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList = foodRepository.findAllByType("素菜");
        for(int i=0;i<foodArrayList.size();i++)
            System.out.println(foodArrayList.get(i));
        return foodArrayList;
    }

    //饮料
    @RequestMapping("Drink")
    @ResponseBody
    public ArrayList<Food> drink_food_information(){
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList = foodRepository.findAllByType("饮料");
        for(int i=0;i<foodArrayList.size();i++)
            System.out.println(foodArrayList.get(i));
        return foodArrayList;
    }

    //面食
    @RequestMapping("Noodle")
    @ResponseBody
    public ArrayList<Food> noodle_food_information(){
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList = foodRepository.findAllByType("面食");
        for(int i=0;i<foodArrayList.size();i++)
            System.out.println(foodArrayList.get(i));
        return foodArrayList;
    }

    //主食
    @RequestMapping("Rice")
    @ResponseBody
    public ArrayList<Food> rice_food_information(){
        ArrayList<Food> foodArrayList = new ArrayList<>();
        foodArrayList = foodRepository.findAllByType("米饭");
        for(int i=0;i<foodArrayList.size();i++)
            System.out.println(foodArrayList.get(i));
        return foodArrayList;
    }

    //按菜品的类别查询
    @RequestMapping("checkFoodByType")
    @ResponseBody
    public ArrayList<Food> check_food_by_type(@RequestParam(value = "type")String type){
        ArrayList<Food> foodArrayList = new ArrayList<>();
        String checkType;//依据菜品种类查询菜品

        System.out.println(type);
        if(type.equals("all")){
            foodArrayList = foodRepository.findAll();
            for(int i=0;i<foodArrayList.size();i++)
                System.out.println(foodArrayList.get(i));
        }
        else {

            if(type.equals("meat"))
                checkType = "荤菜";
            else if(type.equals("vegetable"))
                checkType = "素菜";
            else if (type.equals("drink"))
                checkType = "饮料";
            else if (type.equals("noodle"))
                checkType = "面食";
            else if (type.equals("rice"))
                checkType = "主食";
            else
                checkType = null;

            System.out.println(checkType);
            foodArrayList = foodRepository.findAllByType(checkType);
            for(int i=0;i<foodArrayList.size();i++)
                System.out.println(foodArrayList.get(i));
        }

        return foodArrayList;
    }

}

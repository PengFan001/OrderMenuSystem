package com.example.demo.controller;

import com.example.demo.entities.*;
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
    private ArrayList<ShopCart> shopCarts = new ArrayList<>();
    private String adminAccounts = null;

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
        {
            return "index";
        }
        else
            return "login";
    }

    @RequestMapping("login")
    public String login(){
        userPhone = null;
        shopCarts.clear();
        return "login";
    }

    @RequestMapping("admin")
    public String admin(){
        adminAccounts = null;
        return "admin_login";
    }

    @RequestMapping("register")
    public String register(){
        userPhone = null;
        return "register";
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

    @RequestMapping("adminForm")
    public String admin_form(){
        return "admin_form";
    }

    @RequestMapping("addFood")
    public String addFood(){
        return "add_food";
    }

    @RequestMapping("welcome")
    public String menu(){
        return "welcome";
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

    @RequestMapping("deleteWelcome")
    public String delete_menu(){
        return "delete_welcome";
    }

    @RequestMapping("deleteMeat")
    public String delete_meat(){
        return "delete_meat";
    }

    @RequestMapping("deleteVegetable")
    public String delete_vegetable(){
        return "delete_vegetable";
    }

    @RequestMapping("deleteNoodle")
    public String delete_noodle(){
        return "delete_noodle";
    }

    @RequestMapping("deleteRice")
    public String delete_rice(){
        return "delete_rice";
    }

    @RequestMapping("deleteDrink")
    public String delete_drink(){
        return "delete_drink";
    }

    @RequestMapping("modify_self_information")
    public String modify_self_information(){
        return "modify_self_information";
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

    //管理员登录
    @RequestMapping("adminLogin")
    public String adminLogin(@RequestParam(value = "accounts")String accounts, @RequestParam(value = "password")String password){

        Worker worker = workerRepository.findAllByAccounts(accounts);
        adminAccounts = accounts;
        if (worker.getPassword().equals(password))
            return "admin_index";
        else
            return "admin_login";
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

    //修改个人信息
    @RequestMapping("modifySelfInfo")
    public String modifySelfInfo(@RequestParam(value = "password")String password,
                                 @RequestParam(value = "destination")String destination){

        System.out.println(password + destination);

        if(password!=null && destination!=null){
            User user = userRepository.findAllByPhone(userPhone);
            user.setDestination(destination);
            user.setPassword(password);
            userRepository.save(user);
        }

        return "index";

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
        foodArrayList = foodRepository.findAllByType("主食");
        for(int i=0;i<foodArrayList.size();i++)
            System.out.println(foodArrayList.get(i));
        return foodArrayList;
    }

    //添加菜品
    @RequestMapping("add")
    public String add_food(@RequestParam(value = "name")String name, @RequestParam(value = "type")String type,
                           @RequestParam(value = "price")double price){
        boolean flag = true;
        Food food = new Food();
        ArrayList<Food> foodArrayList = foodRepository.findAll();

        //判断该菜品是否已经存在
        for(int i=0;i<foodArrayList.size();i++){
            Food find = foodArrayList.get(i);
            if(find.getName().equals(name)){
                flag = false;
                break;
            }
        }
        if (flag){
            food.setName(name);
            food.setType(type);
            food.setPrice(price);
            foodRepository.save(food);
        }
        else {
            return "fail";
        }

        return "admin_index";
    }

    //删除菜品
    @RequestMapping("delete_food")
    public String delete_food(@RequestParam(value = "id")int id){
        Food food = foodRepository.findAllById(id);
        foodRepository.delete(food);

        return "admin_index";
    }

    //添加商品进购物车
    @RequestMapping("shopping")
    public String shopping(@RequestParam(value = "name")String name, @RequestParam(value = "price")double price,
                           @RequestParam(value = "number")int number){
        User user = userRepository.findAllByPhone(userPhone);
        if(user!=null && number>=1){
            ShopCart shopCart = new ShopCart();
            shopCart.setPhone(userPhone);
            shopCart.setUsername(user.getUsername());
            shopCart.setPrice(price);
            shopCart.setNumber(number);
            shopCart.setDestination(user.getDestination());
            shopCart.setMenu(name);
            System.out.println(shopCart.toString());
            shopCarts.add(shopCart);
        }
        else
            return "fail";

        for (int i=0;i<shopCarts.size();i++)
            System.out.println(shopCarts.get(i));

        return "index";
    }

    //购物车
    @RequestMapping("ShopCart")
    @ResponseBody
    public ArrayList<ShopCart> pay_for(){

        for (int i=0;i<shopCarts.size();i++)
            System.out.println(shopCarts.get(i));

        return shopCarts;
    }


    //购物车的处理，支付或者清空
    @RequestMapping("buyOrDelete")
    public String buy_or_delete(@RequestParam(value = "select")String select){

        System.out.println(select);

        if (select.equals("支付")){
            double sum_price=0;
            String menu = "";
            Form form = new Form();
            User user = userRepository.findAllByPhone(userPhone);
            form.setDestination(user.getDestination());
            form.setPhone(userPhone);
            form.setUsername(user.getUsername());
            for (int i=0;i<shopCarts.size();i++){
                ShopCart shopCart = new ShopCart();
                shopCart = shopCarts.get(i);
                sum_price = sum_price + shopCart.getPrice() * shopCart.getNumber();
                menu = menu + shopCart.getMenu() + shopCart.getNumber() + "*" + shopCart.getPrice() + "\t";
            }

            form.setPrice(sum_price);
            form.setMenu(menu);

            System.out.println(form.toString());

            //将生成的订单存入数据库中
            formRepository.save(form);

            //每购买一次订单获得相应的积分、并更新用户个人信息
            int points = (int) (sum_price * 10);
            user.setPoints(points);
            userRepository.save(user);

            shopCarts.clear();

            return "index";
        }

        else if(select.equals("清空")){
            shopCarts.clear();
            return "index";
        }
        else
            return "fail";
    }

    //查询订单
    @RequestMapping("getOrderInformation")
    @ResponseBody
    public ArrayList<Form> get_order_information(){
        ArrayList<Form> formArrayList = new ArrayList<>();
        formArrayList = formRepository.findAllByPhone(userPhone);

        return formArrayList;
    }


    //管理员查询订单
    @RequestMapping("adminGetOrderInformation")
    @ResponseBody
    public ArrayList<Form> admin_get_order_information(){
        ArrayList<Form> formArrayList = new ArrayList<>();
        formArrayList = formRepository.findAll();

        return formArrayList;
    }




//    //按菜品的类别查询
//    @RequestMapping("checkFoodByType")
//    @ResponseBody
//    public ArrayList<Food> check_food_by_type(@RequestParam(value = "type")String type){
//        ArrayList<Food> foodArrayList = new ArrayList<>();
//        String checkType;//依据菜品种类查询菜品
//
//        System.out.println(type);
//        if(type.equals("all")){
//            foodArrayList = foodRepository.findAll();
//            for(int i=0;i<foodArrayList.size();i++)
//                System.out.println(foodArrayList.get(i));
//        }
//        else {
//
//            if(type.equals("meat"))
//                checkType = "荤菜";
//            else if(type.equals("vegetable"))
//                checkType = "素菜";
//            else if (type.equals("drink"))
//                checkType = "饮料";
//            else if (type.equals("noodle"))
//                checkType = "面食";
//            else if (type.equals("rice"))
//                checkType = "主食";
//            else
//                checkType = null;
//
//            System.out.println(checkType);
//            foodArrayList = foodRepository.findAllByType(checkType);
//            for(int i=0;i<foodArrayList.size();i++)
//                System.out.println(foodArrayList.get(i));
//        }
//
//        return foodArrayList;
//    }

}

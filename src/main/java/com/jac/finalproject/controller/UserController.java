package com.jac.finalproject.controller;

import com.jac.finalproject.entity.Item;
import com.jac.finalproject.entity.Order;
import com.jac.finalproject.entity.User;
import com.jac.finalproject.entity.UserPaymentInfo;
import com.jac.finalproject.service.ItemService;
import com.jac.finalproject.service.OrderService;
import com.jac.finalproject.service.UserPaymentInfoService;
import com.jac.finalproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserPaymentInfoService userPaymentInfoService;
    private final OrderService orderService;
    private final ItemService itemService;

    @Autowired
    public UserController(UserService userService, UserPaymentInfoService userPaymentInfoService, OrderService orderService, ItemService itemService) {
        this.userService = userService;
        this.userPaymentInfoService = userPaymentInfoService;
        this.orderService = orderService;
        this.itemService = itemService;


    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/list";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/edit/{userId}")
    public String editUserForm(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId, @ModelAttribute User updatedUser) {
        updatedUser.setId(userId);
        userService.updateUser(updatedUser);
        return "redirect:/users";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return "redirect:/users";
    }


    @GetMapping("/detail/{userId}")
    public String showUserDetail(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        model.addAttribute("user", user);
        return "user/detail";
    }

    @GetMapping("/editPayment/{userId}")
    public String showEditPaymentForm(@PathVariable Long userId, Model model) {
        User user = userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        if (user.getUserPaymentInfo() == null) {
            return "redirect:/users/addPayment/" + userId;
        }

        model.addAttribute("user", user);
        return "user/editPayment";
    }

    @PostMapping("/editPayment/{userId}")
    public String editPaymentForm(@PathVariable Long userId, @ModelAttribute User updatedUser) {
        User user = userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        UserPaymentInfo updatedUserUserPaymentInfo = updatedUser.getUserPaymentInfo();
        System.out.println(updatedUserUserPaymentInfo);
        System.out.println(userId);
        userPaymentInfoService.updateUserPaymentInfoByUserId(updatedUserUserPaymentInfo, userId);
        return "redirect:/users/detail/{userId}";
    }


    @GetMapping("/addPayment/{userId}")
    public String showAddPaymentForm(@PathVariable Long userId, Model model) {
        model.addAttribute("userPaymentInfo", new UserPaymentInfo());
        model.addAttribute("userId", userId);
        return "user/addPayment";
    }

    @GetMapping("/addOrder/{userId}")
    public String addOrder(@PathVariable Long userId, Model model) {
        List<Item> itemList = itemService.getAllItems();

        model.addAttribute("itemList", itemList);
        model.addAttribute("userId", userId);
        return "user/addOrder";
    }

    @PostMapping("/submitOrder/{userId}")
    public String submitOrder(@PathVariable Long userId, @RequestParam(name = "selectedItems", required = false) List<Long> selectedItems) {
        if (selectedItems != null && !selectedItems.isEmpty()) {
            List<Item> itemList = selectedItems.stream()
                    .map(itemId -> Item.builder().id(itemId).build())
                    .toList();
            User user = userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
            orderService.createOrder(Order.builder()
                    .user(user)
                    .orderState(Order.OrderState.UNPAID)
                    .items(itemList)
                    .build());
        }

        return "redirect:/users/detail/{userId}";
    }


    @PostMapping("/addPayment/{userId}")
    public String addPaymentForm(@PathVariable Long userId, @ModelAttribute UserPaymentInfo userPaymentInfo) {
        userPaymentInfo.setUser(userService.getUserById(userId).orElseThrow(() -> new IllegalArgumentException("Invalid user ID")));
        userPaymentInfoService.createUserPaymentInfo(userPaymentInfo);
        return "redirect:/users/detail/{userId}";
    }


    @GetMapping("/order/{orderId}/{userId}")
    public String showOrderById(@PathVariable Long orderId, @PathVariable Long userId, Model model) {
        List<Long> itemIdList = itemService.getItemIdsByOrderId(orderId);
        ArrayList<Item> items = new ArrayList<>();

        itemIdList.forEach(itemId -> items.add(itemService.getItemById(itemId).orElseThrow(() -> new IllegalArgumentException("Invalid item ID"))));
        model.addAttribute("items", items);
        model.addAttribute("userId", userId);
        model.addAttribute("userId", userId);
        return "user/orderDetail";
    }

}

package com.jac.finalproject;

import com.jac.finalproject.controller.UserController;
import com.jac.finalproject.entity.User;
import com.jac.finalproject.service.OrderService;
import com.jac.finalproject.service.UserPaymentInfoService;
import com.jac.finalproject.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserPaymentInfoService userPaymentInfoService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        when(userService.getAllUsers()).thenReturn(getMockUserList());
        Model model = mock(Model.class);
        String result = userController.getAllUsers(model);
        verify(userService, times(1)).getAllUsers();
        verify(model, times(1)).addAttribute(eq("users"), anyList());
        assertEquals("user/list", result);
    }


    @Test
    public void testGetUserById() {
        when(userService.getUserById(1L)).thenReturn(Optional.of(getMockUser()));
        Model model = mock(Model.class);
        String result = userController.showUserDetail(1L, model);
        verify(userService, times(1)).getUserById(1L);
        verify(model, times(1)).addAttribute(eq("user"), any());
        assertEquals("user/detail", result);
    }

    @Test
    public void testEditUser() {
        when(userService.getUserById(1L)).thenReturn(Optional.of(getMockUser()));
        String result = userController.editUserForm(1L, mock(Model.class));
        verify(userService, times(1)).getUserById(1L);
        assertEquals("user/edit", result);
    }
    private List<User> getMockUserList() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("user2");

        return Arrays.asList(user1, user2);
    }
    private User getMockUser() {
        User user = new User();
        user.setId(1L);
        user.setUserName("user1");

        return user;
    }


}

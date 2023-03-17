package com.demo.project.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.demo.project.annotation.AuthCheck;
import com.demo.project.common.BaseResponse;
import com.demo.project.common.DeleteRequest;
import com.demo.project.common.ErrorCode;
import com.demo.project.common.ResultUtils;
import com.demo.project.model.dto.user.*;
import com.demo.project.model.entity.User;
import com.demo.project.model.vo.UserVO;
import com.demo.project.exception.BusinessException;
import com.demo.project.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.demo.project.constant.UserConstant.ADMIN_ROLE;
import static com.demo.project.constant.UserConstant.DEFAULT_ROLE;

/**
 * 用户接口
 *
 * @author hzzzzzy
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @AuthCheck(anyRole = {DEFAULT_ROLE,ADMIN_ROLE})
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        Long res = userService.register(userRegisterRequest);
        return ResultUtils.success(res);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    @AuthCheck(anyRole = {DEFAULT_ROLE,ADMIN_ROLE})
    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        User user = userService.userLogin(userLoginRequest, request);
        return ResultUtils.success(user);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @AuthCheck(anyRole = {DEFAULT_ROLE,ADMIN_ROLE})
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @AuthCheck(anyRole = {DEFAULT_ROLE,ADMIN_ROLE})
    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(HttpServletRequest request) {
        User user = userService.getLoginUser(request);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }


    /**
     * 创建用户
     *
     * @param userAddRequest
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @PostMapping("/add")
    public BaseResponse<Long> addUser(@RequestBody UserAddRequest userAddRequest) {
        if (userAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long userId = userService.addUser(userAddRequest);
        return ResultUtils.success(userId);
    }

    /**
     * 删除用户
     *
     * @param deleteRequest
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean flag = userService.removeById(deleteRequest.getId());
        return ResultUtils.success(flag);
    }

    /**
     * 更新用户
     *
     * @param userUpdateRequest
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @PostMapping("/update")
    public BaseResponse<Boolean> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null || userUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Boolean flag = userService.updateById(userUpdateRequest);
        return ResultUtils.success(flag);
    }

    /**
     * 根据 id 获取用户
     *
     * @param id
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @GetMapping("/get")
    public BaseResponse<UserVO> getUserById(int id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return ResultUtils.success(userVO);
    }

    /**
     * 获取用户或管理员列表
     *
     * @param userQueryRequest
     * @param flag 判断获取的是管理员列表还是用户列表
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @GetMapping("/list/{flag}")
    public BaseResponse<List<UserVO>> list(UserQueryRequest userQueryRequest,@PathVariable String flag) {
        List<UserVO> userVOList = userService.list(userQueryRequest, flag);
        return ResultUtils.success(userVOList);
    }


    /**
     * 分页获取用户或管理员列表
     *
     * @param userQueryRequest
     * @param flag 判断获取的是管理员列表还是用户列表
     * @param current
     * @param page
     * @return
     */
    @AuthCheck(mustRole = ADMIN_ROLE)
    @GetMapping("/list/{flag}/{current}/{page}")
    public BaseResponse<Page<UserVO>> listByPage(UserQueryRequest userQueryRequest,@PathVariable String flag, @PathVariable int current,@PathVariable int page) {
        Page<UserVO> userVOPage = userService.listByPage(userQueryRequest, flag, current, page);
        return ResultUtils.success(userVOPage);
    }
}

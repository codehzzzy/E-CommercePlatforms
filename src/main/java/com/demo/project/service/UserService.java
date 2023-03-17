package com.demo.project.service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.project.model.dto.user.*;
import com.demo.project.model.entity.User;
import com.demo.project.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户服务
 *
 * @author hzzzzzy
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @param request
     * @return
     */
    User userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);


    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    Long register(UserRegisterRequest userRegisterRequest);


    /**
     * 创建用户
     *
     * @param userAddRequest
     * @return
     */
    Long addUser(UserAddRequest userAddRequest);

    /**
     * 更新用户信息
     *
     * @param userUpdateRequest
     * @return
     */
    Boolean updateById(UserUpdateRequest userUpdateRequest);


    /**
     * 分页获取用户或管理员列表
     *
     * @param userQueryRequest
     * @param flag 判断获取的是管理员列表还是用户列表
     * @param current
     * @param page
     * @return
     */
    Page<UserVO> listByPage(UserQueryRequest userQueryRequest, String flag, int current, int page);


    /**
     * 获取用户或管理员列表
     *
     * @param userQueryRequest
     * @param flag
     */
    List<UserVO> list(UserQueryRequest userQueryRequest, String flag);
}

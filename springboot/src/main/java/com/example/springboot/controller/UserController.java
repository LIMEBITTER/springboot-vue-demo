package com.example.springboot.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Constants;
import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.service.Impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 管理员服务
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增或者更新管理员用户
     * @param user
     * @return boolean
     */
    @PostMapping
    private boolean save(@RequestBody User user) { //@RequestBody：将网页传入数据转化成User的Json对象

        return userService.saveUser(user);
    }

    /**
     * //查询管理员操作
     * @return List<User>
     */
    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    /**
     * //删除管理员操作
     * @param id
     * @return boolean
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    /**
     *  批量删除操作
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeBatchByIds(ids);
    }


    /**
     * 注册操作
     * @param userDto
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto){ //RequestBody :将前端的json变成后端的java对象
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        if(StrUtil.isBlank(username) ||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        User dto = userService.register(userDto);
        return Result.success(dto);
    }
    /**
     * 登录操作
     * @param userDto
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto){ //RequestBody :将前端的json变成后端的java对象
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        System.out.println("username"+username+"password"+password);

        //hutool工具类校验是否姓名或者密码为空
        if(StrUtil.isBlank(username) ||StrUtil.isBlank(password)){
            return Result.error(Constants.CODE_400,"参数错误");
        }
        UserDto dto = userService.login(userDto);
        return Result.success(dto);
    }



    /**
     * 导入接口
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<User> userList = reader.read(0, 1, User.class);
//        Bean方式读取
        List<User> userList = reader.readAll(User.class);
        userService.saveBatch(userList);
        return true;
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        //从数据库查询出所有的数据
        List<User> list = userService.list();
        //通过工具类创建writer写到磁盘路径
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
//        writer.addHeaderAlias("username","用户名");
//        writer.addHeaderAlias("nickName","昵称");
//        writer.addHeaderAlias("address","地址");
//        writer.addHeaderAlias("phone","电话");
//        writer.addHeaderAlias("createTime","创建时间");
        //一次性写出list内的对象到excel
        writer.write(list,true);
        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlform" +
                "at-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");
        ServletOutputStream out=response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
    }



    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @param username  根据小区的数据库信息,增加或者删除参数
     * @return
     */
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String nickName,
                                @RequestParam(defaultValue = "")  String address,
                                @RequestParam(defaultValue = "")  String username) {

        IPage<User> page = new Page<>(pageNum,pageSize);//创建分页page
        QueryWrapper<User> wrapper = new QueryWrapper<>();//创建wrapper来实现模糊查询
        if (!"".equals(username)) {
            wrapper.like("username", username);
        }
        if (!"".equals(address)) {
            wrapper.like("address", address);
        }
        if (!"".equals(nickName)) {
            wrapper.like("nick_name", nickName);
        }
        wrapper.orderByAsc("id");
        return userService.page(page,wrapper);
    }


}

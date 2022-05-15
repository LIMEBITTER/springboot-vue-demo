package com.example.springboot.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.HealthDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Health;
import com.example.springboot.entity.Volunteer;
import com.example.springboot.service.HealthService;
import com.example.springboot.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * 志愿者服务
 */
@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    /**
     * 查询健康信息 并 分页
     * @return
     */
    @GetMapping("getAllHealth")
    public IPage<HealthDto> findAll(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize) {
        Page<HealthDto> page = new Page<>(pageNum, pageSize);//创建分页page

        return healthService.getAllHealthInfo(page);
    }

    /**
     * 新增或者删除
     * @param health
     * @return
     */
    @PostMapping
    private boolean save(@RequestBody Health health) {
        if(health.getId()==0){
            return healthService.save(health);
        }else {
            return healthService.updateById(health);
        }
    }

    /**
     * 删除操作
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return healthService.removeById(id);
    }

    /**
     * 批量删除志愿者操作
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return healthService.removeBatchByIds(ids);
    }


    @GetMapping("/page")
    public  IPage<HealthDto> findPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String name,
                                         @RequestParam(defaultValue = "") String normal,
                                         @RequestParam(defaultValue = "") String vaccines) {
        IPage<HealthDto> page = new Page<>(pageNum, pageSize);//创建分页page

        QueryWrapper<HealthDto> wrapper = new QueryWrapper<>();//创建wrapper来实现模糊查询
        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(normal)) {
            wrapper.like("normal", normal);
        }
        if (!"".equals(vaccines)) {
            wrapper.like("vaccines", vaccines);
        }
        return healthService.getAllHealthDto(wrapper,page);
//        return healthService.page(page,wrapper)
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {

        //从数据库查询出所有的数据
        List<HealthDto> list = healthService.getAllHealth();

        //通过工具类创建writer写到磁盘路径
        ExcelWriter writer = ExcelUtil.getWriter(true);

        //一次性写出list内的对象到excel
        writer.write(list, true);
        //设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlform" +
                "at-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

}




package com.example.springboot.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.springboot.controller.dto.ResidentDto;
import com.example.springboot.controller.dto.VolunteerDto;
import com.example.springboot.entity.Resident;
import com.example.springboot.entity.Volunteer;

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
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    /**
     * 查询所有志愿者
     * @return
     */
    @GetMapping
    public List<VolunteerDto> findAll() {
        return volunteerService.getAllVolunteer();
    }

    @GetMapping("getAllVol")
    public IPage<VolunteerDto> findAll1(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize){
        Page<VolunteerDto> page = new Page<>(pageNum, pageSize);//创建分页page
        return volunteerService.getAllVolunteer(page);

    }

    /**
     * 新增或者删除
     * @param volunteer
     * @return
     */
    @PostMapping
    private boolean save(@RequestBody Volunteer volunteer) {
        if (volunteer.getId()==0){
            return volunteerService.save(volunteer);

        }else {
            return volunteerService.updateById(volunteer);
        }
    }


    /**志愿者登记
     * 新增或者删除
     * @param volunteerDto
     * @return
     */
    @PostMapping("/save2")
    private boolean save2(@RequestBody VolunteerDto volunteerDto) {
        int save2= volunteerService.save2(volunteerDto);
        System.out.println("志愿者登记：获取插入后的id========="+volunteerDto.getId());
        boolean flag = volunteerService.updateUserVId(volunteerDto.getId(),volunteerDto.getRid());


        return flag;
    }



    /**
     * 删除志愿者操作
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return volunteerService.removeById(id);
    }

    /**
     * 批量删除志愿者操作
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return volunteerService.removeBatchByIds(ids);
    }

    @GetMapping("/page")
    public  IPage<VolunteerDto> findPage(@RequestParam Integer pageNum,
                                         @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String name,
                                         @RequestParam(defaultValue = "") String workAddress) {
        Page<VolunteerDto> page = new Page<>(pageNum, pageSize);//创建分页page
        QueryWrapper<VolunteerDto> wrapper = new QueryWrapper<>();//创建wrapper来实现模糊查询
        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(workAddress)) {
            wrapper.like("workAddress", workAddress);
        }
        return volunteerService.getAllVolunteerDto(wrapper,page);
//        return volunteerService.page(page,wrapper);

    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {

        //从数据库查询出所有的数据
        List<VolunteerDto> list = volunteerService.getAllVolunteer();

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

    /**可视化
     * 工作地点志愿者人数折线图
     *
     * @return
     */
    @GetMapping("/getVolAddressChart")
    @ResponseBody
    private List<VolunteerDto> getVolAddressChart() {

        return volunteerService.getVolAddressChart();
    }

}




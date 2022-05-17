package com.example.springboot.controller;


import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.TravelDto;
import com.example.springboot.entity.Resident;
import com.example.springboot.entity.Travel;
import com.example.springboot.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * 出行服务
 */
@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    private TravelService travelService;
    /**
     * 新增或者删除  管理员
     * @param travel
     * @return
     */
    @PostMapping
    private boolean save(@RequestBody Travel travel) {
        System.out.println("travel"+travel.getId());
        if (travel.getId()==0){
            return travelService.save(travel);
        }else {
            return travelService.updateById(travel);
        }
    }



    /**
     * 新增  用户
     * @param travel
     * @return
     */
    @PostMapping("/save2")
    private boolean save2(@RequestBody TravelDto travel) {
        System.out.println("出行记录增加======="+travel.getId());
        travelService.save2(travel);
        System.out.println("获取插入后的id========="+travel.getId());
//        travelService.select

        return travelService.saveResidentTravel(travel);
    }



    /**修改travel_status
     *
     * @param id
     * @return Resident
     */
    @GetMapping("/changeTStatus")
    private boolean changeRStatus(@RequestParam Integer id) {
        System.out.println("修改travel_status============="+travelService.changeTStatus(id));

        return travelService.changeTStatus(id);
    }


    @PostMapping("/saveTravelForUsers")
    private boolean saveTravelForUsers(@RequestBody Travel travel) {
//        System.out.println("出行记录增加======="+travel.getId());

        return travelService.save(travel);
    }

    /**
     * 查询所有本小区出行  并分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/localTravel")
    public IPage<TravelDto> findAll(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) {
        Page<TravelDto> page = new Page<>(pageNum, pageSize);//创建分页page

        return travelService.getAllLocalTravel(page);
    }

    /**
     * 查询所有外来出行  并分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/nonLocalTravel")
    public IPage<TravelDto> findAll1(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize) {
        Page<TravelDto> page = new Page<>(pageNum, pageSize);//创建分页page

        return travelService.getAllNonTravel(page);
    }
    /**
     * 删除出行
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return travelService.removeById(id);
    }

    /**
     * 批量删除
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return travelService.removeBatchByIds(ids);
    }

    /**
     * 模糊查询
     * @param name
     * @param destination
     * @param totalman
     * @return
     */
    @GetMapping("/localTravelPage")
    public  IPage<TravelDto> findPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                         @RequestParam(defaultValue = "") String name,
                                         @RequestParam(defaultValue = "") String destination,
                                         @RequestParam(defaultValue = "") String totalman) {
        Page<TravelDto> page = new Page<>(pageNum, pageSize);//创建分页page

        QueryWrapper<TravelDto> wrapper = new QueryWrapper<>();//创建wrapper来实现模糊查询
        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(destination)) {
            wrapper.like("destination", destination);
        }
        if (!"".equals(totalman)) {
            wrapper.like("totalman", totalman);
        }
        return travelService.getTravelDto(wrapper,page);

//        return travelService.page(wrapper,page);
    }

    /**
     * 模糊查询1
     * @param name
     * @param destination
     * @param totalman
     * @return
     */
    @GetMapping("/NonLocalTravelPage")
    public  IPage<TravelDto> findPage1(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(defaultValue = "") String name,
                                      @RequestParam(defaultValue = "") String destination,
                                      @RequestParam(defaultValue = "") String totalman) {
        Page<TravelDto> page = new Page<>(pageNum, pageSize);//创建分页page

        QueryWrapper<TravelDto> wrapper = new QueryWrapper<>();//创建wrapper来实现模糊查询
        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(destination)) {
            wrapper.like("destination", destination);
        }
        if (!"".equals(totalman)) {
            wrapper.like("totalman", totalman);
        }
        return travelService.getNonTravelDto(wrapper,page);
//        return travelService.page(page,wrapper);

    }
    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {

        //从数据库查询出所有的数据
        List<TravelDto> list = travelService.getAllTravel();

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




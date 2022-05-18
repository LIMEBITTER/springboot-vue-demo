package com.example.springboot.controller;


import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.controller.dto.ResidentDto;
import com.example.springboot.entity.Resident;
import com.example.springboot.entity.User;
import com.example.springboot.entity.Volunteer;
import com.example.springboot.service.ResidentService;
import com.example.springboot.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 志愿者服务
 */
@RestController
@RequestMapping("/resident")
public class ResidentController {

    @Autowired
    private ResidentService residentService;

    /**查询用户信息绑定
     * user与resident绑定
     * @param id
     * @return Resident
     */
    @GetMapping("/bindUserResident")
    private Resident bindUserResident(@RequestParam Integer id) {
        System.out.println("查询用户信息绑定============="+residentService.bindUserResident(id));

        return residentService.bindUserResident(id);
    }

    /**查询用户提交状态
     * user与resident绑定
     * @param id
     * @return Resident
     */
    @GetMapping("/selectResidentStatus")
    private Resident selectResidentStatus(@RequestParam Integer id) {
        System.out.println("查询用户提交状态============="+residentService.selectResidentStatus(id));

        return residentService.selectResidentStatus(id);
    }

    /**修改resident_status
     *
     * @param id
     * @return Resident
     */
    @GetMapping("/changeRStatus")
    private boolean changeRStatus(@RequestParam Integer id) {
        System.out.println("修改resident_status============="+residentService.changeRStatus(id));

        return residentService.changeRStatus(id);
    }



    /**
     * 查询所有本小区人员信息 并 分页
     * @return
     */
    @GetMapping("/local")
    public IPage<Resident> findLocal(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize) {
        Page<Resident> page = new Page<>(pageNum, pageSize);//创建分页page

        return residentService.selectAllLocal(page);
    }


    /**
     * 查询所有非本小区人员信息 并分页
     * @return
     */
    @GetMapping("/nonLocal")
    public IPage<Resident> findNonLocal(@RequestParam Integer pageNum,
                                       @RequestParam Integer pageSize) {
        Page<Resident> page = new Page<>(pageNum, pageSize);//创建分页page

        return residentService.selectAllNonLocal(page);
    }

    @GetMapping("/getAllPerson")
    public List<Resident> getAllPerson(){return  residentService.getAllPerson();}


    /**
     * 新增或者删除
     * @param resident
     * @return
     */
    @PostMapping
    private boolean save(@RequestBody Resident resident) {
        if (resident.getId()==0){
            return residentService.save(resident);
        }else {
            return residentService.updateById(resident);
        }
    }

    /**用户登记
     * 新增或者删除
     * @param residentDto
     * @return
     */
    @PostMapping("/save2")
    private boolean save2(@RequestBody ResidentDto residentDto) {
        int save2= residentService.save2(residentDto);
        System.out.println("获取插入后的id========="+residentDto.getId());
        boolean flag = residentService.updateUserRId(residentDto);


        return flag;
    }

    /**
     * 删除操作
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return residentService.removeById(id);
    }

    /**
     * 批量操作
     */
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return residentService.removeBatchByIds(ids);
    }


    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        //从数据库查询出所有的数据
        List<Resident> list = residentService.list();
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


    /**
     * 导入接口
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<User> userList = reader.read(0, 1, User.class);
//        Bean方式读取
        List<Resident> residentList = reader.readAll(Resident.class);
        residentService.saveBatch(residentList);
        return true;
    }

    /**
     * 模糊查询
     */
    @GetMapping("/localPage")
    public IPage<Resident> findLocalPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String name,
                                    @RequestParam(defaultValue = "") String address) {
        IPage<Resident> page = new Page<>(pageNum, pageSize);//创建分页page

        QueryWrapper<Resident> wrapper = new QueryWrapper<>();//创建wrapper来实现模糊查询

        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(address)) {
            wrapper.like("address", address);
        }
        wrapper.orderByAsc("id");
//        return residentService.selectLocalPeople(wrapper,page);
        return residentService.page(page,wrapper);
    }


    /**
     * 模糊查询
     */

    @GetMapping("/nonlocalpage")
    public IPage<Resident> findNonLocalPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String name,
                                    @RequestParam(defaultValue = "") String address) {
        IPage<Resident> page = new Page<>(pageNum, pageSize);//创建分页page
        QueryWrapper<Resident> wrapper = new QueryWrapper<>();//创建wrapper来实现模糊查询

        if (!"".equals(name)) {
            wrapper.like("name", name);
        }
        if (!"".equals(address)) {
            wrapper.like("address", address);
        }
        wrapper.orderByAsc("id");
//        return residentService.selectNonLocalPeople(wrapper, page);
        return residentService.page(page,wrapper);
    }

    @GetMapping("getResident/{id}")
    public Resident GetResident(@PathVariable int id){
        return residentService.selectResidentById(id);
    }

    /**可视化
     * 查询居民种类人数
     *
     * @return
     */
    @GetMapping("/getTypeOfPeople")
    @ResponseBody
    public List<ResidentDto> getTypeOfPeople(){
        return residentService.getTypeOfPeople();
    }




}




package com.money.film.controller;

import com.money.film.Service.FilmService;
import com.money.film.Service.WebSiteInfoService;
import com.money.film.entity.Film;
import com.money.film.run.StartupRunner;
import com.money.film.util.DateUtil;
import com.money.film.util.StringUtil;
import org.apache.commons.io.FileUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liugang
 * @create 2018/10/24 23:06
 **/
@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {

    private static final Logger logger = LoggerFactory.getLogger(FilmAdminController.class);

    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Value("${imageFilePath}")
    private String imageFilePath;

    @Resource
    private StartupRunner startupRunner;

    /**
     * 分页查询收录电影信息
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public Map<String,Object> list(Film film, @RequestParam(value = "page",required = false)Integer page, @RequestParam(value = "rows",required = false)Integer rows)throws Exception{
        List<Film> filmList = filmService.list(film,page-1,rows);
        Long total = filmService.getCount(film);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("rows",filmList);
        resultMap.put("total",total);
        return resultMap;
    }

    @RequestMapping("/list/{id}")
    public ModelAndView search(@PathVariable(value = "id",required = false)Integer id)throws Exception{
        ModelAndView mav = new ModelAndView();
        return mav;
    }

    /**
     * 添加或者修改信息
     * @param film
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    public Map<String,Object> save(Film film,@RequestParam("imageFile")MultipartFile file)throws Exception{
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();//获取文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取后缀名
            String newFileName = DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+newFileName));
            film.setImageName(newFileName);
        }
        film.setPublishDate(new Date());
        filmService.save(film);
        startupRunner.loadData();
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("success",true);
        return resultMap;
    }

    @RequestMapping("/ckeditorUpload")
    public String ckeditorUpload(@RequestParam("upload")MultipartFile file,String CKEditorFuncNum)throws Exception{
        String fileName = file.getOriginalFilename();//获取文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));//获取后缀名
        String newFileName = DateUtil.getCurrentDateStr()+suffixName;
        FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imageFilePath+newFileName));

        StringBuffer sb=new StringBuffer();
        sb.append("<script type=\"text/javascript\">");
        sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/static/fileImages/"+ newFileName + "','')");
        sb.append("</script>");
        return sb.toString();
    }

    /**
     * 删除电影信息
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public Map<String,Object> delete(@RequestParam(value = "ids")String ids)throws Exception{
        String[] idsStr = ids.split(",");
        Map<String,Object> map = new HashMap<>();
        boolean flag = true;
        for(String str:idsStr){
            Integer filmId = Integer.parseInt(str);
            if (webSiteInfoService.getByFilmId(filmId).size()>0){
                flag = false;
            }else{
                filmService.delete(filmId);
            }
        }
        if (flag){
            map.put("success",true);
        }else{
            map.put("success",false);
            map.put("errorInfo","电影动态信息中存在电影信息，不能删除");
        }
        startupRunner.loadData();
        return map;
    }

    /**
     * 根据id查找实体
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    public Film findById(Integer id)throws Exception{
        logger.info("测试能找到电影名称吗-------------"+filmService.findById(id).getName()+"---------------");
        return filmService.findById(id);
    }

    /**
     * 下拉框
     * @param q
     * @return
     * @throws Exception
     */
    @RequestMapping("/comboList")
    public List<Film> comboList(String q)throws Exception{
        if (!StringUtil.isNotEmpty(q)){
            return null;
        }else{
            Film film = new Film();
            film.setName(q);
            return filmService.list(film,0,30);
        }
    }
}

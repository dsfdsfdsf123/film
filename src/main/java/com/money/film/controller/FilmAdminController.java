package com.money.film.controller;

import com.money.film.Service.FilmService;
import com.money.film.entity.Film;
import com.money.film.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liugang
 * @create 2018/10/24 23:06
 **/
@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {

    @Resource
    private FilmService filmService;

    @Value("${imageFilePath}")
    private String imageFilePath;

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
}

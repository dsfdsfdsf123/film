package com.money.film.util;

import com.google.gson.Gson;
import com.money.film.common.constants.Constants;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author liugang
 * @create 2018/11/26 7:46
 **/
public class QiniuFileUploadUtil {

    public static String uploadHeadImg(MultipartFile file)throws IOException{
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        //...生成上传凭证，然后准备上传
        UploadManager uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(Constants.QINIU_ACESS_KEY,Constants.QINIU_SECRET_KEY);
        String upToken = auth.uploadToken(Constants.QINIU_HEAD_IMG_BUCKEY_NAME);
        Response response = uploadManager.put(file.getBytes(),null,upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(response.bodyString(),DefaultPutRet.class);
        return putRet.key;
    }
}

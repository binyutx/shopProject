package com.taotao.manager.controller; /**
 * 〈一句话功能简述〉<br>
 * 〈图片上传的controller〉
 *
 * @author kepad
 * @create 2018/2/5
 * @since 1.0.0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.utils.PicUploadResult;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @创建人 kepade
 * @创建时间 2018/2/5
 * @描述
 */
@Controller
@RequestMapping(value = "pic/upload")
public class PicUploadController {
    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;
    //使用jackson工具类把对象转换为json数据
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //可上传的图片类型数组
    private static String[] TYPE ={".jpg",".jpeg",".png",".bmp",".gif"};
    // filePostName : "uploadFile", //上传文件名
    // uploadJson : '/rest/pic/upload', //图片上传请求路径
    // dir : "image" //上传文件类型
    @RequestMapping(method = RequestMethod.POST,produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String upload(MultipartFile uploadFile) throws Exception{
        //声明标志位
        boolean flag = false;
        //初始化返回数据，初始化上传失败
        PicUploadResult picUploadResult = new PicUploadResult();
        picUploadResult.setError(1);
        //校验后缀
        for (String type : TYPE){
            String oname = uploadFile.getOriginalFilename();
            //如果后缀是要求的格式结尾，标识位设置为true，跳出循环
            if (StringUtils.endsWithIgnoreCase(oname,type)){
                flag = true;
                break;
            }
        }

            //如果校验失败，直接返回
            if (!flag){
                String json = MAPPER.writeValueAsString(picUploadResult);
                return json;
            }
            //重置标志位
            flag = false;
            //图片内容校验
            try {
                BufferedImage image = ImageIO.read(uploadFile.getInputStream());
                if (image!=null){
                    picUploadResult.setHeight(String.valueOf(image.getHeight()));
                    picUploadResult.setWidth(String.valueOf(image.getWidth()));
                    flag = true;
                }
            }catch (Exception e){

            }
            //校验成功，需要开始上传图片
            if (flag){
                //1.加载tracker配置文件
                ClientGlobal.init(System.getProperty("user.dir")+"\\src\\main\\resources\\tracker.conf");
                //2.创建TrackerClient
                TrackerClient trackerClient = new TrackerClient();
                //3.获取TrackerServer
                TrackerServer trackerServer = trackerClient.getConnection();
                //4.声明StorageServer 为null就可以了
                StorageServer storageServer = null;
                //5.创建StorageClient
                StorageClient storageClient = new StorageClient(trackerServer,storageServer);
                //abababccb 1.2.3.4.jpg
                //6.使用StorageClient 上传图片
                // 获取上传文件的后缀名
                String ext = StringUtils.substringAfterLast(uploadFile.getOriginalFilename(), ".");
                String[] str = storageClient.upload_file(uploadFile.getBytes(), ext, null);
                //进行返回的结果的拼接，上传图片的url
                String picUrl = this.IMAGE_SERVER_URL + "/" + str[0] + "/"+str[1];
                //设置图片的url
                picUploadResult.setUrl(picUrl);
                //上传成功设置成0
                picUploadResult.setError(0);

            }
            //使用jackson工具类把对象转为json数据
        String json = MAPPER.writeValueAsString(picUploadResult);
        return json;

    }
}

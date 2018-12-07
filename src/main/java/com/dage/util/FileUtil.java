package com.dage.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @className:FileUpLoad
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-23 08:33
 */
public class FileUtil {
    /**
     * 文件上传
     * @param uploadPath
     * @param pic
     * @return
     */
    public static String upLoadFile(String uploadPath, MultipartFile pic){
        String originalFilename = pic.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newName = UUID.randomUUID()+substring;

        File file = new File(uploadPath+ newName);//相当于在F:/images/***.jpg
        //System.out.println(file);
        //调用spring提供的方法进行文件读写
            try {
            pic.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newName;
    }
    /**
     * 通用下载方法
     * @param filename
     * @param response
     * @return
     */
    public static String downLoad(String Path,String filename,HttpServletResponse response){
        File file = new File(Path + "/" + filename);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");//MIME类型
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
           /* int i = bis.read(buffer);
            while(i != -1){
                os.write(buffer);
                i = bis.read(buffer);
            }*/
                int i=0;
                while((i = bis.read(buffer))!=-1){
                    os.write(buffer,0,i);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}

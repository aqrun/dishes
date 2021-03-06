package com.aqrun.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.owlike.genson.Genson;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/file-upload")
public class FileUpload extends HttpServlet {
	 private static final long serialVersionUID = 1L;
 
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload" + File.separator +"images";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");  
		Genson genson = new Genson();
		Map<String, String> resultData = new HashMap<String, String>();
		PrintWriter writer = response.getWriter();
		 // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            resultData.put("code", "1");
            resultData.put("msg", "表单必须包含 enctype=multipart/form-data");
            writer.print(genson.serialize(resultData));
            writer.flush();
            return;
        }
 
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = request.getServletContext().getRealPath("./") 
        		+ File.separator + UPLOAD_DIRECTORY;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                        String realName = fileName.substring(0, fileName.lastIndexOf("."));
                        String newFileName = realName + "-" + getRandomString(8) + "." + suffix;
                        String filePath = uploadPath + File.separator + newFileName;
                        String uri = UPLOAD_DIRECTORY + File.separator + newFileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        //System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        
                        resultData.put("code", "0");
                        resultData.put("msg", "upload success");
                        resultData.put("uri", uri);
                        writer.print(genson.serialize(resultData));
                        writer.flush();
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            resultData.put("code", "1");
            resultData.put("msg", ex.getMessage());
            writer.print(genson.serialize(resultData));
            writer.flush();
        }
	}
	
	//获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
	public static String getRandomString(int length) {
	    //随机字符串的随机字符库
	    String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuffer sb = new StringBuffer();
	    int len = KeyString.length();
	    for (int i = 0; i < length; i++) {
	       sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
	    }
	    return sb.toString();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.dogiant.cms.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import sun.misc.BASE64Decoder;

/**
 * 文件工具类
 *
 */
public class FileUtil {

	/**
	 * 创建目录
	 * @param dir
	 * @return
	 */
	public static boolean mkdir(String dir) {
		File file = new File(dir);
		if (file.exists() && file.isDirectory())
			return true;
		return file.mkdirs();
	}

	/**
	 * 判断文件是否存在
	 * @param filePath
	 * @return
	 */
	public static boolean exists(String filePath) {
		File file = new File(filePath);
		return file.exists();
	}

	/**
	 * 移动文件
	 * @param srcPath
	 * @param destPath
	 */
	public static void mv(String srcPath, String destPath) {
		File src = new File(srcPath);
		File dest = new File(destPath);
		if (!src.exists())
			return;

		src.renameTo(dest);
	}

	/**
	 * 获得文件对象
	 * @param path
	 * @return
	 */
	public static File getFile(String path) {
		return new File(path);
	}

	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean delete(String filePath) {
		File file = new File(filePath);
		if (file.exists() && file.isFile())
			return file.delete();
		else
			return true;
	}

	/**
	 * copy 文件基本操作
	 * 
	 * @param fins
	 * @param destine
	 */
	private static boolean cp(InputStream fins, File destine) {
		try {
			if (fins == null)
				return false;
			destine.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(destine);
			byte[] buf = new byte[1024];
			int readLen;
			while ((readLen = fins.read(buf, 0, buf.length)) > 0) {
				fos.write(buf, 0, readLen);
			}
			fos.flush();
			fos.close();
			fins.close();
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * copy 文件
	 * 
	 * @param src
	 * @param destine
	 */
	public static boolean cp(File src, File destine) {
		try {
			if (!src.exists()
					|| src.getCanonicalPath()
							.equals(destine.getCanonicalPath()))
				return false;
			FileInputStream fins = new FileInputStream(src);
			cp(fins, destine);
			destine.setLastModified(src.lastModified());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 将srcPath文件copy到desPath上
	 * 
	 * @param srcPath
	 * @param desPath
	 */
	public static boolean cp(String srcPath, String desPath) {
		File src = new File(srcPath);
		File destine = new File(desPath);
		return cp(src, destine);
	}
	
	/**
	 * base64字串转成图片
	 * @param imgStr base64字串
	 * @param imgFilePath 图片保存路径
	 * @return
	 */
	public static String[] generateBase64Image(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) // 图像数据为空
            return new String[]{"500",null};
        String param = "jpg";
        if(!imgStr.startsWith("data:image")){
        	return new String[]{"500",null};
        }
        if("gif".equalsIgnoreCase(imgStr.substring(11, imgStr.indexOf(";")))){
        	param = "gif";
        	imgFilePath = imgFilePath.substring(0, imgFilePath.lastIndexOf("."))+".gif";
        }
        imgStr = imgStr.substring(imgStr.indexOf(",")+1);
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            return new String[]{"200",param};
        } catch (Exception e) {
        	e.printStackTrace();
        	return new String[]{"500",null};
        }
    }
    
	/**
	 * 获取远程图片
	 * @param fileUrl 远程图片地址
	 * @param savePath 图片保存路径
	 * @return 第一个返回状态码，第二个返回格式
	 */
    public static String[] generateRemoteImage(String fileUrl,String savePath){
    	try
    	{
    		String param = "jpg";
    		URL url = new URL(fileUrl);
	        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
	        int code = connection.getResponseCode();
	        System.out.println("远程图片 fileUrl = " +fileUrl + ";code = " + code);
	        System.out.println("远程图片 contentType = " +connection.getContentType() + ";contentencoding = " + connection.getContentEncoding());
	        if(code!=200){//没有取到图片
	        	return new String[]{code+"",null};
	        }
	        if(!connection.getContentType().startsWith("image")){//不是图片文件
	        	return new String[]{"",null};
	        }
	        if(connection.getContentType().endsWith("gif")){//是gif图片
	        	savePath = savePath.substring(0, savePath.lastIndexOf("."))+".gif";
	        	param = "gif";
	        }
	        InputStream in = null;
	        if("gzip".equalsIgnoreCase(connection.getContentEncoding())){
	        	in = new GZIPInputStream(connection.getInputStream());  
	        }else{
	        	in = new DataInputStream(connection.getInputStream());
	        }
	        DataOutputStream out = new DataOutputStream(new FileOutputStream(savePath));
	        byte[] buffer = new byte[4096];
	        int count = 0;
	        while ((count = in.read(buffer)) > 0)
	        {
	            out.write(buffer, 0, count);
	        }
	        out.close();
	        in.close();
	        connection.disconnect();
	        return new String[]{"200", param};
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    		System.out.println(e + fileUrl + savePath);
    		return new String[]{"500",null};
    	}
    }
}

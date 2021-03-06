package com.dogiant.cms.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTemplateUtil extends FreeMarkerTemplateUtils {

	/**
	 * templatePath模板文件存放路径,templateName 模板文件名称,filename 生成的文件名称
	 * 
	 * @param templatePath
	 * @param templateName
	 * @param fileName
	 * @param model
	 */
	public static void processTemplateIntoFile(String templatePath, String templateName, String fileName,
			Map<String, Object> model) {

		try {
			// 初使化FreeMarker配置
			Configuration config = new Configuration();
			// 设置要解析的模板所在的目录，并加载模板文件
			config.setDirectoryForTemplateLoading(new File(templatePath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			Template t = config.getTemplate(templateName, "utf-8");
			// 合并数据模型与模板
			Writer out = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
			t.process(model, out);

			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}

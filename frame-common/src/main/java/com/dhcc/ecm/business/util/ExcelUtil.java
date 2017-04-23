package com.dhcc.ecm.business.util;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelUtil {

    @SuppressWarnings("static-access")
    public static HSSFWorkbook createWorkbook(HSSFWorkbook wb, int pages, String sheetName) throws IOException {
        if (wb == null) {
            wb = new HSSFWorkbook();
        }
//        HSSFSheet fromsheet = wb.getSheetAt(0); 
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setMargin(HSSFSheet.TopMargin, 0.1);
      sheet.setMargin(HSSFSheet.BottomMargin, 0.1);
      sheet.setMargin(HSSFSheet.LeftMargin, 0.1);
      sheet.setMargin(HSSFSheet.RightMargin, 0.1);

        HSSFPrintSetup ps = sheet.getPrintSetup();  
        ps.setLandscape(false); // 打印方向，true：横向，false：纵向(默认)  
        ps.setVResolution((short)600);  
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //纸张类型
//        sheet.setMargin(HSSFSheet.TopMargin, 0.0);
//        sheet.setMargin(HSSFSheet.BottomMargin, 0.0);
//        sheet.setMargin(HSSFSheet.LeftMargin, 0.0);
//        sheet.setMargin(HSSFSheet.RightMargin, 0.0);
//        sheet.getPrintSetup().setHeaderMargin(0.0);
//        sheet.getPrintSetup().setFooterMargin(0.315);
        //sheet.getPrintSetup().setFooterMargin(0.0);
        HSSFRow row = null;
        for (int i = 0; i < 0 + 60 * pages; i++) {
            row = sheet.createRow(i);
            row.setHeightInPoints((short) 14.225);
        }
        for (int j = 0; j < 0 + 42; j++) {
            sheet.setColumnWidth(j, 640);
        }
        HSSFPrintSetup hps = sheet.getPrintSetup();
        hps.setPaperSize(hps.A4_PAPERSIZE); // 设置A4纸
        return wb;
    }
    
    
    public static HSSFWorkbook createExportWorkbook(HSSFWorkbook wb,  String sheetName){
    	if (wb == null) {
            wb = new HSSFWorkbook();
        }
    	
    	HSSFSheet sheet = wb.createSheet(sheetName);
    	sheet.setMargin(HSSFSheet.TopMargin, 0.0);
        sheet.setMargin(HSSFSheet.BottomMargin, 0.0);
        sheet.setMargin(HSSFSheet.LeftMargin, 0.0);
        sheet.setMargin(HSSFSheet.RightMargin, 0.0);
        sheet.getPrintSetup().setHeaderMargin(0.0);
        sheet.getPrintSetup().setFooterMargin(0.0);

    	return wb;
    }

    public static void writeXLSWithPrint(String filename, HSSFWorkbook wb, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 写入文件，关闭流
        String mineType =getMineType(filename);
        response.setContentType(mineType);
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.write(wb.getBytes());
        os.close();
    }
    
    public static void writeXLS(String filename, HSSFWorkbook wb) throws IOException {
        // 写入文件，关闭流
        FileOutputStream fileOut = new FileOutputStream(filename);
        wb.write(fileOut);
        fileOut.close();
    }


    public static void main(String[] args) {
//        try {
//            String sheetName = "分发单详细信息";
//            HSSFWorkbook wb = createWorkbook(null, 5, sheetName);
//            distributeInfo(wb, wb.getSheet(sheetName), 0, 0, "福建福清核电有限公司文件分发单", "QS_OP-2012-1052", new ArrayList<DistDocument>(),"毛光虎","2012-12-27 12:56:00");
//            writeXLS("D://distribute.xls", wb);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    // 获得类型
    public static String getMineType(String fileName) {
        String type = "";
        int lastSuff = fileName.lastIndexOf(".");// 后缀
        String lastString = fileName.substring(lastSuff + 1);
        if (lastString.equalsIgnoreCase("doc"))
            type = "application/msword";
        else if (lastString.equalsIgnoreCase("xls"))
            type = "application/vnd.ms-excel";
        else if (lastString.equalsIgnoreCase("ppt"))
            type = "application/vnd.ms-powerpoint";
        else if (lastString.equalsIgnoreCase("docx"))
            type = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        else if (lastString.equalsIgnoreCase("xlsx"))
            type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        else if (lastString.equalsIgnoreCase("pptx"))
            type = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        else if (lastString.equalsIgnoreCase("xlsb"))
            type = "application/vnd.ms-excel.sheet.binary.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("xlsm"))
            type = "application/vnd.ms-excel.sheet.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("ppsx"))
            type = "application/vnd.openxmlformats-officedocument.presentationml.slideshow";
        else if (lastString.equalsIgnoreCase("docm"))
            type = "application/vnd.ms-word.document.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("dotm"))
            type = "application/vnd.ms-word.template.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("dotx"))
            type = "application/vnd.openxmlformats-officedocument.wordprocessingml.template";
        else if (lastString.equalsIgnoreCase("potm"))
            type = "application/vnd.ms-powerpoint.template.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("potx"))
            type = "application/vnd.openxmlformats-officedocument.presentationml.template";
        else if (lastString.equalsIgnoreCase("ppam"))
            type = "application/vnd.ms-powerpoint.addin.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("ppsm"))
            type = "application/vnd.ms-powerpoint.slideshow.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("pptm"))
            type = "application/vnd.ms-powerpoint.presentation.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("xlam"))
            type = "application/vnd.ms-excel.addin.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("xltm"))
            type = "application/vnd.ms-excel.template.macroEnabled.12";
        else if (lastString.equalsIgnoreCase("xltx"))
            type = "application/vnd.openxmlformats-officedocument.spreadsheetml.template";
        else if (lastString.equalsIgnoreCase("svg"))
            type = "image/svg+xml";
        else if (lastString.equalsIgnoreCase("xfdl"))
            type = "application/vnd.xfdl";
        else if (lastString.equalsIgnoreCase("xfdd"))
            type = "application/vnd.xfdl.design";
        else if (lastString.equalsIgnoreCase("bmp"))
            type = "image/bmp";
        else if (lastString.equalsIgnoreCase("pdf"))
            type = "application/pdf";
        else if (lastString.equalsIgnoreCase("jpg"))
            type = "image/jpeg";
        else if (lastString.equalsIgnoreCase("gif"))
            type = "image/gif";
        else if (lastString.equalsIgnoreCase("avi"))
            type = "video/x-msvideo";
        else if (lastString.equalsIgnoreCase("wav"))
            type = "audio/x-wav";
        else if (lastString.equalsIgnoreCase("js"))
            type = "text/javascript";
        else if (lastString.equalsIgnoreCase("sql"))
            type = "text/plain";
        else if (lastString.equalsIgnoreCase("HTML"))
            type = "text/html";
        else if (lastString.equalsIgnoreCase("asf"))
            type = "video/x-ms-asf";
        else if (lastString.equalsIgnoreCase("mov"))
            type = "video/quicktime";
        else if (lastString.equalsIgnoreCase("rmvb"))
            type = "application/vnd.rn-realmedia";
        else if (lastString.equalsIgnoreCase("rm"))
            type = "application/vnd.rn-realmedia";
        else if (lastString.equalsIgnoreCase("mpeg"))
            type = "video/mpeg";
        else if (lastString.equalsIgnoreCase("mp3"))
            type = "audio/x-mpeg";
        else if (lastString.equalsIgnoreCase("vsd"))
            type = "application/x-visio";
        else if (lastString.equalsIgnoreCase("asx"))
            type = "video/x-ms-asf";
        else if (lastString.equalsIgnoreCase("ico"))
            type = "image/x-icon";
        else if (lastString.equalsIgnoreCase("ram"))
            type = "audio/x.pn-realaudio";
        else if (lastString.equalsIgnoreCase("rmi"))
            type = "audio/mid";
        else if (lastString.equalsIgnoreCase("rtf"))
            type = "application/rtf";
        else if (lastString.equalsIgnoreCase("jar"))
            type = "application/java-archive";
        else if (lastString.equalsIgnoreCase("snd"))
            type = "audio/basic";
        else if (lastString.equalsIgnoreCase("swf"))
            type = "application/x-shockwave-flash";
        else if (lastString.equalsIgnoreCase("m3u"))
            type = "audio/x-mpegurl";
        else if (lastString.equalsIgnoreCase("tar"))
            type = "application/x-tar";
        else if (lastString.equalsIgnoreCase("tif"))
            type = "image/tiff";
        else if (lastString.equalsIgnoreCase("ra"))
            type = "audio/vnd.rn-realaudio";
        else if (lastString.equalsIgnoreCase("mid"))
            type = "audio/x-midi";
        else if (lastString.equalsIgnoreCase("ddl"))
            type = "text/plain";
        else if (lastString.equalsIgnoreCase("ai"))
            type = "application/postscript";
        else if (lastString.equalsIgnoreCase("eps"))
            type = "application/postscript";
        else if (lastString.equalsIgnoreCase("mov"))
            type = "video/quicktime";
        else if (lastString.equalsIgnoreCase("exe"))
            type = "application/x-msdownload";
        else if (lastString.equalsIgnoreCase("mp2"))
            type = "audio/x-mpeg";
        else if (lastString.equalsIgnoreCase("aif"))
            type = "audio/x-aiff";
        else if (lastString.equalsIgnoreCase("flc"))
            type = "video/flc";
        else if (lastString.equalsIgnoreCase("vst"))
            type = "application/vnd.visio";
        else if (lastString.equalsIgnoreCase("xla"))
            type = "application/vnd.ms-excel";
        else if (lastString.equalsIgnoreCase("xlb"))
            type = "application/vnd.ms-excel";
        else if (lastString.equalsIgnoreCase("xlc"))
            type = "application/vnd.ms-excel";
        else if (lastString.equalsIgnoreCase("xld"))
            type = "application/vnd.ms-excel";
        else if (lastString.equalsIgnoreCase("xlk"))
            type = "application/vnd.ms-excel";
        else if (lastString.equalsIgnoreCase("zip"))
            type = "application/zip";
        else if (lastString.equalsIgnoreCase("pic"))
            type = "image/pict";
        else if (lastString.equalsIgnoreCase("pps"))
            type = "application/vnd.ms-powerpoint";
        else if (lastString.equalsIgnoreCase("pot"))
            type = "application/vnd.ms-powerpoint";
        else if (lastString.equalsIgnoreCase("psd"))
            type = "image/x-photoshop";
        else if (lastString.equalsIgnoreCase("cdr"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("pcd"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("dxf"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("ufo"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("eps"))
            type = "application/postscript";
        else if (lastString.equalsIgnoreCase("png"))
            type = "image/png";
        else if (lastString.equalsIgnoreCase("pcx"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("tiff"))
            type = "image/tiff";
        else if (lastString.equalsIgnoreCase("jpeg"))
            type = "image/jpeg";
        else if (lastString.equalsIgnoreCase("gif"))
            type = "image/gif";
        else if (lastString.equalsIgnoreCase("tga"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("exif"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("fpx"))
            type = "image/pjpeg";
        else if (lastString.equalsIgnoreCase("svg"))
            type = "image/svg+xml";
        else if (lastString.equalsIgnoreCase("bmp"))
            type = "image/bmp";
        // 默认
        if (type.equals(""))
            type = "application/octet-stream";
        return type;
    }

}

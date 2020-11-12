package com.netdisk.utils;

public class isWFile {
	public static String isFileType(String  value) {
        String type ="others";// 其他  
        // 图片  
        String [] pics = { "JPEG", "PNG", "GIF", "TIFF", "BMP", "DWG", "PSD","JPG" };

        String[] docs = {"RTF", "XML", "HTML", "CSS", "JS", "EML","DBX","PST","DOC","DOCX","VSD",
                "MDB", "WPS", "WPD", "EPS", "PDF", "QDF", "PWL", "ZIP", "RAR", "JSP", "JAVA", "CLASS",
                "MF", "CHM","xlsx" ,"TXT"};

        String[] videos = { "AVI", "RAM", "RM", "MPG", "MOV", "ASF", "MP4", "FLV", "MID","RMVB" };

        String [] tottents = { "TORRENT" };

        String[] audios = { "WAV", "MP3" ,"flac"};

        String[] others = {"EXE","JAR"};
  
        // 图片  
        for (String fileType : pics) {
            if ((fileType.toUpperCase()).equals(value.toUpperCase())) {
                type ="picture";  
            }  
        }  
        // 文档  
        for (String fileType : docs) {
            if ((fileType.toUpperCase()).equals(value.toUpperCase())) {
                type ="document";  
            }  
        }  
        // 视频  
        for (String fileType : videos) {
            if ((fileType.toUpperCase()).equals(value.toUpperCase())) {
                type = "video";  
            }  
        }  
        // 种子  
        for (String fileType : tottents) {
            if ((fileType.toUpperCase()).equals(value.toUpperCase())) {
                type = "tottents";  
            }  
        }  
        // 音乐  
        for (String fileType : audios) {
            if ((fileType.toUpperCase()).equals(value.toUpperCase())) {
                type ="audio";  
            }  
        }  
        
        for (String fileType : others) {
            if ((fileType.toUpperCase()).equals(value.toUpperCase())) {
                type ="others";  
            }  
        }  
        return type;  
    }

	 public static void main(String[] args) {
		 String str="AchsdkSD4";

         System.out.println(isFileType("mp4"));
	}
}

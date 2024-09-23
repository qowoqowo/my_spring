package com.spring.mypage.common.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.spring.mypage.attach.dto.AttachDTO;

@Component
public class FileUploadVO {

	@Value("#{util['file.attach.path']}")
	String attachPath;
	
	public AttachDTO saveFile(MultipartFile file) throws IOException {
		// uploadPath 에 저장될 파일명 생성 (유니크 아이디)
		String fileName = UUID.randomUUID().toString();
		
		// 파일 경로에 파일명 이어붙이기 (=파일의 풀 경로)
		// File.separatorChar 는 윈도우면 \\, 리눅스면 / 들어
		String atchPath = attachPath + File.separatorChar + fileName;
		
		// 해당경로에 실제로 파일 저장 (서버PC 로컬디스크)
		file.transferTo(new File(atchPath));
		
		// 이후 DB에 담을 첨부파일 객체(AttachDTO 객체 생성 후 리턴)
		AttachDTO result = new AttachDTO();
		
		result.setAtchFileName(fileName);	// UUID
		result.setAtchOriginalName(file.getOriginalFilename());	// 첨부될당시 이름
		result.setAtchFileSize(file.getSize());  // byte
		result.setAtchFancySize(transferToFancy(file.getSize())); //  MB, KB, GB ...
		result.setAtchContentType(file.getContentType()); // .png ,...
		result.setAtchPath(atchPath);  	// 풀 경로
		
		return result;
		
	}
	
	public String transferToFancy(long size) {
		
		if(size < 1024) {
			return size + "Byte";
		}else if(size < 1024 * 1024) {
			return size / 1024 + "KB";
		}else if(size < 1024 * 1024 * 1024) {
			return size / 1024 / 1024 + "MB";
		}else {
			return size / 1024 / 1024 / 1024 + "GB";
		}
		 
	}
	
	
	public AttachDTO saveImg(MultipartFile file) throws IOException {
		// uploadPath 에 저장될 파일명 생성 (유니크 아이디)
		String fileName = UUID.randomUUID().toString();
		
		// 파일 경로에 파일명 이어붙이기 (=파일의 풀 경로)
		// File.separatorChar 는 윈도우면 \\, 리눅스면 / 들어
		String atchPath = attachPath + File.separatorChar + fileName;
		
		// 해당경로에 실제로 파일 저장 (서버PC 로컬디스크)
		file.transferTo(new File(atchPath));
		
		// 이후 DB에 담을 첨부파일 객체(AttachDTO 객체 생성 후 리턴)
		AttachDTO result = new AttachDTO();
		
		result.setAtchPath(fileName);  	
		
		return result;
		
	}
	
	
	
	
	
	
	
	
}

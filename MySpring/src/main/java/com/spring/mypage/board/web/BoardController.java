package com.spring.mypage.board.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.mypage.board.dto.BoardDTO;
import com.spring.mypage.board.dto.PhotoDTO;
import com.spring.mypage.board.service.BoardService;
import com.spring.mypage.common.exception.BizNotFoundException;
import com.spring.mypage.common.util.FileUploadVO;
import com.spring.mypage.common.vo.SearchVO;
import com.spring.mypage.member.dto.MemberDTO;
import com.spring.mypage.reply.dto.ReplyDTO;
import com.spring.mypage.reply.service.ReplyService;
import com.spring.mypage.attach.dto.AttachDTO;
import com.spring.mypage.attach.service.AttachService;

@Controller
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	AttachService attachService;
	
	@Autowired
	FileUploadVO fileUpload;
	
	// 자유게시판 화면
	@RequestMapping("/boardView")
	public String boardView(Model model, SearchVO search) {

		int boardCount = boardService.getBoardCount(search);
		
		// DB로 부터 가져온 전체 게시글의 수를 search의 필드변수에 넣어줌
		search.setBoardCount(boardCount);
		
		search.setting();
		
		List<BoardDTO> boardList = boardService.getBoardList(search);
		
		System.out.println(search);
		System.out.println(boardList);
		
		model.addAttribute("keyBoardList",boardList);
		
		model.addAttribute("keySearch", search);
		
		return "board/boardView";
	}
	
	// 자유게시판 글쓰기 화면
	@RequestMapping("/boardWriteView")
	public String boardWriteView(HttpSession session) {
		
		System.out.println(session.getAttribute("login"));
		
		if(session.getAttribute("login") == null) {
			return "redirect:/loginView";
		}
		
		return "board/boardWriteView";
		
	}
	
	// 자유게시판 글쓰기
	@PostMapping("/boardWriteDo")
	public String boardWriteDo(BoardDTO board, HttpSession session
			, MultipartFile[] boardFile) {
		
		System.out.println(board);
		

		// FileUploadVO 의 saveFile() 을 실행
		// 1. 사용자가 파일을 첨부하지 않음 -> boardFile == null
		if(boardFile != null) {
			// 2. 사용자가 파일을 1개 첨부함 -> boardFile.length == 1 -> saveFile() 한번 실행
			// 3. 사용자가 파일을 3개 첨부함 -> boardFile.length == 3 -> saveFile() 세번 실행
			for(int i = 0; i < boardFile.length; i++) {
				// 각각의 첨부파일 객체 MultipartFile에 대해 saveFile() 실행
				try {
					// boardNo 가 비어있음
					AttachDTO fileHistory = fileUpload.saveFile(boardFile[i]);
					System.out.println(fileHistory);
					// fileHistory 를 DB에 저장
					attachService.insertAttach(fileHistory);
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
			}
				
		}

		MemberDTO login= (MemberDTO)session.getAttribute("login");
		
		String memId = login.getMemId();
		board.setMemId(memId);
		
		boardService.writeBoard(board);
		
		return "redirect:/boardView";
		
	}
	
	
	// 자유게시판 글 상세페이지
	@RequestMapping("/boardDetailView")
	public String boardDetailView(int no, Model model) {
		System.out.println("클릭한 게시글 번호" + no);
		
		boardService.boardCountUp(no);
		
		BoardDTO board = null;
		try {
			board = boardService.getBoard(no);
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			// 에러 발생 시 넣은 에러코드와 에러메시지 확인
			String errCode = e.getErrCode();
			String errMsg = e.getMessage();
			
			// 에러페이지에 에러메시지를 보여주고자 한다면 모델에 추가
			model.addAttribute("errMsg", errMsg);
			
			// 에러페이지로 보내기
			return "error/errPage";
			
		}
		
		model.addAttribute("keyBoard", board);

		// 해당 게시글에 달린 댓글 목록 가져오기
		List<ReplyDTO> replyList = replyService.getReplyList(no);
		// 댓글 목록도 모델에 추가
		model.addAttribute("keyReplyList",replyList);
		
		// 해당 게시글에 첨부된 파일 목록 가져오기
		List<AttachDTO> attachList = attachService.getAttachList(no);
		// 첨부 파일 목록도 모델에 추가
		model.addAttribute("keyAttachList" , attachList);		
		
		
		return "board/boardDetailView";
	}
	
	// 자유게시판 글 수정 화면
	@PostMapping("/boardEditView")
	public String boardEditView(int no, Model model) {
		
		try {
			BoardDTO board = boardService.getBoard(no);
			model.addAttribute("keyBoard", board);
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			return "errPage";
		}
		
		return "board/boardEditView";
	}
	
	// 자유게시판 글 수정 등록
	@PostMapping("/boardEditDo")
	public String boardEditDo(BoardDTO board) {
		
		System.out.println(board);
		
		boardService.updateBoard(board);
		
		return "redirect:/boardDetailView?no=" + board.getBoardNo();
		
		
	}
	
	// 자유게시판 글 삭제
	@PostMapping("/boardDeleteDo")
	public String boardDeleteDo(int no) {
		
		boardService.deleteBoard(no);
		
		return "redirect:/boardView";
		
	}
	
	// 사진게시판 글 작성 화면
	@RequestMapping("/photoWriteView")
	public String photoWriteView() {
		
		return "board/photoWriteView";
	}
	
	// 사진게시판 글 목록 화면
	@RequestMapping("/boardPhotoView")
	public String boardPhotoView(Model model,  SearchVO search) {
		
		int photoCount = boardService.getPhotoCount(search);
		
		search.setBoardCount(photoCount);
		
		search.setting();		

		List<PhotoDTO> photoList = boardService.getPhotoList(search);
		
		System.out.println(photoList);
		
		model.addAttribute("keyPhotoList",photoList);
		model.addAttribute("keySearch" , search);
		
		return "board/boardPhotoView";
	}
	
	// 사진게시판 글작성 하기
	@PostMapping("/photoWriteDo")
	public String photoWriteDo(PhotoDTO img, MultipartFile imgFile, HttpSession session) {
		
		MemberDTO login= (MemberDTO)session.getAttribute("login");
		
		try {
			AttachDTO fileHistory = fileUpload.saveImg(imgFile);
			System.out.println(fileHistory);
			
			img.setMemId(login.getMemId());
			img.setPhotoPath(fileHistory.getAtchPath());
			System.out.println(img);
			
			boardService.writePhoto(img);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		return "redirect:/boardPhotoView";
	}

	// 사진게시판 상세페이지 화면
	@RequestMapping("/photoDetailView")
	public String photoDetailView(int no ,Model model) {
		
		boardService.photoCountUp(no);
		
		PhotoDTO photo = boardService.getPhoto(no);
		
		System.out.println(photo);
		
		model.addAttribute("keyPhoto" ,photo);
		
		List<ReplyDTO> replyList = replyService.photoGetReplyList(no);
		model.addAttribute("keyReplyList",replyList);
		
		
		return "board/photoDetailView";
	}
	
	
	// 사진게시판 글 삭제
	@RequestMapping("/photoDeleteDo")
	public String photoDeleteDo(int no) {
		
		boardService.deletePhoto(no);
		
		return "redirect:/boardPhotoView";
	}
	
	
	// 사진게시판 글 수정 화면
	@PostMapping("/photoEditView")
	public String photoEditView(int no ,Model model) {

		PhotoDTO photo = boardService.getPhoto(no);
		
		System.out.println(photo);
		
		model.addAttribute("keyPhoto" ,photo);
		
		return "board/photoEditView";
	}
	
	// 사진게시판 글 수정 하기
	@RequestMapping("/photoEditDo")
	public String photoEditDo(PhotoDTO photo) {
		
		System.out.println(photo);
		
		boardService.updatePhoto(photo);
		
		return "redirect:/photoDetailView?no=" + photo.getPhotoNo();
	}
	
	
	
	
}

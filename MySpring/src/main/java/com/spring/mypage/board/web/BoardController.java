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
	
	// �����Խ��� ȭ��
	@RequestMapping("/boardView")
	public String boardView(Model model, SearchVO search) {

		int boardCount = boardService.getBoardCount(search);
		
		// DB�� ���� ������ ��ü �Խñ��� ���� search�� �ʵ庯���� �־���
		search.setBoardCount(boardCount);
		
		search.setting();
		
		List<BoardDTO> boardList = boardService.getBoardList(search);
		
		System.out.println(search);
		System.out.println(boardList);
		
		model.addAttribute("keyBoardList",boardList);
		
		model.addAttribute("keySearch", search);
		
		return "board/boardView";
	}
	
	// �����Խ��� �۾��� ȭ��
	@RequestMapping("/boardWriteView")
	public String boardWriteView(HttpSession session) {
		
		System.out.println(session.getAttribute("login"));
		
		if(session.getAttribute("login") == null) {
			return "redirect:/loginView";
		}
		
		return "board/boardWriteView";
		
	}
	
	// �����Խ��� �۾���
	@PostMapping("/boardWriteDo")
	public String boardWriteDo(BoardDTO board, HttpSession session
			, MultipartFile[] boardFile) {
		
		System.out.println(board);
		

		// FileUploadVO �� saveFile() �� ����
		// 1. ����ڰ� ������ ÷������ ���� -> boardFile == null
		if(boardFile != null) {
			// 2. ����ڰ� ������ 1�� ÷���� -> boardFile.length == 1 -> saveFile() �ѹ� ����
			// 3. ����ڰ� ������ 3�� ÷���� -> boardFile.length == 3 -> saveFile() ���� ����
			for(int i = 0; i < boardFile.length; i++) {
				// ������ ÷������ ��ü MultipartFile�� ���� saveFile() ����
				try {
					// boardNo �� �������
					AttachDTO fileHistory = fileUpload.saveFile(boardFile[i]);
					System.out.println(fileHistory);
					// fileHistory �� DB�� ����
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
	
	
	// �����Խ��� �� ��������
	@RequestMapping("/boardDetailView")
	public String boardDetailView(int no, Model model) {
		System.out.println("Ŭ���� �Խñ� ��ȣ" + no);
		
		boardService.boardCountUp(no);
		
		BoardDTO board = null;
		try {
			board = boardService.getBoard(no);
		} catch (BizNotFoundException e) {
			e.printStackTrace();
			// ���� �߻� �� ���� �����ڵ�� �����޽��� Ȯ��
			String errCode = e.getErrCode();
			String errMsg = e.getMessage();
			
			// ������������ �����޽����� �����ְ��� �Ѵٸ� �𵨿� �߰�
			model.addAttribute("errMsg", errMsg);
			
			// ������������ ������
			return "error/errPage";
			
		}
		
		model.addAttribute("keyBoard", board);

		// �ش� �Խñۿ� �޸� ��� ��� ��������
		List<ReplyDTO> replyList = replyService.getReplyList(no);
		// ��� ��ϵ� �𵨿� �߰�
		model.addAttribute("keyReplyList",replyList);
		
		// �ش� �Խñۿ� ÷�ε� ���� ��� ��������
		List<AttachDTO> attachList = attachService.getAttachList(no);
		// ÷�� ���� ��ϵ� �𵨿� �߰�
		model.addAttribute("keyAttachList" , attachList);		
		
		
		return "board/boardDetailView";
	}
	
	// �����Խ��� �� ���� ȭ��
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
	
	// �����Խ��� �� ���� ���
	@PostMapping("/boardEditDo")
	public String boardEditDo(BoardDTO board) {
		
		System.out.println(board);
		
		boardService.updateBoard(board);
		
		return "redirect:/boardDetailView?no=" + board.getBoardNo();
		
		
	}
	
	// �����Խ��� �� ����
	@PostMapping("/boardDeleteDo")
	public String boardDeleteDo(int no) {
		
		boardService.deleteBoard(no);
		
		return "redirect:/boardView";
		
	}
	
	// �����Խ��� �� �ۼ� ȭ��
	@RequestMapping("/photoWriteView")
	public String photoWriteView() {
		
		return "board/photoWriteView";
	}
	
	// �����Խ��� �� ��� ȭ��
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
	
	// �����Խ��� ���ۼ� �ϱ�
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

	// �����Խ��� �������� ȭ��
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
	
	
	// �����Խ��� �� ����
	@RequestMapping("/photoDeleteDo")
	public String photoDeleteDo(int no) {
		
		boardService.deletePhoto(no);
		
		return "redirect:/boardPhotoView";
	}
	
	
	// �����Խ��� �� ���� ȭ��
	@PostMapping("/photoEditView")
	public String photoEditView(int no ,Model model) {

		PhotoDTO photo = boardService.getPhoto(no);
		
		System.out.println(photo);
		
		model.addAttribute("keyPhoto" ,photo);
		
		return "board/photoEditView";
	}
	
	// �����Խ��� �� ���� �ϱ�
	@RequestMapping("/photoEditDo")
	public String photoEditDo(PhotoDTO photo) {
		
		System.out.println(photo);
		
		boardService.updatePhoto(photo);
		
		return "redirect:/photoDetailView?no=" + photo.getPhotoNo();
	}
	
	
	
	
}

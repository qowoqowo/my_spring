package com.spring.mypage.reply.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mypage.common.util.MyUtil;
import com.spring.mypage.reply.dto.ReplyDTO;
import com.spring.mypage.reply.service.ReplyService;

@Controller
public class ReplyController {
	
	@Autowired
	ReplyService replyService;

	// �����Խ��� ��� ���
	@ResponseBody
	@PostMapping("/replyWriteDo")
	public ReplyDTO replyWriteDo(ReplyDTO reply) {
		System.out.println(reply); // boardNo, memId, replyContent�� ���
		
		String uniqueId = MyUtil.makeUniqueId();
		reply.setReplyNo(uniqueId); // ����ũ ���̵� �ݿ�
		
		replyService.insertReply(reply);  // DB�� ��� ���
		
		ReplyDTO result = replyService.getReply(uniqueId); // ��� �߰��� ��� ������ ������
		
		return result;
		
		
	}

	// �����Խ��� ��� ����
	@ResponseBody
	@PostMapping("/delReplyDo")
	public String delReplyDo(String replyNo) {
		
		System.out.println(replyNo);
		String resp = "success";
		
		int cnt = replyService.deleteReply(replyNo);
		
		if(cnt == 0) {
			resp = "fail";
		}
		
		
		
		return resp;
	}
	
	// �����Խ��� ��� ���
	@ResponseBody
	@PostMapping("/photoReplyWriteDo")
	public ReplyDTO photoReplyWriteDo(ReplyDTO reply) {
		System.out.println(reply); // boardNo, memId, replyContent�� ���
		
		String uniqueId = MyUtil.makeUniqueId();
		reply.setReplyNo(uniqueId); // ����ũ ���̵� �ݿ�
		
		replyService.photoInsertReply(reply);  // DB�� ��� ���
		
		ReplyDTO result = replyService.photoGetReply(uniqueId); // ��� �߰��� ��� ������ ������
		
		return result;
		
		
	}

	// �����Խ��� ��� ����
	@ResponseBody
	@PostMapping("/photoDelReplyDo")
	public String photoDelReplyDo(String replyNo) {
		
		System.out.println(replyNo);
		String resp = "success";
		
		int cnt = replyService.photoDeleteReply(replyNo);
		
		if(cnt == 0) {
			resp = "fail";
		}
		
		
		
		return resp;
	}	
	
	
	
}

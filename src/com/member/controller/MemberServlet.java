package com.member.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.catalina.deploy.ContextService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.sun.xml.internal.stream.buffer.sax.Properties;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("Big5");
		res.setContentType("image/png");
		String action = req.getParameter("action");
		/***************�|���n�J***************/
		if("getAccount_judge".equals(action)){
			MemberService ms = new MemberService();
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
//			List<String> errorMsgs = new ArrayList<String>();
//			List<String> lostPassword = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*�P�_�b���K�X�O�_�ť�*/
			try{
				String mem_account = req.getParameter("mem_account");
				if(mem_account == null || mem_account.trim().isEmpty()){
					errorMsgs.put("account", "�п�J�b��");
					System.out.println("�п�J�b��");
				}
				String mem_password = req.getParameter("mem_password");
				if(mem_password == null || mem_password.trim().isEmpty()){
					errorMsgs.put("password", "�п�J�K�X");
					System.out.println("�п�J�K�X");
				}			
				/*�p�G���~�T�����O�Ū��N�^�ǿ��~�T��*/
				if(!errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/front_end/login.jsp");
					rd.forward(req, res);
					return;
				}
				/*�P�_��Ʈw�O�_���b���K�X*/
				MemberVO memSelf = new MemberVO();
				memSelf = ms.login(mem_account, mem_password);
				
				if(memSelf == null){
					errorMsgs.put("account", "�L�Ī��b��");
					System.out.println("�L�Ī��b��");
				}
				System.out.println(ms.memIsBan(mem_account, mem_password));
				if(ms.memIsBan(mem_account, mem_password)){
					
					errorMsgs.put("account", "���b���w�Q��");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/front_end/login.jsp");
					rd.forward(req, res);
					return;
				}

				/*����ӤH�s�������s��*/
				HttpSession personal_mem_no = req.getSession();
				/***************�o��|��Session��T**************/
				HttpSession memSession = req.getSession();
				Object isNullSession = memSession.getAttribute("memSelf");
				String location = (String)memSession.getAttribute("location");
				System.out.println(location);
				if(isNullSession == null){	
					memSession.setAttribute("memSelf", memSelf);
					System.out.println("�n�J:"+memSession.getId());
				
				
					if(personal_mem_no != null && (location.equals(req.getContextPath()+"/front_end/member/personal_page.jsp"))){
						String str_personal_mem_no = (String)personal_mem_no.getAttribute("personal_mem_no");
						personal_mem_no.removeAttribute("personal_mem_no");
						res.sendRedirect(location+"?mem_no="+str_personal_mem_no);
						return;
					}
					
					if(location != null){
						memSession.removeAttribute("location");
						res.sendRedirect(location);
						return;
					}
				}
				String index = req.getContextPath()+"/front_end/index.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher(index);
//				rd.forward(req, res);
				res.sendRedirect(index);
				return;
			}catch(Exception e){
				System.out.println("�L�k���o���");
				RequestDispatcher rd = req.getRequestDispatcher("/front_end/login.jsp");
				rd.forward(req, res);
				return;
			}
			
		}
		
		
		/***************�n�J�����s����U����***************/
		if("getlinked_member_register_page".equals(action)){
				res.sendRedirect(req.getContextPath()+"/front_end/member/member_register.jsp");
				return;
		}
		/***************��������n�J����***************/
		if("getlinked_login_page".equals(action)){
			res.sendRedirect(req.getContextPath()+"/front_end/login.jsp");
				return;
		}
		/***************�|���n�X***************/
		if("getlinked_logon_page".equals(action)){
				HttpSession memSession = req.getSession();
				System.out.println("�n�X:"+memSession.getId());
				memSession.invalidate();
				res.sendRedirect(req.getContextPath()+"/front_end/index.jsp");
				return;
		}
		
		
		
		/***************�|�����U***************/
		if("getregister_judge".equals(action)){
			
				Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
				req.setAttribute("errorMsgs", errorMsgs);
				MemberService ms = new MemberService();
			try{
				String mem_account = req.getParameter("mem_account");
				String mem_accountReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if(mem_account == null || mem_account.trim().length() == 0){
					errorMsgs.put("mem_account", "�b��: �ФŪť�");
				}else if(!mem_account.trim().matches(mem_accountReg)){
					errorMsgs.put("mem_account", "�b��: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
				}
				if(ms.isMember(mem_account)){
					errorMsgs.put("mem_account", "�b��: �w�s�b");
				}
				String mem_password = req.getParameter("mem_password");
				String mem_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
				if(mem_password == null || mem_password.trim().length() == 0){
					errorMsgs.put("mem_password", "�K�X: �ФŪť�");
				}else if(!mem_password.trim().matches(mem_passwordReg)){
					errorMsgs.put("mem_password", "�K�X: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
				}
				String doublecheck = req.getParameter("doublecheck");
				
				if(!mem_password.equals(doublecheck)){
					errorMsgs.put("doublecheck", "�K�X���@�P");
				}
				String mem_name = req.getParameter("mem_name");
				if(mem_name == null || mem_name.trim().length() == 0){
					errorMsgs.put("mem_name", "�ʺ�: �ФŪť�");
				}
				
				java.sql.Date mem_birthday = null;
				try{
					mem_birthday = java.sql.Date.valueOf(req.getParameter("mem_birthday").trim());
				}catch(IllegalArgumentException e){
					errorMsgs.put("mem_birthday", "�п�J���");
				}
				
				String mem_gender = req.getParameter("mem_gender");
				if(mem_gender == null || mem_gender.trim().length() == 0){
					errorMsgs.put("mem_gender", "�п�ܩʧO");
				}else if(mem_gender.equals("�k") || mem_gender.equals("�k")){
				}else{
					errorMsgs.put("mem_gender", "�п�ܩʧO");
				}
				
				
				String mem_bloodtype = req.getParameter("mem_bloodtype");
				if(mem_bloodtype == null || mem_bloodtype.trim().length() == 0){
					errorMsgs.put("mem_bloodtype", "�п�z���嫬");
				}else if(mem_bloodtype.equals("A") || mem_bloodtype.equals("B") || mem_bloodtype.equals("AB") || mem_bloodtype.equals("O")){
				}else{
					errorMsgs.put("mem_bloodtype", "�п�z���嫬");
				}
				
				String mem_county = req.getParameter("mem_county");
				if(mem_county == null || mem_county.trim().length() == 0){
					errorMsgs.put("mem_county", "�п�ܦa��");
				}else if("�򶩥�".equals(mem_county) || "�x�_��".equals(mem_county) || "�s�_��".equals(mem_county) || "��饫".equals(mem_county) || "�s�˥�".equals(mem_county) || "�s�˿�".equals(mem_county) || "�]�߿�".equals(mem_county) || "�x����".equals(mem_county) || "���ƿ�".equals(mem_county) || "�n�뿤".equals(mem_county) || "���L��".equals(mem_county) || "�Ÿq��".equals(mem_county) || "�Ÿq��".equals(mem_county) || "�x�n��".equals(mem_county) || "������".equals(mem_county) || "�̪F��".equals(mem_county) || "�x�F��".equals(mem_county) || "�Ὤ��".equals(mem_county) || "�y����".equals(mem_county)){
				}else{
					errorMsgs.put("mem_county", "�п�ܦa��");
				}
				
				Integer mem_height = null;
				try{
					mem_height = Integer.valueOf(req.getParameter("mem_height").trim());
				}catch(NumberFormatException e){
					errorMsgs.put("mem_height", "�п�ܨ���");
				}
		
				Integer mem_weight = null;
				try{
					mem_weight = Integer.valueOf(req.getParameter("mem_weight").trim());	
				}catch(NumberFormatException e){
					errorMsgs.put("mem_weight", "�п���魫");
				}


				
				String mem_emotion = req.getParameter("mem_emotion");
				if(mem_emotion == null || mem_emotion.trim().length() == 0 || "�п��:".equals(mem_emotion)){
					errorMsgs.put("mem_emotion", "�п�ܷP�����A");
				}

				
				
				String mem_contact = req.getParameter("mem_contact");
				System.out.println(mem_contact);
				if(mem_contact == null || mem_contact.trim().length() == 0 || "�п��:".equals(mem_contact)){
					errorMsgs.put("mem_contact", "�п�ܷP�����p");
				}
			
							
				if(! errorMsgs.isEmpty()){
					RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/member_register.jsp");
					rd.forward(req, res);
					return;
				}
				
				MemberVO memRegister = new MemberVO(); 
				memRegister = ms.memberRegisterService(mem_account, mem_password, mem_name, mem_birthday, mem_bloodtype, mem_gender, mem_county, mem_height, mem_weight, mem_emotion, mem_contact);
				RequestDispatcher rd = req.getRequestDispatcher("/front_end/login.jsp");
				rd.forward(req, res);
			}catch(Exception e){
				System.out.println("�L�k���o���");
				RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/member_register.jsp");
				rd.forward(req, res);
			}
			
		}
		/**************�ഫ�ӤH����**************/
			
			if("selfpage".equals(action)){
				Map<String,String> myself_page = new HashMap<String,String>();
					
				String selfp = req.getParameter("selfp");
			
				if("selfintro".equals(selfp)){
					String selfintro_path = "/front_end/member/member_intro.jsp";
					myself_page.put(selfp, selfintro_path);
				}else if("selfdata".equals(selfp)){
					String selfintro_path = "/front_end/member/member_modify_page.jsp";
					myself_page.put(selfp, selfintro_path);	
				}else if("selfpass".equals(selfp)){
					String selfintro_path ="/front_end/member/member_modify_password.jsp";
					myself_page.put(selfp, selfintro_path);
				}

				Set<String> keys = myself_page.keySet();
				for(String key : keys){
					String value = myself_page.get(key);
					System.out.println("key = " + key);
					System.out.println("value = " + value);
				}
				
				HttpSession myself_page_path = req.getSession();
				myself_page_path.setAttribute("myself_page", myself_page);
				
				res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
				return;
			}
		
		/****************�ۧڤ���***************/
		if("getintro_judge".equals(action)){

			try{
			String mem_intro = req.getParameter("mem_intro");
			System.out.println(mem_intro);
			MemberVO memvo = null;
			HttpSession memintro = req.getSession();
			memvo = (MemberVO) memintro.getAttribute("memSelf");
			if(mem_intro.trim().length() == 0 || mem_intro == null || memintro == null){
				System.out.println(mem_intro.isEmpty());
				res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
				return;
			}
			MemberService ms = new MemberService();
			String mem_no = memvo.getMem_no();
			System.out.println("mem_no ="+mem_no);
			memvo = ms.getOneMem(mem_no);
			if(memvo == null){
				System.out.println("���~1");
				res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
				return;
			}
			memvo = ms.updateIntro(mem_intro, mem_no);
			res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
			return;
				
			}catch(Exception e){
				System.out.println("���~");
				res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
				return;
			}
		}
		/***************�ק�K�X***************/
		if("getmodify_password".equals(action)){
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			MemberService ms = new MemberService();
			String mem_passwordReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
			String old_pass = req.getParameter("old_pass");
			String new_pass = req.getParameter("new_pass");
			String new_pass_s = req.getParameter("new_pass_s");
			String mem_no = req.getParameter("mem_no");
			System.out.println("old_pass = " + old_pass);
			System.out.println("new_pass = " + new_pass);
			System.out.println("new_pass_s =" + new_pass_s);
			
			
			if(old_pass == null || old_pass.trim().length() == 0){
				errorMsgs.put("mem_old", "�K�X: �ФŪť�");
			}else if(!old_pass.trim().matches(mem_passwordReg)){
				errorMsgs.put("mem_old", "�K�X: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
			}
			MemberVO memvo = ms.getOneMem(mem_no);
			if(!(old_pass.equals(memvo.getMem_password()))){
				errorMsgs.put("mem_old", "�K�X: ��J���~");
			}
			if(new_pass == null || new_pass.trim().length() == 0){
				errorMsgs.put("mem_new", "�K�X: �ФŪť�");
			}else if(!new_pass.trim().matches(mem_passwordReg)){
				errorMsgs.put("mem_new", "�K�X: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��15����");
			}
			
			if(!(new_pass.equals(new_pass_s))){
				errorMsgs.put("mem_new", "�K�X:���@�P");
			}	
			
			ms.getOneMem(mem_no);
			if(!(errorMsgs.isEmpty())){
				RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/modify_personal_data_main_page.jsp");
				rd.forward(req, res);
				return;
			}
		
			ms.updatePass(new_pass,mem_no);
			res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
			return;
		}
		
		
		
		
		
		
		
		
		
		
		/***************�|���ק�***************/
		if("getmodify_data_judge".equals(action)){
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String mem_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,15}$";
			String mem_phoneReg = "[0-9]{10}";
			String mem_mailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
			try{
				Map<String,String[]> map = (Map<String,String[]>)req.getParameterMap();
				Set<String> keys = map.keySet();
				HttpSession memSelf = req.getSession();
				MemberVO memvo = (MemberVO)memSelf.getAttribute("memSelf");
				MemberService ms = new MemberService();

				for(String key : keys){
					String value = map.get(key)[0];
			
					if(value == null || value.trim().length() == 0){
						switch(key){
						case "mem_no" :
							if(!(value.equals(memvo.getMem_no()))){
								res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
								return;
							}
						case "mem_name" :
							errorMsgs.put("mem_name", "�п�J�ʺ�");
							System.out.println("test");
							break;
						case "mem_phone" :
							errorMsgs.put("mem_phone", "�п�J���");
							break;
						case "mem_mail" :
							errorMsgs.put("mem_mail", "�п�J�H�c");
							break;
						case "mem_height" :
							errorMsgs.put("mem_height", "�п�J����");
							break;
						case "mem_weight" :
							errorMsgs.put("mem_weight", "�п�J�魫");
							break;
						case "mem_emotion" :
							errorMsgs.put("mem_emotion", "�п�J�P�����p");
							break;
						case "mem_contact" :
							errorMsgs.put("mem_contact", "�п�J�橹���Y");
							break;
						default:
							break;
						}
					}

					if(value != null){
						if("mem_name".equals(key)){
							if( !(value.trim().matches(mem_nameReg)))
								errorMsgs.put("mem_name", "�ʺٿ�J���~");
						}
						else if("mem_phone".equals(key)){
							if(!(value.trim().matches(mem_phoneReg)))
								errorMsgs.put("mem_phone", "����榡���~");
							
						}else if("mem_mail".equals(key)){
							if(!(value.matches(mem_mailReg)))
								errorMsgs.put("mem_mail", "�q�l�l��榡���~");
							
						}else if("mem_height".equals(key)){
							int check_height = Integer.valueOf(value);
							if((check_height > 220 || check_height < 120))
								errorMsgs.put("mem_height", "�����榡���~");	
						}else if("mem_weight".equals(key)){
							int check_weight = Integer.valueOf(value);
							if((check_weight > 220 || check_weight < 30))
								errorMsgs.put("mem_weight","�魫�榡���~");
						}else if("mem_emotion".equals(key)){
							String[] s_emotion ={"�樭","í�w�橹��","�w�B","���B","�@������","�O�K"};
							int count = 0;
							for(int i=0; i < s_emotion.length; i++){
								if(value.equals(s_emotion[i])){
									 count = 1;
									 break;
								}
							}
							if(count == 0){
								errorMsgs.put("mem_emotion","�P�����p�榡���~");
							}
						}else if("mem_contact".equals(key)){
							String[] s_contact ={"��L�ͽ�","�ͤߦn��","�k�k�B��","���B��H","�������H"};
							int count = 0;
							for(int i=0; i < s_contact.length; i++){
								if(value.equals(s_contact[i])){
									count = 1;
								}
							}
							if(count == 0){
								errorMsgs.put("mem_contact","�橹���p�榡���~");
							}
							
						}else if("mem_interest".equals(key.substring(0, key.lastIndexOf("t") + 1))){
							String[] s_interest = {"�W��","ť����","�ݹq�v","����","�ݮ�","���y","���C��","�Ȧ�"};
							int count = 0;
							for(int i = 0; i < s_interest.length; i++){
								if(value.equals(s_interest[i])){
									value = value + ",";
									count = 1;
								}
							}
							if(count == 0){
								errorMsgs.put("mem_interest", "����榡���~");
							}
							
						}
					
					}			
				}
					if(!(errorMsgs.isEmpty())){
						System.out.println("�|����ƭק���~");
						for(int i = 0 ; i < errorMsgs.size(); i++){
							System.out.println(errorMsgs.keySet());
							System.out.println(errorMsgs.get(i));
						}
						RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/modify_personal_data_main_page.jsp");
						rd.forward(req, res);
						return;
					}
					
					ms.uodateMember(map);
					
						
				Part part = req.getPart("mem_photo");
				if(part.getSize() > 0){
					System.out.println(part.getName());
					System.out.println(part.getSize());
					System.out.println(part.getContentType());
					
					byte[] pic = uploadPic(part);
					ms.uploadPic(pic,memvo.getMem_no());
				}
				
				
				memvo = ms.getOneMem(memvo.getMem_no());
				memSelf.setAttribute("memSelf", memvo);
				memSelf.getAttribute("memSelf");
				
				System.out.println("success");
				res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
				return;
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("�ק異��");
				res.sendRedirect(req.getContextPath()+"/front_end/member/modify_personal_data_main_page.jsp");
				return;
			}	
		}
		
		
		if("getOne_From06".equals(action)){
			try{
			String mem_no = req.getParameter("mem_no");
			MemberVO memvo = new MemberVO();
			MemberService ms = new MemberService();
			System.out.println(mem_no);
			
			memvo = ms.getOneMem(mem_no);
			req.setAttribute("memvo", memvo);
			boolean openModal=true;
			req.setAttribute("openModal",openModal );
			RequestDispatcher successView = req
					.getRequestDispatcher("/front_end/member/member_manage_friendslist.jsp");
			successView.forward(req, res);
			return;
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	    /***********************************/
		/*								   */
		/*								   */
		/*								   */
		/*				�j�M�\��			   */
		/*								   */
		/* 								   */
		/*   							   */
		/***********************************/

		/***************�򥻷j�M***************/
		
		if("blur_search".equals(action)){
			
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			try{
				String mem_name = req.getParameter("mem_name");
				if(mem_name == null || mem_name.trim().length() == 0){
					errorMsgs.put("�d�L���", "�d�L���");
					RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/member_search_all.jsp");
					rd.forward(req, res);
					return;
				}
					System.out.println(mem_name);
					MemberService ms = new MemberService();
					List<MemberVO> getallMemberData = ms.blur(mem_name);
					req.setAttribute("getallMemberData", getallMemberData);
					RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/member_search_all.jsp");
					rd.forward(req, res);
					return;
			}catch(Exception e){
				errorMsgs.put("�d�L���", "�d�L���");
				RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/member_search_all.jsp");
				rd.forward(req, res);
				return;	
			}
		}
		/***************�i���j�M***************/
			System.out.println(action);
if("listMems_ByCompositeQuery".equals(action)){
				
				try{
//					HttpSession session = req.getSession();
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//					Map<String,String[]> map = (Map<String,String[]>)req.getParameterMap();
//					if(req.getParameter("whichPage")==null){
//						HashMap<String, String[]> map1 = new HashMap(req.getParameterMap());
//						session.setAttribute("map", map1);
//						map = map1;
//						HashMap<String, String[]> map2 = new HashMap(map1);
//						session.setAttribute("map", map2);
//						map = (HashMap<String,String[]>)req.getParameterMap();

//					}
					Map<String, String[]> map = (Map<String, String[]>)req.getParameterMap();
					MemberService ms = new MemberService();
					List<MemberVO>getallMemberData = ms.precise(map);		
					req.setAttribute("getallMemberData1", getallMemberData);
					RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/member_search_all.jsp");
					rd.forward(req, res);
					return;
					

				}catch(Exception e){
					e.printStackTrace();
					RequestDispatcher rd = req.getRequestDispatcher("/front_end/member/member_search_all.jsp");
					rd.forward(req, res);
					return;	
				}
			}
		
		
		
		
	}
		/***************�B�z�Ϥ�***************/
	public static byte[] uploadPic(Part part){
		InputStream is = null;
		byte[] buff=null;
		try{
			is = part.getInputStream();
			buff = new byte[is.available()];
			is.read(buff);
			is.close();
		}catch(IOException e){
			System.out.println("�Ϥ��W�ǥ���");
		}finally{
			try{
				if( is != null){
					is.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return buff;
	}

}

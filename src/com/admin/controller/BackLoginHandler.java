package com.admin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.model.AdminService;
import com.admin.model.AdminVO;

public class BackLoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");

		/**************** 登入比對start *****************/
		if ("backlogin".equals(action)) {
			
			// 比對帳密
			try {

				AdminService adminSvc = new AdminService();
				AdminVO admin;

				String account = req.getParameter("account");
				String psw = req.getParameter("pwd");

				

				// 檢查是否有填帳密
				if (account == null || (account.trim().length()) == 0 || psw == null || (psw.trim().length()) == 0) {
					req.setAttribute("errorMsgs", "請輸入帳號密碼");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/index.jsp");
					failureView.forward(req, res);
					return;
				}
				// 檢查是否有此帳戶
				admin = adminSvc.getOneByAcct(account);
				if (admin == null) {
					req.setAttribute("errorMsgs", "無此員工");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/index.jsp");
					failureView.forward(req, res);
					return;
				} else {

					// 有此帳戶的話比對密碼是否正確
					if (!psw.equals(admin.getAdm_pwd())) {
						req.setAttribute("errorMsgs", "密碼錯誤");
						req.setAttribute("account", account);
						RequestDispatcher failureView = req.getRequestDispatcher("/back_end/index.jsp");
						failureView.forward(req, res);
						return;
					} else {
						admin = adminSvc.getOneByAcct(account);
						HttpSession session = req.getSession();
						session.setAttribute("admin", admin);

						try {
							String location = (String) session.getAttribute("location");
							if (location != null) {
								session.removeAttribute("location");
								res.sendRedirect(location);
								return;
							}
						} catch (Exception ignored) {
						}

						res.sendRedirect(req.getContextPath() + "/back_end/back_page.jsp");
					}
				}

			} catch (Exception e) {
				req.setAttribute("errorMsgs", "無此員工");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/index.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

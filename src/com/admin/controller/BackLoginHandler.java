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

		/**************** �n�J���start *****************/
		if ("backlogin".equals(action)) {
			
			// ���b�K
			try {

				AdminService adminSvc = new AdminService();
				AdminVO admin;

				String account = req.getParameter("account");
				String psw = req.getParameter("pwd");

				

				// �ˬd�O�_����b�K
				if (account == null || (account.trim().length()) == 0 || psw == null || (psw.trim().length()) == 0) {
					req.setAttribute("errorMsgs", "�п�J�b���K�X");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/index.jsp");
					failureView.forward(req, res);
					return;
				}
				// �ˬd�O�_�����b��
				admin = adminSvc.getOneByAcct(account);
				if (admin == null) {
					req.setAttribute("errorMsgs", "�L�����u");
					RequestDispatcher failureView = req.getRequestDispatcher("/back_end/index.jsp");
					failureView.forward(req, res);
					return;
				} else {

					// �����b�᪺�ܤ��K�X�O�_���T
					if (!psw.equals(admin.getAdm_pwd())) {
						req.setAttribute("errorMsgs", "�K�X���~");
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
				req.setAttribute("errorMsgs", "�L�����u");
				RequestDispatcher failureView = req.getRequestDispatcher("/back_end/index.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

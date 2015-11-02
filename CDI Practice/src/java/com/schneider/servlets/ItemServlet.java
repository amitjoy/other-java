/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.schneider.servlets;

import com.schneider.amit.beans.MessageServerBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SESA249907
 */
@WebServlet("/message")
public class ItemServlet extends HttpServlet{
    
    @Inject
    MessageServerBean mbean;
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException{
        PrintWriter writer = res.getWriter();
        writer.print(mbean.getMessage());
    }
}

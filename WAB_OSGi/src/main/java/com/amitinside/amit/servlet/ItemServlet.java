package com.amitinside.amit.servlet;

import com.amitinside.beans.ItemProcessorSvc;
import com.amitinside.beans.MessageServerBeanSvc;
import com.amitinside.item.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.glassfish.osgicdi.OSGiService;

/**
 * @author Amit Kumar Mondal (admin@amitinside.com)
 */
@WebServlet("/message")
public class ItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    @OSGiService(dynamic = true)
    MessageServerBeanSvc mbean;
    @Inject
    @OSGiService(dynamic = true)
    ItemProcessorSvc itemBean;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter writer = res.getWriter();
        writer.println(mbean.getMessage());
        itemBean.execute();

        List<Item> items = itemBean.getItemList();
        for (Item item : items) {
            writer.println(item.toString());
        }
    }
}

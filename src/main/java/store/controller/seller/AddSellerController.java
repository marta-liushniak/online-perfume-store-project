package store.controller.seller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.model.Seller;
import store.service.SellerService;

public class AddSellerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final SellerService sellerService = (SellerService)
            injector.getInstance(SellerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/sellers/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("last_name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Seller seller = new Seller(name, lastName, login, password);
        sellerService.create(seller);
        resp.sendRedirect(req.getContextPath() + "/sellers/add");
    }
}

package store.controller.seller;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.service.SellerService;

public class DeleteSellerController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final SellerService sellerService = (SellerService) injector
            .getInstance(SellerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        sellerService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/sellers");
    }
}

package store.controller.seller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.model.Seller;
import store.service.SellerService;

public class GetAllSellersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final SellerService sellerService = (SellerService) injector
            .getInstance(SellerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Seller> allSellers = sellerService.getAll();
        req.setAttribute("sellers", allSellers);
        req.getRequestDispatcher("/WEB-INF/views/sellers/all.jsp").forward(req, resp);
    }
}

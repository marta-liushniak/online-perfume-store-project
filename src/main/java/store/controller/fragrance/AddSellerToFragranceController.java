package store.controller.fragrance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.model.Fragrance;
import store.model.Seller;
import store.service.FragranceService;
import store.service.SellerService;

public class AddSellerToFragranceController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final FragranceService fragranceService = (FragranceService)
            injector.getInstance(FragranceService.class);
    private final SellerService sellerService = (SellerService)
            injector.getInstance(SellerService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/fragrances/sellers/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long sellerId = Long.parseLong(req.getParameter("seller_id"));
        long fragranceId = Long.parseLong(req.getParameter("fragrance_id"));
        Seller seller = sellerService.get(sellerId);
        Fragrance fragrance = fragranceService.get(fragranceId);
        fragranceService.addSellerToFragrance(seller, fragrance);
        resp.sendRedirect(req.getContextPath() + "/fragrances/sellers/add");
    }
}

package store.controller.fragrance;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import store.lib.Injector;
import store.model.Fragrance;
import store.service.FragranceService;

public class GetMyCurrentFragrancesController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final FragranceService fragranceService = (FragranceService) injector
            .getInstance(FragranceService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long sellerId = (Long) session.getAttribute("seller_id");
        List<Fragrance> fragrances = fragranceService.getAllBySeller(sellerId);
        req.setAttribute("fragrances", fragrances);
        req.getRequestDispatcher("/WEB-INF/views/fragrances/all.jsp").forward(req, resp);
    }
}

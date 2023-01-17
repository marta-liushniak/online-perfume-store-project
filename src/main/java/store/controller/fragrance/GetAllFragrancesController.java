package store.controller.fragrance;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.model.Fragrance;
import store.service.FragranceService;

public class GetAllFragrancesController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final FragranceService fragranceService = (FragranceService) injector
            .getInstance(FragranceService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Fragrance> allFragrances = fragranceService.getAll();
        req.setAttribute("fragrances", allFragrances);
        req.getRequestDispatcher("/WEB-INF/views/fragrances/all.jsp").forward(req, resp);
    }
}

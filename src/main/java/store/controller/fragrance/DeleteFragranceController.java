package store.controller.fragrance;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.service.FragranceService;

public class DeleteFragranceController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final FragranceService fragranceService = (FragranceService) injector
            .getInstance(FragranceService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        fragranceService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/fragrances");
    }
}

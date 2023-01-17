package store.controller.brand;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.service.BrandService;

public class DeleteBrandController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final BrandService brandService = (BrandService) injector
            .getInstance(BrandService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        brandService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/brands");
    }
}

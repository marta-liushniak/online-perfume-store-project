package store.controller.brand;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.model.Brand;
import store.service.BrandService;

public class AddBrandController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final BrandService brandService = (BrandService) injector
            .getInstance(BrandService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/brands/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("brand_name");
        String country = req.getParameter("country");
        Brand brand = new Brand(name, country);
        brandService.create(brand);
        resp.sendRedirect(req.getContextPath() + "/brands/add");
    }
}

package store.controller.brand;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.model.Brand;
import store.service.BrandService;

public class GetAllBrandsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final BrandService brandService = (BrandService) injector
            .getInstance(BrandService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Brand> allBrands = brandService.getAll();
        req.setAttribute("brands", allBrands);
        req.getRequestDispatcher("/WEB-INF/views/brands/all.jsp").forward(req, resp);
    }
}

package store.controller.fragrance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import store.lib.Injector;
import store.model.Brand;
import store.model.Fragrance;
import store.service.BrandService;
import store.service.FragranceService;

public class AddFragranceController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("store");
    private final FragranceService fragranceService = (FragranceService) injector
            .getInstance(FragranceService.class);
    private final BrandService brandService = (BrandService) injector
            .getInstance(BrandService.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/fragrances/add.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fragranceName = req.getParameter("fragrance_name");
        long brandId = Long.parseLong(req.getParameter("brand_id"));
        Brand brand = brandService.get(brandId);
        Fragrance fragrance = new Fragrance(fragranceName, brand);
        fragranceService.create(fragrance);
        resp.sendRedirect(req.getContextPath() + "/fragrances/add");
    }
}

package Health.servlets;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import Health.DiagnosticDaoImpl;

@WebServlet("/metrics")
public class MetricsServlet extends HttpServlet {
    PrometheusMeterRegistry registry = DiagnosticDaoImpl.getRegistry();
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/plain; version=0.0.4; charset=utf-8");
    response.getWriter().write(registry.scrape());
}
}


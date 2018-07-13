package relatorio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public RelatorioServlet() {
        super();      
    }
    
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	List<Cliente> clientesLista = new ArrayList<Cliente>();
		clientesLista.add(new Cliente("Jabes Felipe", "Copacabana, 54", "jabesfelipe@gmail.com", "Rio de Janeiro"));
		clientesLista.add(new Cliente("Camila Ribeiro", "Ipanema,324", "camilarib@gmail.com", "Rio de Janeiro"));
		clientesLista.add(new Cliente("Jaque Silva", "Lapa, 44", "jaquesilva@gmail.com", "Rio de Janeiro"));
		clientesLista.add(new Cliente("Bruna Lira", "Centro, 32", "brunalira@gmail.com", "Rio de Janeiro"));
		clientesLista.add(new Cliente("Amanda Alves", "Lapa, 342", "amandaa@gmail.com", "Rio de Janeiro"));
		clientesLista.add(new Cliente("Priscila Ratina", "Barra Tijuca, 86", "priscilaratina@gmail.com", "Rio de Janeiro"));
		clientesLista.add(new Cliente("Carolina Marques", "Copacabana, 234", "carolinamarques@gmail.com", "Rio de Janeiro"));
		
		try {
			
			String acao = request.getParameter("acao") == null ? "" : request.getParameter("acao");
			
			String caminhoJasper = getServletContext().getRealPath("/WEB-INF/classes/relatorio/") + "/";
			String caminho = getServletContext().getRealPath("/");
			
			JRBeanCollectionDataSource jrDT = new JRBeanCollectionDataSource(clientesLista);
			Map parametros = new HashMap();
			parametros.put("titulo", "Relatórios de Clientes");
			parametros.put(clientesLista, jrDT);
			parametros.put("total", "Total de Clientes: " + clientesLista.size());
			JasperPrint jasperPrint = JasperFillManager.fillReport(caminhoJasper + "clientes.jasper", parametros, jrDT);
			
			if("visualizar".equals(acao)) {
				JasperViewer.viewReport(jasperPrint, false);
			} else if("pdf".equals(acao)) {
				JasperPrintManager.printReportToPdfFile(jasperPrint, caminho + "/RelatorioCliente.pdf");
				response.sendRedirect("RelatorioCliente.pdf");
			} else {
				response.sendRedirect("index.jsp");
			}
					
		} catch (JRException e) {			
			e.printStackTrace();
		}

    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	processRequest(request, response);
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}

package jasper;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Session;
import util.HibernateUtil;

public class ReportGenerator {

    private static Connection getHibernateConnection() {
        final Connection[] connection = new Connection[1];

        Session session = HibernateUtil.getCurrentSession();
        session.doWork(conn -> connection[0] = conn);

        return connection[0];
    }

    public static JasperPrint generarInformeListadoNaves() {
        try {
            String informe = "/Informe1.jasper";
            InputStream inputStream = ReportGenerator.class.getResourceAsStream(informe);

            if (inputStream == null) {
                throw new FileNotFoundException("El archivo " + informe + " no se encuentra en el classpath.");
            }

            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(inputStream, new HashMap<>(), connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static JasperPrint generarInformeListadoTripulantes() throws FileNotFoundException {
        try {
            String informe = "/Informe2.jasper";
            InputStream inputStream = ReportGenerator.class.getResourceAsStream(informe);

            if (inputStream == null) {
                throw new FileNotFoundException("El archivo " + informe + " no se encuentra en el classpath.");
            }

            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(inputStream, new HashMap<>(), connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generarInformeTripulacionPorNave() {
        String informe = "Informe3.jasper";
        try {
            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(informe, new HashMap<>(), connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generarInformeMisionesPorEstado() {
        String informe = "Informe4.jasper";
        try {
            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(informe, new HashMap<>(), connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generarInformeMisionesPorNave(int idNave) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("ID_Nave", idNave);
        String informe = "Informe5.jasper";
        try {
            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(informe, parametros, connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generarInformeTripulacionPorRango(String rango) {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("Rango", rango);
        String informe = "Informe6.jasper";
        try {
            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(informe, parametros, connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

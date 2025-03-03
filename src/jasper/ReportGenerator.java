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
        try {
            String informe = "/Informe3.jasper";
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

    public static JasperPrint generarInformeMisionesPorEstado() {
        try {
            String informe = "/Informe4.jasper";
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

    public static JasperPrint generarInformeMisionesPorNave(int idNave) throws FileNotFoundException {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("ID_Nave", idNave);
        String informe = "/Informe5.jasper";
        InputStream inputStream = ReportGenerator.class.getResourceAsStream(informe);

        if (inputStream == null) {
            throw new FileNotFoundException("El archivo " + informe + " no se encuentra en el classpath.");
        }

        try {
            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(inputStream, parametros, connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generarInformeTripulacionPorRango(String rango) throws FileNotFoundException {
        HashMap<String, Object> parametros = new HashMap<>();
        parametros.put("Rango", rango);
        String informe = "/Informe6.jasper";
        InputStream inputStream = ReportGenerator.class.getResourceAsStream(informe);

        if (inputStream == null) {
            throw new FileNotFoundException("El archivo " + informe + " no se encuentra en el classpath.");
        }

        try {
            Connection connection = getHibernateConnection();
            JasperPrint informeLleno = JasperFillManager.fillReport(inputStream, parametros, connection);
            return informeLleno;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generarGrafico1() {
        try {
            String informe = "/Grafico1.jasper";
            InputStream inputStream = ReportGenerator.class.getResourceAsStream(informe);

            if (inputStream == null) {
                throw new FileNotFoundException("El archivo " + informe + " no se encuentra en el classpath.");
            }

            Connection connection = getHibernateConnection();
            return JasperFillManager.fillReport(inputStream, new HashMap<>(), connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JasperPrint generarGrafico2() {
        try {
            String informe = "/Grafico2.jasper";
            InputStream inputStream = ReportGenerator.class.getResourceAsStream(informe);

            if (inputStream == null) {
                throw new FileNotFoundException("El archivo " + informe + " no se encuentra en el classpath.");
            }

            Connection connection = getHibernateConnection();
            return JasperFillManager.fillReport(inputStream, new HashMap<>(), connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

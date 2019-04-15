package br.com.interagese.interfiscal.utils;

import br.com.interagese.interfiscal.entity.Sessao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import org.apache.commons.io.IOUtils;
import org.hibernate.jpa.HibernatePersistenceProvider;

/**
 * Classe usada pra manipular a instancia estática do entity manager em toda a
 * aplicação
 *
 * @author José
 */
public class JPAUtil {

    //private static EntityManagerFactory emf          = Persistence.createEntityManagerFactory("interservPU", propriedadesInterserv());
    private static EntityManagerFactory emfIntegradoFirebird;
    private static EntityManagerFactory emfIntegradoPostgres;

    static {
        try {
            PersistenceProvider pp = new HibernatePersistenceProvider();
            emfIntegradoFirebird = pp.createEntityManagerFactory("firebird-interfiscalPU", propriedadesIntegrado());
            PersistenceProvider ppp = new HibernatePersistenceProvider();
            emfIntegradoPostgres = ppp.createEntityManagerFactory("postgres-interfiscalPU", propriedadesIntegradoPostgres());
        } catch (Exception e) {
            System.out.println("Erro ao criar entitymanager factory");
            e.printStackTrace();
        }
    }

    public JPAUtil() {

    }

    private static EntityManager entityManager;

    public static EntityManager getEntityManager() {

        return createEntityManagerIntegrado();

    }
    public static EntityManager getEntityManagerPostgreSql() {

        return createEntityManagerIntegradoPostgres();

    }

    public static EntityManager createEntityManagerServidor() {

        return null;

    }

    public static EntityManager createEntityManagerIntegrado() {

        return emfIntegradoFirebird.createEntityManager();

    }

    public static EntityManager createEntityManagerIntegradoPostgres() {

        return emfIntegradoPostgres.createEntityManager();

    }

    //Faz um map com as propriedades jdbc do banco integrado
    private static Map propriedadesInterserv() {

        try {
            Map mapa = new HashMap();

            Properties props = carregarArquivoDatabase();

            String bdPrincipal = props.getProperty("BDSERVIDOR");
            String ipServidor = "";

            if (bdPrincipal != null) {

                int posicaoIp = bdPrincipal.indexOf(":C:");

                if (posicaoIp > -1) {
                    ipServidor = bdPrincipal.substring(0, posicaoIp);
                }

                if (ipServidor == null || ipServidor.length() == 0) {
                    ipServidor = "localhost";
                }

                bdPrincipal = bdPrincipal.substring(bdPrincipal.indexOf("C:"), bdPrincipal.length());

                props.setProperty("BDPRINCIPAL", bdPrincipal);
                props.put("IPSERVIDOR", ipServidor);

            }

            System.out.println("INTEGRADO: " + props.getProperty("BDPRINCIPAL"));
            System.out.println("IP SERVIDOR: " + props.getProperty("IPSERVIDOR"));

            mapa.put("javax.persistence.jdbc.driver", "org.firebirdsql.jdbc.FBDriver");
            mapa.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:" + props.getProperty("IPSERVIDOR") + "/3050:" + props.getProperty("BDPRINCIPAL") + "?encoding=ISO8859_1");
            mapa.put("javax.persistence.jdbc.user", "SYSDBA");
            mapa.put("javax.persistence.jdbc.password", "masterkey");

            return mapa;

        } catch (IOException ex) {
            Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //Faz um map com as propriedades jdbc do banco integrado
    private static Map propriedadesIntegrado() {

        try {
            Map mapa = new HashMap();

            System.out.println("INTEGRADO: " + Sessao.bdFirebird);
            System.out.println("IP SERVIDOR: " + Sessao.ipFirebird);

            mapa.put("javax.persistence.jdbc.driver", "org.firebirdsql.jdbc.FBDriver");
            mapa.put("javax.persistence.jdbc.url", "jdbc:firebirdsql:" + Sessao.ipFirebird + "/3050:" + Sessao.bdFirebird + "?encoding=ISO8859_1");
            mapa.put("javax.persistence.jdbc.user", Sessao.userFirebird);
            mapa.put("javax.persistence.jdbc.password", Sessao.pwFirebird);

            return mapa;

        } catch (Exception ex) {
            Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static Map propriedadesIntegradoPostgres() {

        try {
            Map mapa = new HashMap();

            

            System.out.println("INTEGRADO: " + Sessao.bdPostgres);
            System.out.println("IP SERVIDOR: " + Sessao.ipPostgres);

            mapa.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            mapa.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + Sessao.ipPostgres + ":5432/" + Sessao.bdPostgres);
            mapa.put("javax.persistence.jdbc.user", Sessao.userPostgres);
            mapa.put("javax.persistence.jdbc.password", Sessao.pwPostgres);

            return mapa;

        } catch (Exception ex) {
            Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //Metodo que carrega o arquivo database em um objeto properties
    private static Properties carregarArquivoDatabase() throws FileNotFoundException, IOException {

        File database = new File("C:\\InterageSE\\DataBase.cfg");

        Properties props = new Properties();

        if (database.exists() == true) {

            FileInputStream fis = new FileInputStream(database);

            props.load(new StringReader(IOUtils.toString(fis, StandardCharsets.UTF_8).replace("\\", "/")));

            fis.close();

        }

        return props;

    }

}

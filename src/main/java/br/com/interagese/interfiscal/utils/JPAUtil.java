package br.com.interagese.interfiscal.utils;

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

//    public static void executeCreateScript() {
//
//        Map propriedadesIntegrado = propriedadesIntegrado();
//
//        Connection connection = null;
//
//        try {
//            connection = DriverManager.getConnection(propriedadesIntegrado.get("javax.persistence.jdbc.url").toString(),
//                    propriedadesIntegrado.get("javax.persistence.jdbc.user").toString(),
//                    propriedadesIntegrado.get("javax.persistence.jdbc.password").toString());
//
//            String[] sqls = {"CREATE TABLE RESUMONFE (\n"
//                + "    KEYNFE            INTEGER NOT NULL,\n"
//                + "    CHNFE             VARCHAR(50) NOT NULL,\n"
//                + "    CNPJ              VARCHAR(14),\n"
//                + "    CPF               VARCHAR(11),\n"
//                + "    IE                VARCHAR(14),\n"
//                + "    NSU               INTEGER,\n"
//                + "    CSITCONF          VARCHAR(1),\n"
//                + "    CSITNFE           VARCHAR(1),\n"
//                + "    CODFIL            INTEGER,\n"
//                + "    DHEMI             TIMESTAMP,\n"
//                + "    DHRECBTO          TIMESTAMP,\n"
//                + "    DIGVAL            VARCHAR(255),\n"
//                + "    NPROT             VARCHAR(255),\n"
//                + "    NFEDETALHADA      BLOB SUB_TYPE 1 SEGMENT SIZE 80,\n"
//                + "    NFERESUMIDA       BLOB SUB_TYPE 1 SEGMENT SIZE 80,\n"
//                + "    TPNF              VARCHAR(1),\n"
//                + "    VERSAO            VARCHAR(5),\n"
//                + "    XNOME             VARCHAR(60),\n"
//                + "    VNF               DOUBLE PRECISION,\n"
//                + "    ENTRADANOESTOQUE  VARCHAR(1),\n"
//                + "    RGCODUSU          INTEGER,\n"
//                + "    RGUSUARIO         VARCHAR(8),\n"
//                + "    RGDATA            TIMESTAMP,\n"
//                + "    RGEVENTO          VARCHAR(1),\n"
//                + "    SERIE             VARCHAR(3),\n"
//                + "    NUMERO            INTEGER,\n"
//                + "    TPAMB             VARCHAR(1)\n"
//                + ");", "ALTER TABLE RESUMONFE ADD CONSTRAINT PK_RESUMONFE PRIMARY KEY (KEYNFE);",
//                "ALTER TABLE TABUSU ADD CODCONTA INTEGER;"};
//
//            for (String sql : sqls) {
//                try {
//                    Statement stam = connection.createStatement();
//                    stam.executeUpdate(sql);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException ex) {
//                Logger.getLogger(JPAUtil.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
    //Faz um map com as propriedades jdbc do banco integrado
    private static Map propriedadesIntegrado() {

        try {
            Map mapa = new HashMap();

            Properties props = carregarArquivoDatabase();

            String bdPrincipal = props.getProperty("BDPRINCIPAL");
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

    private static Map propriedadesIntegradoPostgres() {

        try {
            Map mapa = new HashMap();

            Properties props = carregarArquivoDatabase();

            String bdPostgres = props.getProperty("BDPOSTGRES");
            String ipServidor = "";

            if (bdPostgres != null) {
                if (bdPostgres.contains(":")) {
                    int i = 1;
                    for (String s : bdPostgres.split(":")) {
                        switch (i) {
                            case 1: {
                                ipServidor = s;
                                break;
                            }
                            case 2: {
                                bdPostgres = s;
                                break;
                            }
                        }
                        i++;
                    }
                }else{
                    ipServidor="localhost";
                }


                props.setProperty("BDPOSTGRES", bdPostgres);
                props.put("IPSERVIDOR", ipServidor);

            }

            System.out.println("INTEGRADO: " + props.getProperty("BDPOSTGRES"));
            System.out.println("IP SERVIDOR: " + props.getProperty("IPSERVIDOR"));

            mapa.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
            mapa.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + props.getProperty("IPSERVIDOR") + ":5432/" + props.getProperty("BDPOSTGRES"));
            mapa.put("javax.persistence.jdbc.user", props.getProperty("USERPOSTGRES"));
            mapa.put("javax.persistence.jdbc.password", props.getProperty("PASSWORDPOSTGRES"));

            return mapa;

        } catch (IOException ex) {
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

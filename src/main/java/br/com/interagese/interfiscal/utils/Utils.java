/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interagese.interfiscal.utils;

//import br.com.softline.padrao.model.Empresa;
//import br.com.softline.padrao.model.Endereco;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;

/**
 *
 * @author adam
 */
public class Utils {

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static String retirarCaracteresXML(String s) {

        if (s != null && !s.equals("")) {
            s = s.trim();

            s = s.replace("º", "");
            s = s.replace("ª", "");
            s = s.replace("º", "");
            s = s.replace("§", "");
            s = s.replace("'", "");

            s = s.trim();

            String acentuado = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
            String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";

            char[] tabela = new char[256];

            for (int i = 0; i
                    < tabela.length;
                    ++i) {
                tabela[i] = (char) i;

            }
            for (int i = 0; i
                    < acentuado.length();
                    ++i) {
                tabela[acentuado.charAt(i)] = semAcento.charAt(i);

            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i
                    < s.length();
                    ++i) {
                char ch = s.charAt(i);

                if (ch < 256) {
                    sb.append(tabela[ch]);

                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        return null;
    }

    public static int getMod10(String num) {

        //variveis de instancia
        int soma = 0;
        int resto = 0;
        int dv = 0;
        String[] numeros = new String[num.length() + 1];
        int multiplicador = 2;
        String aux;
        String aux2;
        String aux3;

        for (int i = num.length(); i > 0; i--) {
            //Multiplica da direita pra esquerda, alternando os algarismos 2 e 1
            if (multiplicador % 2 == 0) {
                // pega cada numero isoladamente
                numeros[i] = String.valueOf(Integer.valueOf(num.substring(i - 1, i)) * 2);
                multiplicador = 1;
            } else {
                numeros[i] = String.valueOf(Integer.valueOf(num.substring(i - 1, i)) * 1);
                multiplicador = 2;
            }
        }

        // Realiza a soma dos campos de acordo com a regra
        for (int i = (numeros.length - 1); i > 0; i--) {
            aux = String.valueOf(Integer.valueOf(numeros[i]));

            if (aux.length() > 1) {
                aux2 = aux.substring(0, aux.length() - 1);
                aux3 = aux.substring(aux.length() - 1, aux.length());
                numeros[i] = String.valueOf(Integer.valueOf(aux2) + Integer.valueOf(aux3));
            } else {
                numeros[i] = aux;
            }
        }

        //Realiza a soma de todos os elementos do array e calcula o digito verificador
        //na base 10 de acordo com a regra.
        for (int i = numeros.length; i > 0; i--) {
            if (numeros[i - 1] != null) {
                soma += Integer.valueOf(numeros[i - 1]);
            }
        }
        resto = soma % 10;
        dv = 10 - resto;

        //retorna o digito verificador
        if (dv == 10) {
            dv = 0;
        }
        return dv;
    }

    public static String casasDecimais(Integer casas, Double valor) {
        /*
         * if (casas != null && valor != null) { String quantCasas = "%." +
         * casas + "f", textoValor = "0"; try { textoValor =
         * String.format(Locale.getDefault(), quantCasas, valor);
         *
         * } catch (java.lang.IllegalArgumentException e) { if
         * (e.getMessage().equals("Digits < 0")) { textoValor = "0";
         *
         * }
         * System.out.println(e.getMessage()); }
         *
         * return textoValor.replace(",", "."); } return null;
         */

        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));

        nf.setMaximumFractionDigits(casas);
        nf.setMinimumFractionDigits(casas);

        if (valor == null) {
            valor = 0.0;
        }

        String sValor = nf.format(valor);

        sValor = sValor.replace(".", "");
        sValor = sValor.replace(",", ".");

        return sValor;

    }

    public static int getMod11(String num) {

        int base = 9;
        int r = 0;

        int soma = 0;
        int fator = 2;
        String[] numeros, parcial;
        numeros = new String[num.length() + 1];
        parcial = new String[num.length() + 1];

        /* Separacao dos numeros */
        for (int i = num.length(); i > 0; i--) {
            // pega cada numero isoladamente
            numeros[i] = num.substring(i - 1, i);
            // Efetua multiplicacao do numero pelo falor
            parcial[i] = String.valueOf(Integer.parseInt(numeros[i]) * fator);
            // Soma dos digitos
            soma += Integer.parseInt(parcial[i]);
            if (fator == base) {
                // restaura fator de multiplicacao para 2
                fator = 1;
            }
            fator++;

        }

        /* Calculo do modulo 11 */
        if (r == 0) {
            soma *= 10;
            int digito = soma % 11;
            if (digito == 10) {
                digito = 0;
            }
            return digito;
        } else {
            int resto = soma % 11;
            return resto;
        }
    }

    public static void main(String[] args) {
        System.out.println(getMod11Bradesco("0000267"));
    }

    public static int getMod11Bradesco(String num) {
        List<Integer> listaMod11 = new ArrayList<>();
        listaMod11.addAll(Arrays.asList(2, 7, 6, 5, 4, 3));
        List<Integer> listaNossoNumero = new ArrayList<>();
        for (char ch : num.toCharArray()) {
            Integer i = Character.getNumericValue(ch);
            listaNossoNumero.add(i);
        }

        Integer soma = 0;
        int cont = -1;

        for (Integer i : listaNossoNumero) {
            if (cont == 5) {
                cont = -1;
            }
            cont++;
            Integer rslt = i * listaMod11.get(cont);
            soma += rslt;
        }

        Integer resto = soma % 11;
        if (resto == 1) {
            return 111;
        } else if (resto == 0) {
            return 0;
        }
        return 11 - resto;

    }

    public static boolean validarCpfCnpj(String cpfcnpj) {

        if (cpfcnpj == null) {
            return false;
        }

        cpfcnpj = cpfcnpj.replace(".", "");
        cpfcnpj = cpfcnpj.replace("-", "");
        cpfcnpj = cpfcnpj.replace("/", "");

        if (cpfcnpj.length() == 11) {
            boolean valido = isValidCPF(cpfcnpj);
            if (!valido) {
                return false;
            }
        } else if (!isValidCNPJ(cpfcnpj)) {
            return false;
        }

        return true;
    }

    public static String formataStringCPF(String valor) {
        if (valor == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(valor);
        //218.654.654-54
        builder.insert(3, ".").insert(7, ".").insert(11, "-");

        return builder.toString();
    }

    public static String formataStringCNPJ(String valor) {
        if (valor == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(valor);
        //21.654.654/0001-54
        builder.insert(2, ".").insert(6, ".").insert(10, "/").insert(15, "-");

        return builder.toString();
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCPF(String cpf) {
        if ((cpf == null) || (cpf.length() != 11) || "00000000000".equals(cpf)) {
            return false;
        }

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14) || "00000000000000".equals(cnpj)) {
            return false;
        }

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

    public static byte[] fileToByte(File file) {
        FileInputStream fis = null;

        try {

            fis = new FileInputStream(file);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            try {
                for (int readNum; (readNum = fis.read(buf)) != -1;) {
                    bos.write(buf, 0, readNum); //no doubt here is 0
                    //Writes len bytes from the specified byte array starting at offset off to this byte array output stream.

                }
            } catch (IOException ex) {

                return null;
            }
            byte[] bytes = bos.toByteArray();
            fis.close();
            return bytes;
        } catch (Exception e) {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }

    public static Double arredondamento(Double valor) {

        return arredondamento(valor, 2);
    }

    public static Double arredondamento(Double valor, int numCasas) {
        if (valor == null) {
            return null;
        }
        BigDecimal bigDecimal = new BigDecimal(valor);
        bigDecimal = bigDecimal.setScale(numCasas, RoundingMode.HALF_UP);
        valor = bigDecimal.doubleValue();

        return valor;
    }
    private static final char[] FIRST_CHAR = (" !'#$%&'()*+\\-./0123456789:;<->?@ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~ E ,f'.++^%S<O Z  ''''.-"
            + "-~Ts>o ZY !C#$Y|$'(a<--(_o+23'u .,1o>113?AAAAAAACEEEEIIIIDNOO"
            + "OOOXOUUUUyTsaaaaaaaceeeeiiiidnooooo/ouuuuyty").toCharArray();
    /**
     * Para a normaliza??o dos caracteres de 32 a 255, segundo caracter
     */
    private static final char[] SECOND_CHAR = ("  '         ,                                               "
            + "\\                                   $  r'. + o  E      ''  "
            + "  M  e     #  =  'C.<  R .-..     ..>424     E E            "
            + "   E E     hs    e e         h     e e     h ").toCharArray();

    public static String normalize(String str) {
        char[] chars = str.toCharArray();
        StringBuffer ret = new StringBuffer(chars.length * 2);
        for (int i = 0; i < chars.length; ++i) {
            char aChar = chars[i];
            if (aChar == ' ' || aChar == '\t') {
                ret.append(' '); // convertido para espa?o
            } else if (aChar > ' ' && aChar < 256) {
                if (FIRST_CHAR[aChar - ' '] != ' ') {
                    ret.append(FIRST_CHAR[aChar - ' ']); // 1 caracter
                }
                if (SECOND_CHAR[aChar - ' '] != ' ') {
                    ret.append(SECOND_CHAR[aChar - ' ']); // 2 caracteres
                }
            }
        }

        return ret.toString();
    }

    public static String retirarCaracteresEspeciais(String s) {

        if (s != null && !s.equals("")) {
            s = s.trim();
            s = s.replace(".", "");
            s = s.replace("º", "");
            s = s.replace("ª", "");
            s = s.replace("º", "");
            s = s.replace("§", "");
            s = s.replace("-", "");
            s = s.replace("/", "");
            s = s.replace(")", "");
            s = s.replace("(", "");
            s = s.replace("[", "");
            s = s.replace("]", "");
            s = s.replace("*", "");
            s = s.replace("+", "");
            s = s.replace("!", "");
            s = s.replace("?", "");
            s = s.replace("%", "");
            s = s.replace("\\'", "");
            s = s.replace("_", "");
            s = s.replace("´", "");
            s = s.replace("`", "");
            s = s.replace("E7", "");
            s = s.replace("E12", "");
            s = s.trim();
            try {
                Long isNumero = Long.parseLong(s);
            } catch (NumberFormatException n) {
                System.out.println("EAN = " + s);
                return null;
            }
            String acentuado = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
            String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";

            char[] tabela = new char[256];

            for (int i = 0; i
                    < tabela.length;
                    ++i) {
                tabela[i] = (char) i;

            }
            for (int i = 0; i
                    < acentuado.length();
                    ++i) {
                tabela[acentuado.charAt(i)] = semAcento.charAt(i);

            }

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i
                    < s.length();
                    ++i) {
                char ch = s.charAt(i);

                if (ch < 256) {
                    sb.append(tabela[ch]);

                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        return null;
    }

    public static long difDatasMilliSeg(Date date1, Date date2) {
        long differenceMilliSeconds = date2.getTime() - date1.getTime();
        if (date1.after(date2)) {
            differenceMilliSeconds = (differenceMilliSeconds * -1);

        }
        return differenceMilliSeconds;
    }

    /*
     * long differenceMilliSeconds = date2.getTime() - date1.getTime(); return
     * differenceMilliSeconds; System.out.println("diferenca em milisegundos: "
     * + differenceMilliSeconds); System.out.println("diferenca em segundos: " +
     * (differenceMilliSeconds / 1000)); System.out.println("diferenca em
     * minutos: " + (differenceMilliSeconds / 1000 / 60));
     * System.out.println("diferenca em horas: " + (differenceMilliSeconds /
     * 1000 / 60 / 60)); System.out.println("diferenca em dias: " +
     * (differenceMilliSeconds / 1000 / 60 / 60 / 24)); }
     */
    public static long difdatasHoras(Date date1, Date date2) {
        long diffhoras = (difDatasMilliSeg(date1, date2) / 1000 / 60 / 60);
        return diffhoras;
    }

    public static long difdatasSeg(Date date1, Date date2) {
        long diffhoras = (difDatasMilliSeg(date1, date2) / 1000);
        return diffhoras;
    }

    public static long difdatasMin(Date date1, Date date2) {
        long diffhoras = (difDatasMilliSeg(date1, date2) / 1000 / 60);
        return diffhoras;
    }

    public static long difdatasDias(Date date1, Date date2) {
        long diffhoras = (difDatasMilliSeg(date1, date2) / 1000 / 60 / 60 / 24);
        return diffhoras;
    }

    public static String retornaNumeros(String texto) {
        texto = texto.replaceAll("\\D*", ""); //To numeric digits only
        return texto;
    }

    public static boolean somenteNumeros(String valor) {

        boolean aprovado = false;

        try {
            Double test1 = Double.parseDouble(valor);
            aprovado = true;
        } catch (Exception e) {
        }

        if (aprovado) {
            return true;
        }

        try {
            Integer test2 = Integer.parseInt(valor);
            aprovado = true;
        } catch (Exception e) {
        }

        if (aprovado) {
            return true;
        }

        try {
            Long test3 = Long.parseLong(valor);
            aprovado = true;
        } catch (Exception e) {
        }

        if (aprovado) {
            return true;
        } else {
            return false;
        }

    }

    public static String primeiraLetraMaiuscula(String palavra) {

        if (palavra != null) {
            int len = palavra.length();
            String out = "";
            out += palavra.substring(0, 1).toUpperCase();
            out += palavra.substring(1, len);
            return out;
        }
        return palavra;
    }

    public static String tirarCaracteresEspeciais(String s) {

        if (s != null && !s.equals("")) {
            s = s.trim();
            s = s.replace(".", "");
            s = s.replace("", "");
            s = s.replace("", "");
            s = s.replace("", "");
            s = s.replace("", "");
            s = s.replace("-", "");
            s = s.replace("/", "");
            s = s.replace(")", "");
            s = s.replace("(", "");
            s = s.trim();

            String acentuado = "çÇáéíóúýÁÉÍÓÚÝàèìòùÀÈÌÒÙãõñäëïöüÿÄËÏÖÜÃÕÑâêîôûÂÊÎÔÛ";
            String semAcento = "cCaeiouyAEIOUYaeiouAEIOUaonaeiouyAEIOUAONaeiouAEIOU";

            char[] tabela = new char[256];

            for (int i = 0; i
                    < tabela.length;
                    ++i) {
                tabela[i] = (char) i;

            }
            for (int i = 0; i
                    < acentuado.length();
                    ++i) {
                tabela[acentuado.charAt(i)] = semAcento.charAt(i);

            }

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i
                    < s.length();
                    ++i) {
                char ch = s.charAt(i);

                if (ch < 256) {
                    sb.append(tabela[ch]);

                } else {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        return null;
    }

    //
    public static String Encriptar(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            String retornaSenha = hash.toString(16);
            return retornaSenha;
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
            return null;
        }

    }

    public static String replicar(String texto, int vezes) {
        String resultado = "";
        for (int i = 0; i < vezes; i++) {
            resultado = resultado + texto;
        }
        return resultado;
    }

    public static String zeroEsquerda(String valor, int quantDigito) {
        String resultado = replicar("0", quantDigito - valor.length());
        resultado = resultado + valor;
        return resultado;
    }

    public static String espacoEsquerda(String valor, int quantDigito) {
        String resultado = replicar(" ", quantDigito - valor.length());
        resultado = resultado + valor;
        return resultado;
    }

    public static String getStringTamanho(String valor, int tamanhoMaximo) {
        if (valor.length() > tamanhoMaximo) {
            valor = valor.substring(0, tamanhoMaximo);
        }

        return valor;
    }

    public static String espacoDireita(String valor, int quantDigito) {
        int tamanho = valor.length();

        for (int i = 0; i < quantDigito - tamanho; i++) {
            valor += " ";
        }
        return valor;
    }

    public static String pontoDireita(int quantDigito) {
        String valor = "";
        for (int i = 0; i < quantDigito; i++) {
            valor += " . ";
        }
        return valor;

    }

//    public static String valorRealPorExtenso(Double valor) {
//        CurrencyWriter cw = CurrencyWriter.getInstance();
//        String extenso = cw.write(new BigDecimal(valor));
//        return extenso;
//    }
    public static String reaisExtenso(String valor) {
        String extenso = ""; //variavel que retornar o valor por extenso
        String tipo = ""; //variavel que definira o tipo de nmero ( unitario, dezena, centena)
        String parte1 = ""; //variavel que armazenar temporariamente o valor que foi analizado
        int cont = valor.length(); //conta quantos nmeros tem o valor
        int i = 0; //variavel de controle do while
        int somar = 1; //variavel que dir o valor a ser somado  varivel "i"

        while (i < cont) {
            somar = 1;
//verifica se o caracter atual  nmero ou uma "," ou "."
            if (valor.substring(i, i + 1).equals(",") == false && valor.substring(i, i + 1).equals(".") == false) {
//o valor passado tem 3 dgitos, ex: R$ 1,20
                if (cont == 4 && i == 0) {
                    tipo = "unitario";
                } else if (cont == 4 && i == 2) {
                    tipo = "dezena";
                } else if (cont == 4 && i == 3) {
                    tipo = "unitario";
                } //o valor passado tem 4 dgitos, ex: R$ 11,20
                else if (cont == 5 && i == 0) {
                    tipo = "dezena";
                } else if (cont == 5 && i == 1) {
                    tipo = "unitario";
                } else if (cont == 5 && i == 3) {
                    tipo = "dezena";
                } else if (cont == 5 && i == 4) {
                    tipo = "unitario";
                } //o valor passado tem 5 dgitos, ex: R$ 111,20
                else if (cont == 6 && i == 0) {
                    tipo = "centena";
                } else if (cont == 6 && i == 1) {
                    tipo = "dezena";
                } else if (cont == 6 && i == 2) {
                    tipo = "unitario";
                } else if (cont == 6 && i == 4) {
                    tipo = "dezena";
                } else if (cont == 6 && i == 5) {
                    tipo = "unitario";
                } //o valor passado tem 6 d?gitos, ex: R$ 1.111,20
                else if (cont == 8 && i == 0) {
                    tipo = "unitario";
                } else if (cont == 8 && i == 2) {
                    tipo = "centena";
                } else if (cont == 8 && i == 3) {
                    tipo = "dezena";
                } else if (cont == 8 && i == 4) {
                    tipo = "unitario";
                } else if (cont == 8 && i == 6) {
                    tipo = "dezena";
                } else if (cont == 8 && i == 7) {
                    tipo = "unitario";
                } //o valor passado tem 7 dgitos, ex: R$ 11.111,20
                else if (cont == 9 && i == 0) {
                    tipo = "dezena";
                } else if (cont == 9 && i == 1) {
                    tipo = "unitario";
                } else if (cont == 9 && i == 3) {
                    tipo = "centena";
                } else if (cont == 9 && i == 4) {
                    tipo = "dezena";
                } else if (cont == 9 && i == 5) {
                    tipo = "unitario";
                } else if (cont == 9 && i == 7) {
                    tipo = "dezena";
                } else if (cont == 9 && i == 8) {
                    tipo = "unitario";
                } //o valor passado tem 8 dgitos, ex: R$ 111.111,20
                else if (cont == 10 && i == 0) {
                    tipo = "centena";
                } else if (cont == 10 && i == 1) {
                    tipo = "dezena";
                } else if (cont == 10 && i == 2) {
                    tipo = "unitario";
                } else if (cont == 10 && i == 4) {
                    tipo = "centena";
                } else if (cont == 10 && i == 5) {
                    tipo = "dezena";
                } else if (cont == 10 && i == 6) {
                    tipo = "unitario";
                } else if (cont == 10 && i == 8) {
                    tipo = "dezena";
                } else if (cont == 10 && i == 9) {
                    tipo = "unitario";
                }

// se o tipo analisado for do tipo unitrio, define a varivel "parte1"
                if (tipo.equals("unitario")) {
                    if (valor.substring(i, i + 1).equals("1")) {
                        parte1 = "Um ";
                    } else if (valor.substring(i, i + 1).equals("2")) {
                        parte1 = "Dois ";
                    } else if (valor.substring(i, i + 1).equals("3")) {
                        parte1 = "Tr?s ";
                    } else if (valor.substring(i, i + 1).equals("4")) {
                        parte1 = "Quatro ";
                    } else if (valor.substring(i, i + 1).equals("5")) {
                        parte1 = "Cinco ";
                    } else if (valor.substring(i, i + 1).equals("6")) {
                        parte1 = "Seis ";
                    } else if (valor.substring(i, i + 1).equals("7")) {
                        parte1 = "Sete ";
                    } else if (valor.substring(i, i + 1).equals("8")) {
                        parte1 = "Oito ";
                    } else if (valor.substring(i, i + 1).equals("9")) {
                        parte1 = "Nove ";
                    } else if (valor.substring(i, i + 1).equals("0")) {
                        parte1 = "";
                    }
                }
// se o tipo analisado for do tipo dezena, define a varivel "parte1"
                if (tipo.equals("dezena")) {
                    if (valor.substring(i, i + 1).equals("1")) {
//se o caracter comea com "1"  avaliado tb o caracter seguinte
                        if (valor.substring(i, i + 2).equals("10")) {
                            parte1 = "Dez ";
                        } else if (valor.substring(i, i + 2).equals("11")) {
                            parte1 = "Onze ";
                        } else if (valor.substring(i, i + 2).equals("12")) {
                            parte1 = "Doze ";
                        } else if (valor.substring(i, i + 2).equals("13")) {
                            parte1 = "Treze ";
                        } else if (valor.substring(i, i + 2).equals("14")) {
                            parte1 = "Quatorze ";
                        } else if (valor.substring(i, i + 2).equals("15")) {
                            parte1 = "Quinze ";
                        } else if (valor.substring(i, i + 2).equals("16")) {
                            parte1 = "Dezesseis ";
                        } else if (valor.substring(i, i + 2).equals("17")) {
                            parte1 = "Dezesete ";
                        } else if (valor.substring(i, i + 2).equals("18")) {
                            parte1 = "Dezoito ";
                        } else if (valor.substring(i, i + 2).equals("19")) {
                            parte1 = "Dezenove ";
                        }
                        somar = 2; //como foi analisado duas casas do valor, a varivel "i" ser acrescentada em 2
                    } //se o caracter no comea com "1", j  definido a varivel parte1
                    else if (valor.substring(i, i + 1).equals("2")) {
                        parte1 = "Vinte ";
                    } else if (valor.substring(i, i + 1).equals("3")) {
                        parte1 = "Trinta ";
                    } else if (valor.substring(i, i + 1).equals("4")) {
                        parte1 = "Quarenta ";
                    } else if (valor.substring(i, i + 1).equals("5")) {
                        parte1 = "Cinquenta ";
                    } else if (valor.substring(i, i + 1).equals("6")) {
                        parte1 = "Sessenta ";
                    } else if (valor.substring(i, i + 1).equals("7")) {
                        parte1 = "Setenta ";
                    } else if (valor.substring(i, i + 1).equals("8")) {
                        parte1 = "Oitenta ";
                    } else if (valor.substring(i, i + 1).equals("9")) {
                        parte1 = "Noventa ";
                    } else if (valor.substring(i, i + 1).equals("0")) {
                        parte1 = "";
                    }
                }
// se o tipo analisado for do tipo centena, define a varivel "parte1"
                if (tipo.equals("centena")) {
                    if (valor.substring(i, i + 1).equals("1")) {
//se o caracter comea com "1", avalia os proximos dois caracteres, para
//definir se  "Cem" ou "Cento"
                        if (valor.substring(i, i + 3).equals("100")) {
                            parte1 = "Cem ";
                        } else {
                            parte1 = "Cento ";
                        }
                    } else if (valor.substring(i, i + 1).equals("2")) {
                        parte1 = "Duzentos ";
                    } else if (valor.substring(i, i + 1).equals("3")) {
                        parte1 = "Trezentos ";
                    } else if (valor.substring(i, i + 1).equals("4")) {
                        parte1 = "Quatrocentos ";
                    } else if (valor.substring(i, i + 1).equals("5")) {
                        parte1 = "Quinhentos ";
                    } else if (valor.substring(i, i + 1).equals("6")) {
                        parte1 = "Seiscentos ";
                    } else if (valor.substring(i, i + 1).equals("7")) {
                        parte1 = "Setecentos ";
                    } else if (valor.substring(i, i + 1).equals("8")) {
                        parte1 = "Oitocentos ";
                    } else if (valor.substring(i, i + 1).equals("9")) {
                        parte1 = "Novecentos ";
                    } else if (valor.substring(i, i + 1).equals("0")) {
                        parte1 = "";
                    }
                }
//se o caracter  igual a "0" ou "," no  adicionado a palavra "e "
                if (i == 0 || valor.substring(i - 1, i) == ",") {
                    extenso = extenso + parte1;
                } else if (valor.substring(i, i + 1).equals("0") == false) { //se o caracter  igual  "0"
                    if (extenso.equals("")) { //verifica se a varivel extenso  nula
                        extenso = extenso + parte1;
                    } else if (extenso.substring(extenso.length() - 4).equals("Mil ")) { //verifica se a varivel extenso  nula
                        extenso = extenso + parte1;
                    } else {
                        extenso = extenso + "e " + parte1;
                    }
                }

            } //verifica se o caracter atual  ","
            else if (valor.substring(i, i + 1).equals(",")) {
                if (cont == 4 && Integer.parseInt(valor.substring(i - 1, i)) == 1) {
//se o valor tem 3 dgitos e comea com "1", adiciona a palavra Real
                    extenso = extenso + "Real ";
                } else if (cont > 4 || Integer.parseInt(valor.substring(i - 1, i)) > 1) {
//se o valor tem mais 3 dgitos ou no comea com "1", adiciona a palavra Reais
                    extenso = extenso + "Reais ";
                }

            } //verifica se o caracter atual  "."
            else if (valor.substring(i, i + 1).equals(".")) {
                extenso = extenso + "Mil "; //se sim, acrescenta a palavra "Mil"
            }

            i = i + somar;

        }
//verifica se as duas casas decimais  maior que 1
        if (Integer.parseInt(valor.substring(cont - 2, cont)) > 1) {
            extenso = extenso + "Centavos"; //se sim, acrescenta a palavra Centavos
        } else if (Integer.parseInt(valor.substring(cont - 2, cont)) == 1) {
            extenso = extenso + "Centavo"; //se no, acrescenta a palavra Centavo
        }

        return extenso;
    }

    public static String valorPorExtenso(double vlr) {
        if (vlr == 0) {
            return ("zero");
        }

        long inteiro = (long) Math.abs(vlr); // parte inteira do valor
        double resto = vlr - inteiro;       // parte fracionária do valor

        String vlrS = String.valueOf(inteiro);
        if (vlrS.length() > 15) {
            return ("Erro: valor superior a 999 trilhões.");
        }

        String s = "", saux, vlrP;
        String centavos = String.valueOf((int) Math.round(resto * 100));

        String[] unidade = {"", "um", "dois", "três", "quatro", "cinco",
            "seis", "sete", "oito", "nove", "dez", "onze",
            "doze", "treze", "quatorze", "quinze", "dezesseis",
            "dezessete", "dezoito", "dezenove"};
        String[] centena = {"", "cento", "duzentos", "trezentos",
            "quatrocentos", "quinhentos", "seiscentos",
            "setecentos", "oitocentos", "novecentos"};
        String[] dezena = {"", "", "vinte", "trinta", "quarenta", "cinquenta",
            "sessenta", "setenta", "oitenta", "noventa"};
        String[] qualificaS = {"", "mil", "milhão", "bilhão", "trilhão"};
        String[] qualificaP = {"", "mil", "milhões", "bilhões", "trilhões"};

// definindo o extenso da parte inteira do valor
        int n, unid, dez, cent, tam, i = 0;
        boolean umReal = false, tem = false;
        while (!vlrS.equals("0")) {
            tam = vlrS.length();
// retira do valor a 1a. parte, 2a. parte, por exemplo, para 123456789:
// 1a. parte = 789 (centena)
// 2a. parte = 456 (mil)
// 3a. parte = 123 (milhões)
            if (tam > 3) {
                vlrP = vlrS.substring(tam - 3, tam);
                vlrS = vlrS.substring(0, tam - 3);
            } else { // última parte do valor
                vlrP = vlrS;
                vlrS = "0";
            }
            if (!vlrP.equals("000")) {
                saux = "";
                if (vlrP.equals("100")) {
                    saux = "cem";
                } else {
                    n = Integer.parseInt(vlrP, 10);  // para n = 371, tem-se:
                    cent = n / 100;                  // cent = 3 (centena trezentos)
                    dez = (n % 100) / 10;            // dez  = 7 (dezena setenta)
                    unid = (n % 100) % 10;           // unid = 1 (unidade um)
                    if (cent != 0) {
                        saux = centena[cent];
                    }
                    if ((n % 100) <= 19) {
                        if (saux.length() != 0) {
                            saux = saux + " e " + unidade[n % 100];
                        } else {
                            saux = unidade[n % 100];
                        }
                    } else {
                        if (saux.length() != 0) {
                            saux = saux + " e " + dezena[dez];
                        } else {
                            saux = dezena[dez];
                        }
                        if (unid != 0) {
                            if (saux.length() != 0) {
                                saux = saux + " e " + unidade[unid];
                            } else {
                                saux = unidade[unid];
                            }
                        }
                    }
                }
                if (vlrP.equals("1") || vlrP.equals("001")) {
                    if (i == 0) // 1a. parte do valor (um real)
                    {
                        umReal = true;
                    } else {
                        saux = saux + " " + qualificaS[i];
                    }
                } else if (i != 0) {
                    saux = saux + " " + qualificaP[i];
                }
                if (s.length() != 0) {
                    s = saux + ", " + s;
                } else {
                    s = saux;
                }
            }
            if (((i == 0) || (i == 1)) && s.length() != 0) {
                tem = true; // tem centena ou mil no valor
            }
            i = i + 1; // próximo qualificador: 1- mil, 2- milhão, 3- bilhão, ...
        }

        if (s.length() != 0) {
            if (umReal) {
                s = s + " real";
            } else if (tem) {
                s = s + " reais";
            } else {
                s = s + " de reais";
            }
        }

// definindo o extenso dos centavos do valor
        if (!centavos.equals("0")) { // valor com centavos
            if (s.length() != 0) // se não é valor somente com centavos
            {
                s = s + " e ";
            }
            if (centavos.equals("1")) {
                s = s + "um centavo";
            } else {
                n = Integer.parseInt(centavos, 10);
                if (n <= 19) {
                    s = s + unidade[n];
                } else {             // para n = 37, tem-se:
                    unid = n % 10;   // unid = 37 % 10 = 7 (unidade sete)
                    dez = n / 10;    // dez  = 37 / 10 = 3 (dezena trinta)
                    s = s + dezena[dez];
                    if (unid != 0) {
                        s = s + " e " + unidade[unid];
                    }
                }
                s = s + " centavos";
            }
        }
        return (s);
    }

    public static void zeraHora(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 0);

        calendar.set(Calendar.MINUTE, 0);

        calendar.set(Calendar.SECOND, 0);

        date.setTime(calendar.getTime().getTime());
    }

    public static void preencheHora(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.HOUR_OF_DAY, 23);

        calendar.set(Calendar.MINUTE, 59);

        calendar.set(Calendar.SECOND, 59);

        date.setTime(calendar.getTime().getTime());
    }

    public static void zeraDate(Date date) {
        Date result = new Date(0, 0, 0, date.getHours(), date.getMinutes(), date.getSeconds());
        date.setTime(result.getTime());
    }

    public static Date StringToDate(String ddmmaaaa) {
        if (!ddmmaaaa.isEmpty()) {
            ddmmaaaa = Utils.retornaNumeros(ddmmaaaa);
            Integer ano = Integer.parseInt(ddmmaaaa.substring(4)) - 1900;
            Integer mes = Integer.parseInt(ddmmaaaa.substring(2, 3)) - 1;
            Integer dia = Integer.parseInt(ddmmaaaa.substring(0, 1));

            return new Date(ano, mes, dia);
        }
        return null;
    }

    public static Double StringToDouble(String valor, String dec, String separador) {
        Double val = 0.0;
        if (!valor.isEmpty()) {
            Integer d = 0;
            if (dec.equalsIgnoreCase("A")) {
                if (valor.contains(separador)) {
                    d = (valor.length() - valor.indexOf(separador)) - 1;
                }
            } else {
                d = Integer.parseInt(dec);
            }
            Double div = Double.parseDouble("1" + Utils.replicar("0", d));
            val = Double.parseDouble(Utils.retornaNumeros(valor)) / div;
        }
        return val;
    }

    public static Double StringToDouble(String valor, String dec) {
        return StringToDouble(valor, dec, ",");
    }

    public static Double StringToDouble(String valor) {
        return StringToDouble(valor, "A", ",");
    }

    public static Date getDatePrimeiroDiaDoMes() {
        Calendar primeiroDiaMes = Calendar.getInstance();
        primeiroDiaMes.set(Calendar.DAY_OF_MONTH, 1);

        return primeiroDiaMes.getTime();
    }

    public static Date getDateUltimoDiaDoMes() {

        Calendar ultimoDiaMes = Calendar.getInstance();
        ultimoDiaMes.add(Calendar.MONTH, 1);
        ultimoDiaMes.set(Calendar.DAY_OF_MONTH, 1);
        ultimoDiaMes.add(Calendar.DAY_OF_MONTH, -1);

        return ultimoDiaMes.getTime();
    }

    public static Date getDateUltimoDiaDoMes(Date dataReferencia) {

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(dataReferencia);

        int dia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int mes = (cal.get(Calendar.MONDAY) + 1);
        int ano = cal.get(Calendar.YEAR);

        try {
            Date data = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes + "/" + ano);
            return data;
        } catch (ParseException e) {
            return null;
        }

    }

    public static Date getDatePrimeiroDiaDoMes(Date dataReferencia) {

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(dataReferencia);

        int dia = 1;
        int mes = (cal.get(Calendar.MONDAY) + 1);
        int ano = cal.get(Calendar.YEAR);

        try {
            Date data = (new SimpleDateFormat("dd/MM/yyyy")).parse(dia + "/" + mes + "/" + ano);
            return data;
        } catch (ParseException e) {
            return null;
        }

    }

    public static String formatarMoeda(Double vlr) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("###,###,##0.00");
        return df.format(vlr);
    }

    public static String maiuscula(String entrada) {
        entrada = entrada.toLowerCase();
        entrada = entrada.trim();

        String posicao;
        String mais = "";
        String tudo;
        String maiuscula2;

        posicao = "" + entrada.charAt(0);//pega a primeira letra que sera maiuscula
        String pos = posicao.toUpperCase();    //transforma em maiuscula

        for (int i = 1; i < entrada.length(); i++) {  //coloca o for de acordo com o tamanho
            mais = mais + entrada.charAt(i); //acrescenta as letras

            if (entrada.charAt(i) == ' ') {  //se houver um espao a prxima vai ser transformada em maisucula.
                String maiuscula = "" + entrada.charAt(i + 1);//pega a prxima apos o espao
                maiuscula2 = maiuscula.toUpperCase();       //transforma em maiuscula.
                mais = mais + maiuscula2;//acrescenta a maiuscula a palavra completa, que se chama mais
                i = i + 1; //soma um, pois uma letra minuscula foi substituida e ja foi acrescentada
            }
        }
        tudo = pos + mais;
        return tudo;
    }
//
//    public static String montarEnderecoLinha1(Empresa emp) {
//
//        Endereco e = emp.getPessoa().getEnderecoUnico();
//
//        String dados1Empresa = "";
//        if (e.getNmLogradouro() != null && !e.getNmLogradouro().equals("")) {
//            dados1Empresa += e.getNmLogradouro();
//        }
//        if (e.getNumero() != null && !e.getNumero().equals("")) {
//            dados1Empresa += ", " + e.getNumero();
//        }
//        if (e.getComplemento() != null && !e.getComplemento().equals("")) {
//            dados1Empresa += ", " + e.getComplemento();
//        }
//
//        return dados1Empresa;
//    }
//
//    public static String montarEnderecoLinha2(Empresa emp) {
//        Endereco e = emp.getPessoa().getEnderecoUnico();
//        String dados2Empresa = "";
//        if (e.getCidade() != null) {
//            dados2Empresa += " CIDADE: " + e.getCidade().getxMun().toUpperCase() + " / " + e.getCidade().getcUF().getSigla();
//        }
//        if (e.getNmBairro() != null && !e.getNmBairro().equals("")) {
//            dados2Empresa += " BAIRRO: " + e.getNmBairro().toUpperCase();
//        }
//        if (e.getCep() != null && !e.getCep().equals("")) {
//            dados2Empresa += " CEP: " + e.getCep();
//        }
//        if (emp.getTelefoneEmpresa() != null && !emp.getTelefoneEmpresa().equals("")) {
//            dados2Empresa += " FONE : " + emp.getTelefoneEmpresa();
//        }
//
//        return dados2Empresa;
//
//    }

    public static String getExtensaoByFileName(String fileName) {
        String extensao = "";

        for (StringTokenizer stringTokenizer = new StringTokenizer(fileName, "."); stringTokenizer.hasMoreTokens();) {
            extensao = stringTokenizer.nextToken();
        }

        return extensao;
    }

    public static List<Integer> gerarProxCincoAnos() {
        Integer date = new Date().getYear() + 1900;
        List<Integer> listaDate = new ArrayList<>();
        for (int i = date; i <= date + 5; i++) {
            listaDate.add(i);
        }
        return listaDate;
    }

    public static List<Integer> gerarAntDepoisCincoAnos() {
        Integer date = new Date().getYear() + 1900;
        date = date - 5;
        List<Integer> listaDate = new ArrayList<>();
        for (int i = date; i <= date + 10; i++) {
            listaDate.add(i);
        }
        return listaDate;
    }

    public final static String getHDSerial() throws IOException {
        String os = System.getProperty("os.name");
        try {
            if (os.startsWith("Windows")) {
                return getHDSerialWindows("C");
                // } else if(os.startsWith("Linux")) {
                //    return getHDSerialLinux("D");
            } else {
                return getHDSerialLinux("/");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        }
    }

    public static String getHDSerialWindows(String drive) throws IOException, InterruptedException {
        String sc = "cmd /c" + "wmic diskdrive get serialnumber";

        Process p = Runtime.getRuntime().exec(sc);
        p.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        return (sb.substring(sb.toString().lastIndexOf("r") + 1).trim());
    }

    public static String getHDSerialLinux(String drive) throws IOException, InterruptedException {
        String sc = "/sbin/udevadm info --query=property --name=sda"; // get HDD parameters as non root user
        String[] scargs = {"/bin/sh", "-c", sc};

        Process p = Runtime.getRuntime().exec(scargs);
        p.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.indexOf("ID_SERIAL_SHORT") != -1) { // look for ID_SERIAL_SHORT or ID_SERIAL
                sb.append(line);
            }
        }

        return (sb.toString().substring(sb.toString().indexOf("=") + 1));
    }

    public static String doubleToString(Double value, int casas) {
        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMaximumFractionDigits(casas);
        nf.setMinimumFractionDigits(casas);

        return nf.format(value);

    }

    public static String doubleToString(Double value) {
        return doubleToString(value, 2);
    }

    public static String formatStringDoubleToString(String qCom, int minDigFracao, int maxDigFracao) {
        Double value = Double.parseDouble(qCom);
        BigDecimal bd;

        NumberFormat nf = NumberFormat.getInstance(new Locale("pt", "BR"));
        nf.setMaximumFractionDigits(maxDigFracao);
        nf.setMinimumFractionDigits(minDigFracao);

        return nf.format(value);

    }

    public static Date primeiroDiadoMes() {
        Calendar dataAtual = Calendar.getInstance();
        Calendar primeiroDia = Calendar.getInstance();

        //1º dia do mês atual
        primeiroDia.add(Calendar.DAY_OF_MONTH, -dataAtual.get(Calendar.DAY_OF_MONTH));
        primeiroDia.add(Calendar.DAY_OF_YEAR, 1);
        return primeiroDia.getTime();
    }

    public static void deleteFiles(File inFile) {
        if (inFile.isFile()) {
            inFile.delete();
        } else {
            File files[] = inFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteFiles(files[i]);
            }
            inFile.delete();
        }
    }

    public static String createZip(List<File> files, String diretorio, String nmArquivo, String barra) throws IOException {

        //*************************** Create Folder ************************
        // pasta para recepcionar a criação do objeto.zip em função do Download
        File caminho = new File(diretorio + barra + "ITENS_ZIP");

        if (!caminho.exists()) {
            caminho.mkdirs();
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zipfile = new ZipOutputStream(new FileOutputStream(caminho + barra + nmArquivo));

        ZipEntry zipentry = null;
        for (File file : files) {
            boolean zipFolder = false;
            if (!file.isFile()) {
                //********************** create zip Folder *********************
                File nameZip = new File(caminho + file.getPath().replace(diretorio, "") + ".zip");
                ZipOutputStream zipfile2 = new ZipOutputStream(new FileOutputStream(nameZip));
                File[] directory = file.listFiles();
                for (File itemDir : directory) {
                    zipentry = new ZipEntry(itemDir.getName());
                    zipfile2.putNextEntry(zipentry);
                    zipfile2.write(fileToByte(itemDir));
                }
                zipfile2.finish();
                zipfile2.close();

                //********************** Add no ZIP geral **********************
                if (nameZip.exists() && nameZip.isFile()) {
                    file = nameZip;
                }
                zipFolder = true;
            }
            zipentry = new ZipEntry(file.getName());
            zipfile.putNextEntry(zipentry);
            zipfile.write(fileToByte(file));

            //************************* Deletar FOLDER.ZIP **********************
            if (zipFolder) {
                file.delete();
            }
        }
        zipfile.finish();
        zipfile.close();

        bos.toByteArray();
        return caminho.getPath() + barra;
    }

    public static void unzip(File zipFile, File dir) throws IOException {
        ZipFile zip = null;
        File arquivo = null;
        InputStream is = null;
        OutputStream os = null;
        byte[] buffer = new byte[1024];

        try {
            // cria diretório informado, caso não exista
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (!dir.exists() || !dir.isDirectory()) {
                throw new IOException("O diretório " + dir.getName()
                        + " não é um diretório válido");
            }

            zip = new ZipFile(zipFile);
            Enumeration e = zip.entries();
            while (e.hasMoreElements()) {
                ZipEntry entrada = (ZipEntry) e.nextElement();
                arquivo = new File(dir, entrada.getName());

                // se for diretório inexistente, cria a estrutura e pula 
                // pra próxima entrada
                if (entrada.isDirectory() && !arquivo.isFile()) {
                    arquivo.mkdirs();
                    continue;
                }

                // se a estrutura de diretórios não existe, cria
                if (!arquivo.getParentFile().exists()) {
                    arquivo.getParentFile().mkdirs();
                }

                try {
                    // lê o arquivo do zip e grava em disco
                    is = zip.getInputStream(entrada);

                    os = new FileOutputStream(arquivo);
                    int bytesLidos = 0;
                    if (is == null) {
                        throw new ZipException("Erro ao ler a entrada do zip: "
                                + entrada.getName());
                    }
                    while ((bytesLidos = is.read(buffer)) > 0) {
                        os.write(buffer, 0, bytesLidos);
                    }
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception ex) {
                        }
                    }

                    if (os != null) {
                        try {
                            os.close();
                        } catch (Exception ex) {
                        }
                    }
                }
            }

        } finally {
            if (zip != null) {
                try {
                    zip.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static List<String> getLeituraFile(File arquivo) throws Exception {
        List<String> result = new ArrayList();
        BufferedReader buffRead = new BufferedReader(new FileReader(arquivo));
        boolean resp = false;
        while (!resp) {
            String linha = buffRead.readLine();
            if (linha == null || linha.isEmpty()) {
                resp = true;
            } else {
                result.add(linha);
            }
        }
        buffRead.close();
        return result;
    }

    public static String percentFormat(Double valor) {
        return new DecimalFormat("#,##0.##' %'").format(valor);
    }

    public static String currencyFormat(Double valor) {
        return new DecimalFormat("'R$ '#,##0.00").format(valor);
    }

    public static String doubleFormat(Double valor) {
        return new DecimalFormat("#,##0.00").format(valor);
    }

    public static boolean primeiraLinha(JTable tabela) {
        //Se o numero de linhas da tabela for 0
        if (tabela.getRowCount() > 0) {

            JViewport view = (JViewport) tabela.getParent();

            JScrollPane scrollpane = (JScrollPane) view.getParent();

            tabela.requestFocus();

            //Seta a value da barra de rolagem para 1
            scrollpane.getVerticalScrollBar().setValue(1);
            //seleciona a primeira linha
            tabela.setRowSelectionInterval(0, 0);
            //habilita e desablita dos respectivos botões
            return true;
        }

        return false;
    }

    public static boolean linhaAnterior(JTable tabela) {
        int selecionada = tabela.getSelectedRow();
        //Verifica se a tabela possui mais de 0 linhas
        if (tabela.getRowCount() > 0) {

            JViewport view = (JViewport) tabela.getParent();

            JScrollPane scrollpane = (JScrollPane) view.getParent();

            tabela.requestFocus();

            //se a selecionada for diferente de -1  e 0
            if (selecionada != -1 && selecionada != 0) {
                //Seleciona a linha anterior
                tabela.setRowSelectionInterval(selecionada - 1, selecionada - 1);
                //Seta a value da barra de rolagem para acompanhar a linha selecionada
                scrollpane.getVerticalScrollBar().setValue(tabela.getRowHeight() * tabela.getSelectedRow());

                return true;

                //Se a linha selecionada for igual a zero
            } else if (selecionada == 0) {
                //seta as linhas e a os botoes
                tabela.setRowSelectionInterval(0, 0);

                return false;
            }
        }

        return false;
    }

    public static boolean proximaLinha(JTable tabela) {

        int selecionada = tabela.getSelectedRow();
        //Se o numeros de linhas da tabela for maior que zero
        if (tabela.getRowCount() > 0) {

            JViewport view = (JViewport) tabela.getParent();

            JScrollPane jscrollpane = (JScrollPane) view.getParent();

            tabela.requestFocus();

            //Se a linha selecionada for diferente de -1 e do total de linhas - 1
            if (selecionada != -1 && selecionada != tabela.getRowCount() - 1) {
                //Selecionada a proxima linha
                tabela.setRowSelectionInterval(selecionada + 1, selecionada + 1);
                //Seta a value da barra de rolagem para que acompanhe a linha selecionada
                jscrollpane.getVerticalScrollBar().setValue(tabela.getRowHeight() * tabela.getSelectedRow());

                return true;

                //Se a linha selecionada tiver o mesmo valor do total de linhas da tabela -1
            } else if (selecionada == tabela.getRowCount() - 1) {

                //selecionada a ultima linha
                tabela.setRowSelectionInterval(tabela.getRowCount() - 1, tabela.getRowCount() - 1);

                return false;
            }
        }

        return false;

    }

    public static boolean UltimaLinha(JTable tabela) {

        //Se o total de linhas da tabela for maior que 0
        if (tabela.getRowCount() > 0) {

            JViewport view = (JViewport) tabela.getParent();

            JScrollPane scrollpane = (JScrollPane) view.getParent();

            tabela.requestFocus();

            //Seleciona a ultima linha
            tabela.setRowSelectionInterval(tabela.getRowCount() - 1, tabela.getRowCount() - 1);
            //Seta a value da barra de rolagem para acompanhar a linha selecionada
            scrollpane.getVerticalScrollBar().setValue(scrollpane.getVerticalScrollBar().getMaximum());

            return true;

        }

        return false;

    }

    public static boolean DayOfTodayEqualsFinalDate() {
        boolean result = false;
        Calendar cal = Calendar.getInstance();
        if (cal.getTime().getDate() == cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            result = true;
        }

        return result;
    }

    static List<Integer> getBytesInDecimal(byte[] arrayBytes) {

        List<Integer> result = new ArrayList<>(arrayBytes.length);
        for (byte b : arrayBytes) {
            result.add(b & 0xff);
        }
        return result;
    }
}

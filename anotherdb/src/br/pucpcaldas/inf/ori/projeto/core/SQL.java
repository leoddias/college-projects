package br.pucpcaldas.inf.ori.projeto.core;

import java.io.IOException;
import javax.swing.JOptionPane;

public class SQL {

    boolean queryValida = false;
    boolean queryExecutada = false;
    String queryFormatada = "";
    Process p;

    public SQL() {

    }

    public String ExecutaSQL(String q) throws InterruptedException, IOException {
        Hora now = new Hora();
        String retorno = now.getHora();
        q.toUpperCase();
        String[] query = q.split(" ");
        if (query[0].equals("SELECT") && Session.canSelect) {
            if (query[1].equals("*")) {
                if (query[2].equals("FROM")) {
                    if (query.length == 4) {
                        queryFormatada = "SELECT . FROM " + Session.dirTabelas + "\\" + query[3] + ".table";
                        queryValida = true;
                        System.out.println(queryFormatada);
                    } else {
                        if (Session.canSelect) {
                            queryFormatada = "SELECT . FROM " + Session.dirTabelas + "\\" + query[3] + ".table " + query[4] + " " + query[5];
                            queryValida = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Você não possui permissão para executar esta QUERY!", "Erro de Permissão!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    System.out.println(queryFormatada);
                }
            }
        }

        if (query[0].equals("INSERT") && Session.canInsert) {
            System.out.println("Insert ok");
            if (query[1].equals("INTO")) {
                if (query[3].equals("VALUES")) {
                    queryFormatada = "INSERT INTO " + Session.dirTabelas + "\\" + query[2] + "";
                    for (int i = 3; i < query.length; i++) {
                        queryFormatada = queryFormatada + " " + query[i];
                    }
                    if (Session.canInsert) {
                        queryValida = true;
                        System.out.println(queryFormatada);
                    } else {
                        JOptionPane.showMessageDialog(null, "Você não possui permissão para executar esta QUERY!", "Erro de Permissão!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        if (query[0].equals("CREATE") && Session.canCreate) {
            if (query[1].equals("DATABASE")) {
                switch (Session.createDatabase(query[2]))  {

                    case 1:
                        JOptionPane.showMessageDialog(null, "Base de dados criada com sucesso!");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Erro ao criar base de dados");
                        break;

                }
                return null;
            } else {
                queryFormatada = "CREATE " + Session.dirTabelas + "\\" + query[1] + "";
                for (int i = 2; i < query.length; i++) {
                    queryFormatada += " " + query[i];
                }
                System.out.println("Query create formatada: "+queryFormatada);
                if (Session.canCreate) {
                    queryValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Você não possui permissão para executar esta QUERY!", "Erro de Permissão!", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

        if (query[0].equals("USER")) {
            if(query[1].equals("CREATE"))
            {
                queryFormatada=("USER CREATE "+query[2]+query[3]+query[4]+query[5]+query[6]+query[7]+query[8]);
                queryValida=true;
            }
        }

        if (query[0].equals("DELETE") && Session.canDelete) {
            if (query[1].equals("FROM")) {
                if (query[3].equals("WHERE")) {
                    queryFormatada = "DELETE FROM " + Session.dirTabelas + "\\" + query[2] + " WHERE " + query[4];
                    if (Session.canDelete) {
                        queryValida = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Você não possui permissão para executar esta QUERY!", "Erro de Permissão!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        
        if (query[0].equals("UPDATE") && Session.canDelete) {
            if (query[1].equals("FROM")) {
                if (query[3].equals("WHERE")) {
                    queryFormatada = "DELETE FROM " + Session.dirTabelas + "\\" + query[2] + " WHERE " + query[4];
                    if (Session.canDelete) {
                        queryValida = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Você não possui permissão para executar esta QUERY!", "Erro de Permissão!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        if (queryValida) {
            //CODIGO PARA CHAMAR O RUNTIME E DEFINIR OS RETORNOS, SE EXECUTADA COM SUCESSO QUERYEXECUTADA = TRUE
            p = Runtime.getRuntime().exec(queryFormatada);
            System.out.println("query formatada: " + queryFormatada);

            Thread.sleep(1000);

            switch (p.exitValue()) {
                case 0:
                    retorno = retorno + " Query invalida ! 0";
                    break;

                case 1:
                    System.out.println("Deu certo!");
                    retorno = retorno + " Sucesso! -> " + q + " [" + Session.userName + "]";
                    queryExecutada = true;
                    break;

                case 2:
                    retorno = retorno + " Query invalida! 2";
                    break;

                case -1:
                    retorno = retorno + " Query invalida! -1";
                    break;

            }
        } 
        return retorno;
    }

}

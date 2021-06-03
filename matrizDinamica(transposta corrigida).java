package listaSingular;

import java.util.Arrays;
import java.util.Random;

public class matrizDinamica {

    protected  int tamanho=1000;
    protected  Elo geral[]= new Elo [tamanho] ;





    protected class Elo
    {
        protected int col;
        protected int dado;
        protected Elo prox;

        public Elo()
        {
            prox = null;
        }

        public Elo(int elem,int elem2 )
        {
            col= elem2 ;
            dado = elem;
            prox = null;
        }

        public Elo(int elem,int elem2, Elo proxElem)
        {
            col= elem2 ;
            dado = elem;
            prox = proxElem;
        }
    }



  



    public void insere(int linha, int coluna, int elemento){

        Elo p, q;
        Elo ant = null;

        q = new Elo(elemento, coluna);

        for (p = geral[linha]; ((p != null) && (p.col <coluna)); p = p.prox)
            ant = p;

        if (ant == null)
            geral[linha] = q;
        else
            ant.prox = q;

        q.prox = p;

    }


    public boolean vazia() {

        for (int i = 0; i < tamanho; i++) {
            if (geral[i] != null){return  false;}
        }
        return true;
    }


    public boolean remove(int ele) {
        for (int i = 0; i < tamanho; i++) {

            //
           Elo p;
           Elo ant = null; /* Referência para anterior. */

            for(p = geral[i]; ((p != null) && (p.dado != ele)); p = p.prox)
            {ant = p;}

            /* Se p é null, então não encontrou elemento. */
            if (p == null)
            { continue;}

            if (p == geral[i])
                {geral[i] = geral[i].prox;} /* Remove elemento do início. */
            else
                {ant.prox = p.prox;}  /* Remove elemento do meio. */

            /* Remove a última referência para o elo a ser removido. Dessa forma,
             * o Garbage Collector irá liberar essa memória. */
            p = null;

            return true;
            //


        }
        return false;
    }

    public boolean busca(int ele) {
        for (int i = 0; i < tamanho; i++) {
            Elo p;

            for(p = geral[i]; p != null; p = p.prox)
            {
                if(p.dado == ele)
                    return true;
            }

        }
        return false;
    }


    public boolean diagonal() {

        for (int i = 0; i < tamanho; i++) {
            if (geral[i].prox == null) {
                if (geral[i].col != i) {
                    return false;
                }
            }
            else {return false;}
        }
        return true;
    }

    public boolean linha() {
        int count=0;
        for (int i = 0; i < tamanho; i++) {
            if (geral[i] == null){count++;}
        }
        if(count==tamanho-1){return true;}
        return false;
    }


    public boolean coluna() {

        int priLin=geral[0].col;

        for (int i = 0; i < tamanho; i++) {
            if (geral[i].prox == null) {
                if (geral[i].col != priLin) {
                    return false;
                }
            }
            else {return false;}
        }
        return true;
    }

    public boolean triangInf() {
      //////
        for (int i = 0; i < tamanho; i++) {
            Elo p;
            int j=-1;
            for(p = geral[i]; p != null ; p = p.prox)
            {
               j++;
            }
            if(j!=i||p.col!=i){return  false;}
        }
        return true;
    }

    public boolean triangSup() {
        //////
        for (int i = 0; i < tamanho; i++) {
            Elo p;
            int j=-1;
            for(p = geral[i]; p != null ; p = p.prox)
            {
                j++;
            }
            if(j!=tamanho-i||p.col!=tamanho-i){return  false;}
        }
        return true;
    }



    public void imprime() {
        System.out.println("Matriz Dinamica:   ");

        for (int i = 0; i < tamanho; i++) {

            Elo p;
            int j = 0;
        for (p = geral[i]; p != null; p = p.prox) {
            for (; j < tamanho; j++) {
             if(p.col!=j){
                 System.out.print("x");
                  }
             else{
                 System.out.print(p.dado);
                 j=p.col+1;
                 break;

             }
            }

        }
           if(j<tamanho){
               for (; j < tamanho; j++) { System.out.print("x"); }}
            System.out.println("");
        }
    }


    public matrizDinamica transposta() {
        matrizDinamica  transposta=new matrizDinamica();
        for (int i = 0; i < tamanho; i++) {

            Elo p;
            for (p = geral[i]; p != null; p = p.prox) {

                      transposta.insere(p.col,i,p.dado);


            }


        }
        return transposta;
    }


    public boolean Simetria() {
        matrizDinamica  Simetrica=this.transposta();
        for (int i = 0; i < tamanho; i++) {
            Elo p;
            Elo L;
            L= Simetrica.geral[i];
            for (p = geral[i],L= Simetrica.geral[i]; p != null && L!= null; p = p.prox,  L = L.prox) {
                for (int j = 0; j < tamanho; j++) {
                    if(p.col!=L.col||p.dado!=L.dado){return false;

                    }


                }

            }
        }
        return true;
    }

    public matrizDinamica Soma(matrizDinamica  somante) {
        matrizDinamica  soma=new matrizDinamica();
        for (int i = 0; i < tamanho; i++) {
            Elo p;
            Elo L;


                for (p = geral[i], L = somante.geral[i]; p != null && L != null; ) {
                    if (p.col == L.col) {
                        soma.insere(i, p.col, p.dado + L.dado);
                        p = p.prox;
                        L = L.prox;
                    }
                    if (p != null && L != null  && p.col > L.col) {
                        soma.insere(i, L.col, L.dado);
                        L = L.prox;
                    }
                    if (p != null && L != null && p.col < L.col) {
                        soma.insere(i, p.col, p.dado);
                        p = p.prox;
                    }

                }


        }
        return soma;
    }


    public matrizDinamica Multiplica(matrizDinamica  mult) {
        matrizDinamica  result=new matrizDinamica();
        matrizDinamica  multT=mult.transposta();
        for (int i = 0; i < tamanho; i++) {
            Elo p;
            Elo L;
            L= multT.geral[i];
            for (int j = 0; j < tamanho; j++) {
                int soma=0;
            for (p = geral[i],L= multT.geral[j]; p != null && L!= null;) {

                    if(p.col==L.col){soma=soma+(p.dado*L.dado);
                        p = p.prox;
                        L = L.prox;
                    }
                    if(p != null && L != null  && p.col > L.col){
                        L = L.prox;
                    }
                    if(p != null && L != null && p.col < L.col){
                        p = p.prox;
                    }

                }
                result.insere(i,j,soma);


            }


        }
        return result;
    }


    public void esparsa() {
        new java.util.Random();
        int ref= (int) Math.round(tamanho*tamanho*0.4);
        int soma=0;
        while(soma<ref) {
            for (int linha = 0; linha < tamanho; linha++) {
                Lista sorteados = new Lista();//colunas já usadas nessa linha
                //sorteia quantas colunas não nulas  vai ter por linha
                int Qcoluna = new Random().nextInt(tamanho-1)+1;
                if (Qcoluna + soma <= ref) {

                    for (int i = 0; i < Qcoluna; i++) {
                        int valor = new Random().nextInt(10);
                        int coluna = new Random().nextInt(tamanho);
                        if (!sorteados.busca(coluna)) {
                            sorteados.insere(coluna);
                            insere(linha, coluna, valor);
                            soma++;
                        }

                    }
                } else {
                    if (ref - soma > 0) {
                        for (int i = 0; i < ref - soma; i++) {
                            int valor = new Random().nextInt(10);
                            int coluna = new Random().nextInt(10);
                            if (!sorteados.busca(coluna)) {
                                sorteados.insere(coluna);
                                insere(linha, coluna, valor);
                                soma++;
                            }

                        }
                    }
                    else {
                        break;

                    }

                }

            }
        }

    }


    public void espacaSimples() {
        new java.util.Random();
        int ref= (int) Math.round(tamanho*0.4);
        int soma=0;

            for (int linha = 0; linha < tamanho; linha++) {
                Lista sorteados = new Lista();//colunas já usadas nessa linha
                //sorteia quantas colunas não nulas  vai ter por linha

                    for (int i = 0; i < ref;i++) {

                        int valor = new Random().nextInt(10);
                        int coluna = new Random().nextInt(tamanho);
                        if (!sorteados.busca(coluna)) {
                            sorteados.insere(coluna);
                            insere(linha, coluna, valor);


                        }

                    }

                }

            }



}
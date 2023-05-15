/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kaio de Oliveira e Sousa
 *         202165080AC
 */
public class ModoDeJogo {
    private Integer [][] tabuleiro = new Integer[9][9];
    
    ModoDeJogo(int opcao)
    {
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                this.tabuleiro[i][j] = 0;
            }
        }
        switch(opcao)
        {
            case 1:
                jogoAleatorio();
                break;
            case 2:
                jogoDefinido();
                break;
            default:
                System.out.println("Opção inválida!");
                
        }
    }
    private boolean ganhou()
    {
        for (int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                if(validaJogada(i,j,this.tabuleiro[i][j]) == false)
                {
                    return false;
                }
            }
        }
        return true;
    }
    private void imprimeTabuleiro()
    {
         for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    if(this.tabuleiro[i][j]==0)
                        System.out.print("_"+ " ");
                    else
                        System.out.print(this.tabuleiro[i][j]+" ");
                }
                System.out.println();
            }
    }
    
    private void imprimeMenu()
    {
        System.out.println("a)Adiconar jogada\nb)Remover jogada\nc)Verificar\nd)Dica\nSair\nDigite a letra da opção desejada ou 'sair' para sair");
    }
    
    private boolean validaJogada(int linha, int coluna, int valor)
    {   
        //Verificar nos quadrantes
        if((valor < 1 || valor > 9)|| (linha > 8 || linha < 0)|| (coluna > 8 || coluna < 0))
        {
            return false;
        }
        for(int k = 0; k <= 6; k = k+3)
        {
            if(linha >= k && linha <= k+2)
            {
                if(coluna >= 0 && coluna <= 2)//Primeiro bloco da linha k
                {
                    for(int i = k ; i <= k+2; i++)
                    {
                        for(int j = 0 ; j <= 2; j++)
                        {
                            if(this.tabuleiro[i][j] == valor && (i != linha && j!= coluna) )
                            {
                                return false;
                            }
                        }
                    }
                }
                else if(coluna >= 3 && coluna <= 5)//Segundo bloco da linha k
                {
                    for(int i = k ; i <= k+2; i++)
                    {
                        for(int j = 3 ; j <= 5; j++)
                        {
                            if(this.tabuleiro[i][j] == valor && (i != linha && j!= coluna))
                            {
                                return false;
                            }
                        }
                    }
                }
                else if(coluna >= 6 && coluna <= 8)//Terceiro bloco da linha k
                {
                    for(int i = k ; i <= k+2; i++)
                    {
                        for(int j = 6 ; j <= 8; j++)
                        {
                            if(this.tabuleiro[i][j] == valor && (i != linha && j!= coluna))
                            {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        for(int i = 0; i < 9; i++)
        {

            if(this.tabuleiro[linha][i] == valor && i!=coluna)//Verifica se o valor já existe nessa linha ou na coluna
            {
                return false;
            }

        }
        for(int i = 0; i < 9; i++)
        {

            if(this.tabuleiro[i][coluna] == valor && i!=linha )//Verifica se o valor já existe nessa linha ou na coluna
            {
                return false;
            }

        }
        return true;
    }
   
    private void vamosJogar()
    {
        Scanner teclado = new Scanner(System.in);
        imprimeMenu();
        String opcao =  teclado.nextLine();
        while(opcao.equals("sair") == false)
        {
            if(opcao.equals("a"))//Adição
            {
                System.out.println("Digite onde deseja jogar ([linha],[coluna],[valor])");
                String jogada = teclado.nextLine();
                String[] posicoes = new String[20];
                posicoes = jogada.split("\\)");
                int linha = 0,coluna = 0, valor = 0;
                for(String posicao: posicoes)
                {   
                    posicao = posicao + ")";
                    //System.out.println(posicao);
                    int aux = 0;

                     for(int i=0; i < posicao.length(); i++)
                     {
                         String aux2 = new String();
                         while(posicao.charAt(i) >= '1' && posicao.charAt(i) <= '9')
                         {
                             aux2 = aux2 + posicao.charAt(i);
                             if(i < posicao.length()-1)
                             {
                                 i++;
                             }

                         }

                         if(aux == 0 && aux2.isEmpty() == false)
                         {
                             linha = Integer.parseInt(aux2)-1;
                             aux++;
                         }
                         else if(aux == 1 && aux2.isEmpty() == false)
                         {
                             coluna = Integer.parseInt(aux2)-1;
                             aux++;
                         }
                         else if(aux == 2 && aux2.isEmpty() == false)
                         {
                             valor = Integer.parseInt(aux2);
                             aux++;
                         }
                        
                        
                    }
                    if((linha > 8 || linha < 0) || (coluna > 8 || coluna < 0) || (valor > 9 || valor < 1) )
                    {
                        System.out.println("Jogada Invalida!");       
                    }
                    else 
                    {
                        this.tabuleiro[linha][coluna] = valor;
                    }
                 
                }
                
            }
            else if(opcao.equals("b"))//Remoção
            {
                System.out.println("Digite onde deseja remover ([linha],[coluna])");
                String jogada = teclado.nextLine();
                String[] posicoes = new String[20];
                posicoes = jogada.split("\\)");
                int linha = 0,coluna = 0;
                for(String posicao: posicoes)
                {   
                    posicao = posicao + ")";
                    //System.out.println(posicao);
                    int aux = 0;

                     for(int i=0; i < posicao.length(); i++)
                     {
                         String aux2 = new String();
                         while(posicao.charAt(i) >= '1' && posicao.charAt(i) <= '9')
                         {
                             aux2 = aux2 + posicao.charAt(i);
                             if(i < posicao.length()-1)
                             {
                                 i++;
                             }

                         }

                         if(aux == 0 && aux2.isEmpty() == false)
                         {
                             linha = Integer.parseInt(aux2)-1;
                             aux++;
                         }
                         else if(aux == 1 && aux2.isEmpty() == false)
                         {
                             coluna = Integer.parseInt(aux2)-1;
                             aux++;
                         }
                        
                        
                    }
                    if((linha > 8 || linha < 0) || (coluna > 8 || coluna < 0) )
                    {
                        System.out.println("Jogada Invalida!");       
                    }
                    else 
                    {
                        this.tabuleiro[linha][coluna] = 0;
                    }
                 
                }

                
            }
            else if(opcao.equals("c"))//Verificação
            {
                for(int i = 0; i < 9; i++)
                {
                    for(int j = 0; j < 9; j++)
                    {
                        if(this.tabuleiro[i][j]!=0)
                        {
                            if(validaJogada(i,j,this.tabuleiro[i][j])== false)
                            {
                                System.out.println("Valor "+this.tabuleiro[i][j]+" na linha "+(i+1)+" e coluna "+(j+1)+" viola as regras");
                            }
                        }
                    }
                }
            }
            if(opcao.equals("d"))
            {
                System.out.println("Digite a ([linha],[coluna]) que deseja receber a dica");
                String jogada = teclado.nextLine();
                String[] posicoes = new String[20];
                posicoes = jogada.split("\\)");
                int linha = 0,coluna = 0;
                for(String posicao: posicoes)
                {   
                    posicao = posicao + ")";
                    //System.out.println(posicao);
                    int aux = 0;

                     for(int i=0; i < posicao.length(); i++)
                     {
                         String aux2 = new String();
                         while(posicao.charAt(i) >= '1' && posicao.charAt(i) <= '9')
                         {
                             aux2 = aux2 + posicao.charAt(i);
                             if(i < posicao.length()-1)
                             {
                                 i++;
                             }

                         }

                         if(aux == 0 && aux2.isEmpty() == false)
                         {
                             linha = Integer.parseInt(aux2)-1;
                             aux++;
                         }
                         else if(aux == 1 && aux2.isEmpty() == false)
                         {
                             coluna = Integer.parseInt(aux2)-1;
                             aux++;
                         }
                        
                        
                    }
                    if((linha > 8 || linha < 0) || (coluna > 8 || coluna < 0) )
                    {
                        System.out.println("Psicao Invalida!");       
                    }
                    else 
                    {
                        dica(linha,coluna);
                    }
                 
                }
                
            }
            if(ganhou())
            {
                System.out.println("Parabéns você venceu!");
            }
            imprimeTabuleiro();
            imprimeMenu();
            opcao = teclado.nextLine();
        }
    }
    
    private void jogoAleatorio()
    {
        System.out.println("Quantas posições deseja que sejam preenchidas?");
        Scanner teclado = new Scanner(System.in);
        int qtd = teclado.nextInt();
        if(qtd > 81 || qtd < 0)
        {
            System.out.println("Quantidade invalida!");
        }
       
        else
        {
            
            Random rand = new Random();
            int randL;//Linha aleatória
            int randC;//Coluna aleatória
            int randV;//Valor aleatório
            randL = rand.nextInt(9);
            randC = rand.nextInt(9);
            randV = rand.nextInt(8)+1; //1 a 9
            for(int i = 0; i < qtd; i++)
            {
                while(this.tabuleiro[randL][randC]!=0)
                {
                    randL = rand.nextInt(9);
                    randC = rand.nextInt(9);
                    randV = rand.nextInt(9)+1; //1 a 9
                }
                if(this.tabuleiro[randL][randC] == 0 && validaJogada(randL,randC,randV))
                {
                    this.tabuleiro[randL][randC] = randV;
                }
                else
                {
                    randV = 1;
                    while((validaJogada(randL,randC,randV) == false)&& randV <= 9)
                    {
                        randV++;
                        if(validaJogada(randL,randC,randV))
                            this.tabuleiro[randL][randC] = randV;
                    }
                    
                }
            }
            imprimeTabuleiro();
        }
        vamosJogar();
    }
    private void dica(int linha,int coluna)
    {
        int [] vet = new int[9];
        int cont = 0;
        for(int i = 1; i< 10; i++)
        {
            if(validaJogada(linha,coluna,i))
            {
                vet[cont] = i;
                cont++;
            }
        }
        System.out.print("Os valores validos paraa posicao ("+(linha+1)+","+(coluna+1)+") sao: ");
        for(int i = 0; i < vet.length; i++)
        {
            if(vet[i] != 0)
                System.out.print(vet[i]+" ");
        }
        System.out.println("");
    }
    private void jogoDefinido()
    {
        Scanner teclado = new Scanner(System.in);
        String jogada = new String();
        System.out.println("Digite onde deseja jogar ([linha],[coluna],[valor])\nDigite sair para comecar o jogo");
        jogada = teclado.nextLine();
        String[] posicoes = new String[20];
        int linha = 0, coluna = 0, valor = 0;
        while(jogada.equals("sair") == false)
        {
            posicoes = jogada.split("\\)");
            for(String posicao: posicoes)
            {   
                posicao = posicao + ")";
                //System.out.println(posicao);
                int aux = 0;
                
                 for(int i=0; i < posicao.length(); i++)
                 {
                     String aux2 = new String();
                     while(posicao.charAt(i) >= '1' && posicao.charAt(i) <= '9')
                     {
                         aux2 = aux2 + posicao.charAt(i);
                         if(i < posicao.length()-1)
                         {
                             i++;
                         }
                         
                     }
                     
                     if(aux == 0 && aux2.isEmpty() == false)
                     {
                         linha = Integer.parseInt(aux2)-1;
                         aux++;
                     }
                     else if(aux == 1 && aux2.isEmpty() == false)
                     {
                         coluna = Integer.parseInt(aux2)-1;
                         aux++;
                     }
                     else if(aux == 2 && aux2.isEmpty() == false)
                     {
                         valor = Integer.parseInt(aux2);
                         aux++;
                     }
                     //System.out.println("aux "+aux2);
                     
                 }
                 //System.out.println(linha+" "+coluna+" "+valor);
                 if((linha > 8 || linha < 0) || (coluna > 8 || coluna < 0) || (valor > 9 || valor < 1) )
                 {
                      System.out.println("Jogada Invalida!");       
                 }
                 else if(validaJogada(linha,coluna,valor))
                 {
                     this.tabuleiro[linha][coluna] = valor;
                     imprimeTabuleiro();
                 }
                 else 
                 {
                     System.out.println("Jogada invalida!");

                 }
            }
           System.out.println("Digite onde deseja jogar ([linha],[coluna],[valor])\nOu digite 'sair' para finalizar");
           jogada = teclado.nextLine();
        }
        vamosJogar();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author User
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
        System.out.println("a)Adiconar jogada\nb)Remover jogada\nc)Verificar\nSair\nDigite a letra da opção desejada ou 'sair' para sair");
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
                    for(int i = 0 ; i <= 2; i++)
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
                    for(int i = 0 ; i <= 2; i++)
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
                    for(int i = 0 ; i <= 2; i++)
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
                String[] posicoes = new String[3];
                posicoes = jogada.split(",");
                int linha = Character.getNumericValue((posicoes[0].charAt(0)))-1;
                int coluna = Character.getNumericValue((posicoes[1].charAt(0)))-1;
                int valor = Character.getNumericValue((posicoes[2].charAt(0)));

                if((posicoes[0].length()>1 || posicoes[1].length()>1 || posicoes[2].length()>1)|| (linha<0 || coluna < 0 || valor <= 0))
                {
                    System.out.println("Jogada invalida: Valores fora do intervalo permitido de 1 a 9");

                }
                else
                {
                    this.tabuleiro[linha][coluna] = valor;
                    
                }
            }
            if(opcao.equals("b"))//Remoção
            {
                System.out.println("Digite onde deseja remover ([linha],[coluna])");
                String jogada = teclado.nextLine();
                String[] posicoes = new String[2];
                posicoes = jogada.split(",");
                int linha = Character.getNumericValue((posicoes[0].charAt(0)))-1;
                int coluna = Character.getNumericValue((posicoes[1].charAt(0)))-1;

                if((posicoes[0].length()>1 || posicoes[1].length()>1 )|| (linha<0 || coluna < 0))
                {
                    System.out.println("Jogada invalida: Valores fora do intervalo permitido de 1 a 9");

                }
                else 
                {
                    this.tabuleiro[linha][coluna] = 0;
                }
            }
            if(opcao.equals("c"))//Verificação
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
            for(int i = 0; i < qtd; i++)
            {
                randL = rand.nextInt(9);
                randC = rand.nextInt(9);
                randV = rand.nextInt(8)+1; //1 a 9
           
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
    private void jogoDefinido()
    {
        Scanner teclado = new Scanner(System.in);
        String jogada;
        System.out.println("Digite onde deseja jogar ([linha],[coluna],[valor])\nDigite sair para comecar o jogo");
        jogada = teclado.nextLine();
        String[] posicoes = new String[3];
        int linha, coluna, valor;
        while(jogada.equals("sair") == false)
        {
          
           posicoes = jogada.split(",");
           linha = Character.getNumericValue((posicoes[0].charAt(0)))-1;
           coluna = Character.getNumericValue((posicoes[1].charAt(0)))-1;
           valor = Character.getNumericValue((posicoes[2].charAt(0)));

           if((posicoes[0].length()>1 || posicoes[1].length()>1 || posicoes[2].length()>1)|| (linha<0 || coluna < 0 || valor <= 0))
           {
               System.out.println("Jogada invalida: Valores fora do intervalo permitido de 1 a 9");
               
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
           
           System.out.println("Digite onde deseja jogar ([linha],[coluna],[valor])\nOu digite 'sair' para finalizar");
           jogada = teclado.nextLine();
        }
        vamosJogar();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sudoku;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Sudoku {

    public static void main(String[] args) {
        String jogar = "s";
        System.out.println("Bem-Vindo!");
        while(jogar.equals("s"))
        {
            System.out.println("Como deseja jogar?\n1)Jogo aleatorio\n2)Definir proprio jogo\nDigite a opcao desejada: ");
            Scanner teclado = new Scanner(System.in);
            int opcao = teclado.nextInt();
            ModoDeJogo modo =  new ModoDeJogo(opcao);
            System.out.println("Deseja jogar novamente?\ns = sim\nn = nao");
            jogar = teclado.nextLine();
        }
              
    }
}

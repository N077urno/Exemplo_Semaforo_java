/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exemplo_semaforo_java;

/**
 *
 * @author nando
 */
import java.util.concurrent.Semaphore;
public class ContaBancaria {
    private double saldo;
    private final Semaphore semaphore = new Semaphore(1); //Semáfaro binário
    
    public ContaBancaria(double saldoInicial){
        this.saldo = saldoInicial;
    }
    
    //saque com semáforo
    public void sacar(double valor){
        try{
            semaphore.acquire();  //Equivalente a fazer um down no semaforo
            if(saldo >= valor){
                System.out.println(Thread.currentThread().getName()+ "esta sacando..: "+String.format("%.2f",valor));
                saldo -= valor;
                System.out.println("Novo saldo apos o saque..: "+String.format("%.2f", saldo));
            }else{
                System.out.println(Thread.currentThread().getName()+ "Saldo Insuficiente para Saque de..: "+String.format("%.2f",valor));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            semaphore.release(); //Equivalente a fazer um up no semaforo
        }
    }
    
    // deposito com semaforp
    public void depositar(double valor){
        try{
            semaphore.acquire();  //Equivalente a fazer um down no semaforo
            System.out.println(Thread.currentThread().getName()+ " esta depositando..: "+String.format("%.2f",valor));
            saldo += valor;
            System.out.println("Novo saldo apos deposito..: "+String.format("%.2f",saldo));
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally{
            semaphore.release();  //Equivalente a fazer um UP no semaforo
        }
    }
    
    public double getSaldo(){
        return saldo;
    }
}

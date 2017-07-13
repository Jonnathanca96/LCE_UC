/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import javax.swing.JOptionPane;

/**
 *
 * @author Original Emily Arteaga, Jefferson Arias, Christian Salinas
 * Programacion 3: Estructura de Archivo - Universidad de Cuenca
 * Modificaciones realizadas para un mejor funcionamiento hechas por Jonnathan Campoberde,Ariel Bravo, Vanessa Romero
 */

public class Time {
    long startSystemTimeNano;
    long startUserTimeNano;
    long taskUserTimeNano;
    long taskSystemTimeNano;
    long startTimeNano ;
    long taskTimeNano;

    public Time() {
    }

    public long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? bean.getCurrentThreadCpuTime() : 0L;
    }

    /**
     * Get user time in nanoseconds.
     */
    public long getUserTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported()
                ? bean.getCurrentThreadUserTime() : 0L;
    }

    /**
     * Get system time in nanoseconds.
     */
    public long getSystemTime() {//getCurrentCpuTime()
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        return bean.isCurrentThreadCpuTimeSupported() ? (bean.getCurrentThreadCpuTime() - bean.getCurrentThreadUserTime()) : 0L;
    }

    public void inicio() {
        startSystemTimeNano = getSystemTime();
        startUserTimeNano = getUserTime();
        startTimeNano = System.nanoTime();
        
    }
    
    
    public void fin(){
        taskUserTimeNano = getUserTime() - startUserTimeNano;
        taskSystemTimeNano = getSystemTime() - startSystemTimeNano;
        taskTimeNano = System.nanoTime() - startTimeNano;
        JOptionPane.showMessageDialog(null, 
                "Wall clock: time: "+ taskTimeNano+"El CPU time es:"+taskUserTimeNano+taskSystemTimeNano
                +"\n User:"+taskUserTimeNano
                +"\n System:"+taskSystemTimeNano,"CPU TIME", JOptionPane.INFORMATION_MESSAGE);
    }
}

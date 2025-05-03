/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog2.vista;

import java.util.Random;

/**
 *
 * @author Daniel Ortiz
 */
public class VariableNormal {
    private Random random;
    private float mean;
    private float standardDeviation;

    public VariableNormal(float mean, float standardDeviation, long seed) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
        this.random = new Random(seed);
    }

    public float seguentValor() {
        return (float) mean + standardDeviation * (float) random.nextGaussian();
    }

}

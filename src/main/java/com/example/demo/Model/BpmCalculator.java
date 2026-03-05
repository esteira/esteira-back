package com.example.demo.Model;

public class BpmCalculator {

    private double bpmMinimo;
    private double bpmMaximo;

    public double[] calcularBPM(double altura, double velocidadeKmh) {
        // Define o coeficiente com base na altura
        double coeficientePassada = getCoeficientePassada(altura);

        // Converte a velocidade de km/h para metros por minuto
        double velocidadeMpm = (velocidadeKmh * 1000) / 60;

        // Calcula o comprimento da passada
        double comprimentoPassada = coeficientePassada * altura;

        // Calcula o número de passos por minuto
        double passosPorMinuto = velocidadeMpm / comprimentoPassada;

        // Calcula o BPM máximo e mínimo
        this.bpmMaximo = (Math.round(passosPorMinuto / 10.0) * 10) + 10;
        this.bpmMinimo = (Math.round(passosPorMinuto / 10.0) * 10) - 10;

        // Retorna o BPM máximo e mínimo como um array
        return new double[] { bpmMinimo, bpmMaximo };
    }

    // Função auxiliar para determinar o coeficiente com base na altura
    private double getCoeficientePassada(double altura) {
        if (altura > 1.50 && altura < 1.75) {
            return 0.4;
        } else if (altura >= 1.75 && altura < 2.0) {
            return 0.5;
        } else if (altura >= 2.0) {
            return 0.6;
        }
        return 0.5; // Valor padrão para alturas fora do intervalo
    }

    public static void main(String[] args) {
        BpmCalculator calculator = new BpmCalculator();
        double altura = 1.90; // Altura em metros
        double velocidadeKmh = 11.5; // Velocidade em km/h

        double[] bpmEstimado = calculator.calcularBPM(altura, velocidadeKmh);
        System.out.println("BPM estimado: Min: " + bpmEstimado[0] + ", Max: " + bpmEstimado[1]);
    }
}

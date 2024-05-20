package entities;

public class RandomNumber {
    int number; // Número aleatório gerado
    int min; // Valor mínimo para o número aleatório
    int max; // Valor máximo para o número aleatório

    // metodo para o número aleatório
    public RandomNumber(int min, int max) {
        setRandomNumber(min, max); // Inicializa o número aleatório dentro do intervalo especificado
    }

    // método para gerar o número aleatório dentro do intervalo especificado
    private void setRandomNumber(int min, int max) {
        // Gera um número aleatório entre min e max (inclusive)
        number = (int) Math.round(Math.random() * (max - min) + min);
    }

    public int getRandomNumber() {
        return number; // Retorna o número aleatório gerado
    }
}

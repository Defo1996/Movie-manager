package ru.netology;

public class MovieManager {
    private final String[] movies;
    private int size = 0; // Текущий размер (количество добавленных фильмов)
    private final int limit; // Лимит для метода findLast

    // Конструктор без параметров (лимит по умолчанию — 5)
    public MovieManager() {
        this.limit = 5;
        this.movies = new String[100]; // Инициализируем массив с запасом
    }

    // Конструктор с параметром (задаём лимит)
    public MovieManager(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Лимит должен быть положительным числом");
        }
        this.limit = limit;
        this.movies = new String[100]; // Инициализируем массив с запасом
    }

    // Метод добавления нового фильма
    public void addMovie(String movie) {
        if (movie == null || movie.isEmpty()) {
            throw new IllegalArgumentException("Название фильма не может быть пустым");
        }
        if (size >= movies.length) {
            throw new IllegalStateException("Превышен максимальный размер хранилища фильмов");
        }
        movies[size++] = movie;
    }

    // Метод вывода всех фильмов в порядке добавления
    public String[] findAll() {
        String[] allMovies = new String[size];
        System.arraycopy(movies, 0, allMovies, 0, size); // Копируем только заполненную часть
        return allMovies;
    }

    // Метод вывода последних добавленных фильмов в обратном порядке
    public String[] findLast() {
        int resultLength;
        if (size <= limit) {
            resultLength = size; // Если фильмов меньше или равно лимиту, возвращаем все
        } else {
            resultLength = limit; // Иначе возвращаем только лимит фильмов
        }

        String[] lastMovies = new String[resultLength];
        for (int i = 0; i < resultLength; i++) {
            lastMovies[i] = movies[size - 1 - i]; // Заполняем в обратном порядке
        }
        return lastMovies;
    }
}

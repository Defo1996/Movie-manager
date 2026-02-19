package ru.netology;

public class MovieManager {
    private String[] movies;
    private int size; // Текущий размер массива (количество добавленных фильмов)
    private int capacity; // Максимальная ёмкость массива
    private final int limit; // Лимит на количество последних фильмов для вывода

    public MovieManager() {
        this.capacity = 10;
        this.movies = new String[capacity];
        this.size = 0;
        this.limit = 5;
    }

    public MovieManager(int limit) {
        this.capacity = 10;
        this.movies = new String[capacity];
        this.size = 0;
        this.limit = limit;
    }

    public void addMovie(String movie) {
        if (size >= capacity) {
            String[] newMovies = new String[capacity * 2];
            System.arraycopy(movies, 0, newMovies, 0, size);
            movies = newMovies;
            capacity *= 2;
        }
        movies[size++] = movie;
    }

    public String[] findAll() {
        String[] allMovies = new String[size];
        System.arraycopy(movies, 0, allMovies, 0, size);
        return allMovies;
    }

    public String[] findLast() {
        int resultLength = Math.min(size, limit);

        String[] result = new String[resultLength];

        for (int i = 0; i < resultLength; i++) {
            result[i] = movies[size - 1 - i];
        }

        return result;
    }
}

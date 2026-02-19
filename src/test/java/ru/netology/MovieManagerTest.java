package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieManagerTest {

    @Test
    public void shouldAddMoviesAndFindAll() {
        MovieManager manager = new MovieManager();

        manager.addMovie("Фильм 1");
        manager.addMovie("Фильм 2");
        manager.addMovie("Фильм 3");

        String[] expected = {"Фильм 1", "Фильм 2", "Фильм 3"};
        String[] actual = manager.findAll();

        assertArrayEquals(expected, actual, "Массив всех фильмов должен совпадать с ожидаемым");
    }


    @Test
    public void shouldFindLastFiveMovies() {
        MovieManager manager = new MovieManager(); // Лимит по умолчанию = 5

        // Добавляем 6 фильмов (больше лимита)
        for (int i = 1; i <= 6; i++) {
            manager.addMovie("Фильм " + i);
        }

        String[] expected = {"Фильм 6", "Фильм 5", "Фильм 4", "Фильм 3", "Фильм 2"};
        String[] actual = manager.findLast();


        assertArrayEquals(expected, actual, "Должны вернуться последние 5 фильмов в обратном порядке");
    }

    @Test
    public void shouldFindAllWhenLessThanLimit() {
        MovieManager manager = new MovieManager(); // Лимит = 5

        manager.addMovie("Фильм 1");
        manager.addMovie("Фильм 2");

        String[] expected = {"Фильм 2", "Фильм 1"}; // Порядок: от последнего к первому
        String[] actual = manager.findLast();


        assertArrayEquals(expected, actual, "Если фильмов меньше лимита, возвращаются все в обратном порядке");
    }


    @Test
    public void shouldUseCustomLimit() {
        MovieManager manager = new MovieManager(3); // Устанавливаем лимит = 3

        // Добавляем 4 фильма (больше лимита)
        for (int i = 1; i <= 4; i++) {
            manager.addMovie("Фильм " + i);
        }

        String[] expected = {"Фильм 4", "Фильм 3", "Фильм 2"}; // Только последние 3
        String[] actual = manager.findLast();

        assertArrayEquals(expected, actual, "При лимите 3 должны вернуться только 3 последних фильма");
    }

    @Test
    public void shouldReturnEmptyArraysWhenNoMovies() {
        MovieManager manager = new MovieManager();

        String[] all = manager.findAll();
        String[] last = manager.findLast();

        // Проверяем длину массивов
        assertEquals(0, all.length, "Массив findAll() должен быть пустым");
        assertEquals(0, last.length, "Массив findLast() должен быть пустым");


        // Дополнительно проверяем, что массивы действительно пустые
        assertArrayEquals(new String[0], all, "findAll() должен вернуть пустой массив");
        assertArrayEquals(new String[0], last, "findLast() должен вернуть пустой массив");
    }

    @Test
    void shouldThrow_WhenMovieIsNull() {
        MovieManager manager = new MovieManager();
        assertThrows(IllegalArgumentException.class, () -> manager.addMovie(null));
    }

    @Test
    void shouldThrow_WhenMovieIsEmpty() {
        MovieManager manager = new MovieManager();
        assertThrows(IllegalArgumentException.class, () -> manager.addMovie(""));
    }

    @Test
    void shouldReturnEmptyArray_WhenNoMovies() {
        MovieManager manager = new MovieManager();
        String[] actual = manager.findLast();
        assertArrayEquals(new String[0], actual);
    }

    @Test
    void shouldReturnSingleMovie_WhenOneMovie() {
        MovieManager manager = new MovieManager();
        manager.addMovie("Фильм 1");
        String[] actual = manager.findLast();
        assertArrayEquals(new String[]{"Фильм 1"}, actual);
    }

    @Test
    void shouldReturnExactLimit_WhenSizeEqualsLimit() {
        MovieManager manager = new MovieManager(2);
        manager.addMovie("Фильм 1");
        manager.addMovie("Фильм 2");
        String[] actual = manager.findLast();
        assertArrayEquals(new String[]{"Фильм 2", "Фильм 1"}, actual);
    }


}
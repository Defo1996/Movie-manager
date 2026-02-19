package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieManagerTest {

    @Test
    void testAddAndFindAll() {
        MovieManager manager = new MovieManager();
        manager.addMovie("Бладшот");
        manager.addMovie("Вперёд");
        manager.addMovie("Отель «Белград»");

        String[] expected = {"Бладшот", "Вперёд", "Отель «Белград»"};
        String[] actual = manager.findAll();

        assertArrayEquals(expected, actual, "Массивы фильмов не совпадают");
    }

    @Test
    void testFindLastWithDefaultLimit() {
        MovieManager manager = new MovieManager(); // Лимит по умолчанию — 5

        // Добавляем 3 фильма (меньше лимита)
        manager.addMovie("Фильм 1");
        manager.addMovie("Фильм 2");
        manager.addMovie("Фильм 3");

        String[] expected = {"Фильм 3", "Фильм 2", "Фильм 1"};
        String[] actual = manager.findLast();

        assertArrayEquals(expected, actual, "Последние фильмы не совпадают (случай с количеством фильмов < лимита)");

        // Добавляем ещё 3 фильма (всего 6, лимит 5)
        manager.addMovie("Фильм 4");
        manager.addMovie("Фильм 5");
        manager.addMovie("Фильм 6");

        expected = new String[]{"Фильм 6", "Фильм 5", "Фильм 4", "Фильм 3", "Фильм 2"};
        actual = manager.findLast();

        assertArrayEquals(expected, actual, "Последние фильмы не совпадают (случай с количеством фильмов > лимита)");
    }

    @Test
    void testFindLastWithCustomLimit() {
        MovieManager manager = new MovieManager(3); // Кастомный лимит — 3

        manager.addMovie("Фильм 1");
        manager.addMovie("Фильм 2");
        manager.addMovie("Фильм 3");
        manager.addMovie("Фильм 4");
        manager.addMovie("Фильм 5");

        String[] expected = {"Фильм 5", "Фильм 4", "Фильм 3"};
        String[] actual = manager.findLast();

        assertArrayEquals(expected, actual, "Последние фильмы не совпадают при кастомном лимите");
    }

    @Test
    void testEmptyManager() {
        MovieManager manager = new MovieManager();
        String[] lastMovies = manager.findLast();
        String[] expected = new String[0];

        assertArrayEquals(expected, lastMovies, "Менеджер без фильмов должен возвращать пустой массив");
    }

    @Test
    void testAddMovieWithResize() {
        MovieManager manager = new MovieManager();
        for (int i = 0; i < 15; i++) {
            manager.addMovie("Фильм " + i);
        }
        assertEquals(15, manager.findAll().length);
    }

    @Test
    void testFindLastWithZeroLimit() {
        MovieManager manager = new MovieManager(0);
        manager.addMovie("Фильм 1");
        String[] result = manager.findLast();
        assertArrayEquals(new String[0], result);
    }

    @Test
    void testFindLastEmptyWithPositiveLimit() {
        MovieManager manager = new MovieManager(5);
        String[] result = manager.findLast();
        assertArrayEquals(new String[0], result);
    }

}
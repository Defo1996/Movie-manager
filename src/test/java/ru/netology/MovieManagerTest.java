package ru.netology;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovieManagerTest {

    @Test
    public void testAddAndFindAll() {
        MovieManager manager = new MovieManager();
        manager.addMovie("Бладшот");
        manager.addMovie("Вперёд");
        manager.addMovie("Отель «Белград»");

        String[] allMovies = manager.findAll();

        // Проверяем, что массив содержит 3 элемента
        assertEquals(3, allMovies.length);

        // Проверяем каждый элемент по индексу
        assertEquals("Бладшот", allMovies[0]);
        assertEquals("Вперёд", allMovies[1]);
        assertEquals("Отель «Белград»", allMovies[2]);
    }


    @Test
    public void testFindLastWithDefaultLimit() {
        MovieManager manager = new MovieManager(); // Лимит = 5
        for (int i = 0; i < 7; i++) {
            manager.addMovie("Фильм " + i);
        }

        String[] lastMovies = manager.findLast();

        // Должно быть 5 последних фильмов
        assertEquals(5, lastMovies.length);

        // Проверяем элементы в обратном порядке (последний добавленный — первый в массиве)
        assertEquals("Фильм 6", lastMovies[0]);
        assertEquals("Фильм 5", lastMovies[1]);
        assertEquals("Фильм 4", lastMovies[2]);
        assertEquals("Фильм 3", lastMovies[3]);
        assertEquals("Фильм 2", lastMovies[4]);
    }


    @Test
    public void testFindLastWithCustomLimit() {
        MovieManager manager = new MovieManager(3); // Лимит = 3
        for (int i = 0; i < 5; i++) {
            manager.addMovie("Фильм " + i);
        }

        String[] lastMovies = manager.findLast();

        // Должно быть 3 последних фильма
        assertEquals(3, lastMovies.length);

        assertEquals("Фильм 4", lastMovies[0]);
        assertEquals("Фильм 3", lastMovies[1]);
        assertEquals("Фильм 2", lastMovies[2]);
    }


    @Test
    public void testFindLastWhenMoviesLessThanLimit() {
        MovieManager manager = new MovieManager(5);
        manager.addMovie("Фильм 1");
        manager.addMovie("Фильм 2");

        String[] lastMovies = manager.findLast();

        // Фильмов меньше лимита — возвращаем все (2)
        assertEquals(2, lastMovies.length);

        assertEquals("Фильм 2", lastMovies[0]); // Последний добавленный
        assertEquals("Фильм 1", lastMovies[1]); // Предпоследний
    }

    @Test
    public void testFindLast_WhenSizeExceedsLimit() {
        MovieManager manager = new MovieManager(2); // Лимит = 2
        manager.addMovie("Фильм 1");
        manager.addMovie("Фильм 2");
        manager.addMovie("Фильм 3"); // size > limit

        String[] lastMovies = manager.findLast();

        assertEquals(2, lastMovies.length); // Должно быть ровно 2 фильма
        assertEquals("Фильм 3", lastMovies[0]); // Последний
        assertEquals("Фильм 2", lastMovies[1]); // Предпоследний
    }

    @Test
    public void testFindAll_Empty() {
        MovieManager manager = new MovieManager();
        String[] allMovies = manager.findAll();
        assertEquals(0, allMovies.length);
    }

    @Test
    public void testConstructor_NegativeLimit() {
        assertThrows(IllegalArgumentException.class, () -> new MovieManager(-1));
    }

    @Test
    public void testAddMovie_ValidInput() {
        // Сценарий: корректный ввод → фильм добавлен
        MovieManager manager = new MovieManager();
        manager.addMovie("Звёздные войны");


        String[] allMovies = manager.findAll();
        assertEquals(1, allMovies.length);
        assertEquals("Звёздные войны", allMovies[0]);
    }

    @Test
    public void testAddMovie_NullInput() {
        // Сценарий: movie == null → должно выбросить исключение
        MovieManager manager = new MovieManager();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> manager.addMovie(null)
        );

        assertEquals("Название фильма не может быть пустым", exception.getMessage());
    }

    @Test
    public void testAddMovie_EmptyString() {
        // Сценарий: movie.isEmpty() → должно выбросить исключение
        MovieManager manager = new MovieManager();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> manager.addMovie("")
        );

        assertEquals("Название фильма не может быть пустым", exception.getMessage());
    }

    @Test
    public void testAddMovie_ArrayOverflow() {
        MovieManager manager = new MovieManager();

        // Заполняем массив полностью (предполагаем, что movies.length = 100)
        for (int i = 0; i < 100; i++) {
            manager.addMovie("Фильм " + i);
        }

        // Попытка добавить 101-й фильм → должно выбросить IllegalStateException
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> manager.addMovie("Лишний фильм")  // Код, который должен выбросить исключение
        );

        // Проверяем сообщение исключения (опционально, но рекомендуется)
        assertEquals(
                "Превышен максимальный размер хранилища фильмов",
                exception.getMessage()
        );
    }
}
package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService service;

    @GetMapping
    public Collection<Film> findAll() {
        return service.getFilms();
    }

    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        return service.create(film);
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film newFilm) {
        return service.updateFilm(newFilm);
    }

    @GetMapping("/{id}")
    public Film getFilm(@PathVariable long id) {
        return service.getFilm(id);
    }

    @PutMapping("/{id}/like/{userId}")
    public void likeFilm(@PathVariable Long id, @PathVariable Long userId) {
        service.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like/{userId}")
    public void unlikeFilm(@PathVariable Long id, @PathVariable Long userId) {
        service.removeLike(id, userId);
    }

    @GetMapping("/popular")
    public List<Film> getPopularFilms(@RequestParam(defaultValue = "10") int count) {
        return service.getMostPopularFilms(count);
    }
}

package com.cmoncrieffe.satdungeongeneration.controller;

import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonDrawing;
import com.cmoncrieffe.satdungeongeneration.draw.dungeon.DungeonSize;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/dungeon-generation/map/generate")
public class MapGenerationController {

    @GetMapping(value = "/extra-small", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] extraSmallMap() {
        return map(DungeonSize.EXTRA_SMALL);
    }

    @GetMapping(value = "/small", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] smallMap() {
        return map(DungeonSize.SMALL);
    }

    @GetMapping(value = "/medium", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] mediumMap() {
        return map(DungeonSize.MEDIUM);
    }

    @GetMapping(value = "/large", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] largeMap() {
        return map(DungeonSize.LARGE);
    }

    @GetMapping(value = "/extra-large", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] extraLargeMap() {
        return map(DungeonSize.EXTRA_LARGE);
    }

    @GetMapping(value = "/random", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] randomMap() {
        return map(DungeonSize.RANDOM);
    }

    private byte[] map(DungeonSize dungeonSize) {
        return (new DungeonDrawing(dungeonSize)).get();
    }
}

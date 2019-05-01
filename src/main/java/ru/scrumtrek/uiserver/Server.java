package ru.scrumtrek.uiserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.scrumtrek.uiserver.domain.Line;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Server {
    private AtomicInteger idInt = new AtomicInteger(0);
    private List<Line> lines = new LinkedList<>();

    @RequestMapping(method = RequestMethod.GET, path = {"/lines"})
    public List<Line> getLines() {
        return lines;
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/lines"})
    public ResponseEntity<Void> setLines(@RequestBody String line) {
        if (lines.stream().anyMatch(l -> l.getLineChars().equals(line)))
            return ResponseEntity.badRequest().build();
        lines.add(new Line(idInt.incrementAndGet(), line));
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/lines"})
    public void removeAll() { lines.clear(); }
}

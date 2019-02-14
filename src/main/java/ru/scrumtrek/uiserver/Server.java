package ru.scrumtrek.uiserver;

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
    public void setLines(@RequestBody String line) {
        lines.add(new Line(idInt.incrementAndGet(), line));
    }
}

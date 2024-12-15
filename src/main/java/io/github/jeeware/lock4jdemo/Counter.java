package io.github.jeeware.lock4jdemo;

import org.springframework.data.annotation.Id;

public record Counter(@Id String name, int count) {

    public Counter increment() {
        return new Counter(this.name, this.count + 1/*, version != null ? version + 1 : 0*/);
    }
}

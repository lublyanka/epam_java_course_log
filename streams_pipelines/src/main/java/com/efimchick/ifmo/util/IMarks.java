package com.efimchick.ifmo.util;

import java.util.Optional;

@FunctionalInterface
public interface IMarks {
    Optional<String> toString(Double x);

}


package com.optum.icr.repository;

public interface SequenceGeneratorRepository {
    long generateSequence(String seqName);
}

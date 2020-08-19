package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Override
    @EntityGraph(Accident.ACCIDENT_ALL_FIELDS)
    Optional<Accident> findById(Integer integer);

    @Query("select distinct a from Accident a left join fetch a.type left join fetch a.rules")
    Collection<Accident> allAccidents();

    @Query("select at from AccidentType at")
    Collection<AccidentType> allAccidentTypes();

    @Query("select at from AccidentType at where at.id = :id")
    AccidentType findAccidentTypeById(@Param("id") int id);

    @Query("select r from Rule r")
    Collection<Rule> allRules();

    @Query("select r from Rule r where r.id in (:ids)")
    Set<Rule> findRulesByIds(@Param("ids") Collection<Integer> ids);
}

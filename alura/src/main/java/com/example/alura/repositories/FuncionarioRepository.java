package com.example.alura.repositories;

import com.example.alura.entities.Funcionario;
import com.example.alura.interfaces.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {

    /*
    * Derived Queries - criadas através de comandos Java!
    * Jpql - criadas através de estrutura SQL, porém com nomes das entidades Java!
    * Native Query - queries padrões SQL que conseguimos executar no client sql, por exemplo
    * */

    List<Funcionario> findByName(String nome);

    @Query("SELECT f FROM Funcionario f where f.nome = :nome " +
            "AND f.salario >= :salario AND f.dataContratacao = :data")
    List<Funcionario> findNomeSalarioMaiorDataContratacao(String nome, Double salario, LocalDate data);

    @Query(value = "SELECT f funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(LocalDate data);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();
}

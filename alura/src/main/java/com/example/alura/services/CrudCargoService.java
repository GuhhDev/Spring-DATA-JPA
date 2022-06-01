package com.example.alura.services;

import com.example.alura.entities.Cargo;
import com.example.alura.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;


@Service
public class CrudCargoService {

    private Boolean system = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository){
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner){

        while(system){
            System.out.println("Qual opção de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Deletar");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    salvar(scanner);
                    break;
                case 2:
                    atualizar(scanner);
                    break;
                case 3:
                    visualizar(scanner);
                    break;
                case 4:
                    deletar(scanner);
                    break;

            }
        }

    }

    private void salvar(Scanner scanner){
        System.out.println("descrição do cargo");
        String descricao = scanner.next();
        Cargo cargo = new Cargo();
        cargoRepository.save(cargo);
    }

    private void atualizar (Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        System.out.println("Descrição do cargo: ");
        String descricao = scanner.next();

        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Atualizado com sucesso!");
    }

    private void visualizar (Scanner scanner) {
        Iterable<Cargo> cargos = cargoRepository.findAll();
        cargos.forEach(cargo -> System.out.println(cargo));
    }

    private void deletar (Scanner scanner) {
        System.out.println("Id");
        int id = scanner.nextInt();
        cargoRepository.deleteById(id);
        System.out.println("Deletado com sucesso!");
    }
}

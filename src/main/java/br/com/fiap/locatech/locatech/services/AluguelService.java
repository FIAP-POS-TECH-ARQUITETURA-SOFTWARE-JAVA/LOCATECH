package br.com.fiap.locatech.locatech.services;

import br.com.fiap.locatech.locatech.dtos.AluguelRequestDTO;
import br.com.fiap.locatech.locatech.entities.Aluguel;
import br.com.fiap.locatech.locatech.repositories.AluguelRepository;
import br.com.fiap.locatech.locatech.services.execptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    private  final AluguelRepository aluguelRepository;

    public AluguelService(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }



    public List<Aluguel> findAllAlugueis(int page, int size ){
        int offset = (page - 1) * size;
        return aluguelRepository.findAll(size , offset);
    }

    public Optional<Aluguel> findAluguelById(Long id){

        return Optional.of(aluguelRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Aluguél não encontrado")
        ));
    }

    public void saveAluguel(Aluguel aluguel){
        var save = aluguelRepository.save(aluguel);
        Assert.state (save == 1 , "Erro ao cadastrar aluguel " + aluguel.getId());
    }


    public void updateAluguel(Aluguel veiculo , Long id){
        var update = aluguelRepository.update(veiculo , id);
        if(update == 0 ){
            throw new RuntimeException("aluguel não encontrado");
        }
    }

    public void deleteAluguel(Long id){
        var delete = aluguelRepository.delete(id);
        if(delete == 0 ){
            throw new RuntimeException("Erro ao deletar aluguel não encontrado");
        }
    }


}

package com.dio.santander.banklineapi.controller;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.dto.NovoCorrentista;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;
import com.dio.santander.banklineapi.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<Movimentacao> findAll(){
        return movimentacaoRepository.findAll();
    }

    @GetMapping("/{idConta}")
    public List<Movimentacao> findAll(@PathVariable("idConta") Integer idConta){
        return movimentacaoRepository.findByIdConta(idConta);
    }

    @PostMapping
    public void save(@RequestBody NovaMovimentacao novaMovimentacao){
        movimentacaoService.save(novaMovimentacao);
    }
}

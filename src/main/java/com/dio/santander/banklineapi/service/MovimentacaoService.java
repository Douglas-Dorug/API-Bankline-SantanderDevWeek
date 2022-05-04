package com.dio.santander.banklineapi.service;

import com.dio.santander.banklineapi.dto.NovaMovimentacao;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.model.MovimentacaoTipo;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private CorrentistaRepository correntistaRepository;

    public void save(NovaMovimentacao novaMovimentacao){
        Movimentacao movimentacao = new Movimentacao();

        //Double valor = novaMovimentacao.getMovimentacaoTipo() == MovimentacaoTipo.RECEITA? novaMovimentacao.getValor() : novaMovimentacao.getValor() * -1;

        Double valor = novaMovimentacao.getValor();

        if(novaMovimentacao.getMovimentacaoTipo() == MovimentacaoTipo.DESPESA){
            valor = valor * -1;
        }

        movimentacao.setDataHora(LocalDateTime.now());
        movimentacao.setDescricao(novaMovimentacao.getDescricao());
        movimentacao.setIdConta(novaMovimentacao.getIdConta());
        movimentacao.setMovimentacaoTipo(novaMovimentacao.getMovimentacaoTipo());
        movimentacao.setValor(valor);

        Correntista correntista = correntistaRepository.findById(novaMovimentacao.getIdConta()).orElse(null);

        if (correntista != null){
            correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
            correntistaRepository.save(correntista);
        }

        movimentacaoRepository.save(movimentacao);

    }
}

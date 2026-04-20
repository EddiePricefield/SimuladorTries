/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package buscaemtrie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Posicao {

    int linha, coluna;

    Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    @Override
    public String toString() {
        return String.format("Linha %d, Coluna %d", linha, coluna);
    }
}

class TrieNode {

    Map<Character, TrieNode> filhos = new HashMap<>();
    List<Posicao> ocorrencias = new ArrayList<>();
    boolean eFimDePalavra = false;
}

public class Trie {

    private final TrieNode raiz;

    public Trie() {
        this.raiz = new TrieNode();
    }

    public void inserir(String palavra, int linha, int coluna) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        TrieNode atual = raiz;
        for (char c : palavra.toLowerCase().toCharArray()) {
            atual.filhos.putIfAbsent(c, new TrieNode());
            atual = atual.filhos.get(c);
        }

        atual.eFimDePalavra = true;
        atual.ocorrencias.add(new Posicao(linha, coluna));
    }

    public List<Posicao> buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return new ArrayList<>();
        }

        TrieNode atual = raiz;
        for (char c : palavra.toLowerCase().toCharArray()) {
            atual = atual.filhos.get(c);
            if (atual == null) {
                return new ArrayList<>();
            }
        }

        return atual.eFimDePalavra ? atual.ocorrencias : new ArrayList<>();
    }

    public void clear() {
        this.raiz.filhos.clear();
    }
}

package com.geral.maps.softblue;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe que mapeia chaves a valores e permite recuperar um valor com base em uma chave.
 * 
 * @param <K> Tipo de dado das chaves
 * @param <V> Tipo de dado dos valores
 */
public class MeuMap<K, V> {

	/**
	 * Lista de chaves 
	 */
	private List<K> keys = new ArrayList<K>();
	
	
	/**
	 * Lista de valores
	 */
	private List<V> values = new ArrayList<V>();
	
	/**
	 * Insere um par de chave e valor. Se a chave que est� sendo inserida j� existe, o c�digo atualiza o valor 
	 * que corresponde � chave j� existente (n�o insere a chave novamente).
	 * @param key Chave a ser inserida.
	 * @param value Valor associado � chave.
	 * @return True se a chave n�o existia anteriormente; false, caso contr�rio.
	 */
	public boolean put(K key, V value) {
		if (keys.contains(key)) { // A chave foi encontrada
			// Obt�m o �ndice da chave
			int index = getKeyIndex(key);
			
			// Define o novo valor na lista de valores, no mesmo �ndice da chave encontrada
			values.set(index, value);
			
			// Retorna false porque a chave j� existia
			return false;
		
		} else { // A chave n�o foi encontrada
			// Adiciona a chave na lista de chaves
			keys.add(key);
			
			// Adiciona o valor na lista de valores
			values.add(value);
			
			// Retorna true porque a chave n�o existia anteriormente
			return true;
		}
	}
	
	/**
	 * Obt�m o valor associado a uma chave.
	 * @param key Chave para buscar.
	 * @return Valor associado � chave. Se a chave n�o for encontrada, retorna null.
	 */
	public V get(K key) {
		int index = getKeyIndex(key);
		
		// Se index for -1, significa que a chave n�o existe
		if (index < 0) {
			return null;
		}
		
		// Retorna o valor da lista de valores da posi��o equivalente � posi��o da chave
		return values.get(index);
	}
	
	/**
	 * Retorna o �ndice da lista para uma determinada chave
	 * @param key Chave para procurar
	 * @return �ndice na lista que corresponde � chave. Se a chave n�o existir, retorna -1.
	 */
	private int getKeyIndex(K key) {
		// Itera sobre a lista de chaves para encontrar a chave pesquisada
		for (int i = 0; i < keys.size(); i++) {
			K k = keys.get(i);
			
			// Na hora em que a chave da lista for igual � chave passada como par�metro, retorna o �ndice
			if (k.equals(key)) {
				return i;
			}
		}
		
		// Se chegou aqui � porque a chave n�o foi encontrada
		return -1;
	}
	
	/**
	 * Remove os itens armazenados por esta classe.
	 */
	public void clear() {
		// Limpa a lista de chaves
		keys.clear();
		
		// Limpa a lista de valores
		values.clear();
	}
}

package co.com.michael.blacklist.services.processors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import co.com.michael.blacklist.services.exception.InternalServerException;
import co.com.michael.blacklist.services.exception.NotFoundException;
import co.com.michael.blacklist.services.model.dao.BlackList;
import co.com.michael.blacklist.services.repository.IBlackListRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlackListServices {

	private LevenshteinDistance levenshteinDistance;

	private List<BlackList> chains;
	private IBlackListRepository repository;

	public BlackListServices(IBlackListRepository repository) {
		this.repository = repository;
		this.chains = new ArrayList<>();
	}

	/**
	 * Carga las cadenas desde el repositorio al iniciar el servicio.
	 */
	public void load() {

		if (this.chains == null || this.chains.isEmpty()) {
			log.debug("Inicia la carga de las cadenas");

			StopWatch stopWatch = new StopWatch();
			stopWatch.start();

			this.chains = repository.findAll();

			stopWatch.stop();

			log.debug("La carga de las cadenas termino, se cargaron {} cadenas en: {} milisegundos", this.chains.size(),
					stopWatch.getTotalTimeMillis());
		} else {
			log.debug("No se cargo ninguna cadena ya existen {} registros en memoria", this.chains.size());
		}

	}

	/**
	 * Busca una entrada en el diccionario por cadena.
	 *
	 * @param chain cadena para buscar en el diccionario.
	 * @return Objeto BlackList asociado a la cadena proporcionada.
	 */
	public BlackList findByChain(String chain) {
		return this.chains.stream().filter(blackListSearch -> blackListSearch.getChain().equals(chain)).findFirst()
				.orElseThrow(NotFoundException::new);
	}

	/**
	 * Valida si una cadena es válida o no.
	 *
	 * @param chain Cadena a validar.
	 * @return true si la cadena es válida, false si no lo es.
	 */
	public boolean isValidChain(String chain) {
		log.debug("(isValidateChain) Inicia la validación de la cadena {}", chain);

		StopWatch watch = new StopWatch();
		watch.start();

		try {
			for (BlackList BlackList : getChains()) {
				if (!compareChains(chain, BlackList.getChain())) {
					log.error("(isValidateChain) La cadena {} es similar a: {}", chain, BlackList.getChain());
					return false;
				}
			}
		} catch (Exception e) {
			log.error("(isValidateChain) Exception: ", e.getMessage(), e);
		} finally {
			watch.stop();
			log.debug("(isValidateChain) La validación de la cadena tomó: {} milisegundos", watch.getTotalTimeMillis());
		}
		return true;
	}

	/**
	 * Valida dos cadenas comparando su similitud y longitud, aplicando el algoritmo
	 * de Levenshtein.
	 *
	 * @param chain  La primera cadena a comparar.
	 * @param chain2 La segunda cadena a comparar.
	 * @return true si las cadenas son similares, false si son diferentes o si
	 *         alguna es nula/vacía.
	 * @throws InternalServerException Si ocurre un error inesperado durante la
	 *                                 validación.
	 */
	public boolean compareChains(String chain, String chain2) {
		StopWatch watch = new StopWatch();
		watch.start();

		try {
			// Verifica si las cadenas son nulas o vacías
			if (chain == null || chain.isEmpty() || chain2 == null || chain2.isEmpty()) {
				log.debug("(validateChains) Una de las cadenas es nula o vacía");
				return false;
			}

			// Calcula la distancia permitida para la comparación de cadenas
			int allowedDistance = chain.length() / 2;
			log.debug("(validateChains) allowedDistance: {}", allowedDistance);

			// Calcula la distancia entre las cadenas usando el algoritmo de Levenshtein
			int editDistance = levenshteinDistance.apply(chain.toLowerCase(), chain2.toLowerCase());

			// Comprueba si la distancia de edición es menor que la distancia permitida
			boolean isDistanceWithinThreshold = editDistance < allowedDistance;
			log.debug(
					"(validateChains) Validado por distancia {}, la palabra tiene una distancia de {}, distancia permitida {}",
					isDistanceWithinThreshold, allowedDistance, editDistance);

			return !isDistanceWithinThreshold;
		} catch (Exception e) {
			throw new InternalServerException("Error durante la validación: " + e.getMessage());
		} finally {
			watch.stop();
			log.debug("(validateChains) La validación de cadenas tardó: {} nanosegundos", watch.getTotalTimeNanos());
		}
	}

	/**
	 * Devuelve todos los registros de la tabla Blacklist.
	 *
	 * @return Lista de cadenas de la lista negra.
	 */
	public List<BlackList> findAll() {
		return getChains();
	}

	/**
	 * Devuelve todos los registros de la tabla Blacklist. Carga los registros en
	 * memoria de ser necesario
	 *
	 * @return Lista de cadenas de la lista negra.
	 */
	public List<BlackList> getChains() {
		load();
		return repository.findAll();
	}

}

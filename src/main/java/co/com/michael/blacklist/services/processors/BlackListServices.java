package co.com.michael.blacklist.services.processors;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import co.com.michael.blacklist.services.exception.InternalServerException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BlackListServices {

	private LevenshteinDistance levenshteinDistance;

	/**
	 * Valida dos cadenas comparando su similitud y longitud, aplicando el algoritmo
	 * de Levenshtein.
	 *
	 * @param chain  La primera cadena a comparar.
	 * @param chain2 La segunda cadena a comparar.
	 * @return true si las cadenas son similares, false si son diferentes o si
	 *         alguna es nula/vacía o de diferentes tamano.
	 * @throws InternalServerException Si ocurre un error inesperado durante la
	 *                                 validación.
	 */
	public boolean validateChains(String chain, String chain2) {
		StopWatch watch = new StopWatch();
		watch.start();

		try {
			// Verifica si las cadenas son nulas o vacías
			if (chain == null || chain.isEmpty() || chain2 == null || chain2.isEmpty()) {
				log.trace("(isValidateChain) Una de las cadenas es nula o vacía");
				return false;
			}

			// Verifica si las cadenas tienen diferente longitud
			if (chain.length() != chain2.length()) {
				log.trace("Las cadenas no tienen la misma longitud");
				return false;
			}

			// Calcula la distancia permitida para la comparación de cadenas
			int distanciaPermitida = chain.length() / 2;
			log.trace("(isValidateChain) distanciaPermitida: {}", distanciaPermitida);

			// Calcula la distancia entre las cadenas usando el algoritmo de Levenshtein
			int editDistance = levenshteinDistance.apply(chain.toLowerCase(), chain2.toLowerCase());

			// Comprueba si la distancia de edición es menor que la distancia permitida
			boolean distancia = editDistance < distanciaPermitida;
			log.trace(
					"(isValidateChain) Validado por distancia {}, la palabra tiene una distancia de {}, distancia permitida {}",
					distancia, distanciaPermitida, editDistance);

			return !distancia;
		} catch (Exception e) {
			throw new InternalServerException("Error durante la validación: " + e.getMessage());
		} finally {
			watch.stop();
			log.trace("(isValidateChain) La validación de cadenas tardó: {} nanosegundos", watch.getTotalTimeNanos());
		}
	}

}
